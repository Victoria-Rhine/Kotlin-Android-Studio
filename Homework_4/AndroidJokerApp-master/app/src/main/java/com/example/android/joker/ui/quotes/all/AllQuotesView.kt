package com.example.android.joker.ui.quotes.all

import com.example.android.joker.model.Quote

interface AllQuotesView {

  fun showNoDataDescription()

  fun hideNoDataDescription()

  fun setFavoriteQuotesIds(favoriteQuotesIds: List<String>)

  fun addQuotes(quote: Quote)
}