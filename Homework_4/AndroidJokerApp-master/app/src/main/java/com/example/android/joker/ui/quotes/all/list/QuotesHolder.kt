package com.example.android.joker.ui.quotes.all.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.android.joker.R
import com.example.android.joker.common.onClick
import com.example.android.joker.model.Quote
import kotlinx.android.synthetic.main.item_quotes.view.*

class QuotesHolder(
    itemView: View,
    private inline val onFavoriteClickHandler: (Quote) -> Unit
) : RecyclerView.ViewHolder(itemView) {

  fun displayData(quote: Quote) = with(itemView) {
    favoriteButton.onClick { onFavoriteClickHandler(quote) }

    quotesAuthor.text = quote.authorName
    quotesDescription.text = quote.text

    favoriteButton.setImageResource(if(quote.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border)
  }
}