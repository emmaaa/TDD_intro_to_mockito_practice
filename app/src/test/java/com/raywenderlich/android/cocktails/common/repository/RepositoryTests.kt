package com.raywenderlich.android.cocktails.common.repository

import android.content.SharedPreferences
import com.nhaarman.mockitokotlin2.*
import com.raywenderlich.android.cocktails.common.network.CocktailsApi
import org.junit.Test

class RepositoryUnitTests {

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
    fun `score can be read from preferences`() {
        repository.getHighScore()

        verify(sharedPreferences).getInt(any(), any())
    }

}