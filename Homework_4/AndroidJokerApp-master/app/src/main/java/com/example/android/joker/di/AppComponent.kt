package com.example.android.joker.di

import com.example.android.joker.di.module.PresentationModule
import com.example.android.joker.presentation.*
import dagger.Component
import javax.inject.Singleton

@Component(modules = [PresentationModule::class])
@Singleton
interface AppComponent {

  fun registerPresenter(): RegisterPresenter

  fun loginPresenter(): LoginPresenter

  fun allQuotesPresenter(): AllQuotesPresenter

  fun favoriteQuotesPresenter(): FavoriteQuotesPresenter

  fun profilePresenter(): ProfilePresenter

  fun addQuotesPresenter(): AddQuotesPresenter

  fun welcomePresenter(): WelcomePresenter
}