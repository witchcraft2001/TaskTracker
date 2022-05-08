package com.dmdev.tasktracker.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.R

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
            modifier = Modifier.align(Alignment.Center).padding(16.dp)
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
    title: String,
    actionIcon: @Composable (() -> Unit)? = null,
    onBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
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
fun ToolbarTextWithActionButton(
    title: String,
    actionIcon: @Composable (() -> Unit)? = null
) {
    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
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