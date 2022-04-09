package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelledTextCard(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.displaySmall
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(text = label, style = MaterialTheme.typography.labelMedium)

            Text(text = text, style = textStyle, modifier = Modifier.align(Alignment.End), textAlign = TextAlign.End)
        }
    }
}