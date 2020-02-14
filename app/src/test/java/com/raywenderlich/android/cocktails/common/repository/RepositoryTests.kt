package com.raywenderlich.android.cocktails.common.repository

import android.content.SharedPreferences
import com.nhaarman.mockitokotlin2.*
import com.raywenderlich.android.cocktails.common.network.CocktailsApi
import org.junit.Test

class RepositoryUnitTests {

    @Test
    fun `high score is stored to preferences`() {
        val api: CocktailsApi = mock()
        val sharedPreferencesEditor: SharedPreferences.Editor = mock()
        val sharedPreferences: SharedPreferences = mock()
        val repository = CocktailsRepositoryImpl(api, sharedPreferences)
        val score = 100
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)

        repository.saveHighScore(score)

        inOrder(sharedPreferencesEditor) {
            verify(sharedPreferencesEditor).putInt(any(), eq(score))
            verify(sharedPreferencesEditor).apply()
        }
    }
}