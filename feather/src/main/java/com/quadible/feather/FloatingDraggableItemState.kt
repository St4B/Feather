package com.quadible.feather

import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

data class FloatingDraggableItemState(
    val contentSize: IntSize = IntSize(width = 0, height = 0),
    val containerSize: IntSize = IntSize(width = 0, height = 0),
    val offset: IntOffset = IntOffset(x = 0, y = 0),
) {

    val dragAreaSize: IntSize
        get() = IntSize(
            width = containerSize.width - contentSize.width,
            height = containerSize.height - contentSize.height,
        )
}

internal fun MutableState<FloatingDraggableItemState>.updateContainerSize(size: IntSize) {
    value = value.copy(containerSize = size)
}

internal fun MutableState<FloatingDraggableItemState>.updateContentSizeSize(
    size: IntSize,
    initialOffset: (FloatingDraggableItemState) -> IntOffset,
) {
    val wasNotRenderedBefore = size.isNotEmpty() && value.contentSize.isEmpty()
    val offset = if (wasNotRenderedBefore) {
        val stateWithUpdatedSize = value.copy(contentSize = size)
        initialOffset(stateWithUpdatedSize)
    } else {
        value.offset
    }

    value = value.copy(contentSize = size, offset = offset)
}

private fun IntSize.isEmpty(): Boolean = this == IntSize(width = 0, height = 0)

private fun IntSize.isNotEmpty(): Boolean = !isEmpty()

internal fun MutableState<FloatingDraggableItemState>.updateOffset(offset: IntOffset) {
    value = value.copy(offset = offset)
}
