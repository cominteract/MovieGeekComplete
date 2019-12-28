package com.andreinsigne.moviegeek_free.ui.fragments


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreinsigne.moviegeek_free.R

import com.andreinsigne.moviegeek.ui.adapters.MovieCardDBAdapter
import com.andreinsigne.moviegeek_moviedb.MainFragment
import kotlinx.android.synthetic.main.fragment_home.*



class Home : MainFragment() {

    var movieCardDBAdapter : MovieCardDBAdapter? = null
    override fun movieImageClicked() {
        super.movieImageClicked()
        appTransitionInterface!!.moveToDetails()
    }


    override fun onResume() {
        super.onResume()
        if(dataInterface!!.allMoviesDB().size > 0)
            updateAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(dataInterface!!.allMoviesDB().size > 0)
            updateAdapter()
        else
            populateData("avengers")

        homeMovieSearchBtn.setOnClickListener{
            populateData(homeMovieSearchText.text.toString())
        }
        homeBtnNext.setOnClickListener {
            movieCardDBAdapter!!.increasePageIndex()
            refreshData()
        }
        homeBtnPrevious.setOnClickListener {
            movieCardDBAdapter!!.reducePageIndex()
            refreshData()
        }


    }

    fun populateData(title : String)
    {
        movieViewModel.getFreeMoviesDB(title).observe(this, Observer {
            dataInterface!!.onMoviesDBRetrieved(it!!)
            updateAdapter()
            homeMovieSearchText.setText("")
        })
    }

    fun updateAdapter()
    {
        movieList.layoutManager = LinearLayoutManager(activity)
        if(movieCardDBAdapter == null) {
            movieCardDBAdapter = MovieCardDBAdapter(dataInterface!!, this)
            movieList.adapter = movieCardDBAdapter
        }
        else {
            refreshData()
        }
    }

    companion object {

        fun newInstance() : Home{
            return Home()
        }
    }

    fun refreshData()
    {
        movieCardDBAdapter!!.updateData(dataInterface!!)
        movieCardDBAdapter!!.notifyDataSetChanged()
        movieList.adapter = movieCardDBAdapter
    }
}
