package victoria.mytvapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

const val SHOW_BACKDROP = "extra_show_backdrop"
const val SHOW_POSTER = "extra_show_poster"
const val SHOW_TITLE = "extra_show_title"
const val SHOW_RATING = "extra_show_rating"
const val SHOW_AIR_DATE = "extra_show_air_date"
const val SHOW_OVERVIEW = "extra_show_overview"

class ShowDetailsActivity : AppCompatActivity() {

    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var airDate: TextView
    private lateinit var overview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        backdrop = findViewById(R.id.show_backdrop)
        poster = findViewById(R.id.show_poster)
        title = findViewById(R.id.show_title)
        rating = findViewById(R.id.show_rating)
        airDate = findViewById(R.id.show_air_date)
        overview = findViewById(R.id.show_overview)

        val extras = intent.extras
        if (extras != null) {
            populateDetails(extras)
        } else {
            finish()
        }
    }

    private fun populateDetails(extras: Bundle) {
        extras.getString(SHOW_BACKDROP)?.let { backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280$backdropPath")
                .transform(CenterCrop())
                .into(backdrop)
        }

        extras.getString(SHOW_POSTER)?.let { posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342$posterPath")
                .transform(CenterCrop())
                .into(poster)
        }

        title.text =  extras.getString(SHOW_TITLE,  "")
        rating.rating = extras.getFloat(SHOW_RATING, 0f) / 2
        airDate.text = extras.getString(SHOW_AIR_DATE, "")
        overview.text = extras.getString(SHOW_OVERVIEW, "")
    }
}
