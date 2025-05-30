package org.example.project.app.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TopBarWidget(
    title: String,
    actionItemIcon: ImageVector? = null,
    actionItemClick: () -> Unit = {},
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    titleColor: Color = Color.Black,
    iconColor: Color = Color.Black,
    backgroundColor: Color = Color.Transparent
) {
    TopAppBar(
        title = {
            Text(
                text = title.uppercase(),
                fontWeight = FontWeight.Bold,
                color = titleColor,
                fontSize = 18.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = iconColor
                )
            }
        },
        actions = {
            actionItemIcon?.let {
                IconButton(onClick = actionItemClick) {
                    Icon(
                        imageVector = it,
                        contentDescription = "ActionItem",
                        tint = iconColor
                    )
                }
            }
        },
        backgroundColor = backgroundColor,
        contentColor = iconColor,
        modifier = modifier
    )
}