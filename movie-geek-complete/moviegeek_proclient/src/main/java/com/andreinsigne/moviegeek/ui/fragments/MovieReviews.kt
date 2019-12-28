package com.andreinsigne.moviegeek.ui.fragments


import android.arch.lifecycle.Observer
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andreinsigne.moviegeek.R

import com.andreinsigne.moviegeek.ui.adapters.MovieReviewCardDBAdapter
import com.andreinsigne.moviegeek_moviedb.MainFragment

import kotlinx.android.synthetic.main.fragment_movie_reviews.*

class MovieReviews : MainFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_reviews, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel.getReviews(dataInterface!!.selectedMovieDB()!!.id.toString()).observe(this, Observer {
            dataInterface!!.onSelectedReviewResultDBRetrieved(it!!)
            movieReviewsList.layoutManager = LinearLayoutManager(activity)
            movieReviewsList.adapter = MovieReviewCardDBAdapter(dataInterface!!,this)
        })
    }
    companion object {

        fun newInstance() : MovieReviews{
            return MovieReviews()
        }
    }

}
