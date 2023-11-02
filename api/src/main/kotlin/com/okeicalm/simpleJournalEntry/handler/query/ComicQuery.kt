package com.okeicalm.simpleJournalEntry.handler.query

import com.expediagroup.graphql.server.operations.Query
import com.okeicalm.simpleJournalEntry.handler.type.ComicType
import com.okeicalm.simpleJournalEntry.repository.ComicRepository
import org.springframework.stereotype.Component

@Component
class ComicQuery(private val repository: ComicRepository): Query {

    fun allComics(): List<ComicType> {
        val entities = repository.findAll()
        return entities.map { ComicType(it) }
    }
}