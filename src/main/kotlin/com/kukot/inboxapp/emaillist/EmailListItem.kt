package com.kukot.inboxapp.emaillist

import org.springframework.data.annotation.Transient
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.util.UUID

@Table(value = "messages_by_user_folder")
class EmailListItem(

    @PrimaryKey
    var key: EmailListItemKey,

    @CassandraType(type = CassandraType.Name.LIST, typeArguments = [CassandraType.Name.TEXT])
    var to: List<String>,

    @CassandraType(type = CassandraType.Name.TEXT)
    var subject: String,

    @CassandraType(type = CassandraType.Name.BOOLEAN)
    var isUnRead: Boolean
) {
    @Transient
    var prettyTimeString: String? = null
}

@PrimaryKeyClass
class EmailListItemKey(
    @PrimaryKeyColumn(value = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    var id: String,

    @PrimaryKeyColumn(value = "label", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    var label: String,

    @PrimaryKeyColumn(value = "created_time_uuid", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    var timeUUID: UUID,
)
