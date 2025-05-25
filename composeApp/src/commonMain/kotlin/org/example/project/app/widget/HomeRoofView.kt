package org.example.project.app.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.app.constants.Constants
import org.example.project.presentation.theme.ThemeColors

@Composable
fun HomeRoofView (
    onSearchClick: (searchQuery: String) -> Unit,
    onProfileClick: () -> Unit,
    colors: ThemeColors
) {
    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    @Composable
    fun RowScope.DefaultRoofView() {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = Constants.Spike.APP_NAME,
                fontSize = 24.sp,
                color = colors.primary,
                fontWeight = FontWeight.Medium
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = colors.black,
                modifier = Modifier.clickable {
                    isSearching = true
                }
            )

            Spacer(modifier = Modifier.padding(horizontal = 6.dp))

            Image(
                painter = rememberVectorPainter(image = Icons.Filled.Face),
                contentDescription = "Person icon",
                modifier = Modifier.size(40.dp).clip(CircleShape)
                    .clickable { onProfileClick() }
                    .border(1.5.dp, color = colors.black, CircleShape)
                    .background(Color.White)
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if(isSearching) {
            SearchBarWidget(
                searchText,
                onValueChange = {
                    searchText = it
                },
                onCloseSearchBar = {
                    isSearching = false
                    searchText = ""
                },
                onSearchClick = {
                    onSearchClick(searchText)
                    searchText = ""
                    isSearching = false
                },
                colors = colors
            )
        } else {
            DefaultRoofView()
        }
    }
}

