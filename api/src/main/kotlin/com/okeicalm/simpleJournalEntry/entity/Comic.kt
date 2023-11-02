package com.okeicalm.simpleJournalEntry.entity

import java.time.LocalDate
import java.time.LocalDateTime

data class Comic(
    val id: Long = 0,
    val name: String,
    val isPublished: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)