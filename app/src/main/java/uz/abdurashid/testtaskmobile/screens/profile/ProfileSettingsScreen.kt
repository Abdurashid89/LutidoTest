package uz.abdurashid.testtaskmobile.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.abdurashid.testtaskmobile.ui.theme.Color_Bottom_Nav
import uz.abdurashid.testtaskmobile.ui.theme.Edit_Bottom_Color
import uz.abdurashid.testtaskmobile.ui.theme.Favourite_Back_Color
import uz.abdurashid.testtaskmobile.ui.theme.robotoFamily
import uz.abdurashid.testtaskmobile.ui.theme.suseFamily

@Preview(showBackground = true)
@Composable
fun Profile_Preview(modifier: Modifier = Modifier) {
    val item1 = SettingsItem("Language", "Select language", Icons.Default.Edit)
    val item2 = SettingsItem("Support", "Get help", Icons.Default.ThumbUp)
    val item3 = SettingsItem("Share", "Share to friends", Icons.Default.Share)
    val list = listOf(item1, item2, item3)
    ProfileSettingsScreen("Abdurashid", "abubabu892@gmail.com",
        onEditProfileClick = {},
        settingsItems = list,
        {

        })
}

@Composable
fun ProfileSettingsScreen(
    userName: String,
    userEmail: String,
    onEditProfileClick: () -> Unit,
    settingsItems: List<SettingsItem>,
    onSettingClick: (SettingsItem) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .background(Color_Bottom_Nav)
        ) {
            // Profile Section
            item {
                ProfileSection(
                    userName = userName,
                    userEmail = userEmail,
                    onEditProfileClick = onEditProfileClick
                )
            }

            // Divider
            item { Divider(modifier = Modifier.padding(vertical = 8.dp)) }

            // Settings Items
            items(settingsItems) { item ->
                SettingsListItem(
                    item = item,
                    onClick = { onSettingClick(item) }
                )
            }
        }
    }
}

@Composable
fun ProfileSection(userName: String, userEmail: String, onEditProfileClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Favourite_Back_Color)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Icon
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )

            // User Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    fontFamily = robotoFamily)
                Text(text = userEmail,
                    style = MaterialTheme
                        .typography.bodyMedium,
                    fontFamily = suseFamily,
                    color = Color.Black)
            }

            // Edit Profile Button
            Button(
                colors = androidx.compose.material.ButtonDefaults
                    .buttonColors(Edit_Bottom_Color),
                onClick = onEditProfileClick
            ) {
                Text("Edit")
            }
        }
    }
}

@Composable
fun SettingsListItem(item: SettingsItem, onClick: () -> Unit) {
    ListItem(
        colors = ListItemDefaults.colors(
            containerColor = Color.White,
            leadingIconColor = Color.Black,
            headlineColor = Color.Black,
            overlineColor = Color.Black,
            trailingIconColor = Color.Black,
            disabledHeadlineColor = Color.Black,
            disabledLeadingIconColor = Color.Black,
            disabledTrailingIconColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        headlineContent = { Text(item.title) },
        supportingContent = item.subtitle?.let { { Text(it) } },
        leadingContent = item.icon?.let { { Icon(it, contentDescription = null) } },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Go to setting",
                modifier = Modifier.size(24.dp)
            )
        }
    )
}

data class SettingsItem(
    val title: String,
    val subtitle: String? = null,
    val icon: ImageVector? = null,
)
