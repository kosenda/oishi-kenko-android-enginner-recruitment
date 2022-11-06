package com.oishikenko.android.recruitment.feature.list.convert

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ConvertRecipeTypeKtTest {
    @Test
    fun give_main_dish_return_not_empty() {
        assert("" != convertRecipeType("main_dish"))
    }
    @Test
    fun give_side_dish_return_not_empty() {
        assert("" != convertRecipeType("side_dish"))
    }
    @Test
    fun give_soup_return_not_empty() {
        assert("" != convertRecipeType("soup"))
    }
    @Test
    fun give_not_exist_recipe_return_empty() {
        assert("" == convertRecipeType("not_exist_recipe"))
    }
}