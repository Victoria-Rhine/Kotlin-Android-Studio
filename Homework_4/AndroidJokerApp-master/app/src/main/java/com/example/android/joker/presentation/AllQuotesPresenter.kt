package com.example.android.joker.presentation

import com.example.android.joker.model.Quote
import com.example.android.joker.ui.quotes.all.AllQuotesView

interface AllQuotesPresenter : BasePresenter<AllQuotesView> {

  fun viewReady()

  fun getAllQuotes()

  fun onFavoriteButtonTapped(quote: Quote)
}