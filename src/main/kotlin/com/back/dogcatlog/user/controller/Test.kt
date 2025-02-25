package com.back.dogcatlog.user.controller


import com.back.dogcatlog.user.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/test")
class Test(
    private val authService: AuthService
) {
    @GetMapping("/hello")
    fun sayHello(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello, welcome to PawAlert!")
    }


}
