package com.okeicalm.simpleJournalEntry.handler.scalar

import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.language.StringValue
import graphql.scalars.ExtendedScalars
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import graphql.schema.GraphQLScalarType
import graphql.schema.GraphQLType
import kotlin.reflect.KClass
import kotlin.reflect.KType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CustomSchemaGeneratorHooks : SchemaGeneratorHooks {

    private fun localDatetimeScalar(): GraphQLScalarType = GraphQLScalarType.newScalar()
        .name("LocalDatetime")
        .description("LocalDatetime scalar")
        .coercing(object : Coercing<LocalDateTime, String> {
            override fun parseValue(input: Any): LocalDateTime = runCatching {
                LocalDateTime.parse(input.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            }.getOrElse { throw CoercingParseValueException("Expected valid LocalDateTime but was $input") }

            override fun parseLiteral(input: Any): LocalDateTime {
                val stringValue = (input as? StringValue)?.value
                    ?: throw CoercingParseLiteralException("Expected AST type 'StringValue' but was ${input.javaClass.simpleName}")
                return parseValue(stringValue)
            }

            override fun serialize(dataFetcherResult: Any): String = runCatching {
                (dataFetcherResult as? LocalDateTime)?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    ?: throw CoercingSerializeException("Expected valid LocalDateTime but was $dataFetcherResult")
            }.getOrElse { throw CoercingSerializeException("Expected valid LocalDateTime but was $dataFetcherResult") }
        })
        .build()

    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier as? KClass<*>) {
        ZonedDateTime::class -> ExtendedScalars.DateTime
        LocalDate::class -> ExtendedScalars.Date
        LocalDateTime::class -> localDatetimeScalar()
        else -> null
    }
}
