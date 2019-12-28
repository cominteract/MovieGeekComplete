package com.andreinsigne.moviegeek_moviedb.model

import android.arch.lifecycle.ViewModel


data class MovieDBResult(val page: Int,val total_pages : Int, val total_results : Int, val results : ArrayList<MovieDB>)
data class MovieDB(val release_date : String, val vote_count: Int, val id : Int, val video : Boolean, val vote_average : Double
                   , val title : String, val popularity : Double, var poster_path : String, val backdrop_path : String,
                   val genre_ids : ArrayList<Int>, val overview : String)

data class MovieDetailsDB(val release_date : String, val vote_count: Int, val id : Int, val video : Boolean, val vote_average : Double
                   , val title : String, val popularity : Double, val poster_path : String , val backdrop_path : String,
                   val genre_ids : ArrayList<Int> , val overview : String, val revenue : String, val genres : ArrayList<Genre>)

data class Genre(val id : Int , val name : String )

data class TrailerResult(val id : Int , val results : ArrayList<MovieTrailerDB> )
data class MovieTrailerDB(val id : String, val key : String, val name : String, val size : Int, val site : String)


data class ReviewResult(val id : Int , val results : ArrayList<MovieReviewDB> )
data class MovieReviewDB(val id : String, val url : String, val author : String, val content : String)