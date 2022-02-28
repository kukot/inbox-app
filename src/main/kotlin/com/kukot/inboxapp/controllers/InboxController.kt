package com.kukot.inboxapp.controllers

import com.kukot.inboxapp.folders.Folder
import com.kukot.inboxapp.folders.FolderRepository
import com.kukot.inboxapp.services.FolderService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class InboxController(
    val folderRepository: FolderRepository,
    val folderService: FolderService
) {

    @GetMapping
    fun viewHomePage(model: Model, @AuthenticationPrincipal principal: OAuth2User?): String {
        val loginId = principal?.getAttribute<String>("login")
        if (loginId != null) {
            val folders: List<Folder> = folderRepository.findAllById(loginId)
            model.addAttribute("userFolders", folders)
            model.addAttribute("defaultFolders", folderService.getDefaultFolders(loginId))
            return "inbox-page"
        }
        return "index"
    }
}
