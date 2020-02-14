package com.raywenderlich.android.cocktails.common.repository

import android.content.SharedPreferences
import com.nhaarman.mockitokotlin2.*
import com.raywenderlich.android.cocktails.common.network.CocktailsApi
import org.junit.Test

class RepositoryTests {

    private val api: CocktailsApi = mock()
    private val sharedPreferences: SharedPreferences = mock()
    private val repository = CocktailsRepositoryImpl(api, sharedPreferences)
    private val sharedPreferencesEditor: SharedPreferences.Editor = mock()
    private val score = 100

    @Test
    fun `high score is stored to preferences`() {
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)

        repository.saveHighScore(score)

        inOrder(sharedPreferencesEditor) {
            verify(sharedPreferencesEditor).putInt(any(), eq(score))
            verify(sharedPreferencesEditor).apply()
        }
    }

    @Test
    fun `high score is read from preferences`() {
        repository.getHighScore()

        verify(sharedPreferences).getInt(any(), any())
    }

    @Test
    fun `score is not saved when lower than high score`() {
        val previouslySavedHighScore = 100
        val newHighScore = 10
        val spyRepository = spy(repository)
        doReturn(previouslySavedHighScore)
                .whenever(spyRepository)
                .getHighScore()

        spyRepository.saveHighScore(newHighScore)

        verify(sharedPreferencesEditor, never())
                .putInt(any(), eq(newHighScore))
    }
}