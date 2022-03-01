package com.kukot.inboxapp.bootstrap

import com.datastax.oss.driver.api.core.uuid.Uuids
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
    val emailListItemRepository: EmailListItemRepository
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

        // create some email list item
        for (i in 1..10) {
            val inboxKey = EmailListItemKey("kukot", "Inbox", Uuids.timeBased())
            val welcomeInbox = EmailListItem(
                key = inboxKey,
                to = listOf("404errors", "koushik"),
                subject = "Inbox item $i",
                isUnRead = true
            )
            emailListItemRepository.save(welcomeInbox)
        }

        for (i in 1..10) {
            val inboxKey = EmailListItemKey("kukot", "Important", Uuids.timeBased())
            val welcomeInbox = EmailListItem(
                key = inboxKey,
                to = listOf("404errors", "koushik"),
                subject = "Important $i",
                isUnRead = true
            )
            emailListItemRepository.save(welcomeInbox)
        }
        for (i in 1..10) {
            val inboxKey = EmailListItemKey("kukot", "Sent", Uuids.timeBased())
            val welcomeInbox = EmailListItem(
                key = inboxKey,
                to = listOf("404errors", "koushik"),
                subject = "Sent item $i",
                isUnRead = true
            )
            emailListItemRepository.save(welcomeInbox)
        }
        for (i in 1..10) {
            val inboxKey = EmailListItemKey("undefined_user", "Inbox", Uuids.timeBased())
            val welcomeInbox = EmailListItem(
                key = inboxKey,
                to = listOf("404errors", "koushik"),
                subject = "Sent item $i",
                isUnRead = true
            )
            emailListItemRepository.save(welcomeInbox)
        }
    }
}
