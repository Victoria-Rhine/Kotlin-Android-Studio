package com.example.android.joker.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.joker.R
import com.example.android.joker.common.onClick
import com.example.android.joker.common.onPageChange
import com.example.android.joker.ui.addQuotes.AddQuotesActivity
import com.example.android.joker.ui.quotes.all.AllQuotesFragment
import com.example.android.joker.ui.quotes.favorite.FavoriteQuotesFragment
import com.example.android.joker.ui.main.pager.MainPagerAdapter
import com.example.android.joker.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  companion object {
    fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initUi()
  }

  private fun initUi() {
    val adapter = MainPagerAdapter(supportFragmentManager)
    adapter.setPages(listOf(AllQuotesFragment(), FavoriteQuotesFragment(), ProfileFragment()))

    mainPager.adapter = adapter
    mainPager.offscreenPageLimit = 3
    bottomNavigation.setOnNavigationItemSelectedListener {
      switchNavigationTab(it.order)
      true
    }

    mainPager.onPageChange { position ->
      val item = bottomNavigation.menu.getItem(position)

      bottomNavigation.selectedItemId = item.itemId
    }

    addquotes.onClick { startActivity(Intent(this, AddQuotesActivity::class.java)) }
  }

  private fun switchNavigationTab(position: Int) = mainPager.setCurrentItem(position, true)
}