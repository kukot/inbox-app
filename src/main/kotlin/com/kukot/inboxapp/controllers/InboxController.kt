package com.kukot.inboxapp.controllers

import com.datastax.oss.driver.api.core.uuid.Uuids
import com.kukot.inboxapp.emaillist.EmailListItem
import com.kukot.inboxapp.emaillist.EmailListItemRepository
import org.ocpsoft.prettytime.PrettyTime
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.util.Date

@Controller
class InboxController(
    val emailListItemRepository: EmailListItemRepository
) {

    @GetMapping
    fun viewHomePage(model: Model, @AuthenticationPrincipal principal: OAuth2User?): String {
        val loginId = principal?.getAttribute<String>("login")
        if (loginId != null) {
            val inboxEmailList: List<EmailListItem> =
                emailListItemRepository.findAllByKey_IdAndKey_Label(loginId, "Inbox")
            val prettyTime = PrettyTime()
            inboxEmailList.forEach {
                val sentDate = Uuids.unixTimestamp(it.key.timeUUID)
                it.prettyTimeString = prettyTime.format(Date(sentDate))
            }
            model.addAttribute("inboxItems", inboxEmailList)
            return "inbox-page"
        }
        return "index"
    }
}
