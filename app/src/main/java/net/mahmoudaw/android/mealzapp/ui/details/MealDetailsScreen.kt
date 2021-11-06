package net.mahmoudaw.android.mealzapp.ui.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import net.mahmoudaw.model.response.MealsResponse
import java.lang.Float.min

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MealDetaisScreen(meal: MealsResponse?) {
//val scrollState= rememberScrollState() //column scroll and animation
    //    val offset=min(1f,1-(scrollState.value/600f))
//    val size by animateDpAsState(targetValue = max(100.dp,200.dp*offset))

    val scrollState= rememberLazyListState()
    val scrollRowState= rememberLazyListState()
        val offset=min(1f,1-(scrollState.firstVisibleItemScrollOffset/600f + scrollState.firstVisibleItemIndex))
    val size by animateDpAsState(targetValue = max(100.dp,140.dp*offset))




//    var profilePictureState by remember { mutableStateOf(MealProfilePictureState.Normal) }
//    var transition = updateTransition(targetState = profilePictureState, label = "")
//    val imageSizeDp by transition.animateDp(targetValueByState = { it.size }, label = "")
//    val color by transition.animateColor(targetValueByState = { it.color }, label = "")
//    val borderStroke by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")
    Surface(color = MaterialTheme.colors.background) {
        Column() {
            Surface(elevation = 5.dp) {
                Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Start) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(width = 2.dp, color = Color.Green)
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = meal?.imageUrl,
                                builder = {
                                    transformations(CircleCropTransformation())
                                }
                            ), contentDescription = null,
                            modifier = Modifier
                                .size(size)
                                )


                    }
                    Text(
                        text = meal?.name ?: "Default Name", modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                            )
                }
            }

//        Button(
//            modifier = Modifier.padding(16.dp),
//            onClick = {
//                profilePictureState =
//                    if (profilePictureState == MealProfilePictureState.Normal) MealProfilePictureState.Expanded else MealProfilePictureState.Normal
//            }) {
//            Text("Change State of Meal Profile Pic")
//        }

//            Column(modifier = Modifier.verticalScroll(scrollState)) {
//
//            }
            val dummyContentList=(1..100).map{"This is element nmber :${it.toString()}"}
            LazyRow(state = scrollRowState,modifier = Modifier.fillMaxWidth()){
                items(dummyContentList){
                    Text(text = it,modifier = Modifier.padding(10.dp))
                }
            }
           LazyColumn(state = scrollState,modifier = Modifier.fillMaxWidth()) {
                items(dummyContentList){
                    Text(text = it,modifier = Modifier.padding(10.dp))
                }

            }
        }
    }


}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 100.dp, 4.dp),
    Expanded(Color.Green, 200.dp, 10.dp)
}