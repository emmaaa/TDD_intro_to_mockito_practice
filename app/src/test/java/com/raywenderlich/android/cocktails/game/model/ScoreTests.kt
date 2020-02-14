package com.raywenderlich.android.cocktails.game.model

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class ScoreTests {

    private val score = Score()

    @Test
    fun `incrementScore increments current score`() {
        score.increment()

        assertEquals("Current score should have been 1", 1, score.current)
    }

    @Test
    fun `incrementScore also increments high score when above high score`() {
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
        val question = mock<Question>()

        whenever(question.answer(anyString())).thenReturn(true)
        val score = mock<Score>()
        val game = Game(listOf(question), score)

        game.answer(question, "OPTION")

        verify(score).increment()
    }

    @Test
    fun `incorrect answer does not increment current score`() {
        val question = mock<Question>()

        whenever(question.answer(anyString())).thenReturn(false)
        val score = mock<Score>()
        val game = Game(listOf(question))

        game.answer(question, "INCORRECT")

        verify(score, never()).increment()
    }

}