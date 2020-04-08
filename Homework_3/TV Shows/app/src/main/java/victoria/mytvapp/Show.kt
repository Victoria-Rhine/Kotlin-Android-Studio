package victoria.mytvapp

import com.google.gson.annotations.SerializedName

data class Show
    (@SerializedName("id") val id: Long,
     @SerializedName("name") val title: String,
     @SerializedName("overview") val overview: String,
     @SerializedName("poster_path") val posterPath: String,
     @SerializedName("backdrop_path") val backdropPath: String,
     @SerializedName("vote_average") val rating: Float,
     @SerializedName("first_air_date") val firstAirDate: String
)