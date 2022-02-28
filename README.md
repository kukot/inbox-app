### Schema design
1. Data needed for each folder
a. folder_for_user
{
    user_id (K),
    name/label (C),
    color,
    unread_count
}

In Cassandra, you think about each table like a perfect/ideal data model needed for an UI need, you don't wanna mess up with JOIN like what we usually do in relational database.
* Primary key, partition key - which node will the data will be saved
We don't want to end up  having folders of the same user being saved in different nodes, but the same node. That's why we want to have user_id as partition key
* Clustering key
 folder name is used as clustering key, we don't use it as partition key because we still want all the folder of the same user to be on the same partition (node), but still, having some kind of clustering based on this column

 2. messages_by_user_and_folder - this is to load the list of message ONLY, not the message content it self
 {
     user_id (K),
     label (K),
     timestamp (C) asc,
     message_id,
     to,
     subject,
     is_read,
 }
 Cassandara doesn't do sorting when fetching, what you should do is to define how you want to save the data, which order you want Cassandra to save the data, that's why we have timestamp asc as a clustering column
 3. message_by_id - the actual message
 {
     message_id,
     from,
     to,
     subject,
     body,
 }

 4. Actual physical data modeling
 To tackle the unread count, Cassandra kind of has the concept of counter, the only restriction for such a table is, all column other than the counter columns must be Primary key. To overcome this, but still get rid of the the ACID, we introduce another table just for the counter

 folder_by_user
 {
     user_id: text (K),
     label/folder: text (C),
     color: text
 }

 unread_count_by_folder_and_user
 {
     user_id: text (K),
     label/folder: text (C),
     num_unread: counter
 }

 emails_by_user_folder
 {
     user_id: text (K),
     label: text(K),
     id: timeuuid (C) desc,
     to: text,
     subject: text,
     is_read: bool
 }

 messages
 {
     id: timeuuid (K),
     from: text,
     to: List<text>,
     subject: text,
     body: text
 }

 time uuid is UUID (guarantee the uniqueness, + sorting based on timestamp), we can think about this as a combination of a timestamp + an ID