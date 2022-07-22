package com.euphony.project.account_touch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

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
        val observer = Observer<Boolean> {}
        val euTxManager = mock(EuphonyManager.getEuTxManager()::class.java)

        try {
            viewModel.isSpeaking.observeForever(observer)

            // When
            viewModel.speak(euTxManager)

            // Then
            assertEquals(viewModel.isSpeaking.value, true)
        } finally {
            viewModel.isSpeaking.removeObserver(observer)
        }
    }

    @Test
    fun speak_setIsSpeakFalse() {
        // Given
        val observer = Observer<Boolean> {}
        val euTxManager = mock(EuphonyManager.getEuTxManager()::class.java)

        try {
            viewModel.isSpeaking.observeForever(observer)

            // When
            viewModel.speak(euTxManager)
            viewModel.speak(euTxManager)

            // Then
            assertEquals(viewModel.isSpeaking.value, false)
        } finally {
            viewModel.isSpeaking.removeObserver(observer)
        }
    }
}
