package com.oishikenko.android.recruitment.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.oishikenko.android.recruitment.R

val notoSansJP = FontFamily(Font(R.font.noto_sans_jp_regular))

val Typography = Typography(
        h1 = TextStyle(
                fontFamily = notoSansJP,
                fontWeight = FontWeight.Normal,
                fontSize = 96.sp,
        ),
        h2 = TextStyle(
                fontFamily = notoSansJP,
                fontWeight = FontWeight.Normal,
                fontSize = 60.sp,
        ),
        h3 = TextStyle(
                fontFamily = notoSansJP,
                fontWeight = FontWeight.Normal,
                fontSize = 48.sp,
        ),
        h4 = TextStyle(
                fontFamily = notoSansJP,
                fontWeight = FontWeight.Normal,
                fontSize = 34.sp,
        ),
        h5 = TextStyle(
                fontFamily = notoSansJP,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
        ),
        h6 = TextStyle(
                fontFamily = notoSansJP,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
        ),
        subtitle1 = TextStyle(
                fontFamily = notoSansJP,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
        ),
        subtitle2 = TextStyle(
                fontFamily = notoSansJP,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
        ),
        body1 = TextStyle(
                fontFamily = notoSansJP,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
        ),
        body2 = TextStyle(
                fontFamily = notoSansJP,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
        )
)