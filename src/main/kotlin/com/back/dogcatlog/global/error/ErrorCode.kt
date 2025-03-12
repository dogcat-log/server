package com.back.dogcatlog.global.error

enum class ErrorCode(
    val code: String,
    val message: String
) {
    // Common
    INVALID_INPUT_VALUE("C001", "잘못된 입력값입니다"),
    INTERNAL_SERVER_ERROR("C002", "서버 오류가 발생했습니다"),

    // Authentication
    UNAUTHORIZED("A001", "인증되지 않은 사용자입니다"),
    INVALID_TOKEN("A002", "유효하지 않은 토큰입니다"),
    EXPIRED_TOKEN("A003", "만료된 토큰입니다"),
    INVALID_PROVIDER("A004", "지원하지 않는 로그인 제공자입니다"),
    OAUTH2_PROCESSING_ERROR("O001", "소셜 로그인 처리 중 오류가 발생했습니다"),
    // User
    USER_NOT_FOUND("U001", "사용자를 찾을 수 없습니다"),
    DUPLICATE_EMAIL("U002", "이미 존재하는 이메일입니다"),

    // Resource
    RESOURCE_NOT_FOUND("R001", "리소스를 찾을 수 없습니다"),
    ACCESS_DENIED("R002", "접근이 거부되었습니다"),

    // Terms
    TERMS_NOT_FOUND("T001", "찾을 수 없는 약관입니다."),
    TERMS_ALREADY_EXISTS("T002", "이미 존재하는 약관타입입니다."),
    TERMS_VERSION_ALREADY_EXISTS("T003", "중복된 버전입니다.")

}
