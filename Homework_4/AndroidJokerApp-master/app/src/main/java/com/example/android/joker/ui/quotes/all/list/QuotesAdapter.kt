package com.example.android.joker.ui.jokes.all.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.joker.R
import com.example.android.joker.model.Quote
import com.example.android.joker.ui.quotes.all.list.QuotesHolder

class QuotesAdapter(
        private val onFavoriteClickHandler: (Quote) -> Unit
) : RecyclerView.Adapter<QuotesHolder>() {

  private val items = mutableListOf<Quote>()
  private val favoriteQuotesIds = mutableListOf<String>()

  override fun getItemCount() = items.size

  fun setFavoriteQuotesIds(ids: List<String>) {
    favoriteQuotesIds.clear()
    favoriteQuotesIds.addAll(ids)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quotes, parent, false)

    return QuotesHolder(view, onFavoriteClickHandler)
  }

  override fun onBindViewHolder(holder: QuotesHolder, position: Int) {
    val quote = items[position].apply { isFavorite = id in favoriteQuotesIds }

    holder.displayData(quote)
  }

  fun addQuotes(quote: Quote) {
    items.add(quote)
    notifyItemInserted(items.size - 1)
  }
}