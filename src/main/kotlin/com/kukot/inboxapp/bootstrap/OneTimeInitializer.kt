package com.kukot.inboxapp.bootstrap

import com.datastax.oss.driver.api.core.uuid.Uuids
import com.kukot.inboxapp.constants.AppConstants
import com.kukot.inboxapp.email.Email
import com.kukot.inboxapp.email.EmailRepository
import com.kukot.inboxapp.emaillist.EmailListItem
import com.kukot.inboxapp.emaillist.EmailListItemKey
import com.kukot.inboxapp.emaillist.EmailListItemRepository
import com.kukot.inboxapp.folders.Folder
import com.kukot.inboxapp.folders.FolderRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class OneTimeInitializer(
    val folderRepository: FolderRepository,
    val emailListItemRepository: EmailListItemRepository,
    val emailRepository: EmailRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val countFolder = folderRepository.count()
        if (countFolder == 0L) {
            initDefaultData()
        }
    }

    companion object {
        const val MAIN_USER = "kukot"
        const val USER1 = "koushik"
        const val USER2 = "chetan"
    }

    private fun initDefaultData() {
        val kevinInboxBlue = Folder(MAIN_USER, AppConstants.INBOX_FOLDER_LABEL, "blue")
        val kevinSentGreen = Folder(MAIN_USER, AppConstants.SENT_ITEMS_FOLDER_LABEL, "green")
        val kevinInboxRed = Folder(MAIN_USER, AppConstants.IMPORTANT_FOLDER_LABEL, "red")
        folderRepository.saveAll(mutableListOf(kevinInboxBlue, kevinSentGreen, kevinInboxRed))

        // create some email list item

        createTenEmailListItem(MAIN_USER, AppConstants.INBOX_FOLDER_LABEL)
        createTenEmailListItem(MAIN_USER, AppConstants.SENT_ITEMS_FOLDER_LABEL)
        createTenEmailListItem(MAIN_USER, AppConstants.IMPORTANT_FOLDER_LABEL)
        createTenEmailListItem(MAIN_USER, AppConstants.SENT_FOLDER_LABEL)
        createTenEmailListItem("undefined_user", AppConstants.INBOX_FOLDER_LABEL)
    }

    private fun createTenEmailListItem(user: String, label: String) {
        for (i in 1..30 step 3) {
            val inboxKey = EmailListItemKey(user, label, Uuids.timeBased())
            val welcomeInbox = EmailListItem(
                key = inboxKey, to = listOf(USER2, USER1), subject = "$label $i", isUnRead = true
            )
            emailListItemRepository.save(welcomeInbox)
            val email = Email(
                inboxKey.timeUUID,
                MAIN_USER,
                listOf(USER1, USER2),
                "$i March daily meeting notification",
                "We are thrilled to invite you to this meeting"
            )
            emailRepository.save(email)
        }
    }
}
