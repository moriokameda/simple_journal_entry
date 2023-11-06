/*
 * This file is generated by jOOQ.
 */
package com.okeicalm.simpleJournalEntry.infra.db.tables


import com.okeicalm.simpleJournalEntry.infra.db.SimpleJournalEntryDb
import com.okeicalm.simpleJournalEntry.infra.db.keys.KEY_COMICS_PRIMARY
import com.okeicalm.simpleJournalEntry.infra.db.tables.records.ComicsRecord

import java.time.LocalDateTime

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Name
import org.jooq.Record
import org.jooq.Row5
import org.jooq.Schema
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class Comics(
    alias: Name,
    child: Table<out Record>?,
    path: ForeignKey<out Record, ComicsRecord>?,
    aliased: Table<ComicsRecord>?,
    parameters: Array<Field<*>?>?
): TableImpl<ComicsRecord>(
    alias,
    SimpleJournalEntryDb.SIMPLE_JOURNAL_ENTRY_DB,
    child,
    path,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table()
) {
    companion object {

        /**
         * The reference instance of <code>simple_journal_entry_db.comics</code>
         */
        val COMICS: Comics = Comics()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<ComicsRecord> = ComicsRecord::class.java

    /**
     * The column <code>simple_journal_entry_db.comics.id</code>.
     */
    val ID: TableField<ComicsRecord, Long?> = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "")

    /**
     * The column <code>simple_journal_entry_db.comics.name</code>. 漫画名
     */
    val NAME: TableField<ComicsRecord, String?> = createField(DSL.name("name"), SQLDataType.VARCHAR(100).nullable(false), this, "漫画名")

    /**
     * The column <code>simple_journal_entry_db.comics.is_published</code>.
     * 公開フラグ
     */
    val IS_PUBLISHED: TableField<ComicsRecord, Boolean?> = createField(DSL.name("is_published"), SQLDataType.BOOLEAN.nullable(false), this, "公開フラグ")

    /**
     * The column <code>simple_journal_entry_db.comics.created_at</code>.
     */
    val CREATED_AT: TableField<ComicsRecord, LocalDateTime?> = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "")

    /**
     * The column <code>simple_journal_entry_db.comics.updated_at</code>.
     */
    val UPDATED_AT: TableField<ComicsRecord, LocalDateTime?> = createField(DSL.name("updated_at"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "")

    private constructor(alias: Name, aliased: Table<ComicsRecord>?): this(alias, null, null, aliased, null)
    private constructor(alias: Name, aliased: Table<ComicsRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, aliased, parameters)

    /**
     * Create an aliased <code>simple_journal_entry_db.comics</code> table
     * reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>simple_journal_entry_db.comics</code> table
     * reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>simple_journal_entry_db.comics</code> table reference
     */
    constructor(): this(DSL.name("comics"), null)

    constructor(child: Table<out Record>, key: ForeignKey<out Record, ComicsRecord>): this(Internal.createPathAlias(child, key), child, key, COMICS, null)
    override fun getSchema(): Schema? = if (aliased()) null else SimpleJournalEntryDb.SIMPLE_JOURNAL_ENTRY_DB
    override fun getIdentity(): Identity<ComicsRecord, Long?> = super.getIdentity() as Identity<ComicsRecord, Long?>
    override fun getPrimaryKey(): UniqueKey<ComicsRecord> = KEY_COMICS_PRIMARY
    override fun `as`(alias: String): Comics = Comics(DSL.name(alias), this)
    override fun `as`(alias: Name): Comics = Comics(alias, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): Comics = Comics(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): Comics = Comics(name, null)

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------
    override fun fieldsRow(): Row5<Long?, String?, Boolean?, LocalDateTime?, LocalDateTime?> = super.fieldsRow() as Row5<Long?, String?, Boolean?, LocalDateTime?, LocalDateTime?>
}
