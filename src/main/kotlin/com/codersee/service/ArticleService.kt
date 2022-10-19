package com.codersee.service

import com.codersee.model.Article
import com.codersee.repository.ArticleRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class ArticleService(
    private val articleRepository: ArticleRepository
) {

    fun create(article: Article): Mono<Article> =
        articleRepository.save(article)

    fun findAll(): Flux<Article> =
        articleRepository.findAll()

    fun findById(id: String): Mono<Article> =
        articleRepository.findById(id)
            .switchIfEmpty(
                Mono.error(
                    HttpStatusException(HttpStatus.NOT_FOUND, "Article with id: $id does not exists.")
                )
            )

    fun update(id: String, updated: Article): Mono<Article> =
        findById(id)
            .map { foundArticle -> updated.copy(id = foundArticle.id) }
            .flatMap(articleRepository::update)

    fun deleteById(id: String): Mono<Void> =
        findById(id)
            .flatMap(articleRepository::delete)
            .flatMap { deletedCount ->
                if (deletedCount > 0L)
                    Mono.empty()
                else
                    Mono.error(
                        HttpStatusException(HttpStatus.NOT_FOUND, "Article with id: $id was not deleted.")
                    )
            }

    fun findByTitleLike(name: String): Flux<Article> =
        articleRepository.findByTitleLikeCaseInsensitive(name)
}