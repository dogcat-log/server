package com.back.dogcatlog.user.service

import com.back.dogcatlog.config.JwtTokenProvider
import com.back.dogcatlog.global.error.CustomException
import com.back.dogcatlog.global.error.ErrorCode
import com.back.dogcatlog.user.dto.AuthProvider
import com.back.dogcatlog.user.dto.TokenResponse
import com.back.dogcatlog.user.entity.User
import com.back.dogcatlog.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {
    private val restTemplate = RestTemplate()

    fun authenticateWithOAuth2(accessToken: String, provider: String): TokenResponse {
        val userInfo = when (provider.uppercase()) {
            "KAKAO" -> getKakaoUserInfo(accessToken)
            "GOOGLE" -> getGoogleUserInfo(accessToken)
            "NAVER" -> getNaverUserInfo(accessToken)
            else -> throw CustomException(ErrorCode.INVALID_PROVIDER)
        }

        val user = userRepository.findByUserEmail(userInfo.email)
            ?: saveUser(userInfo.email, AuthProvider.valueOf(provider.uppercase()))

        return TokenResponse(
            accessToken = jwtTokenProvider.createAccessToken(user.id),
            refreshToken = jwtTokenProvider.createRefreshToken(user.id)
        )
    }

    private fun getKakaoUserInfo(accessToken: String): OAuthUserInfo {
        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer $accessToken")

        val entity = HttpEntity<String>(headers)

        val response = restTemplate.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.GET,
            entity,
            Map::class.java
        )

        val body = response.body ?: throw CustomException(ErrorCode.OAUTH2_PROCESSING_ERROR)
        val kakaoAccount = body["kakao_account"] as Map<*, *>
        val email = kakaoAccount["email"] as String

        return OAuthUserInfo(email)
    }


    fun refreshToken(refreshToken: String): TokenResponse {
        val userId = jwtTokenProvider.validateRefreshToken(refreshToken)
        return TokenResponse(
            accessToken = jwtTokenProvider.createAccessToken(userId),
            refreshToken = jwtTokenProvider.createRefreshToken(userId)
        )
    }

    private fun getGoogleUserInfo(accessToken: String): OAuthUserInfo {
        val response = restTemplate.getForEntity(
            "https://www.googleapis.com/oauth2/v2/userinfo",
            Map::class.java,
            mapOf("Authorization" to "Bearer $accessToken")
        )

        val body = response.body ?: throw CustomException(ErrorCode.OAUTH2_PROCESSING_ERROR)
        val email = body["email"] as String

        return OAuthUserInfo(email)
    }

    private fun getNaverUserInfo(accessToken: String): OAuthUserInfo {
        val headers= HttpHeaders()
        headers.set("Authorization", "Bearer $accessToken")

        val entity = HttpEntity<String>(headers)

        val response = restTemplate.exchange(
            "https://openapi.naver.com/v1/nid/me",
            HttpMethod.GET,
            entity,
            Map::class.java
        )

        val body = response.body ?: throw CustomException(ErrorCode.OAUTH2_PROCESSING_ERROR)
        val responseData = body["response"] as Map<*, *>
        val email = responseData["email"] as String

        return OAuthUserInfo(email)

    }

    private fun saveUser(email: String, provider: AuthProvider): User {
        val user = User(
            userEmail = email,
            userPassword = "",
            provider = provider
        )

        return userRepository.save(user)
    }
}

data class OAuthUserInfo(
    val email: String
)
