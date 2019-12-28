package com.andreinsigne.moviegeek_moviedb.model

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.andreinsigne.moviegeek_moviedb.data.DateRetrieve
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MovieViewModel : ViewModel()
{
    var moviesDBLive : MutableLiveData<ArrayList<MovieDB>> = MutableLiveData()
    var moviesSimilarDBLive : MutableLiveData<ArrayList<MovieDB>> = MutableLiveData()
    var movieRecommendationsDBLive : MutableLiveData<ArrayList<MovieDB>> = MutableLiveData()
    var movieDetailsDBLive : MutableLiveData<MovieDetailsDB> = MutableLiveData()
    var movieTrailerDBLive : MutableLiveData<TrailerResult> = MutableLiveData()
    var movieReviewDBLive : MutableLiveData<ReviewResult> = MutableLiveData()
    val dataRetrieve = DateRetrieve()


//    fun getMovies(title : String) : LiveData<ArrayList<Movie>>
//    {
//        if(dataRetrieve.isFinished == true)
//            startMovieAPI(title)
//        else
//            Log.d( "TAG","Can't do this anymore")
//        return moviesLive
//    }
//
//    fun getMovieDetails(title : String) : LiveData<ArrayList<MovieDetails>>
//    {
//        if(dataRetrieve.isFinished == true)
//            startMovieAPI(title)
//        else
//            Log.d( "TAG","Can't do this anymore")
//        return movieDetailsLive
//    }

    fun getMoviesDB(title : String) : LiveData<ArrayList<MovieDB>>
    {
        if(dataRetrieve.isFinished)
            startMoviesDBAPI(title)
        else
            Log.d( "TAG","Can't do this for now")
        return moviesDBLive
    }

    fun getFreeMoviesDB(title : String) : LiveData<ArrayList<MovieDB>>
    {
        if(dataRetrieve.isFinished == true)
            startFreeMoviesDBAPI(title)
        else
            Log.d( "TAG","Can't do this for now")
        return moviesDBLive
    }

    fun getPaidMoviesDB(title : String) : LiveData<ArrayList<MovieDB>>
    {
        if(dataRetrieve.isFinished == true)
            startPaidMoviesDBAPI(title)
        else
            Log.d( "TAG","Can't do this for now")
        return moviesDBLive
    }

    fun getSimilarMoviesDB(id : String) : LiveData<ArrayList<MovieDB>>
    {
        if(dataRetrieve.isFinished == true)
            startSimilarMoviesDBAPI(id)
        else
            Log.d( "TAG","Can't do this for now")
        return moviesSimilarDBLive
    }

    fun getRecommendedMoviesDB(id : String) : LiveData<ArrayList<MovieDB>>
    {
        if(dataRetrieve.isFinished == true)
            startRecommendedMoviesDBAPI(id)
        else
            Log.d( "TAG","Can't do this for now")
        return movieRecommendationsDBLive
    }

    fun getDetails(id : String) : LiveData<MovieDetailsDB>
    {
        dataRetrieve.getMovieDetailsDB(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resultDetails ->
                    Log.d("  Details ", " ${resultDetails.title} ")
                    movieDetailsDBLive.value = resultDetails
                }, { errorDetails ->
                    Log.d(" Error Details ", " ${errorDetails.localizedMessage} ")
                })
        return movieDetailsDBLive
    }

    fun getReviews(id : String) : LiveData<ReviewResult>
    {
        dataRetrieve.getMovieReviewsDB(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resultReviews ->
                    movieReviewDBLive.value = resultReviews
                }, { errorReviews ->
                    Log.d(" Error Reviews ", " ${errorReviews.localizedMessage} ")
                })
        return movieReviewDBLive
    }

    fun getTrailers(id : String) : LiveData<TrailerResult>
    {
        dataRetrieve.getMovieTrailersDB(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resultTrailers ->
                    movieTrailerDBLive.value = resultTrailers
                }, { errorTrailers ->
                    Log.d(" Error Reviews ", " ${errorTrailers.localizedMessage} ")
                })
        return movieTrailerDBLive
    }

    private fun startSimilarMoviesDBAPI(id : String)
    {

        dataRetrieve.isFinished = false;
        dataRetrieve.resultSize = 0
        dataRetrieve.getSimilarMoviesDB(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    if(dataRetrieve.resultSize > 0) {
                        Log.d( " Movie size ", " ${result.size}")
                        moviesSimilarDBLive.value = result
                        dataRetrieve.isFinished = true
                    }

                },{ error ->
                    Log.d( " Error ", " ${error.localizedMessage.toString()}")
                })
    }

    private fun startRecommendedMoviesDBAPI(id : String)
    {

        dataRetrieve.isFinished = false;
        dataRetrieve.resultSize = 0
        dataRetrieve.getRecommendedMoviesDB(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    if(dataRetrieve.resultSize > 0) {
                        Log.d( " Movie size ", " ${result.size}")
                        movieRecommendationsDBLive.value = result
                        dataRetrieve.isFinished = true
                    }

                },{ error ->
                    Log.d( " Error ", " ${error.localizedMessage.toString()}")
                })
    }

    private fun startMoviesDBAPI(title : String)
    {
        dataRetrieve.isFinished = false;
        dataRetrieve.resultSize = 0
        dataRetrieve.getMovieDBWithPage(title,"1").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    if(dataRetrieve.resultSize > 0) {
                        Log.d( " Movie size ", " ${result.size}")
                        moviesDBLive.value = result
                        dataRetrieve.isFinished = true
                    }

                },{ error ->
                })
    }
    private fun startPaidMoviesDBAPI(title : String)
    {
        dataRetrieve.isFinished = false;
        dataRetrieve.resultSize = 0
        dataRetrieve.getPaidMovieDBWithPage(title,"1").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    if(dataRetrieve.resultSize > 0) {
                        Log.d( " Movie size ", " ${result.size}")
                        moviesDBLive.value = result
                        dataRetrieve.isFinished = true
                    }
                },{ error ->

                })
    }

    private fun startFreeMoviesDBAPI(title : String)
    {
        dataRetrieve.isFinished = false;
        dataRetrieve.resultSize = 0
        dataRetrieve.getFreeMovieDBWithPage(title,"1").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    if(dataRetrieve.resultSize > 0) {
                        Log.d( " Movie size ", " ${result.size}")
                        moviesDBLive.value = result
                        dataRetrieve.isFinished = true
                    }

                },{ error ->

                })
    }

    companion object{

        fun create(activity: FragmentActivity?): MovieViewModel {

            var movieViewModel = ViewModelProviders.of(activity!!).get(MovieViewModel::class.java)
            movieViewModel.dataRetrieve.isFinished = true
            return movieViewModel
        }

    }

}