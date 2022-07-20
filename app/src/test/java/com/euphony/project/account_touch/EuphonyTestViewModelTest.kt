package com.euphony.project.account_touch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EuphonyTestViewModelTest {

    private lateinit var viewModel: EuphonyTestViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        viewModel = EuphonyTestViewModel()
    }

    @Test
    fun speak_setIsSpeakTrue() {
        // Given

        // When
        viewModel.speak()

        // Then
        assertEquals(viewModel.isSpeaking.value, true)
    }

    @Test
    fun speak_setIsSpeakFalse() {
        // Given

        // When
        viewModel.speak() // true
        viewModel.speak() // false

        // Then
        assertEquals(viewModel.isSpeaking.value, false)
    }
}
