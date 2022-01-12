package uz.qmgroup.pharmabook.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.models.Tag

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TagsList(
    modifier: Modifier = Modifier,
    list: List<Tag>,
    onClick: (Tag) -> Unit
){
    if (list.isNotEmpty()) {
        LazyColumn(modifier = modifier){
            items(list) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = 8.dp,
                    onClick = {
                        onClick(it)
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_tag_black_24dp),
                            contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = it.label)
                    }
                }
            }
        }
    }else{
        Box(modifier = modifier){
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.No_tags)
            )
        }
    }
}