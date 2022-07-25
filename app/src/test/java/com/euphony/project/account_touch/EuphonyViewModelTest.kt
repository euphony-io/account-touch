package com.euphony.project.account_touch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.euphony.project.account_touch.euphony.EuphonyManager
import com.euphony.project.account_touch.euphony.EuphonyViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class EuphonyViewModelTest {

    private lateinit var viewModel: EuphonyViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        viewModel = EuphonyViewModel()
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
        val euRxManager = mock(EuphonyManager.getEuRxManager()::class.java)

        try {
            viewModel.isListening.observeForever(observer)

            // When
            viewModel.listen(euRxManager)

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
        val euRxManager = mock(EuphonyManager.getEuRxManager()::class.java)

        try {
            viewModel.isListening.observeForever(observer)

            // When
            viewModel.listen(euRxManager)
            viewModel.listen(euRxManager)

            // Then
            assertEquals(viewModel.isListening.value, false)
        } finally {
            viewModel.isListening.removeObserver(observer)
        }
    }

}