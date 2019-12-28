package com.andreinsigne.moviegeek_free.ui.fragments


import android.arch.lifecycle.Observer
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreinsigne.moviegeek_free.R
import com.andreinsigne.moviegeek_moviedb.MainFragment

import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_details.*



class MovieProfile : MainFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel.getDetails(dataInterface!!.selectedMovieDB()!!.id.toString()).observe(this, Observer {
                movieDetailsText.text = it!!.title
                movieDetailsDate.text = it.release_date
                movieDetailsPlot.text = it.overview
                var genre = ""
                for (g in it.genres) {
                    genre = "${g.name},${genre}"
                }
                movieDetailsGenre.text = genre
                movieDetailsAverageVote.text = it.vote_average.toString()
                movieDetailsRated.text = it.vote_count.toString()
                movieDetailsPopularity.text = it.popularity.toString()
                movieDetailsBoxOffice.text = it.revenue

                if (it.poster_path != null)
                    Glide.with(view.context).load(urlFullPathFrom(it.poster_path)).into(movieDetailsImage)
                else if (it.backdrop_path != null)
                    Glide.with(view.context).load(urlFullPathFrom(it.backdrop_path)).into(movieDetailsImage)

                movieDetailsReviewButton.setOnClickListener { appTransitionInterface!!.moveToReviews() }
                movieDetailsTrailerButton.setOnClickListener { appTransitionInterface!!.moveToTrailers() }
        })


    }
    fun urlFullPathFrom(path : String) : String
    {
        return "http://image.tmdb.org/t/p/w185//${path}"
    }

    companion object {

        fun newInstance() : MovieProfile{
            return MovieProfile()
        }
    }

}
