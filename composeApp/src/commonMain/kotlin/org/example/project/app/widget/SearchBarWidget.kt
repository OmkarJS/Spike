package org.example.project.app.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.example.project.app.components.percentOfScreenWidth
import org.example.project.presentation.theme.ThemeColors

@Composable
fun SearchBarWidget(
    searchText: String,
    onValueChange: (String) -> Unit,
    onCloseSearchBar: () -> Unit,
    onSearchClick: () -> Unit,
    colors: ThemeColors
) {
    val focusRequester = remember { FocusRequester() }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = searchText, selection = TextRange(searchText.length)))
    }

    LaunchedEffect(searchText) {
        if (searchText != textFieldValue.text) {
            textFieldValue = TextFieldValue(
                text = searchText,
                selection = TextRange(searchText.length)
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onValueChange(it.text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .focusRequester(focusRequester)
            .clip(RoundedCornerShape(40.dp)),
        shape = RoundedCornerShape(40.dp),
        placeholder = { Text("Search...") },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Close Search",
                tint = colors.grey,
                modifier = Modifier
                    .padding(start = percentOfScreenWidth(2))
                    .clickable {
                        onCloseSearchBar()
                    }
            )
        },
        trailingIcon = {
            Row(
                modifier = Modifier.padding(end = percentOfScreenWidth(5)),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (searchText.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = colors.grey,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onSearchClick() }
                    )

                    Spacer(modifier = Modifier.width(percentOfScreenWidth(1)))

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear Text",
                        tint = colors.grey,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onValueChange("") }
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colors.background,
            cursorColor = colors.primary,
            focusedIndicatorColor = colors.grey,
            unfocusedIndicatorColor = colors.grey,
            textColor = colors.black
        )
    )
}