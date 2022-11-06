package com.oishikenko.android.recruitment.feature.list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oishikenko.android.recruitment.feature.R

@OptIn(ExperimentalLayoutApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val cookingRecords by viewModel.cookingRecords.collectAsStateWithLifecycle()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                modifier = Modifier,
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = stringResource(id = R.string.cooking_records_title),
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        modifier = Modifier.size(64.dp),
                        painter = painterResource(id = R.drawable.my_cooking_record_header),
                        contentDescription = null
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            items(cookingRecords) {
                RecipeListItem(it)
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewRecipeListScreenDark(){
    MaterialTheme {
        RecipeListScreen()
    }
}


@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewRecipeListScreenLight(){
    MaterialTheme {
        RecipeListScreen()
    }
}