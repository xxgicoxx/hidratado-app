package dev.gico.hidratado.data.domain

data class MenuItemRadio(var id: Int, var label: String, var checked: Boolean, var onClick: () -> Unit)
