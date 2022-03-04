package com.kukot.inboxapp.controllers

import com.kukot.inboxapp.email.EmailRepository
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@Controller
@RequestMapping("emails")
class EmailController(
    val emailRepository: EmailRepository
) {
    @GetMapping("{id}")
    fun viewEmailById(
        @PathVariable("id") emailId: String,
        model: Model,
        @AuthenticationPrincipal principal: OAuth2User
    ): String {
        val email = emailRepository.findById(UUID.fromString(emailId))
            .orElseThrow { RuntimeException("Unable to find message with id $emailId") }
        model.addAttribute("email", email)
        return "view_email_by_id"
    }
}
