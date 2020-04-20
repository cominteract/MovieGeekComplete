package com.andreinsigne.moviegeek_moviedb.data;


import android.util.Log;


import com.andreinsigne.moviegeek_moviedb.model.MovieDB;
import com.andreinsigne.moviegeek_moviedb.model.MovieDBResult;
import com.andreinsigne.moviegeek_moviedb.model.MovieDetailsDB;
import com.andreinsigne.moviegeek_moviedb.model.ReviewResult;
import com.andreinsigne.moviegeek_moviedb.model.TrailerResult;
import com.andreinsigne.moviegeek_moviedb.services.MovieAPIService;

import java.util.ArrayList;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class DateRetrieve {

    private MovieAPIService movieAPIService = MovieAPIService.Companion.createMovieDBAPI();
    private ArrayList<MovieDB> moviesDB = new ArrayList<>();
    private ArrayList<MovieDB> similarMoviesDB = new ArrayList<>();
    private ArrayList<MovieDetailsDB> moviesDetailsDB = new ArrayList<>();
    private ArrayList<TrailerResult> moviesTrailersDB = new ArrayList<>();
    private ArrayList<ReviewResult> moviesReviewsDB = new ArrayList<>();
    public int resultSize;
    public boolean isFinished = true;
    private static final String movieDbApiKey = "a46a4ee2b4c59209d6b729bdd54386a0";

//    public Observable<ArrayList<Movie>> getPageAndNext(final String title,final String page) {
//        return movieAPIService.getMoviesByTitleWithPage(title,"3245d620",page)
//                .concatMap(new Function<MovieBySearch, Observable<ArrayList<Movie>>>() {
//
//                    @Override
//                    public Observable<ArrayList<Movie>> apply(MovieBySearch movieBySearch) throws Exception {
//
//                        String nextPage = String.valueOf(Integer.valueOf(page) + 1);
//                        int next = Integer.valueOf(nextPage);
//                        int res = Integer.valueOf(movieBySearch.getTotalResults())/10;
//                        movies.addAll(movieBySearch.getSearch());
//
//
//                        if (next > res) {
//                            resultSize = Integer.valueOf(movieBySearch.getTotalResults());
//                            return Observable.just(movies);
//                        }
//
//                        return Observable.just(movies)
//                                .concatWith(getPageAndNext(title,nextPage));
//
//                    }
//                });
//    }


    public Observable<ArrayList<MovieDB>> getPaidMovieDBWithPage(final String title, final String page) {
        return movieAPIService.getMovieDBByTitleWithPage(title,movieDbApiKey,page)
                .concatMap(new Function<MovieDBResult, Observable<ArrayList<MovieDB>>>() {

                    @Override
                    public Observable<ArrayList<MovieDB>> apply(MovieDBResult movieDBResult) throws Exception {
                        String nextPage = String.valueOf(Integer.valueOf(page) + 1);
                        int next = Integer.valueOf(nextPage);
                        moviesDB.addAll(movieDBResult.getResults());
                        if (next > movieDBResult.getTotal_results()/2) {
                            resultSize = moviesDB.size();
                            return Observable.just(moviesDB);
                        }
                        return Observable.just(moviesDB)
                                .concatWith(getPaidMovieDBWithPage(title,nextPage));
                    }
                });
    }

    public Observable<ArrayList<MovieDB>> getFreeMovieDBWithPage(final String title, final String page) {
        return movieAPIService.getFreeMovieDBByTitleWithPage(title,"2018",movieDbApiKey,page)
                .concatMap(new Function<MovieDBResult, Observable<ArrayList<MovieDB>>>() {

                    @Override
                    public Observable<ArrayList<MovieDB>> apply(MovieDBResult movieDBResult) throws Exception {
                        String nextPage = String.valueOf(Integer.valueOf(page) + 1);
                        int next = Integer.valueOf(nextPage);
                        moviesDB.addAll(movieDBResult.getResults());
                        if (next > movieDBResult.getTotal_results()) {
                            resultSize = moviesDB.size();
                            return Observable.just(moviesDB);
                        }
                        return Observable.just(moviesDB)
                                .concatWith(getFreeMovieDBWithPage(title,nextPage));
                    }
                });
    }


    public Observable<ArrayList<MovieDB>> getMovieDBWithPage(final String title, final String page) {
        return movieAPIService.getMovieDBByTitleWithPage(title,movieDbApiKey,page)
                .concatMap(new Function<MovieDBResult, Observable<ArrayList<MovieDB>>>() {

                    @Override
                    public Observable<ArrayList<MovieDB>> apply(MovieDBResult movieDBResult) throws Exception {
                        String nextPage = String.valueOf(Integer.valueOf(page) + 1);
                        int next = Integer.valueOf(nextPage);

                        moviesDB.addAll(movieDBResult.getResults());
                        if (next > movieDBResult.getTotal_results() || next > 45) {
                            resultSize = moviesDB.size();
                            return Observable.just(moviesDB);
                        }

                        return Observable.just(moviesDB)
                                .concatWith(getMovieDBWithPage(title,nextPage));
                    }
                });
    }


    public Observable<ArrayList<MovieDB>> getSimilarMoviesDB(final String id) {
        return movieAPIService.getSimilarMoviesFromId(id,movieDbApiKey)
                .concatMap(new Function<MovieDBResult, Observable<ArrayList<MovieDB>>>() {

                    @Override
                    public Observable<ArrayList<MovieDB>> apply(MovieDBResult movieDBResult) throws Exception {
                        similarMoviesDB.addAll(movieDBResult.getResults());
                        resultSize = similarMoviesDB.size();
                        return Observable.just(similarMoviesDB);
                    }
                });
    }

    public Observable<ArrayList<MovieDB>> getRecommendedMoviesDB(final String id) {
        return movieAPIService.getRecommendedMoviesFromId(id,movieDbApiKey)
                .concatMap(new Function<MovieDBResult, Observable<ArrayList<MovieDB>>>() {

                    @Override
                    public Observable<ArrayList<MovieDB>> apply(MovieDBResult movieDBResult) throws Exception {
                        similarMoviesDB.addAll(movieDBResult.getResults());
                        resultSize = similarMoviesDB.size();
                        return Observable.just(similarMoviesDB);
                    }
                });
    }


    public Observable<ReviewResult> getMovieReviewsDB(final String id) {
        return movieAPIService.getMovieReviewsFromId(id,movieDbApiKey).concatMap(
                new Function<ReviewResult, ObservableSource<? extends ReviewResult>>() {
                    @Override
                    public ObservableSource<? extends ReviewResult> apply(ReviewResult reviewResult) throws Exception {
                        moviesReviewsDB.add(reviewResult);
                        return Observable.just(reviewResult);
                    }});
    }

    public Observable<TrailerResult> getMovieTrailersDB(final String id) {
        return movieAPIService.getMovieTrailersFromId(id,movieDbApiKey).concatMap(
                new Function<TrailerResult, ObservableSource<? extends TrailerResult>>() {
                    @Override
                    public ObservableSource<? extends TrailerResult> apply(TrailerResult trailerResult) throws Exception {
                        moviesTrailersDB.add(trailerResult);
                        return Observable.just(trailerResult);
                    }
                });
    }

    public Observable<MovieDetailsDB> getMovieDetailsDB( final String id) {
        return movieAPIService.getMovieDetailsFromId(id,movieDbApiKey).concatMap(
                new Function<MovieDetailsDB, ObservableSource<? extends MovieDetailsDB>>() {
            @Override
            public ObservableSource<? extends MovieDetailsDB> apply(MovieDetailsDB movieDetailsDB) throws Exception {
                moviesDetailsDB.add(movieDetailsDB);
                return Observable.just(movieDetailsDB);
            }
        });
    }


//    public Observable<ArrayList<MovieDetails>> getMovieDetails( final String id) {
//
//        return movieAPIService.getMovieDetailsById(id, "3245d620")
// .concatMap(new Function<MovieDetails, ObservableSource<? extends ArrayList<MovieDetails>>>() {
//            @Override
//            public ObservableSource<? extends ArrayList<MovieDetails>> apply(MovieDetails movieDetails) throws Exception {
//                allMovieDetails.add(movieDetails);
//                return Observable.just(allMovieDetails);
//            }
//        });
//    }


    /*
      return movieAPIService.getMoviesByTitleWithPage(title,"3245d620",page)
                .concatMap(new Function<MovieBySearch, Observable<ArrayList<Movie>>>() {

                    @Override
                    public Observable<ArrayList<Movie>> apply(MovieBySearch movieBySearch) throws Exception {

                        String nextPage = String.valueOf(Integer.valueOf(page) + 1);
                        int next = Integer.valueOf(nextPage);
                        int res = Integer.valueOf(movieBySearch.getTotalResults())/10;
                        movies.addAll(movieBySearch.getSearch());
                        if (next > res) {
                            return Observable.just(movies);
                        }

                        return Observable.just(movies)
                                .concatWith(getPageAndNext(title,nextPage));

                    }
                });
     */




}
