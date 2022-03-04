package com.kukot.inboxapp.email

import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.util.UUID

@Table(value = "messages_by_id")
class Email(

    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @Id
    var id: UUID,

    @CassandraType(type = CassandraType.Name.TEXT)
    var from: String,

    @CassandraType(type = CassandraType.Name.LIST, typeArguments = [CassandraType.Name.TEXT])
    var to: List<String>,

    @CassandraType(type = CassandraType.Name.TEXT)
    var subject: String,

    @CassandraType(type = CassandraType.Name.TEXT)
    var body: String
)
