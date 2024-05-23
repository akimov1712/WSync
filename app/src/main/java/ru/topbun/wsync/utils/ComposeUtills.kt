package ru.topbun.wsync.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

@Composable
fun createFont(resId: Int) = FontFamily(Font(resId))