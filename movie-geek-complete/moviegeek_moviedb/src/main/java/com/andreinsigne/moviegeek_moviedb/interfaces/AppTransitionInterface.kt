package com.andreinsigne.moviegeek_moviedb.interfaces

interface AppTransitionInterface {
    fun moveToDetails()
    fun moveToTrailers()
    fun moveToReviews()
    fun moveToSimilar()
    fun moveToRecommendations()
    fun playVideo( key : String)
}