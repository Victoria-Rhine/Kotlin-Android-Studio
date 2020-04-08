package com.example.android.joker.ui.quotes.favorite.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.joker.R
import com.example.android.joker.model.Quote
import com.example.android.joker.ui.quotes.all.list.QuotesHolder

class FavoriteQuotesAdapter(
    private val onFavoriteClickHandler: (Quote) -> Unit
) : RecyclerView.Adapter<QuotesHolder>() {

  private val items = mutableListOf<Quote>()

  override fun getItemCount() = items.size

  fun setData(data: List<Quote>) {
    items.clear()
    items.addAll(data)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quotes, parent, false)

    return QuotesHolder(view, onFavoriteClickHandler)
  }

  override fun onBindViewHolder(holder: QuotesHolder, position: Int) = holder.displayData(items[position])

  fun clear() {
    items.clear()
    notifyDataSetChanged()
  }
}