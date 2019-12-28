package com.andreinsigne.moviegeek_paidclient.ui.fragments


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreinsigne.moviegeek_moviedb.MainFragment

import com.andreinsigne.moviegeek_paidclient.R
import com.andreinsigne.moviegeek_paidclient.ui.adapters.MovieCardDBAdapter
import kotlinx.android.synthetic.main.fragment_movie_similar.*


class MovieSimilar : MainFragment() {

    override fun movieImageClicked() {
        super.movieImageClicked()
        appTransitionInterface!!.moveToTrailers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_similar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel.getSimilarMoviesDB(dataInterface!!.selectedMovieDB()!!.id.toString()).observe(this, Observer {
            movieSimilarList.layoutManager = LinearLayoutManager(activity)
            val movieSimilarAdapter = MovieCardDBAdapter(dataInterface!!, this)
            movieSimilarAdapter.updateSimilarMovies(it!!)
            movieSimilarList.adapter = movieSimilarAdapter

        })
    }

    companion object {
        fun newInstance() : MovieSimilar{
            return MovieSimilar()
        }
    }
}
