package dev.gico.hidratado.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material3.Icon
import dev.gico.hidratado.data.MenuHeader
import dev.gico.hidratado.data.MenuItem
import kotlinx.coroutines.launch

@Composable
fun ChipList(menuHeader: MenuHeader, menuItems: MutableList<MenuItem>) {
    val scalingLazyListState = rememberScalingLazyListState()

    Scaffold(
        Modifier.background(MaterialTheme.colors.background),
        positionIndicator = {
            PositionIndicator(scalingLazyListState = scalingLazyListState)
        }
    ) {
        val focusRequester = remember {
            FocusRequester()
        }

        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        ScalingLazyColumn(
            modifier = Modifier
                .onRotaryScrollEvent {
                    coroutineScope.launch {
                        scalingLazyListState.animateScrollBy(it.verticalScrollPixels)
                    }

                    true
                }
                .focusRequester(focusRequester)
                .focusable(),
            state = scalingLazyListState,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                ListHeader {
                    Text(
                        text = menuHeader.title,
                        color = MaterialTheme.colors.secondary
                    )
                }
            }

            items(menuItems) { item ->
                Chip(
                    onClick = item.onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    enabled = true,
                    label = {
                        Text(
                            text = item.label,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    secondaryLabel = {
                        item.secondaryLabel?.let {
                            Text(
                                text = it,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    },
                    icon = {
                        item.icon?.let {
                            Icon(
                                painter = painterResource(id = it),
                                contentDescription = item.label,
                                modifier = Modifier
                                    .size(ChipDefaults.IconSize)
                                    .wrapContentSize(align = Alignment.Center),
                                tint = Color.Unspecified
                            )
                        }
                    }
                )
            }
        }
    }
}
