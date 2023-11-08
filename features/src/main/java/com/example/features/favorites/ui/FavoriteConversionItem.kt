package com.example.features.favorites.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.features.common.theme.BackgroundCard
import com.example.features.common.theme.BackgroundDefault
import com.example.features.common.theme.TextDefault
import com.example.domain.entity.Favorite
import com.example.features.R

@Composable
fun ConversionRateItem(
    rate: String,
    favorite: Favorite,
    onFavoriteClick: (Favorite) -> Unit
) {

    val favoritePainter = painterResource(id = R.drawable.ic_favorites_on)

    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundDefault),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(color = BackgroundCard, shape = RoundedCornerShape(12.dp))
                .padding(16.dp, 14.dp)
                .fillMaxWidth(),
        ) {
            val (column, text1) = createRefs()
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .wrapContentWidth()
                    .constrainAs(column) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Text(
                    color = TextDefault,
                    text = favorite.conversionRate,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

            }

            Row(
                modifier = Modifier
                    .height(24.dp).wrapContentWidth().wrapContentHeight()
                    .constrainAs(text1) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.bottom)
                        bottom.linkTo(parent.top)
                    },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = rate,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = typography.titleSmall.copy(color = TextDefault)
                )
                Image(
                    modifier = Modifier.clickable { onFavoriteClick(favorite) },
                    painter = favoritePainter,
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
            }
        }
    }
}
