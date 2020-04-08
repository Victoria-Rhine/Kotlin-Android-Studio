package com.example.android.joker.firebase.database

import com.example.android.joker.model.Quote
import com.example.android.joker.model.User

interface FirebaseDatabaseInterface {

  fun listenToQuotes(onQuotesAdded: (Quote) -> Unit)

  fun addNewQuotes(quote: Quote, onResult: (Boolean) -> Unit)

  fun getFavoriteQuotes(userId: String, onResult: (List<Quote>) -> Unit)

  fun changeQuotesFavoriteStatus(quote: Quote, userId: String)

  fun createUser(id: String, name: String, email: String)

  fun getProfile(id: String, onResult: (User) -> Unit)
}