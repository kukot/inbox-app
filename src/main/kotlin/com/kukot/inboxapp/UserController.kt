package com.kukot.inboxapp

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    @GetMapping("")
    fun getAuthenticatedUser(@AuthenticationPrincipal principal: OAuth2User): String? {
        println(principal)
        return principal.getAttribute<String>("name")
    }
}
