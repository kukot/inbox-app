package com.kukot.inboxapp.services

import com.kukot.inboxapp.folders.Folder
import org.springframework.stereotype.Service

@Service
class FolderService {

    fun getDefaultFolders(userId: String): List<Folder> {
        return listOf(
            Folder(userId, "Inbox", "white"),
            Folder(userId, "Sent items", "blue"),
            Folder(userId, "Important", "red")
        )
    }
}
