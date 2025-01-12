package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.ComicType
import com.okeicalm.simpleJournalEntry.usecase.comic.ComicUpdateUseCase
import com.okeicalm.simpleJournalEntry.usecase.comic.ComicUpdateUseCaseInput
import org.springframework.stereotype.Component

data class UpdateComicInput(val id: ID, val name: String, val isPublished: Boolean)

@Component
class UpdateComicMutation(private val useCase: ComicUpdateUseCase) : Mutation {
    fun updateComic(input: UpdateComicInput): ComicType {
        val output = useCase.call(ComicUpdateUseCaseInput(input.id.toString().toLong(), input.name, input.isPublished))
        return ComicType(output.comic)
    }
}
