package com.yannickpulver.wonderfluff.ui.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.yannickpulver.wonderfluff.R
import com.yannickpulver.wonderfluff.domain.Puppy
import com.yannickpulver.wonderfluff.ui.theme.WonderfluffTheme
import dev.chrisbanes.accompanist.insets.statusBarsPadding


@ExperimentalFoundationApi
@Composable
fun PuppyList(viewModel: PuppyListViewModel, navController: NavHostController) {
    val puppies: List<Puppy> by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Wonderfluff", style = MaterialTheme.typography.h4)
                    }
                },
                modifier = Modifier.statusBarsPadding(),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background
            )
        }
    ) {
        PuppyList(puppies, navController)
    }
}

@ExperimentalFoundationApi
@Composable
private fun PuppyList(
    puppies: List<Puppy>,
    navController: NavHostController
) {
    LazyVerticalGrid(cells = GridCells.Fixed(2), contentPadding = PaddingValues(16.dp)) {
        itemsIndexed(puppies) { index, puppy ->
            val even = index % 2 == 0
            val startPadding = if (even) 0.dp else 8.dp
            val endPadding = if (even) 8.dp else 0.dp

            PuppyItem(puppy, modifier = Modifier
                .padding(start = startPadding, end = endPadding, bottom = 24.dp)
                .clickable {
                    navController.navigate("puppyDetail/${puppy.id}")
                }
                .fillMaxWidth())

        }
    }
}

@Composable
fun PuppyItem(puppy: Puppy, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painterResource(puppy.imageRes),
                contentDescription = "Image of ${puppy.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Icon(
                    painterResource(id = R.drawable.pets_24px),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .alpha(0.4f)
                )
                Text(
                    puppy.name,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPuppyItem() {
    WonderfluffTheme {
        PuppyItem(
            Puppy(
                7,
                R.drawable.puppy7,
                "Yodaag",
                0.2f,
                0.2f,
                1.0f,
                "When you look at the bark side, careful you must be. For the bark side looks back."
            )
        )
    }
}
