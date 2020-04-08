package victoria.mytvapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("tv/popular")
    fun  getPopularShows(
        @Query("api_key") apiKey: String = "f2add0be2b6cd1597387d0aad7113f01",
        @Query("page") page: Int
    ):  Call<GetShowsResponse>

    @GET("tv/top_rated")
    fun getTopRatedShows(
        @Query("api_key") apiKey: String = "f2add0be2b6cd1597387d0aad7113f01",
        @Query("page") page: Int
    ):  Call<GetShowsResponse>

    @GET("tv/on_the_air")
    fun getOnTheAirShows(
        @Query("api_key") apiKey: String = "f2add0be2b6cd1597387d0aad7113f01",
        @Query("page") page: Int
    ):  Call<GetShowsResponse>

    @GET("tv/airing_today")
    fun getAiringTodayShows(
        @Query("api_key") apiKey: String = "f2add0be2b6cd1597387d0aad7113f01",
        @Query("page") page: Int
    ):  Call<GetShowsResponse>

    @GET("movie/popular")
    fun  getPopularMovies(
        @Query("api_key") apiKey: String = "f2add0be2b6cd1597387d0aad7113f01",
        @Query("page") page: Int
    ):  Call<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "f2add0be2b6cd1597387d0aad7113f01",
        @Query("page") page: Int
    ):  Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "f2add0be2b6cd1597387d0aad7113f01",
        @Query("page") page: Int
    ):  Call<GetMoviesResponse>

    @GET("movie/now_playing")
    fun getPlayingMovies(
        @Query("api_key") apiKey: String = "f2add0be2b6cd1597387d0aad7113f01",
        @Query("page") page: Int
    ):  Call<GetMoviesResponse>
}