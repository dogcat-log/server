package com.back.dogcatlog.global.error

class CustomException(
    val errorCode: ErrorCode,
    override val message: String = errorCode.message
) : RuntimeException(message)
