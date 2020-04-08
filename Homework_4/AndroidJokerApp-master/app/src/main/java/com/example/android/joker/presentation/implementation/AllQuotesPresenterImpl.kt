package com.example.android.joker.presentation.implementation

import com.example.android.joker.firebase.authentication.FirebaseAuthenticationInterface
import com.example.android.joker.firebase.database.FirebaseDatabaseInterface
import com.example.android.joker.model.Quote
import com.example.android.joker.presentation.AllQuotesPresenter
import com.example.android.joker.ui.quotes.all.AllQuotesView

import javax.inject.Inject

class AllQuotesPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : AllQuotesPresenter {

  private lateinit var view: AllQuotesView

  override fun setView(view: AllQuotesView) {
    this.view = view
  }

  override fun viewReady() {
    databaseInterface.getFavoriteQuotes(authenticationInterface.getUserId()) { onFavoriteQuotesResult(it) }
    getAllQuotes()
  }

  private fun onFavoriteQuotesResult(favoriteQuotes: List<Quote>) = view.setFavoriteQuotesIds(favoriteQuotes.map { it.id })

  override fun getAllQuotes() = databaseInterface.listenToQuotes { view.addQuotes(it) }

  override fun onFavoriteButtonTapped(quote: Quote) {
    databaseInterface.changeQuotesFavoriteStatus(quote, authenticationInterface.getUserId())
  }
}

