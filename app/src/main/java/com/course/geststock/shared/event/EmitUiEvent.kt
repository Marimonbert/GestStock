package com.course.geststock.shared.event

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay

suspend fun <T> Channel<T>.emitWithDelay(
    value: T,
    delay: Long = 500L
) {
    delay(delay)
    send(value)
}

suspend fun Channel<UiEvent>.emitUiEvent(
    value: UiEvent,
    delay: Long = 500L
) {
    emitWithDelay(value, delay)
}