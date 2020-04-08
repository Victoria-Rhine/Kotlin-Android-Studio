package com.example.android.joker.ui.quotes.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.joker.R
import com.example.android.joker.favoriteQuotesPresenter
import com.example.android.joker.model.Quote
import com.example.android.joker.ui.quotes.favorite.list.FavoriteQuotesAdapter
import kotlinx.android.synthetic.main.fragment_quotes.*

class FavoriteQuotesFragment : Fragment(), FavoriteView {

  private val presenter by lazy { favoriteQuotesPresenter() }
  private val adapter by lazy { FavoriteQuotesAdapter(presenter::onFavoriteButtonTapped) }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_quotes, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.setView(this)
    initUi()

    presenter.getFavoriteQuotes()
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

  override fun clearItems() = adapter.clear()

  override fun showFavoriteQuotes(quote: List<Quote>) = adapter.setData(quote)
}