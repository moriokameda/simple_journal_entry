package com.okeicalm.simpleJournalEntry.handler.type

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.entity.Comic
import java.time.LocalDateTime

const val comicTypeGraphQLName = "Comic"
@GraphQLName(comicTypeGraphQLName)
data class ComicType(
    val id: ID,
    val name: String,
    val isPublished: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    constructor(comic: Comic) : this(
        ID(comic.id.toString()),
        comic.name,
        comic.isPublished,
        comic.createdAt,
        comic.updatedAt
    )
}