package victoria.mytvapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var popularShows: RecyclerView
    private lateinit var popularShowsAdapter: ShowsAdapter
    private lateinit var popularShowsLayoutMgr: LinearLayoutManager

    private var popularShowsPage = 1

    private lateinit var topRatedShows: RecyclerView
    private lateinit var topRatedShowsAdapter: ShowsAdapter
    private lateinit var topRatedShowsLayoutMgr: LinearLayoutManager

    private var topRatedShowsPage = 1

    private lateinit var onTheAirShows: RecyclerView
    private lateinit var onTheAirShowsAdapter: ShowsAdapter
    private lateinit var onTheAirShowsLayoutMgr: LinearLayoutManager

    private var onTheAirShowsPage = 1

    private lateinit var airingTodayShows: RecyclerView
    private lateinit var airingTodayShowsAdapter: ShowsAdapter
    private lateinit var airingTodayShowsLayoutMgr: LinearLayoutManager

    private var airingTodayShowsPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        popularShows = findViewById(R.id.popular_shows)
        popularShowsLayoutMgr = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        topRatedShows = findViewById(R.id.top_rated_shows)
        topRatedShowsLayoutMgr = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL,
            false
        )

        onTheAirShows = findViewById(R.id.on_the_air)
        onTheAirShowsLayoutMgr = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL,
            false
        )

        airingTodayShows = findViewById(R.id.airing_today)
        airingTodayShowsLayoutMgr = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL,
            false
        )

        popularShows.layoutManager = popularShowsLayoutMgr
        popularShowsAdapter = ShowsAdapter(mutableListOf()){ show -> showShowDetails(show)}
        popularShows.adapter = popularShowsAdapter

        topRatedShows.layoutManager = topRatedShowsLayoutMgr
        topRatedShowsAdapter = ShowsAdapter(mutableListOf()){ show -> showShowDetails(show)}
        topRatedShows.adapter = topRatedShowsAdapter

        onTheAirShows.layoutManager = onTheAirShowsLayoutMgr
        onTheAirShowsAdapter = ShowsAdapter(mutableListOf()){ show -> showShowDetails(show)}
        onTheAirShows.adapter = onTheAirShowsAdapter

        airingTodayShows.layoutManager = airingTodayShowsLayoutMgr
        airingTodayShowsAdapter = ShowsAdapter(mutableListOf()){ show -> showShowDetails(show)}
        airingTodayShows.adapter = airingTodayShowsAdapter

        getPopularShows()
        getTopRatedShows()
        getOnTheAirShows()
        getAiringTodayShows()

    }

    private fun getPopularShows() {
        ShowsRepository.getPopularShows(
            popularShowsPage,
            ::onPopularShowsFetched,
            ::onError
        )
    }

    private fun getTopRatedShows() {
        ShowsRepository.getTopRatedShows(
            topRatedShowsPage,
            ::onTopRatedShowsFetched,
            ::onError
        )
    }

    private fun getOnTheAirShows() {
        ShowsRepository.getOnTheAirShows(
            onTheAirShowsPage,
            ::onTheAirShowsFetched,
            ::onError
        )
    }

    private fun getAiringTodayShows() {
        ShowsRepository.getAiringTodayShows(
            airingTodayShowsPage,
            ::airingTodayShowsFetched,
            ::onError
        )
    }

    private fun showShowDetails(show:  Show) {
        val intent = Intent(this, ShowDetailsActivity::class.java)
        intent.putExtra(SHOW_BACKDROP, show.backdropPath)
        intent.putExtra(SHOW_POSTER, show.posterPath)
        intent.putExtra(SHOW_TITLE, show.title)
        intent.putExtra(SHOW_RATING, show.rating)
        intent.putExtra(SHOW_AIR_DATE, show.firstAirDate)
        intent.putExtra(SHOW_OVERVIEW, show.overview)
        startActivity(intent)
    }

    private fun onPopularShowsFetched(shows: List<Show>) {
        popularShowsAdapter.appendShows(shows)
        attachPopularShowsOnScrollListener()
    }

    private fun onTopRatedShowsFetched(shows: List<Show>) {
        topRatedShowsAdapter.appendShows(shows)
        attachTopRatedShowsOnScrollListener()
    }

    private fun onTheAirShowsFetched(shows: List<Show>) {
        onTheAirShowsAdapter.appendShows(shows)
        attachOnTheAirShowsOnScrollListener()
    }

    private fun airingTodayShowsFetched(shows: List<Show>) {
        airingTodayShowsAdapter.appendShows(shows)
        attachAiringTodayOnScrollListener()
    }

    private fun attachPopularShowsOnScrollListener() {
        popularShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularShowsLayoutMgr.itemCount
                val visibleItemCount = popularShowsLayoutMgr.childCount
                val firstVisibleItem = popularShowsLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularShows.removeOnScrollListener(this)
                    popularShowsPage++
                    getPopularShows()
                }
            }
        })
    }

    private fun attachTopRatedShowsOnScrollListener() {
        topRatedShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedShowsLayoutMgr.itemCount
                val visibleItemCount = topRatedShowsLayoutMgr.childCount
                val firstVisibleItem = topRatedShowsLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedShows.removeOnScrollListener(this)
                    topRatedShowsPage++
                    getTopRatedShows()
                }
            }
        })
    }

    private fun attachOnTheAirShowsOnScrollListener() {
        onTheAirShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView,  dx:  Int,  dy:  Int)  {
                val totalItemCount = onTheAirShowsLayoutMgr.itemCount
                val visibleItemCount = onTheAirShowsLayoutMgr.childCount
                val firstVisibleItem = onTheAirShowsLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    onTheAirShows.removeOnScrollListener(this)
                    onTheAirShowsPage++
                    getOnTheAirShows()
                }
            }
        })
    }

    private fun attachAiringTodayOnScrollListener() {
        airingTodayShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView,  dx:  Int,  dy:  Int)  {
                val totalItemCount = airingTodayShowsLayoutMgr.itemCount
                val visibleItemCount = airingTodayShowsLayoutMgr.childCount
                val firstVisibleItem = airingTodayShowsLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    airingTodayShows.removeOnScrollListener(this)
                    airingTodayShowsPage++
                    getAiringTodayShows()
                }
            }
        })
    }

    private  fun  onError() {
        Toast.makeText(this,  getString(R.string.error_fetch_shows),  Toast.LENGTH_SHORT).show() }

}