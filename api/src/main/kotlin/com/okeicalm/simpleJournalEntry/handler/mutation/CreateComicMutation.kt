package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.ComicType
import com.okeicalm.simpleJournalEntry.usecase.comic.ComicCreateUseCase
import com.okeicalm.simpleJournalEntry.usecase.comic.ComicCreateUseCaseInput
import org.springframework.stereotype.Component

data class CreateComicInput(val name: String, val isPublished: Boolean)

@Component
class CreateComicMutation(private val useCase: ComicCreateUseCase): Mutation {

    fun createComic(input: CreateComicInput): ComicType {
        val output = useCase.call(ComicCreateUseCaseInput(
            name = input.name,
            isPublished = input.isPublished
        ))
        return ComicType(output.comic)
    }
}