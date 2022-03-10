package com.ravnnerdery.starwarschallenge.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ravnnerdery.music_player_android_12.R

private var sfProFamily = FontFamily(
    Font(R.font.sfprodisplayregular),
    Font(R.font.sfprodisplaymedium, FontWeight.W500),
    Font(R.font.sfprodisplaybold, FontWeight.SemiBold)
)


val ApplicationTypography = Typography(
    h4 = TextStyle(
        fontFamily = sfProFamily,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = sfProFamily,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp
    ),
    h6 = TextStyle(
        fontFamily = sfProFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = sfProFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = sfProFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = sfProFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = sfProFamily,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = sfProFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = sfProFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = sfProFamily,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)