package com.example.composenotesapp

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
}