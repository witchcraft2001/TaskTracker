package com.dmdev.tasktracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.R
import com.dmdev.tasktracker.ui.theme.BaseTheme

@Composable
fun LoadingBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BaseTheme.colors.textWhite)
    ) {
        CircularProgressIndicator(
            color = BaseTheme.colors.primaryBlue,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ErrorBox(
    message: String,
    buttonText: String,
    onReloadClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BaseTheme.colors.textWhite)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_danger),
                contentDescription = null,
                tint = BaseTheme.colors.textRed,
                modifier = Modifier.size(72.dp)
            )
            Text(
                text = message,
                color = BaseTheme.colors.textRed,
                style = BaseTheme.typography.text15M,
                modifier = Modifier.padding(start = 8.dp, top = 24.dp)
            )
            ButtonTextCenter(
                text = buttonText,
                onClick = onReloadClick,
                modifier = Modifier.padding(top = 24.dp)
            )
        }
    }
}

@Composable
fun ButtonTextCenter(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BaseTheme.colors.primaryDarkBlue,
            contentColor = BaseTheme.colors.textWhite,
            disabledBackgroundColor = BaseTheme.colors.primaryDarkBlue,
            disabledContentColor = BaseTheme.colors.textWhite
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            color = BaseTheme.colors.textWhite,
            style = BaseTheme.typography.text15M,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp)
        )
    }
}

@Composable
fun ToolbarTextWithBackAndActionButton(
    modifier: Modifier = Modifier.padding(16.dp),
    title: String,
    actionIcon: @Composable (() -> Unit)? = null,
    onBack: () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "Back Icon",
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = title,
            textAlign = TextAlign.Center,
            color = BaseTheme.colors.textBlack,
            style = BaseTheme.typography.title2,
            modifier = Modifier.align(Alignment.Center)
        )
        if (actionIcon != null) {
            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                actionIcon()
            }
        }
    }
}

@Composable
fun ToolbarTextWithBack(
    modifier: Modifier = Modifier.padding(16.dp),
    contentColor: Color = BaseTheme.colors.textBlack,
    title: String,
    onBack: () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "Back Icon",
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = title,
            textAlign = TextAlign.Center,
            color = contentColor,
            style = BaseTheme.typography.title2,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

//@Composable
//fun ToolbarTextWithActionButton(
//    title: String,
//    actionIcon: @Composable (() -> Unit)? = null
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(
//            text = title,
//            textAlign = TextAlign.Center,
//            color = BaseTheme.colors.textBlack,
//            style = BaseTheme.typography.title2,
//            modifier = Modifier.align(Alignment.Center)
//        )
//        if (actionIcon != null) {
//            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
//                actionIcon()
//            }
//        }
//    }
//}

@Composable
fun ToolbarTextWithActionButton(
    title: String,
    menuIcon: @Composable (() -> Unit)? = null,
    actionIcon: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (menuIcon != null) {
            Box(modifier = Modifier.align(Alignment.CenterStart)) {
                menuIcon()
            }
        }
        Text(
            text = title,
            textAlign = TextAlign.Center,
            color = BaseTheme.colors.textBlack,
            style = BaseTheme.typography.title2,
            modifier = Modifier.align(Alignment.Center)
        )
        if (actionIcon != null) {
            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                actionIcon()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputField(
    modifier: Modifier,
    value: String,
    label: String,
    hint: String,
    isError: Boolean = false,
    click: (() -> Unit)? = null,
    errorText: String = "",
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    trailingText: String = "",
    trailingTint: Color = BaseTheme.colors.textRed,
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier.clickable { if (click != null) click() }) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = label,
                color = BaseTheme.colors.textDarkGray,
                style = BaseTheme.typography.text15,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            if (trailingText.isNotEmpty()) {
                Text(
                    text = trailingText,
                    color = trailingTint,
                    style = BaseTheme.typography.text15,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = LocalTextStyle.current.copy(
                fontSize = BaseTheme.typography.text15.fontSize,
                fontWeight = BaseTheme.typography.text15.fontWeight
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = BaseTheme.colors.textBlack,
                cursorColor = BaseTheme.colors.primaryDarkBlue,
                backgroundColor = BaseTheme.colors.bgGray,
                disabledTextColor = BaseTheme.colors.textDarkGray,
                disabledBorderColor = BaseTheme.colors.bgGray,
                focusedBorderColor = BaseTheme.colors.primaryLightBlue,
                unfocusedBorderColor = BaseTheme.colors.bgGray,
                errorBorderColor = BaseTheme.colors.textRed,
                errorCursorColor = BaseTheme.colors.textRed,
                disabledPlaceholderColor = BaseTheme.colors.textGray,
                placeholderColor = BaseTheme.colors.textGray
            ),
            enabled = click == null,
            isError = isError,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            visualTransformation = visualTransformation,
            placeholder = {
                Text(
                    text = hint,
                    color = BaseTheme.colors.textGray,
                    style = BaseTheme.typography.text15
                )
            },
            singleLine = singleLine,
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        if (isError) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_info_circle),
                    contentDescription = null,
                    tint = BaseTheme.colors.textRed,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = errorText,
                    color = BaseTheme.colors.textRed,
                    style = BaseTheme.typography.text12R,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun SelectedItemWithIcon(
    text: String,
    isSelected: Boolean,
    leadingIcon: @Composable (() -> Unit)? = null,
    click: () -> Unit
) {
    Row(modifier = Modifier
        .clickable { click() }
        .padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            if (leadingIcon != null) {
                Box {
                    leadingIcon()
                }
            }
            Text(
                text = text,
                style = BaseTheme.typography.text15M,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                color = if (!isSelected) BaseTheme.colors.textBlack else BaseTheme.colors.primaryBlue,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        if (isSelected) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                tint = BaseTheme.colors.primaryBlue,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}