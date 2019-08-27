package com.auth0.MovieReviewBoard.resolver

import com.auth0.MovieReviewBoard.director.Director
import com.auth0.MovieReviewBoard.director.DirectorRepository
import com.auth0.MovieReviewBoard.movie.Movie
import com.auth0.MovieReviewBoard.movie.MovieRepository
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.stereotype.Component
import java.util.*

@Component
@GraphQLApi
class Query(val movieRepository: MovieRepository, val directorRepository: DirectorRepository) : GraphQLQueryResolver {

    fun findAllMovies(): Iterable<Movie> {
        return movieRepository.findAll()
    }

    fun findAllDirectors(): Iterable<Director> {
        return directorRepository.findAll()
    }

    fun countMovies(): Long {
        return movieRepository.count()
    }

    fun countDirectors(): Long {
        return directorRepository.count()
    }

    fun findMoviesByDirector(directorId: Long): Iterable<Movie> {
        val director = directorRepository.findById(directorId)
        return movieRepository.findByDirector(director.get())
    }

    fun countMoviesByDirector(directorId: Long): Long {
        val director = directorRepository.findById(directorId)
        return movieRepository.countByDirector(director.get())
    }

    @GraphQLQuery(name = "directors")
    fun getDirectors(): MutableIterable<Director> {
        return directorRepository.findAll();
    }

    @GraphQLQuery(name = "director")
    fun getDirector(@GraphQLArgument(name = "id") id: Long): Optional<Director> {
        return directorRepository.findById(id);
    }

    @GraphQLQuery(name = "movies")
    fun getMovies(): MutableIterable<Movie> {
        return movieRepository.findAll();
    }

    @GraphQLQuery(name = "movie")
    fun getMovie(@GraphQLArgument(name = "id") id: Long): Optional<Movie> {
        return movieRepository.findById(id);
    }
}
