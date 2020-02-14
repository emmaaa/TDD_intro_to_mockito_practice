package com.raywenderlich.android.cocktails.game.model

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers

class ScoreTests {

    @Test
    fun `incrementScore also increments high score when above high score`() {
        val game = Game(emptyList(), 0)

        game.incrementScore()

        assertEquals(1, game.highestScore)
    }

    @Test
    fun `incrementScore does not increment high score when below high score `() {
        val game = Game(emptyList(), 10)

        game.incrementScore()

        assertEquals(10, game.highestScore)
    }

    @Test
    fun `incrementScore increments current score`() {
        val game = Game(emptyList(), 0)

        game.incrementScore()

        assertEquals("Current score should have been 1", 1, game.currentScore)
    }

    @Test
    fun `correct answer increments current score`() {
        val question = mock<Question>()

        whenever(question.answer(ArgumentMatchers.anyString())).thenReturn(true)
        val game = Game(listOf(question))

        game.answer(question, "OPTION")

        assertEquals(1, game.currentScore)
    }

    @Test
    fun `incorrect answer does not increment current score`() {
        val question = mock<Question>()

        whenever(question.answer(ArgumentMatchers.anyString())).thenReturn(false)
        val game = Game(listOf(question))

        game.answer(question, "INCORRECT")

        assertEquals(0, game.currentScore)
    }

}