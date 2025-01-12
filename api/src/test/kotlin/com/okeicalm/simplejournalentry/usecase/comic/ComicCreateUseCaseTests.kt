package com.okeicalm.simplejournalentry.usecase.comic

import com.okeicalm.simpleJournalEntry.entity.Comic
import com.okeicalm.simpleJournalEntry.repository.ComicRepository
import com.okeicalm.simpleJournalEntry.usecase.comic.ComicCreateUseCase
import com.okeicalm.simpleJournalEntry.usecase.comic.ComicCreateUseCaseInput
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ComicCreateUseCaseTests {
    @InjectMockKs
    @SpyK
    lateinit var useCase: ComicCreateUseCase

    @MockK
    lateinit var repository: ComicRepository

    @Test
    fun makeComicTest() {
        val nowDatetime = LocalDateTime.of(2023, 11, 1, 0, 0)

        every { repository.create(any()) } returns Comic(
            id = 1,
            name = "ワイルドコミック",
            isPublished = true,
            createdAt = nowDatetime,
            updatedAt = nowDatetime
        )
        mockkStatic(LocalDateTime::class)
        every { LocalDateTime.now() } returns nowDatetime

        val actual = useCase.call(ComicCreateUseCaseInput("ワイルドコミック", true))

        unmockkStatic(LocalDateTime::class)
        assertEquals(1, actual.comic.id)
        assertEquals("ワイルドコミック", actual.comic.name)
        assertEquals(true, actual.comic.isPublished)
        assertEquals(LocalDateTime.of(2023, 11, 1, 0, 0), actual.comic.createdAt)
        assertEquals(LocalDateTime.of(2023, 11, 1, 0, 0), actual.comic.updatedAt)

        verify {
            repository.create(withArg {
                assertEquals("ワイルドコミック", it.name)
            })
        }
    }
}