package com.andreinsigne.moviegeek_moviedb.services



import com.andreinsigne.moviegeek_moviedb.model.MovieDBResult
import com.andreinsigne.moviegeek_moviedb.model.MovieDetailsDB
import com.andreinsigne.moviegeek_moviedb.model.ReviewResult
import com.andreinsigne.moviegeek_moviedb.model.TrailerResult
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPIService
{

    @GET("search/movie/")
    fun getMovieDBByTitleWithPage(@Query("query") title : String, @Query("api_key") apikey : String, @Query("page") page : String) : Observable<MovieDBResult>

    @GET("search/movie/")
    fun getFreeMovieDBByTitleWithPage(@Query("query") title : String,@Query("year") year : String, @Query("api_key") apikey : String, @Query("page") page : String) : Observable<MovieDBResult>

    @GET("search/movie/")
    fun getPaidMovieDBByTitleWithPage(@Query("query") title : String, @Query("api_key") apikey : String, @Query("page") page : String) : Observable<MovieDBResult>

    @GET("movie/{id}/similar")
    fun getSimilarMoviesFromId(@Path("id") id : String, @Query("api_key") apikey : String) : Observable<MovieDBResult>

    @GET("movie/{id}/recommendations")
    fun getRecommendedMoviesFromId(@Path("id") id : String, @Query("api_key") apikey : String) : Observable<MovieDBResult>

    @GET("movie/{id}")
    fun getMovieDetailsFromId(@Path("id") id : String, @Query("api_key") apikey : String) : Observable<MovieDetailsDB>

    @GET("movie/{id}/reviews")
    fun getMovieReviewsFromId(@Path("id") id : String, @Query("api_key") apikey : String) : Observable<ReviewResult>

    @GET("movie/{id}/videos")
    fun getMovieTrailersFromId(@Path("id") id : String, @Query("api_key") apikey : String) : Observable<TrailerResult>

    companion object {
        fun createMovieDBAPI(): MovieAPIService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .build()

            return retrofit.create(MovieAPIService::class.java)
        }
    }
}