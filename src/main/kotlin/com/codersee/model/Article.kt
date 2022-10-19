package com.codersee.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity
data class Article(
    @field:Id
    @field:GeneratedValue
    val id: String? = null,
    val title: String,
    val category: ArticleCategory,
    val author: Author
)
