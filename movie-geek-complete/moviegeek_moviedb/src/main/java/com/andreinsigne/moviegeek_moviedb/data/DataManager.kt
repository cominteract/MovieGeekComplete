package com.andreinsigne.moviegeek_moviedb.data


import com.andreinsigne.moviegeek_moviedb.Printer
import com.andreinsigne.moviegeek_moviedb.interfaces.DataInterface
import com.andreinsigne.moviegeek_moviedb.model.MovieDB
import com.andreinsigne.moviegeek_moviedb.model.MovieDetailsDB
import com.andreinsigne.moviegeek_moviedb.model.ReviewResult
import java.util.*

class DataManager() : DataInterface
{


    var movieDBData = ArrayList<MovieDB>()
    var movieDetailsDBData = ArrayList<MovieDetailsDB>()
    var selectedMovieDetailsDB : MovieDetailsDB? = null
    var selectedMovieDB : MovieDB? = null
    var selectedReviewResult : ReviewResult? = null

    override fun selectedReviewResult(): ReviewResult {
        return selectedReviewResult!!
    }

    override fun onSelectedReviewResultDBRetrieved(review: ReviewResult) {
        selectedReviewResult = review
    }



    override fun onSelectedMovieDetailsDBRetrieved(movie: MovieDetailsDB) {
        selectedMovieDetailsDB = movie
    }

    override fun onMoviesDetailsDBRetrieved(movies: ArrayList<MovieDetailsDB>) {
        movieDetailsDBData = movies
    }




    override fun allMoviesDetailsDB(): ArrayList<MovieDetailsDB> {
        return movieDetailsDBData
    }

    override fun onSelectedMovieDBRetrieved(movie: MovieDB) {
        selectedMovieDB = movie
    }

    override fun onMoviesDBRetrieved(movies: ArrayList<MovieDB>) {
        movieDBData = movies
    }

    override fun selectedMovieDB(): MovieDB? {
        return selectedMovieDB
    }

    override fun allMoviesDB(): ArrayList<MovieDB> {
        return movieDBData
    }





}