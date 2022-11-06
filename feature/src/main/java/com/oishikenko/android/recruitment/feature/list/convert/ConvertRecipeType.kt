package com.oishikenko.android.recruitment.feature.list.convert

/**
 * レスポンスから取得したレシピの種類を表示する用の日本語に変換する処理
 * @param recipe_type レスポンスから取得するレシピの種類
 * @return 日本語に変換したレシピの種類
 */
fun convertRecipeType(recipe_type: String): String {
    return when(recipe_type) {
        "main_dish" -> "主菜/主食"
        "side_dish" -> "副菜"
        "soup"      -> "スープ"
        else        -> ""
    }
}