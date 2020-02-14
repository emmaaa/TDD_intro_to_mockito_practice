/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.cocktails.game.model

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.*
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class GameUnitTests {

    private val question1 = Question("CORRECT", "INCORRECT")
    private val questions = listOf(question1)


    @Test
    fun `incrementScore increments current score`() {
        val game = Game(emptyList(), 0)

        game.incrementScore()

        assertEquals("Current score should have been 1", 1, game.currentScore)
    }

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
    fun `nextQuestion returns next question`() {
        val game = Game(questions)

        val nextQuestion = game.nextQuestion()

        assertSame(question1, nextQuestion)
    }

    @Test
    fun `next question returns null when there are no more questions`() {
        val game = Game(questions)

        game.nextQuestion()
        val nextQuestion = game.nextQuestion()

        assertNull(nextQuestion)
    }

    @Test
    fun `given answer is delegated to its question`() {
        val question = mock<Question>()
        val game = Game(listOf(question))

        game.answer(question, "OPTION")

        verify(question).answer(eq("OPTION"))
    }

    @Test
    fun `correct answer increments current score`() {
        val question = mock<Question>()

        whenever(question.answer(anyString())).thenReturn(true)
        val game = Game(listOf(question))

        game.answer(question, "OPTION")

        assertEquals(1, game.currentScore)
    }
}