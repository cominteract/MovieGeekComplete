package com.andreinsigne.moviegeek_moviedb

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View

import com.andreinsigne.moviegeek_moviedb.CommonActivity


import com.andreinsigne.moviegeek_moviedb.interfaces.AdapterInteractionInterface
import com.andreinsigne.moviegeek_moviedb.interfaces.AppTransitionInterface
import com.andreinsigne.moviegeek_moviedb.interfaces.DataInterface
import com.andreinsigne.moviegeek_moviedb.model.MovieViewModel


open class MainFragment : Fragment(), AdapterInteractionInterface {


    var appTransitionInterface : AppTransitionInterface? = null
    var dataInterface : DataInterface? = null
    var main : CommonActivity? = null
    lateinit var movieViewModel : MovieViewModel

    override fun movieTitleClicked() {

    }

    override fun movieImageClicked() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(main!=null)
        {
            dataInterface = main!!.dataManager
            appTransitionInterface = main
        }
        movieViewModel = MovieViewModel.create(activity)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is CommonActivity)
            main = context
    }

    override fun onDetach() {
        super.onDetach()
        appTransitionInterface = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)




    }
}