package com.codersee.repository

import com.codersee.model.Article
import io.micronaut.data.mongodb.annotation.MongoFindQuery
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.reactive.ReactorCrudRepository
import reactor.core.publisher.Flux

@MongoRepository
interface ArticleRepository : ReactorCrudRepository<Article, String> {

    @MongoFindQuery("{ title: { \$regex: :title, '\$options' : 'i'}}")
    fun findByTitleLikeCaseInsensitive(title: String): Flux<Article>

}