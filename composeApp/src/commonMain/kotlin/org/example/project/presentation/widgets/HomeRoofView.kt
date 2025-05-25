package org.example.project.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.app.constants.Constants
import org.example.project.presentation.components.ThemeColors


@Composable
fun HomeRoofView (
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit,
    colors: ThemeColors
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = Constants.Spike.APP_NAME,
                fontSize = 24.sp,
                color = colors.primary,
                fontWeight = FontWeight.Medium
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon (
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = colors.white,
                modifier = Modifier.clickable { onSearchClick() }
            )

            Spacer(modifier = Modifier.padding(horizontal = 6.dp))

            Image(
                painter = rememberVectorPainter(image = Icons.Filled.Face),
                contentDescription = "Person icon",
                modifier = Modifier.size(40.dp).clip(CircleShape)
                    .clickable { onProfileClick() }
                    .border(1.5.dp, color = colors.black, CircleShape)
                    .background(colors.background)
            )
        }
    }
}