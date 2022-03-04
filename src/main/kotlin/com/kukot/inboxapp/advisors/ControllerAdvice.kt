package com.kukot.inboxapp.advisors

import com.kukot.inboxapp.controllers.EmailController
import com.kukot.inboxapp.folders.Folder
import com.kukot.inboxapp.folders.FolderRepository
import com.kukot.inboxapp.services.FolderService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute

@ControllerAdvice(basePackageClasses = [EmailController::class])
class ControllerAdvice(
    val folderRepository: FolderRepository,
    val folderService: FolderService
) {

    @ModelAttribute
    fun getUserFolders(model: Model, @AuthenticationPrincipal principal: OAuth2User) {
        val loginId = principal.getAttribute<String>("login") ?: return
        val folders: List<Folder> = folderRepository.findAllById(loginId)
        model.addAttribute("defaultFolders", folderService.getDefaultFolders(loginId))
        model.addAttribute("userFolders", folders)
    }
}
