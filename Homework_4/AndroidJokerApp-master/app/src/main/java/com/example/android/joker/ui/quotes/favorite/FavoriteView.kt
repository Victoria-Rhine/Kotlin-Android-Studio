package com.example.android.joker.ui.quotes.favorite

import com.example.android.joker.model.Quote

interface FavoriteView {

  fun showFavoriteQuotes(quote: List<Quote>)

  fun showNoDataDescription()

  fun hideNoDataDescription()

  fun clearItems()
}