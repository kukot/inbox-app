package com.kukot.inboxapp.email

import org.springframework.data.cassandra.repository.CassandraRepository
import java.util.UUID

interface EmailRepository : CassandraRepository<Email, UUID>
