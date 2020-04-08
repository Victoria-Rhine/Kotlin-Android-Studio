package victoria.mytvapp

import com.google.gson.annotations.SerializedName

data class GetShowsResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val shows: List<Show>,
    @SerializedName("total_pages") val pages: Int
)