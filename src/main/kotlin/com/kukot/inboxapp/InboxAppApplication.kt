package com.kukot.inboxapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InboxAppApplication

fun main(args: Array<String>) {
    runApplication<InboxAppApplication>(*args)
}
