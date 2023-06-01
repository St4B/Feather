package com.quadible.feather

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun FloatingDraggableItem(
    initialOffset: (FloatingDraggableItemState) -> IntOffset,
    content: @Composable BoxScope.(FloatingDraggableItemState) -> Unit,
) {
    val state = remember { mutableStateOf(FloatingDraggableItemState()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { state.updateContainerSize(size = it.size) },
    ) {
        Box(
            modifier = Modifier
                .offset { state.value.offset }
                .onGloballyPositioned {
                    state.updateContentSizeSize(
                        size = it.size,
                        initialOffset = initialOffset,
                    )
                }
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        val calculatedX = state.value.offset.x + dragAmount.x.roundToInt()
                        val calculatedY = state.value.offset.y + dragAmount.y.roundToInt()

                        val offset = IntOffset(
                            calculatedX.coerceIn(0, state.value.dragAreaSize.width),
                            calculatedY.coerceIn(0, state.value.dragAreaSize.height),
                        )
                        state.updateOffset(offset = offset)
                    }
                },
        ) {
            content(state.value)
        }

        DisposableEffect(true) {
            // Reset position on hide
            onDispose {
                val offset = initialOffset(state.value)
                state.updateOffset(offset = offset)
            }
        }
    }
}
