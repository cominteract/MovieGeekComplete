package com.andreinsigne.moviegeek.ui.fragments


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.moviegeek.R
import com.andreinsigne.moviegeek.ui.adapters.MovieCardDBAdapter
import com.andreinsigne.moviegeek_moviedb.MainFragment
import kotlinx.android.synthetic.main.fragment_movie_recommendations.*


class MovieRecommendations : MainFragment() {

    override fun movieImageClicked() {
        super.movieImageClicked()
        appTransitionInterface!!.moveToTrailers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_recommendations, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel.getRecommendedMoviesDB(dataInterface!!.selectedMovieDB()!!.id.toString()).observe(this, Observer {
            movieRecommendationList.layoutManager = LinearLayoutManager(activity)
            val movieRecommendationAdapter = MovieCardDBAdapter(dataInterface!!, this)
            movieRecommendationAdapter.updateRecommendedMovies(it!!)
            movieRecommendationList.adapter = movieRecommendationAdapter
        })
    }


    companion object {

        fun newInstance() : MovieRecommendations{
            return MovieRecommendations()
        }
    }

}
