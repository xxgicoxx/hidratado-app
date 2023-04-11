package dev.gico.hidratado.presentation.ui.menu

import androidx.compose.runtime.Composable
import dev.gico.hidratado.data.MenuHeader
import dev.gico.hidratado.data.MenuItem
import dev.gico.hidratado.presentation.components.ChipList

@Composable
fun MainMenu(menuHeader: MenuHeader, menuItems: MutableList<MenuItem>) {
    ChipList(menuHeader, menuItems)
}
