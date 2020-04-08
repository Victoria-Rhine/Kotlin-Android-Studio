package victoria.mytvapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class ShowsAdapter(
    private var shows: MutableList<Show>,
    private val onShowClick: (show: Show) -> Unit
) : RecyclerView.Adapter<ShowsAdapter.ShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_show, parent, false)
        return ShowViewHolder(view)
    }

    override fun getItemCount(): Int = shows.size

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    fun appendShows(shows: List<Show>) {
        this.shows.addAll(shows)
        notifyItemRangeInserted(
            this.shows.size,
            shows.size - 1 ) }

    inner class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        private val poster: ImageView = itemView.findViewById(R.id.item_show_poster)
        fun bind(show: Show) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${show.posterPath}")
                .transform(CenterCrop())
                .into(poster)
            itemView.setOnClickListener { onShowClick.invoke(show) }
        }
    }
}