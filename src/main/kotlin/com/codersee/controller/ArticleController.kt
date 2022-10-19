package com.codersee.controller

import com.codersee.model.Article
import com.codersee.model.dto.ArticleRequest
import com.codersee.model.dto.SearchRequest
import com.codersee.service.ArticleService
import io.micronaut.http.HttpStatus.CREATED
import io.micronaut.http.HttpStatus.NO_CONTENT
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@Controller("/articles")
@ExecuteOn(TaskExecutors.IO)
open class ArticleController(
    private val articleService: ArticleService
) {

    @Get
    fun findAll(): Flux<Article> =
        articleService.findAll()

    @Get("/{id}")
    fun findById(@PathVariable id: String): Mono<Article> =
        articleService.findById(id)

    @Post
    @Status(CREATED)
    open fun create(@Valid @Body request: ArticleRequest): Mono<Article> =
        articleService.create(
            article = request.toArticle()
        )

    @Post("/search")
    open fun search(@Valid @Body searchRequest: SearchRequest): Flux<Article> =
        articleService.findByTitleLike(
            name = searchRequest.title
        )

    @Put("/{id}")
    open fun updateById(
        @PathVariable id: String,
        @Valid @Body request: ArticleRequest
    ): Mono<Article> =
        articleService.update(id, request.toArticle())

    @Delete("/{id}")
    @Status(NO_CONTENT)
    fun deleteById(@PathVariable id: String) =
        articleService.deleteById(id)


    private fun ArticleRequest.toArticle(): Article =
        Article(
            id = null,
            title = this.title,
            category = this.category,
            author = this.author
        )
}