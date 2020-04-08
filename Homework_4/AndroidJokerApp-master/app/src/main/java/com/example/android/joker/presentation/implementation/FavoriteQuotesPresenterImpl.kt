package com.example.android.joker.presentation.implementation

import com.example.android.joker.firebase.authentication.FirebaseAuthenticationInterface
import com.example.android.joker.firebase.database.FirebaseDatabaseInterface
import com.example.android.joker.model.Quote
import com.example.android.joker.presentation.FavoriteQuotesPresenter
import com.example.android.joker.ui.quotes.favorite.FavoriteView
import javax.inject.Inject

class FavoriteQuotesPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : FavoriteQuotesPresenter {

  private lateinit var view: FavoriteView

  override fun setView(view: FavoriteView) {
    this.view = view
  }

  override fun getFavoriteQuotes() {
    val id = authenticationInterface.getUserId()

    databaseInterface.getFavoriteQuotes(id) {
      it.forEach { it.isFavorite = true }

      displayItems(it)
    }
  }

  private fun displayItems(items: List<Quote>) {
    if (items.isEmpty()) {
      view.clearItems()
      view.showNoDataDescription()
    } else {
      view.hideNoDataDescription()
      view.showFavoriteQuotes(items)
    }
  }

  override fun onFavoriteButtonTapped(quote: Quote) {
    databaseInterface.changeQuotesFavoriteStatus(quote, authenticationInterface.getUserId())
  }
}