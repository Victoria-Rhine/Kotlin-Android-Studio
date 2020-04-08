package com.example.android.joker.firebase.database

import com.google.firebase.database.*
import com.example.android.joker.model.*
import javax.inject.Inject

private const val KEY_USER = "user"
private const val KEY_QUOTE = "quote"
private const val KEY_FAVORITE = "favorite"

class FirebaseDatabaseManager @Inject constructor(private val database: FirebaseDatabase) : FirebaseDatabaseInterface {

  override fun createUser(id: String, name: String, email: String) {
    val user = User(id, name, email)

    database.reference.child(KEY_USER).child(id).setValue(user)
  }

  override fun listenToQuotes(onQuotesAdded: (Quote) -> Unit) {
    database.reference.child(KEY_QUOTE)
        .orderByKey()
        .addChildEventListener(object : ChildEventListener {
          override fun onCancelled(p0: DatabaseError?) = Unit
          override fun onChildMoved(p0: DataSnapshot?, p1: String?) = Unit
          override fun onChildChanged(p0: DataSnapshot?, p1: String?) = Unit
          override fun onChildRemoved(p0: DataSnapshot?) = Unit

          override fun onChildAdded(snapshot: DataSnapshot?, p1: String?) {
            snapshot?.getValue(QuotesResponse::class.java)?.run {
              if (isValid()) {
                onQuotesAdded(mapToQuote())
              }
            }
          }
        })
  }

  override fun addNewQuotes(quote: Quote, onResult: (Boolean) -> Unit) {
    val newQuotesReference = database.reference.child(KEY_QUOTE).push()
    val newQuote = quote.copy(id = newQuotesReference.key)

    newQuotesReference.setValue(newQuote).addOnCompleteListener { onResult(it.isSuccessful && it.isComplete) }
  }

  override fun getFavoriteQuotes(userId: String, onResult: (List<Quote>) -> Unit) {
    database.reference
        .child(KEY_USER)
        .child(userId)
        .child(KEY_FAVORITE)
        .addValueEventListener(object : ValueEventListener {
          override fun onCancelled(error: DatabaseError?) = onResult(listOf())

          override fun onDataChange(snapshot: DataSnapshot?) {
            snapshot?.run {
              val quotes = children.mapNotNull { it.getValue(QuotesResponse::class.java) }

              onResult(quotes.map(QuotesResponse::mapToQuote))
            }
          }
        })
  }

  override fun changeQuotesFavoriteStatus(quote: Quote, userId: String) {
    val reference = database.reference
        .child(KEY_USER)
        .child(userId)
        .child(KEY_FAVORITE)
        .child(quote.id)

    reference.addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onCancelled(error: DatabaseError?) {}

      override fun onDataChange(snapshot: DataSnapshot?) {
        val oldQuote = snapshot?.getValue(QuotesResponse::class.java)

        if (oldQuote!=null) {
          reference.setValue(null)
        } else {
          reference.setValue(quote)
        }
      }
    })
  }

  override fun getProfile(id: String, onResult: (User) -> Unit) {
    database.reference
        .child(KEY_USER)
        .child(id)
        .addValueEventListener(object : ValueEventListener {
          override fun onCancelled(error: DatabaseError?) = Unit

          override fun onDataChange(snapshot: DataSnapshot?) {
            val user = snapshot?.getValue(UserResponse::class.java)
            val favoriteQuotes = snapshot?.child(KEY_FAVORITE)?.children
                ?.map { it?.getValue(QuotesResponse::class.java) }
                ?.mapNotNull { it?.mapToQuote() }
                ?: listOf()


            user?.run { onResult(User(id, username, email, favoriteQuotes)) }
          }
        })
  }
}