package com.euphony.project.account_touch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals
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

    @Test
    fun listen_setIsListeningTrue() {
        // Given
        val observer = Observer<Boolean> {}
        val euTxManager = mock(EuphonyManager.getEuTxManager()::class.java)
        val euRxManager = mock(EuphonyManager.getEuRxManager()::class.java)

        try {
            viewModel.isListening.observeForever(observer)

            // When
            viewModel.listen(euTxManager, euRxManager)

            // Then
            assertEquals(viewModel.isListening.value, true)
        } finally {
            viewModel.isListening.removeObserver(observer)
        }
    }

    @Test
    fun listen_setIsListeningFalse() {
        // Given
        val observer = Observer<Boolean> {}
        val euTxManager = mock(EuphonyManager.getEuTxManager()::class.java)
        val euRxManager = mock(EuphonyManager.getEuRxManager()::class.java)

        try {
            viewModel.isListening.observeForever(observer)

            // When
            viewModel.listen(euTxManager, euRxManager)
            viewModel.listen(euTxManager, euRxManager)

            // Then
            assertEquals(viewModel.isListening.value, false)
        } finally {
            viewModel.isListening.removeObserver(observer)
        }
    }

}
