package com.course.geststock.shared.navigation

sealed class Screen(val route: String) {
    data object Stock : Screen("stock")
    data object Cart : Screen("cart")
}
