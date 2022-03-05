package com.kukot.inboxapp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("compose")
class ComposeController {

    @GetMapping
    fun composeEmail(): String {
        return "compose_page"
    }
}
