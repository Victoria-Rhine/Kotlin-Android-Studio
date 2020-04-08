package com.example.android.joker.presentation.implementation
import com.example.android.joker.common.isValidQuote
import com.example.android.joker.firebase.authentication.FirebaseAuthenticationInterface
import com.example.android.joker.firebase.database.FirebaseDatabaseInterface
import com.example.android.joker.model.Quote
import com.example.android.joker.presentation.AddQuotesPresenter
import com.example.android.joker.ui.addQuotes.AddQuotesView
import javax.inject.Inject

class AddQuotesPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : AddQuotesPresenter {

  private lateinit var view: AddQuotesView

  private var quotesText = ""

  override fun setView(view: AddQuotesView) {
    this.view = view
  }

  override fun addQuotesTapped() {
    if (isValidQuote(quotesText)) {
      val quote = Quote("", authenticationInterface.getUserName(), authenticationInterface.getUserId(), quotesText)

      databaseInterface.addNewQuotes(quote) { onAddQuotesResult(it) }
    }
  }

  override fun onQuotesTextChanged(quotesText: String) {
    this.quotesText = quotesText

    if (!isValidQuote(quotesText)) {
      view.showQuotesError()
    } else {
      view.removeQuotesError()
    }
  }

  private fun onAddQuotesResult(isSuccessful: Boolean) {
    if (isSuccessful) {
      view.onQuotesAdded()
    } else {
      view.showAddQuotesError()
    }
  }
}