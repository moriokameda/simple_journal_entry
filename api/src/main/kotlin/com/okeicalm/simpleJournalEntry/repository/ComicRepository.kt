package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Comic
import com.okeicalm.simpleJournalEntry.entity.Journal
import com.okeicalm.simpleJournalEntry.infra.db.tables.Comics
import com.okeicalm.simpleJournalEntry.infra.db.tables.records.ComicsRecord
import com.okeicalm.simpleJournalEntry.infra.db.tables.references.COMICS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

interface ComicRepository {
    fun findAll(): List<Comic>
    fun findById(id: Long): Comic?
    fun findByIds(ids: Set<Long>): List<Comic>
    fun create(comic: Comic): Comic
    fun update(comic: Comic): Comic
    fun delete(id: Long): Long
}

@Repository
class ComicRepositoryImpl(private val context: DSLContext) : ComicRepository {
    override fun findAll(): List<Comic> {
        return context.select(
            COMICS.ID,
            COMICS.NAME,
            COMICS.IS_PUBLISHED,
            COMICS.CREATED_AT,
            COMICS.UPDATED_AT
        ).from(COMICS)
            .fetch()
            .into(Comic::class.java)
    }

    override fun findById(id: Long): Comic? {
        return context.fetchOne(COMICS, COMICS.ID.eq(id))?.into(Comic::class.java)
    }

    override fun findByIds(ids: Set<Long>): List<Comic> {
        return context.select().from(COMICS).where(COMICS.ID.`in`(ids)).fetchInto(Comic::class.java)
    }

    override fun create(comic: Comic): Comic {
        val record = context.newRecord(COMICS).apply {
            this.id = comic.id
            this.name = comic.name
            this.isPublished = comic.isPublished
            this.createdAt = comic.createdAt
            this.updatedAt = comic.updatedAt
        }
        record.store()
        return comic.copy(id = record.id!!)
    }

    override fun update(comic: Comic): Comic {
        context.update(COMICS)
            .set(COMICS.NAME, comic.name)
            .set(COMICS.IS_PUBLISHED, comic.isPublished)
            .set(COMICS.UPDATED_AT, comic.updatedAt)
            .where(COMICS.ID.eq(comic.id))
            .execute()
        return comic
    }

    override fun delete(id: Long): Long {
        context.delete(COMICS).where(COMICS.ID.eq(id)).execute()
        return id
    }
}