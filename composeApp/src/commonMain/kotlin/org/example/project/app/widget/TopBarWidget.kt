package org.example.project.app.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.example.project.app.components.percentOfScreenHeight
import org.example.project.presentation.theme.LocalAppColors

@Composable
fun TopBarWidget(
    title: String,
    actionItemIcon: ImageVector? = null,
    actionItemClick: () -> Unit = {},
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    titleColor: Color = LocalAppColors.current.black,
    iconColor: Color = LocalAppColors.current.black,
    backgroundColor: Color = Color.Transparent
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .padding(bottom = percentOfScreenHeight(1))
        .height(percentOfScreenHeight(5))
        .background(backgroundColor)) {

        Text(
            text = title.uppercase(),
            modifier = Modifier
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = titleColor,
            fontSize = 18.sp
        )

        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = iconColor
            )
        }

        actionItemIcon?.let {
            IconButton(
                onClick = actionItemClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = it,
                    contentDescription = "ActionItem",
                    tint = iconColor
                )
            }
        }
    }
}