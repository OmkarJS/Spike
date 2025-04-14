package org.example.project.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.example.project.presentation.components.ExtraSmallSpacer
import org.example.project.presentation.components.ThemeColors

@Composable
fun SearchBar(
    query: String = "",
    onQueryChange: (String) -> Unit = { query -> "" },
    modifier: Modifier = Modifier,
    placeholder: String = "",
    themeColors: ThemeColors
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                themeColors.white,
                shape = RoundedCornerShape(50.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .border(
                    width = 0.1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(50.dp)
                ),
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            shape = RoundedCornerShape(50.dp), // Set rounded corners
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = themeColors.black,
                focusedBorderColor = themeColors.black.copy(alpha = 0.6f),
                unfocusedBorderColor = Color.Transparent,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            },
            trailingIcon = {
                /*if (query.isNotEmpty()) {
                    ClearIconButton {
                        onQueryChange("")
                        focusManager.clearFocus() // Close the keyboard
                    }
                }*/
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() } )
        )
    }
}

@Composable
fun EdittextField (
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    allowedPattern: Regex,
    maxLength: Int,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
    themeColors: ThemeColors
) {
    ExtraSmallSpacer()

    val placeholderText = /*StringDesc.Resource(MR) + " " +*/ label
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }
    var hasBeenFocused by remember { mutableStateOf(false) }
    val showError = hasBeenFocused && !isFocused && errorText != null

    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            // Filter out any characters that do not match the allowed pattern
            val filtered = input.filter { it.toString().matches(allowedPattern) }
            // Enforce the maximum length
            val limited = if (filtered.length > maxLength) filtered.take(maxLength) else filtered
            if (limited != value) {
                onValueChange(limited)
            }
        },
        placeholder = { Text(text = placeholderText) },
        modifier = modifier.onFocusChanged { focusState ->
            isFocused = focusState.isFocused
            if (!focusState.isFocused) {
                hasBeenFocused = true
            }
        },
       /* colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (showError) colorResource(id = R.color.error_red)
            else colorResource(id = R.color.semi_light_gray),
            focusedBorderColor = if (showError) colorResource(id = R.color.error_red)
            else colorResource(id = R.color.light_gray),
            containerColor = colorResource(id = R.color.transparent),
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorCursorColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error
        ),*/
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) }),
        isError = showError
    )

    if (showError && errorText != null) {
        /*Text(
            text = errorText,
            color = MaterialTheme.colorScheme.error,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = percentOfScreenWidth(2), top = 1.dp)
        )*/
    }

    ExtraSmallSpacer()
}

@Composable
fun simpleEdittextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    width: Float = 0.9f,
) {
    OutlinedTextField (
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text("YouTube URL") },
        placeholder = { Text("https://www.youtube.com/watch?v=...") },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { }
        ),
        modifier = Modifier.fillMaxWidth(width)
    )
}
