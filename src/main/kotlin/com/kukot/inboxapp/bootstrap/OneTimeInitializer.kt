package com.kukot.inboxapp.bootstrap

import com.kukot.inboxapp.folders.Folder
import com.kukot.inboxapp.folders.FolderRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class OneTimeInitializer(
    val folderRepository: FolderRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val countFolder = folderRepository.count()
        if (countFolder == 0L) {
            initDefaultData()
        }
    }

    private fun initDefaultData() {
        val kevinInboxBlue = Folder("kukot", "Inbox", "blue")
        val kevinInboxRed = Folder("kukot", "Important", "red")
        val kevinSentGreen = Folder("kukot", "Sent", "green")
        folderRepository.saveAll(mutableListOf(kevinInboxBlue, kevinInboxRed, kevinSentGreen))
    }
}
