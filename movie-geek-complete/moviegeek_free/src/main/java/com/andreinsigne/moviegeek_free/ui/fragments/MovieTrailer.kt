package com.andreinsigne.moviegeek_free.ui.fragments

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import com.andreinsigne.moviegeek_free.R

import android.widget.VideoView

import kotlinx.android.synthetic.main.fragment_movie_trailer.*
import android.media.MediaPlayer
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.andreinsigne.moviegeek_free.MainActivity
import com.andreinsigne.moviegeek_moviedb.MainFragment
import com.andreinsigne.moviegeek_moviedb.model.MovieTrailerDB




class MovieTrailer : MainFragment() {
    var path : String = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movie_trailer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        doAsync {
//            val result = URL("http://www.youtube.com/get_video_info?&video_id=YsyAYOyBMBs").readText()
//            uiThread {
//                Log.d("Request", result)
//                Log.d("Request Finished", " Finished ")
//            }
//        }
        movieViewModel.getTrailers(dataInterface!!.selectedMovieDB()!!.id.toString()).observe(this, Observer {
            movieTrailerList.adapter = TrailerAdapter(activity!!, it!!.results)
            movieTrailerList.setOnItemClickListener { parent, view, position, id ->
                path = "http://youtube.com/watch?v=${it!!.results[position].key}"
                appTransitionInterface!!.playVideo(it!!.results[position].key)
            }
        })

    }

    class TrailerAdapter(val context: FragmentActivity, val array: ArrayList<MovieTrailerDB>)
        : ArrayAdapter<MovieTrailerDB>(context, android.R.layout.simple_list_item_1, array) {
        companion object {
            class Holder {
                var textView: TextView? = null
            }
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var holder: Holder;
            if (convertView == null) {
                val view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
                holder = Holder()
                holder.textView = view.findViewById(android.R.id.text1)
                holder.textView?.text = array[position].name
                view.tag = holder
                return view;
            } else {
                holder = convertView.tag as Holder
                holder.textView?.text = array[position].name
                return convertView
            }
        }
    }




    companion object {

        fun newInstance() : MovieTrailer{
            return MovieTrailer()
        }
    }
}
