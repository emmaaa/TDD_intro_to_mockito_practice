package com.raywenderlich.android.cocktails.game.model

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreTests {

    private val question = MockQuestion()

    @Test
    fun `incrementScore increments current score`() {
        val score = Score()
        score.increment()

        assertEquals("Current score should have been 1", 1, score.current)
    }

    @Test
    fun `incrementScore also increments high score when above high score`() {
        val score = Score()
        score.increment()

        assertEquals(1, score.highest)
    }

    @Test
    fun `incrementScore does not increment high score when below high score `() {
        val score = Score(10)

        score.increment()

        assertEquals(10, score.highest)
    }

    @Test
    fun `correct answer increments current score`() {
        val score = mock<Score>()
        val game = Game(listOf(question), score)

        game.answer(question, "CORRECT")

        verify(score).increment()
    }

    @Test
    fun `incorrect answer does not increment current score`() {
        val score = mock<Score>()
        val game = Game(listOf(question))

        game.answer(question, "INCORRECT")

        verify(score, never()).increment()
    }

}
