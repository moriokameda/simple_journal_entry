package com.okeicalm.simpleJournalEntry.usecase.comic

import com.okeicalm.simpleJournalEntry.entity.Comic
import com.okeicalm.simpleJournalEntry.repository.ComicRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

data class ComicUpdateUseCaseInput(val id: Long, val name: String, val isPublished: Boolean)
data class ComicUpdateUseCaseOutPut(val comic: Comic)
interface ComicUpdateUseCase {
    fun call(input: ComicUpdateUseCaseInput): ComicUpdateUseCaseOutPut
}

@Service
class ComicUpdateUseCaseImpl(private val repository: ComicRepository) : ComicUpdateUseCase {
    @Transactional(readOnly = false)
    override fun call(input: ComicUpdateUseCaseInput): ComicUpdateUseCaseOutPut {
        val comic = repository.findById(input.id) ?: throw RuntimeException("this id of comic is not found")

        return ComicUpdateUseCaseOutPut(
            repository.update(
                comic.copy(
                    name = input.name,
                    isPublished = input.isPublished,
                    updatedAt = LocalDateTime.now()
                )
            )
        )
    }
}