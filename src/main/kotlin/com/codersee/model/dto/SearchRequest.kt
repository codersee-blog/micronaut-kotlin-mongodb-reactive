package com.codersee.model.dto

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class SearchRequest(
    @field:NotBlank
    @field:Size(max = 50)
    val title: String
)