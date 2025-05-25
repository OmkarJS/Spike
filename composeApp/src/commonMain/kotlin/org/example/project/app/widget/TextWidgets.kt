package org.example.project.app.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import org.example.project.presentation.theme.LocalAppColors

@Composable
fun Small1Text(
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = LocalAppColors.current.black,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontSize = 10.sp,
        color = textColor,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun Small2Text(
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = LocalAppColors.current.black,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontSize = 12.sp,
        color = textColor,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun Small3Text(
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = LocalAppColors.current.black,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontSize = 14.sp,
        color = textColor,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun Medium1Text(
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = LocalAppColors.current.black,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontSize = 16.sp,
        color = textColor,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun Medium2Text(
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = LocalAppColors.current.black,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontSize = 18.sp,
        color = textColor,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun Medium3Text(
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textColor: Color = LocalAppColors.current.black,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontSize = 20.sp,
        color = textColor,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun Large1Text(
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = LocalAppColors.current.black,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontSize = 22.sp,
        color = textColor,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun Large2Text(
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = LocalAppColors.current.black,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontSize = 24.sp,
        color = textColor,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun Large3Text(
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textColor: Color = LocalAppColors.current.black,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontSize = 26.sp,
        color = textColor,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
