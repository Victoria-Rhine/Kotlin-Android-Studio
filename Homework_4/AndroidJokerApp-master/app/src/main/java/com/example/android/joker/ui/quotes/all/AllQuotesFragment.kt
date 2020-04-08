package com.example.android.joker.ui.quotes.all

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.joker.R
import com.example.android.joker.allQuotesPresenter
import com.example.android.joker.model.Quote
import com.example.android.joker.ui.jokes.all.list.QuotesAdapter

import kotlinx.android.synthetic.main.fragment_quotes.*


class AllQuotesFragment : Fragment(), AllQuotesView {

  private val presenter by lazy { allQuotesPresenter() }
  private val adapter by lazy { QuotesAdapter(presenter::onFavoriteButtonTapped) }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_quotes, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
    presenter.setView(this)

    presenter.viewReady()
  }

  override fun addQuotes(quote: Quote) {
    adapter.addQuotes(quote)
    noItems.visibility = if (adapter.itemCount!=0) View.INVISIBLE else View.VISIBLE
  }

  private fun initUi() {
    quotes.layoutManager = LinearLayoutManager(activity)
    quotes.setHasFixedSize(true)
    quotes.adapter = adapter
  }

  override fun showNoDataDescription() {
    noItems.visibility = View.VISIBLE
  }

  override fun hideNoDataDescription() {
    noItems.visibility = View.GONE
  }

  override fun setFavoriteQuotesIds(favoriteQuotesIds: List<String>) = adapter.setFavoriteQuotesIds(favoriteQuotesIds)
}