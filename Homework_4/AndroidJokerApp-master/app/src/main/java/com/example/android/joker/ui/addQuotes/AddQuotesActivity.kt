package com.example.android.joker.ui.addQuotes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.joker.R
import com.example.android.joker.addQuotesPresenter
import com.example.android.joker.common.onClick
import com.example.android.joker.common.onTextChanged
import com.example.android.joker.common.showGeneralError
import kotlinx.android.synthetic.main.activity_add_quotes.*

class AddQuotesActivity : AppCompatActivity(), AddQuotesView {

  private val presenter by lazy { addQuotesPresenter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_quotes)
    presenter.setView(this)

    initUi()
  }

  private fun initUi() {
    quotesDescription.onTextChanged { presenter.onQuotesTextChanged(it) }
    addquotes.onClick { presenter.addQuotesTapped() }
  }

  override fun showQuotesError() {
    quotesDescription.error = getString(R.string.quotes_error)
  }

  override fun removeQuotesError() {
    quotesDescription.error = null
  }

  override fun onQuotesAdded() = finish()

  override fun showAddQuotesError() = showGeneralError(this)
}