package uz.abdurashid.testtaskmobile.screens.map.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import uz.abdurashid.testtaskmobile.model.Address
import uz.abdurashid.testtaskmobile.ui.theme.robotoFamily

@Composable
fun DialogEditText(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    readOnly: Boolean = true,
    onClick: () -> Unit = {},

    ) {

    BasicTextField(
        value = value,
        onValueChange = {

        },
        readOnly = readOnly,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 14.sp,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Normal
        )
    )

}

@Composable
fun DialogText(
    padding: Int = 0,
    modifier: Modifier = Modifier,
    title: String,
    color: Color = Color.Black,
    fontSize: Int = 18,
    fontFamily: FontFamily = robotoFamily,
    fontWeight: FontWeight = FontWeight.Normal,
    onClick: () -> Unit = {},

    ) {
    Text(
        modifier = modifier
            .padding(top = padding.dp)
            .clickable { onClick() },
        text = title,
        fontFamily = fontFamily,
        color = color,
        fontSize = fontSize.sp,
        fontWeight = fontWeight
    )
}

@Preview(showBackground = true)
@Composable
fun AddFavouriteDialog_Preview(modifier: Modifier = Modifier) {
    AddFavouriteDialog()
}

@Composable
fun AddFavouriteDialog(
    text: String = "ул. Узбекистон Овози, 2",
    onDismissRequest: () -> Unit = {},
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
) {

    Dialog(onDismissRequest = { }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(175.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                DialogText(
                    padding = 12,
                    fontWeight = FontWeight.Bold,
                    title = "Добавить адрес в избранное"
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    DialogEditText(
                        value = "ул. Узбекистон Овози, 2"
                    )

                    Icon(
                        modifier = Modifier.clickable {
                        },
                        imageVector = Icons.Default.Edit,
                        contentDescription = null
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    DialogText(
                        color = Color(0xFFFB4141),
                        fontWeight = FontWeight.Bold,
                        title = "Отмена",
                        onClick = { onCancel() }
                    )

                    DialogText(
                        fontWeight = FontWeight.Bold,
                        title = "Подтвердить",
                        onClick = { onConfirm() }
                    )

                }
            }
        }
    }
}