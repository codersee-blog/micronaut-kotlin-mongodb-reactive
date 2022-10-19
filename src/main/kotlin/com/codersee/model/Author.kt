package com.codersee.model

import io.micronaut.serde.annotation.Serdeable.Deserializable
import io.micronaut.serde.annotation.Serdeable.Serializable
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Serializable
@Deserializable
data class Author(
    @field:NotBlank
    @field:Size(max = 50)
    val firstName: String,

    @field:NotBlank
    @field:Size(max = 50)
    val lastName: String,

    @field:Size(max = 50)
    @field:Email
    val email: String
)
