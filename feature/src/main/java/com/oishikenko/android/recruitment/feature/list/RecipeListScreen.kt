package com.oishikenko.android.recruitment.feature.list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.oishikenko.android.recruitment.feature.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val cookingRecords = viewModel.recordPager.collectAsLazyPagingItems()
    val lazyListState = rememberLazyListState()
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Restart)
    )

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
        if(cookingRecords.itemCount > 0) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(top = 8.dp)
                    .consumedWindowInsets(innerPadding),
                state = lazyListState
            ) {
                items(cookingRecords.itemCount) { index ->
                    cookingRecords[index]?.let {
                        RecipeListItem(it)
                    }
                }
                item { Spacer(modifier = Modifier.height(60.dp)) }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.circle_background),
                    contentDescription = "now loading"
                )
                Image(
                    modifier = Modifier.size(48.dp).rotate(angle),
                    painter = painterResource(id = R.drawable.circle_surface),
                    contentDescription = "now loading"
                )
//                CircularProgressIndicator(
//                    modifier = Modifier.size(48.dp),
//                    color = Color(0xFF063A77),
//                    strokeWidth = 4.dp
//                )
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