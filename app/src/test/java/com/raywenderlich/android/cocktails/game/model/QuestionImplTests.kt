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

import org.junit.Assert.*
import org.junit.Test

class QuestionImplTests {

    private var question = QuestionImpl("CORRECT", "INCORRECT")

    @Test
    fun `new question does not have answered option`() {
        assertNull(question.answeredOption)
    }

    @Test
    fun `new question returns options in reversed order`() {
        val options = question.getOptions { it.reversed() }

        assertEquals(listOf("INCORRECT", "CORRECT"), options)
    }

    @Test
    fun `new question returns options in shuffled order`() {
        val options = question.getOptions { it.shuffled() }

        assertTrue(options.containsAll(listOf("INCORRECT", "CORRECT")))
    }

    @Test
    fun `answered question has answered option`() {
        question.answer("INCORRECT")

        assertEquals("INCORRECT", question.answeredOption)
    }

    @Test
    fun `correct answer returns true`() {
        val result = question.answer("CORRECT")

        assertTrue(result)
    }

    @Test
    fun `incorrect answer returns false`() {
        val result = question.answer("INCORRECT")

        assertFalse(result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `invalid answer throws exception`() {
        question.answer("INVALID")
    }

}