package dev.gico.hidratado.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.RadioButton
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChip
import dev.gico.hidratado.data.MenuHeader
import dev.gico.hidratado.data.MenuItemRadio
import kotlinx.coroutines.launch

@Composable
fun ChipRadioButtonList(menuHeader: MenuHeader, menuItems: MutableList<MenuItemRadio>) {
    val scalingLazyListState = rememberScalingLazyListState()

    Scaffold(
        Modifier.background(MaterialTheme.colors.background),
        positionIndicator = {
            PositionIndicator(scalingLazyListState = scalingLazyListState)
        }
    ) {
        var selected: MenuItemRadio? by remember {
            mutableStateOf(menuItems.firstOrNull {
                it.checked
            })
        }

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
                ToggleChip(
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
                    toggleControl = {
                        RadioButton(
                            onClick = item.onClick,
                            selected = selected?.id == item.id,
                            enabled = true,
                            modifier = Modifier.semantics {
                                this.contentDescription = item.label
                            }
                        )
                    },
                    checked = selected?.id == item.id,
                    onCheckedChange = { checked ->
                        item.checked = checked
                        selected = item

                        item.onClick().apply {}
                    }
                )
            }
        }
    }
}
