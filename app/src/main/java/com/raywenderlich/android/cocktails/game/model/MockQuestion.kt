package com.raywenderlich.android.cocktails.game.model

class MockQuestion : Question {

    private val correct = "CORRECT"
    private val incorrect = "INCORRECT"

    override fun answer(option: String) = when (option) {
        correct -> true
        incorrect -> false
        else -> throw Exception("MockQuestion.answer() was called with \"$option\". Expected \"CORRECT\" or \"INCORRECT\"")
    }

    override fun getOptions(sort: (List<String>) -> List<String>) = listOf(correct, incorrect)

}
