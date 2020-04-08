package com.example.android.joker.presentation

import com.example.android.joker.ui.addQuotes.AddQuotesView

interface AddQuotesPresenter : BasePresenter<AddQuotesView> {

  fun addQuotesTapped()

  fun onQuotesTextChanged(quotesText: String)
}