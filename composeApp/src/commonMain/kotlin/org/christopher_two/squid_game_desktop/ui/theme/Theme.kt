package org.christopher_two.squid_game_desktop.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import squid_game_desktop.composeapp.generated.resources.Game_Of_Squids
import squid_game_desktop.composeapp.generated.resources.Res

val DarkColorScheme: ColorScheme = darkColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    background = background
)

@Composable
fun TitleFont(): TextStyle = TextStyle(
    fontFamily = FontFamily(Font(Res.font.Game_Of_Squids)),
    fontSize = 32.sp,
    fontWeight = FontWeight.Bold,
)

@Composable
fun MaterialThemeApp(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content,
        typography = MaterialTheme.typography.copy(
            titleLarge = TitleFont()
        )
    )
}