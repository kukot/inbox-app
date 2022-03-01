package com.kukot.inboxapp.emaillist

import org.springframework.data.cassandra.repository.CassandraRepository

interface EmailListItemRepository : CassandraRepository<EmailListItem, EmailListItemKey> {
    fun findAllByKey_IdAndKey_Label(id: String, label: String): List<EmailListItem>
}
