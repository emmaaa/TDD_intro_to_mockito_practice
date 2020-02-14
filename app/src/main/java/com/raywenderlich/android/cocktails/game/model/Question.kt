package com.raywenderlich.android.cocktails.game.model

interface Question {

    fun answer(option: String): Boolean

    fun getOptions(sort: (List<String>) -> List<String>): List<String>

}