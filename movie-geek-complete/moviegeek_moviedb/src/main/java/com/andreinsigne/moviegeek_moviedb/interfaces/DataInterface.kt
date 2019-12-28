package com.andreinsigne.moviegeek_moviedb.interfaces


import com.andreinsigne.moviegeek_moviedb.model.MovieDB
import com.andreinsigne.moviegeek_moviedb.model.MovieDetailsDB
import com.andreinsigne.moviegeek_moviedb.model.ReviewResult
import java.io.Serializable
import java.util.*

interface DataInterface : Serializable {

    fun onSelectedMovieDBRetrieved(movie: MovieDB)
    fun onMoviesDBRetrieved(movies : ArrayList<MovieDB>)
    fun selectedMovieDB() : MovieDB?
    fun allMoviesDB() : ArrayList<MovieDB>
    fun onSelectedMovieDetailsDBRetrieved(movie: MovieDetailsDB)
    fun onSelectedReviewResultDBRetrieved(review: ReviewResult)
    fun selectedReviewResult() : ReviewResult
    fun onMoviesDetailsDBRetrieved(movies : ArrayList<MovieDetailsDB>)
    fun allMoviesDetailsDB() : ArrayList<MovieDetailsDB>

}