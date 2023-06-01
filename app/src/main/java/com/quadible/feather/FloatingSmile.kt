package com.quadible.feather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun FloatingSmile(
    onClick: () -> Unit,
    visible: Boolean,
) {
    FloatingDraggableItem(
        initialOffset = { state ->
            IntOffset(
                x = (state.containerSize.width - state.contentSize.width) / 2,
                y = state.containerSize.height - 2 * state.contentSize.height,
            )
        }
    ) { state ->
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically { state.containerSize.height },
            exit = slideOut { IntOffset(-state.offset.x - state.contentSize.width, 0) },
        ) {

            Image(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .shadow(elevation = 8.dp, shape = CircleShape)
                    .size(size = 64.dp)
                    .clip(shape = CircleShape)
                    .clickable(onClick = onClick),
                painter = painterResource(id = R.drawable.smile),
                contentDescription = null,
            )
        }
    }
}
