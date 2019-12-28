package com.andreinsigne.moviegeek_paidclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.util.Log
import android.view.View
import com.andreinsigne.moviegeek_moviedb.CommonActivity

import com.andreinsigne.moviegeek_moviedb.data.DataManager
import com.andreinsigne.moviegeek_moviedb.interfaces.AppTransitionInterface
import com.andreinsigne.moviegeek_paidclient.ui.fragments.*
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer



class MainActivity : CommonActivity(), YouTubePlayer.OnInitializedListener{



    override fun playVideo(key: String) {
        super.playVideo(key)
        showPlayer()
        player!!.loadVideo(key)
        videoKey = key
    }

    override fun onBackPressed() {

        if(supportFragmentManager.findFragmentById(homeContainerLayout.id) != null
                && supportFragmentManager.findFragmentById(homeContainerLayout.id) is MovieTrailer
                && homeContainerLayout.visibility == View.GONE) {
            hidePlayer()
            videoKey = null
        }
        else
            super.onBackPressed()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //DaggerMovieComponent.create().build(this)
        if(savedInstanceState!=null) {
            if(savedInstanceState.getString(playerVideo)!=null) {
                showPlayer()
            }
        }
        if(supportFragmentManager.findFragmentById(homeContainerLayout.id) == null)
            supportFragmentManager.beginTransaction().replace(homeContainerLayout.id,Home.newInstance()).commit()
        initializeYoutube(this)
    }

    private fun showPlayer()
    {
        homeContainerLayout.visibility = View.GONE
        showCommonPlayer()
        if(supportActionBar != null)
            supportActionBar!!.hide()
    }

    private fun hidePlayer()
    {
        homeContainerLayout.visibility = View.VISIBLE
        hideCommonPlayer()
        if(supportActionBar != null)
            supportActionBar!!.hide()
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player_: YouTubePlayer?, provided: Boolean) {
        player = player_
        if(videoKey!=null)
            player!!.loadVideo(videoKey,playerTime)
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, initresult: YouTubeInitializationResult?) {

    }

    override fun moveToRecommendations() {

    }

    override fun moveToSimilar() {
        supportFragmentManager.beginTransaction().replace(homeContainerLayout.id, MovieSimilar.newInstance()).addToBackStack(null).commit()
        supportActionBar!!.title = "Similar Movies"
    }

    override fun moveToReviews() {

        supportFragmentManager.beginTransaction().replace(homeContainerLayout.id, MovieReviews.newInstance()).addToBackStack(null).commit()
        supportActionBar!!.title = "Movie Reviews"
    }

    override fun moveToTrailers() {

        supportFragmentManager.beginTransaction().replace(homeContainerLayout.id, MovieTrailer.newInstance()).addToBackStack(null).commit()
        supportActionBar!!.title = "Movie Trailers"
    }


    override fun moveToDetails() {
        supportFragmentManager.beginTransaction().replace(homeContainerLayout.id, MovieProfile.newInstance()).addToBackStack(null).commit()
        supportActionBar!!.title = "Movie Details"
    }

    override fun onPause() {
        super.onPause()
        unregisterComponentCallbacks(this)
    }

    override fun onResume() {
        super.onResume()
        registerComponentCallbacks(this)
    }

}
