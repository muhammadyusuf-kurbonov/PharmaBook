package uz.qmgroup.pharmabook.screens.editor.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Search
import uz.qmgroup.pharmabook.R
import uz.qmgroup.pharmabook.components.MedicinesList
import uz.qmgroup.pharmabook.components.ScreenTitle
import uz.qmgroup.pharmabook.screens.destinations.EditorMedicineScreenDestination

@OptIn(ExperimentalAnimationApi::class)
@Destination
@Composable
fun EditorHomeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    viewModel: EditorHomeViewModel = viewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.startSearch()
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScreenTitle(title = stringResource(id = R.string.Editor))

                if (!viewModel.searchEnabled)
                    IconButton(onClick = viewModel::enableSearch) {
                        Icon(
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp),
                            imageVector = FontAwesomeIcons.Solid.Search,
                            contentDescription = ""
                        )
                    }
            }
            AnimatedContent(targetState = viewModel.searchEnabled) {
                if (it) {
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = viewModel.searchPattern,
                            onValueChange = viewModel::updateSearchPattern,
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.search),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    this.defaultKeyboardAction(ImeAction.Search)
                                    viewModel.startSearch()
                                }
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = null,
                                    modifier = Modifier.wrapContentHeight()
                                )
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            MedicinesList(
                modifier = modifier,
                list = viewModel.list,
                loading = viewModel.loading,
                editorEnabled = true,
                onDelete = viewModel::deleteMedicine,
                onEdit = { navigator.navigate(EditorMedicineScreenDestination(it.id)) },
                onCopy = viewModel::duplicateMedicine
            )
        }

        FloatingActionButton(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { navigator.navigate(EditorMedicineScreenDestination(-1)) }
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
    }
}