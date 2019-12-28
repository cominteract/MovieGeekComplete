package com.andreinsigne.moviegeek.ui.adapters


import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.andreinsigne.moviegeek_free.R
import com.andreinsigne.moviegeek_moviedb.inflate
import com.andreinsigne.moviegeek_moviedb.interfaces.AdapterInteractionInterface
import com.andreinsigne.moviegeek_moviedb.interfaces.DataInterface
import com.andreinsigne.moviegeek_moviedb.model.MovieReviewDB
import com.andreinsigne.moviegeek_moviedb.model.ReviewResult
import kotlinx.android.synthetic.main.movie_review.view.*


class MovieReviewCardDBAdapter(val dataInterface: DataInterface,
                               val adapterInteractionInterface: AdapterInteractionInterface ) : RecyclerView.Adapter<MovieReviewCardDBAdapter.MovieReviewDBHolder>()
{
    val reviews : ReviewResult = dataInterface.selectedReviewResult();
    val adapterInteractionInterface_ = adapterInteractionInterface

    class MovieReviewDBHolder(movieView: View, adapterInteractionInterface: AdapterInteractionInterface)
        : RecyclerView.ViewHolder(movieView), View.OnClickListener {

        private var view : View = movieView

        override fun onClick(p0: View?) {

        }

        init{
            movieView.setOnClickListener(this)

        }

        fun bind(review: MovieReviewDB, dataInterface: DataInterface, adapterInteractionInterface: AdapterInteractionInterface) {

            view.reviewAuthor.text = review.author
            view.reviewContent.text = review.content
            view.reviewContent.setOnClickListener{

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewDBHolder {
        val inflatedView = parent.inflate(R.layout.movie_review, false)
        return MovieReviewDBHolder(inflatedView, adapterInteractionInterface_)
    }

    override fun onBindViewHolder(holder: MovieReviewDBHolder, position: Int) {
        val review = reviews.results[position]
        holder.bind(review, dataInterface,adapterInteractionInterface_)
    }

    override fun getItemCount(): Int {
        return reviews.results.count()
    }

}