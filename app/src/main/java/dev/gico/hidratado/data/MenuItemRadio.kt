package dev.gico.hidratado.data

data class MenuItemRadio(var id: Int, var label: String, var checked: Boolean, var onClick: () -> Unit)
