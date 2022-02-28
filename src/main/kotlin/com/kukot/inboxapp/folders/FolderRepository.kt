package com.kukot.inboxapp.folders

import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface FolderRepository : CassandraRepository<Folder, String> {
    fun findAllById(id: String): List<Folder>
}
