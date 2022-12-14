package com.oishikenko.android.recruitment.feature.list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.oishikenko.android.recruitment.feature.R
import com.oishikenko.android.recruitment.feature.navigation.NavigationRoute
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel(),
    navController: NavController
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

// ?????????????????? ----------------------------------------------------------- START
                    Text(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .fillMaxHeight()
                            .clickable {
                                navController.navigate(
                                    NavigationRoute.MYRecipeList.route
                                ) {
                                    popUpTo(NavigationRoute.MYRecipeList.route)
                                }
                            },
                        text = stringResource(
                            id = R.string.side_button_text_cooking_records_title),
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
// ?????????????????? ----------------------------------------------------------- END
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
                        val encodeUrl = URLEncoder.encode(it.imageUrl, StandardCharsets.UTF_8.toString())
                        RecipeListItem(it) {
                            navController.navigate(
                                NavigationRoute.RecipeDetail.route +
                                        "/$encodeUrl" +
                                        "/${it.comment}" +
                                        "/${it.recipeType}" +
                                        "/${it.recordedAt}"
                            ) {
                                popUpTo(NavigationRoute.RecipeDetail.route)
                            }
                        }
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
                    modifier = Modifier
                        .size(48.dp)
                        .rotate(angle),
                    painter = painterResource(id = R.drawable.circle_surface),
                    contentDescription = "now loading"
                )
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewRecipeListScreenDark(){
    MaterialTheme {
        RecipeListScreen(navController = NavController(LocalContext.current))
    }
}


@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewRecipeListScreenLight(){
    MaterialTheme {
        RecipeListScreen(navController = NavController(LocalContext.current))
    }
}