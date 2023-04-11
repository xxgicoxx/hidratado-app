package dev.gico.hidratado.presentation.ui.time

import androidx.compose.runtime.Composable
import dev.gico.hidratado.data.MenuHeader
import dev.gico.hidratado.data.MenuItemRadio
import dev.gico.hidratado.presentation.components.ChipRadioButtonList

@Composable
fun TimerOptions(menuHeader: MenuHeader, menuItems: MutableList<MenuItemRadio>) {
    ChipRadioButtonList(menuHeader, menuItems)
}
