package com.okeicalm.simpleJournalEntry.usecase.comic

import com.okeicalm.simpleJournalEntry.entity.Comic
import com.okeicalm.simpleJournalEntry.repository.ComicRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

data class ComicCreateUseCaseInput(val name: String, val isPublished: Boolean)
data class ComicCreateUseCaseOutPut(val comic: Comic)
interface ComicCreateUseCase {
    fun call(input: ComicCreateUseCaseInput): ComicCreateUseCaseOutPut
}

@Service
class ComicCreateUseCaseImpl(private val repository: ComicRepository) : ComicCreateUseCase {
    @Transactional(readOnly = false)
    override fun call(input: ComicCreateUseCaseInput): ComicCreateUseCaseOutPut {
        val comic = repository.create(
            Comic(
                name = input.name,
                isPublished = input.isPublished,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )
        return ComicCreateUseCaseOutPut(comic)
    }
}