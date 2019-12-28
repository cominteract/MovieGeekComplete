package com.andreinsigne.moviegeek_free

import android.os.Bundle
import android.view.View
import com.andreinsigne.moviegeek_free.ui.fragments.Home
import com.andreinsigne.moviegeek_free.ui.fragments.MovieProfile
import com.andreinsigne.moviegeek_free.ui.fragments.MovieReviews
import com.andreinsigne.moviegeek_free.ui.fragments.MovieTrailer
import kotlinx.android.synthetic.main.activity_main.*
import android.os.CountDownTimer
import android.support.design.widget.Snackbar
import com.andreinsigne.moviegeek_moviedb.CommonActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer


class MainActivity : CommonActivity(), YouTubePlayer.OnInitializedListener {



    override fun playVideo(key: String) {
        super.playVideo(key)
        showPlayer()
        player!!.loadVideo(key)
        videoKey = key
        startFreeCounter()
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
                startFreeCounter()
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

    private fun startFreeCounter()
    {
        val Count = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                if(homeContainerLayout.visibility == View.GONE) {
                    hidePlayer()
                    videoKey = null
                    Snackbar.make(mainView, " Annoying right? Pay up, poor people don't " +
                            "deserve full trailers", Snackbar.LENGTH_LONG).show()
                }
            }
        }
        Count.start()
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
