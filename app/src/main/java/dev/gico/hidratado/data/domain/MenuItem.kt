package dev.gico.hidratado.data.domain

data class MenuItem(var id: Int, var label: String, var onClick: () -> Unit) {

    var secondaryLabel: String? = null
    var icon: Int? = null
}
