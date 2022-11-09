package com.oishikenko.android.recruitment.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.oishikenko.android.recruitment.feature.R
import com.oishikenko.android.recruitment.feature.list.convert.convertRecipeType
import com.oishikenko.android.recruitment.feature.list.convert.convertRecordedAt

@Composable
fun RecipeDetailScreen(
    image_url: String,
    comment: String,
    recipe_type: String,
    record_at: String,
    navController: NavController
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp,
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
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
                        Text(
                            text = stringResource(id = R.string.recipe_detail_title),
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier.size(screenWidth),
                contentAlignment = Alignment.BottomEnd
            ) {
                AsyncImage(
                    model = image_url,
                    contentDescription = comment,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp, end = 8.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF063A77)),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(
                                id = when (recipe_type) {
                                    "main_dish" -> R.drawable.main_dish_recipe_detail_icon
                                    "side_dish" -> R.drawable.side_dish_recipe_detail_icon
                                    else -> R.drawable.soup_recipe_detail_icon
                                }
                            ),
                            contentDescription = recipe_type,
                            modifier = Modifier.padding(end = 8.dp).size(24.dp)
                        )
                        Text(
                            text = convertRecipeType(recipe_type),
                            style = MaterialTheme.typography.subtitle1,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                text = comment,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 8.dp, end = 16.dp)
                    .fillMaxWidth(),
                text = convertRecordedAt(record_at),
                textAlign = TextAlign.End,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}