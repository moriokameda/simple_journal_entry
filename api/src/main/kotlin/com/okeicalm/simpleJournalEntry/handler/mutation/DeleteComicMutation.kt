package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.ComicType
import com.okeicalm.simpleJournalEntry.usecase.comic.ComicDeleteUseCase
import com.okeicalm.simpleJournalEntry.usecase.comic.ComicDeleteUseCaseInput
import org.springframework.stereotype.Component

data class DeleteComicInput(val id: ID)
data class DeleteComicPayload(val deletedComic: ComicType?)

@Component
class DeleteComicMutation(private val useCase: ComicDeleteUseCase) : Mutation {
    fun deleteComic(input: DeleteComicInput): DeleteComicPayload {
        val deletedComic = useCase.call(ComicDeleteUseCaseInput(input.id.toString().toLong()))
        return deletedComic.comic?.let {
            DeleteComicPayload(ComicType(it))
        } ?: DeleteComicPayload(null)
    }
}
