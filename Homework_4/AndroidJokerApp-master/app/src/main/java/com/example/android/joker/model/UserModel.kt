package com.example.android.joker.model

data class UserResponse(val id: String = "",
                        val username: String = "",
                        val email: String = "")

data class User(val id: String,
                val username: String,
                val email: String,
                val favoriteJokes: List<Quote> = listOf())