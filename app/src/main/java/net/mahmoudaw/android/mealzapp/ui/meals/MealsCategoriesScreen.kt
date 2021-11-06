package net.mahmoudaw.android.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import net.mahmoudaw.android.mealzapp.ui.theme.MealzAppTheme
import net.mahmoudaw.model.response.MealsResponse


@Composable
fun MealsCategoriesScreen(navigatonCallback:(String)->Unit) {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val meal = viewModel.mealsState.value

    //without suspend function
//    viewModel.getMeals(){response ->
//        val mealsFormApi=response?.categories
//      rememberedMeals.value=mealsFormApi.orEmpty()
//    }

    //with suspend function
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meal) { meals ->
            mealsItems(mealList = meals,navigatonCallback)
        }
    }
}

@Composable
fun mealsItems(mealList: MealsResponse,navigatonCallback:(String)->Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .clickable {
                       navigatonCallback(mealList.id)
            }, elevation = 3.dp, shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(4.dp)
                .animateContentSize() ,
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = mealList.imageUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterVertically)
//                verticalArrangement = Arrangement.Top,
//                horizontalAlignment = Alignment.Start
            ) {
                Text(text = mealList.name, style = MaterialTheme.typography.h6)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = mealList.description,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.subtitle2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if(isExpanded) 15 else 4
                    )

                }


            }
            Icon(
                imageVector = if(isExpanded)  Icons.Filled.KeyboardArrowUp  else Icons.Filled.KeyboardArrowDown ,
                contentDescription = "Expand Row Icon",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        isExpanded= !isExpanded
                    }
            )
        }


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        MealsCategoriesScreen({})
    }
}