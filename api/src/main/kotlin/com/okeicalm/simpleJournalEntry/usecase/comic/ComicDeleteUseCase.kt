package com.okeicalm.simpleJournalEntry.usecase.comic

import com.okeicalm.simpleJournalEntry.entity.Comic
import com.okeicalm.simpleJournalEntry.repository.ComicRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class ComicDeleteUseCaseInput(val id: Long)
data class ComicDeleteUseCaseOutput(val comic: Comic?)
interface ComicDeleteUseCase {
    fun call(input: ComicDeleteUseCaseInput): ComicDeleteUseCaseOutput
}

@Service
class ComicDeleteUseCaseImpl(private val comicRepository: ComicRepository): ComicDeleteUseCase {
    @Transactional(readOnly = false)
    override fun call(input: ComicDeleteUseCaseInput): ComicDeleteUseCaseOutput {
        val comic = comicRepository.findById(input.id) ?: return ComicDeleteUseCaseOutput(null)
        comicRepository.delete(input.id)
        return ComicDeleteUseCaseOutput(comic)
    }
}