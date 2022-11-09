package com.oishikenko.android.recruitment.feature.prototype

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oishikenko.android.recruitment.data.model.CookingRecord
import com.oishikenko.android.recruitment.feature.list.convert.convertRecipeType

@Composable
fun MyRecipeListItem(
    cookingRecord: CookingRecord,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick),
        contentAlignment = Alignment.TopStart
    ) {
        AsyncImage(
            model = cookingRecord.imageUrl,
            contentDescription = cookingRecord.comment,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
        )
        Box(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = convertRecipeType(cookingRecord.recipeType),
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMyRecipeListItem() {
    MyRecipeListItem(
        cookingRecord = CookingRecord(
            imageUrl= "",
            comment = "豚肉のコクとごぼうの香り、野菜の甘みで奥行きのある味わい。",
            recipeType = "soup",
            recordedAt = "2018-05-01 17:57:31"
        ),
        modifier = Modifier.size(64.dp),
        onClick = {}
    )
}