package com.example.android.joker.model

data class QuotesResponse(val id: String = "",
                        val authorName: String = "",
                        val authorId: String = "",
                        val text: String = "")

fun QuotesResponse.isValid() = id.isNotBlank()
    && authorName.isNotBlank()
    && authorId.isNotBlank()
    && text.isNotBlank()

fun QuotesResponse.mapToQuote() = Quote(id, authorName, authorId, text)

data class Quote(val id: String,
                val authorName: String,
                val authorId: String,
                val text: String,
                var isFavorite: Boolean = false)