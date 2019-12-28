package com.andreinsigne.moviegeek_paidclient.ui.adapters

import android.graphics.Movie
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.andreinsigne.moviegeek_moviedb.inflate


import com.andreinsigne.moviegeek_moviedb.interfaces.AdapterInteractionInterface
import com.andreinsigne.moviegeek_moviedb.interfaces.DataInterface
import com.andreinsigne.moviegeek_moviedb.model.MovieDB
import com.andreinsigne.moviegeek_paidclient.R
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.movie_view.view.*

class MovieCardDBAdapter(dataInterface_: DataInterface,
                         adapterInteractionInterface: AdapterInteractionInterface ) : RecyclerView.Adapter<MovieCardDBAdapter.MovieDBHolder>()
{
    var dataInterface : DataInterface = dataInterface_
    var movies : ArrayList<MovieDB> = dataInterface.allMoviesDB()
    val adapterInteractionInterface_ = adapterInteractionInterface
    var currentPageIndex = 0
    val maxItemPerPage = 50
    fun updateData(dataInterface_: DataInterface)
    {
        dataInterface = dataInterface_
    }

    fun updateSimilarMovies(similarMovies : ArrayList<MovieDB>)
    {
        movies = similarMovies
        Log.d(" Movies Size ", " ${similarMovies.size} Size ")
        notifyDataSetChanged()
    }

    fun reducePageIndex()
    {
        currentPageIndex--
        if(currentPageIndex < 0)
            currentPageIndex = 0
    }
    fun increasePageIndex()
    {
        currentPageIndex++
        if(currentPageIndex + 1 > movies.count()/maxItemPerPage)
            currentPageIndex = movies.count()/maxItemPerPage
    }
    fun homePageIndex()
    {
        currentPageIndex = 0
    }



    class MovieDBHolder(movieView: View, adapterInteractionInterface: AdapterInteractionInterface)
        : RecyclerView.ViewHolder(movieView), View.OnClickListener {

        val imageBaseUrl = "http://image.tmdb.org/t/p/w185//"
        private var view : View = movieView

        override fun onClick(p0: View?) {

        }

        init{
            movieView.setOnClickListener(this)
        }

        fun urlFullPathFrom(path : String) : String
        {
            return "${imageBaseUrl}${path}"
        }


        fun bind(movie: MovieDB, dataInterface: DataInterface, adapterInteractionInterface: AdapterInteractionInterface) {

            view.moviePlot.text = movie.overview
            view.movieTitle.text = movie.title
            if(movie.poster_path != null)
                Glide.with(view.context).load(urlFullPathFrom(movie.poster_path)).into(view.moviePoster)
            else {
                if (movie.backdrop_path != null)
                    Glide.with(view.context).load(urlFullPathFrom(movie.backdrop_path)).into(view.moviePoster)
            }

            view.moviePoster.setOnClickListener{
                dataInterface.onSelectedMovieDBRetrieved(movie)
                adapterInteractionInterface.movieImageClicked()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDBHolder {
        val inflatedView = parent.inflate(R.layout.movie_view, false)
        return MovieDBHolder(inflatedView, adapterInteractionInterface_)
    }

    override fun onBindViewHolder(holder: MovieDBHolder, position: Int) {
        val pagePosition : Int = (currentPageIndex * maxItemPerPage) + position
        lateinit var movie: MovieDB
        if(pagePosition < movies.count())
            movie  = movies.get(pagePosition)
        else
            movie = movies.get(position)
        holder.bind(movie, dataInterface,adapterInteractionInterface_)
    }

    override fun getItemCount(): Int {
        if (movies.count() < 50)
            return movies.count()
        else if (currentPageIndex + 1 > movies.count() / maxItemPerPage)
            return movies.count() % maxItemPerPage
        else
            return maxItemPerPage
    }
}