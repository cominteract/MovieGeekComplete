package com.andreinsigne.moviegeek_moviedb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.andreinsigne.moviegeek_moviedb.data.DataManager
import com.andreinsigne.moviegeek_moviedb.interfaces.AppTransitionInterface
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.ytframe_layout.*

open class CommonActivity : AppCompatActivity(), AppTransitionInterface {

    var player: YouTubePlayer? = null
    val data = "data"
    val playerVideo = "playervideo"
    val playerVideoTime = "playertime"
    val apiKey = "AIzaSyBDMxY9jy1o-6PN29fSf0angwVwde8rur8"
    var dataManager : DataManager? = DataManager()
    var videoKey : String? = null
    var playerTime : Int = -1
    override fun moveToDetails() {

    }

    override fun moveToTrailers() {

    }

    override fun moveToReviews() {

    }

    override fun moveToSimilar() {

    }

    override fun moveToRecommendations() {

    }

    override fun playVideo(key: String) {

    }

    fun initializeYoutube(youtubeActivity: YouTubePlayer.OnInitializedListener)
    {
        if(homeContainerYoutube!=null)
        (homeContainerYoutube as YouTubePlayerSupportFragment).initialize(apiKey, youtubeActivity)
    }

    fun hideCommonPlayer()
    {

        if(homeContainerYoutube!=null && homeContainerYoutube.view!=null)
            homeContainerYoutube.view!!.visibility = View.GONE
    }
    fun showCommonPlayer()
    {
        if(homeContainerYoutube!=null && homeContainerYoutube.view!=null)
            homeContainerYoutube.view!!.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState!=null) {
            if (savedInstanceState.getSerializable(data) != null)
                dataManager = savedInstanceState.getSerializable(data) as DataManager

            if (savedInstanceState.getString(playerVideo) != null) {
                playerTime = savedInstanceState.getInt(playerVideoTime)
                videoKey = savedInstanceState.getString(playerVideo)
            }
        }


    }

    override fun onDestroy() {
        if(player != null)
            player!!.release()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if(dataManager != null)
            outState!!.putSerializable(data, dataManager)
        if(player!=null && videoKey != null)
        {
            outState!!.putInt(playerVideoTime,player!!.currentTimeMillis)
            outState.putString(playerVideo, videoKey)
        }
    }
}
