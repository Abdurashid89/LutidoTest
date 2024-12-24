package uz.abdurashid.testtaskmobile.screens.map.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.abdurashid.testtaskmobile.R
import uz.abdurashid.testtaskmobile.ui.theme.Color_SearchBar


@Preview()
@Composable
fun SearchBar_Preview(modifier: Modifier = Modifier) {
    SearchBar(query = "Поиск") {

    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text("Поиск") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.size(18.dp).clickable {
                            val start = if (query.length > 1) query.lastIndex - 1 else 0
                            val end = if (query.length > 1) query.lastIndex else 0

                            onQueryChange(query.substring(IntRange(end,start)))
                        },
                        painter = painterResource(id = R.drawable.ic_close), contentDescription = "Clear Icon"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color_SearchBar,
                unfocusedIndicatorColor = Color_SearchBar
            ),
            singleLine = true
        )
    }
}