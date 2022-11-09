package com.oishikenko.android.recruitment.feature.prototype

import android.content.res.Configuration
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration
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
fun MyRecipeListScreen(
    viewModel: MyRecipeListViewModel = hiltViewModel(),
    navController: NavController
) {
    val cookingRecords = viewModel.recordPager.collectAsLazyPagingItems()
    val lazyGridState = rememberLazyGridState()
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Restart)
    )
    val halfScreenWidth = (LocalConfiguration.current.screenWidthDp / 2).dp

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
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .clickable { navController.popBackStack() }
                            .size(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "go to pre screen",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = stringResource(id = R.string.my_records_album_title),
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        modifier = Modifier.size(64.dp),
                        painter = painterResource(id = R.drawable.my_cooking_record_header),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    ) { innerPadding ->
        if(cookingRecords.itemCount > 0) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(top = 8.dp)
                    .consumedWindowInsets(innerPadding),
                state = lazyGridState
            ) {
                items(cookingRecords.itemCount) { index ->
                    cookingRecords[index]?.let {
                        val encodeUrl = URLEncoder.encode(it.imageUrl, StandardCharsets.UTF_8.toString())
                        MyRecipeListItem(
                            cookingRecord = it,
                            modifier = Modifier
                                .padding(all = 4.dp)
                                .size(halfScreenWidth)
                        ) {
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMyRecipeListScreenDark(){
    MaterialTheme {
        MyRecipeListScreen(navController = NavController(LocalContext.current))
    }
}