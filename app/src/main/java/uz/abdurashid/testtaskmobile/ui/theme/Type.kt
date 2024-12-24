package uz.abdurashid.testtaskmobile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import uz.abdurashid.testtaskmobile.R

val robotoFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val suseFamily = FontFamily(
    Font(R.font.suse_light, FontWeight.Light),
    Font(R.font.suse_regular, FontWeight.Normal),
    Font(R.font.suse_semi_bold, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.suse_medium, FontWeight.Medium),
    Font(R.font.suse_bold, FontWeight.Bold),
    Font(R.font.suse_semi_bold, FontWeight.SemiBold)
)

val sansFamily = FontFamily(
    Font(R.font.open_sans_light, FontWeight.Light),
    Font(R.font.open_sans_regular, FontWeight.Normal),
    Font(R.font.open_sans_semi_bold, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.open_sans_medium, FontWeight.Medium),
    Font(R.font.open_sans_bold, FontWeight.Bold),
    Font(R.font.open_sans_semi_bold, FontWeight.SemiBold)
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)