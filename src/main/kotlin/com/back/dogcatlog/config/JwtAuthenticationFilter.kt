package com.back.dogcatlog.config

import com.back.dogcatlog.global.error.CustomException
import com.back.dogcatlog.global.error.ErrorCode
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.filter.OncePerRequestFilter


class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider, private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token = extractToken(request)
            if (token != null && jwtTokenProvider.validateToken(token)) {
                val userId = jwtTokenProvider.getUserId(token)
                val userDetails = userDetailsService.loadUserByUsername(userId.toString()) as CustomUserDetails

                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )

                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            throw CustomException(ErrorCode.INVALID_TOKEN)
        }
        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }
}
