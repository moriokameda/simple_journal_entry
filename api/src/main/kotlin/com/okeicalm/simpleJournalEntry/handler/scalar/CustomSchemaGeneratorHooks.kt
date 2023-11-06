package com.okeicalm.simpleJournalEntry.handler.scalar

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.scalars.ExtendedScalars
import graphql.schema.Coercing
import graphql.schema.GraphQLScalarType
import graphql.schema.GraphQLType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.KClass
import kotlin.reflect.KType

class CustomSchemaGeneratorHooks : SchemaGeneratorHooks {

    private fun localDatetimeScalar(): GraphQLScalarType = GraphQLScalarType.newScalar()
        .name("LocalDatetime")
        .description("LocalDatetime scalar")
        .coercing(object : Coercing<LocalDateTime, String> {
            override fun serialize(dataFetcherResult: Any): String {
                return dataFetcherResult.toString()
            }

            override fun parseValue(input: Any): LocalDateTime {
                return LocalDateTime.parse(input.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            }

            override fun parseLiteral(input: Any): LocalDateTime {
                return LocalDateTime.parse(input.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            }
        })
        .build()

    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier as? KClass<*>) {
        ZonedDateTime::class -> ExtendedScalars.DateTime
        LocalDate::class -> ExtendedScalars.Date
        else -> null
    }
}
