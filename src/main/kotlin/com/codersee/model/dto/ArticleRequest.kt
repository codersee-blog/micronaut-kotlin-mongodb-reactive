package com.codersee.model.dto

import com.codersee.model.ArticleCategory
import com.codersee.model.Author
import io.micronaut.core.annotation.Introspected
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@Introspected
data class ArticleRequest(
    @field:NotBlank val title: String,
    val category: ArticleCategory,
    @field:Valid val author: Author
)