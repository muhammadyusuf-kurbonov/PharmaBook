package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Section(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 4.dp)
    ) {
        Box(
            modifier = modifier
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.onBackground.copy(alpha = ContentAlpha.disabled),
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                    content()
                }
            }
        }
        Row(
            modifier = Modifier
                .offset(12.dp, (-8).dp)
                .background(MaterialTheme.colorScheme.background),
        ){
            Text(
                modifier = Modifier.padding(4.dp, 0.dp),
                style = MaterialTheme.typography.labelSmall,
                text = title,
            )
        }
    }
}

@Preview
@Composable
fun PreviewSection() {
    Section(title = "Test title") {
        Text(text = "Content")
    }
}