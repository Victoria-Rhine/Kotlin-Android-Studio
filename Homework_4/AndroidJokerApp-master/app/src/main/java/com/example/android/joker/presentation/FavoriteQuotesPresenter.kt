package com.example.android.joker.presentation

import com.example.android.joker.model.Quote
import com.example.android.joker.ui.quotes.favorite.FavoriteView

interface FavoriteQuotesPresenter : BasePresenter<FavoriteView> {

  fun getFavoriteQuotes()

  fun onFavoriteButtonTapped(quote: Quote)
}