package uz.qmgroup.pharmabook.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import uz.qmgroup.pharmabook.components.MedicinesList
import uz.qmgroup.pharmabook.components.TagsList
import uz.qmgroup.pharmabook.medicines.Medicine
import uz.qmgroup.pharmabook.repos.MedicinesRepo
import uz.qmgroup.pharmabook.repos.TagsRepo
import uz.qmgroup.pharmabook.tags.Tag

@Composable
fun TagsScreen(
    modifier: Modifier = Modifier
) {
    var tagFilter by remember { mutableStateOf<String?>(null) }

    var loading by remember {
        mutableStateOf(true)
    }
    val list by produceState(initialValue = emptyList<Tag>(), producer = {
        this.value = TagsRepo().getTags()
        loading = false
    })

    BackHandler(enabled = tagFilter != null) {
        tagFilter = null
    }

    if (loading) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
    } else {
        if (tagFilter != null)
            MedicineByTagScreen(
                modifier = modifier,
                tag = tagFilter!!
            )
        else
            TagsList(
                modifier = modifier,
                list = list,
                onClick = {
                    tagFilter = it.label
                }
            )
    }
}

@Composable
fun MedicineByTagScreen(
    modifier: Modifier = Modifier,
    tag: String
) {
    var loading by remember {
        mutableStateOf(true)
    }
    val list by produceState(initialValue = emptyList<Medicine>(), producer = {
        this.value = MedicinesRepo().getMedicinesByTags(
            listOf(tag)
        )
        loading = false
    }, key1 = tag)


    if (loading) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
    } else {
        MedicinesList(
            modifier = modifier,
            list = list,
        )
    }
}