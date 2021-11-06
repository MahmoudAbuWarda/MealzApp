package net.mahmoudaw.android.mealzapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.mahmoudaw.android.mealzapp.ui.details.MealDetailsViewModel
import net.mahmoudaw.android.mealzapp.ui.details.MealDetaisScreen
import net.mahmoudaw.android.mealzapp.ui.meals.MealsCategoriesScreen
import net.mahmoudaw.android.mealzapp.ui.theme.MealzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MealzAppTheme {
                FoodzApp()
            }
        }
    }
}

@Composable
private fun FoodzApp() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = "destination_meals_list") {
        composable(route = "destination_meals_list") {
            MealsCategoriesScreen {navigationMealId->
                navigationController.navigate(route = "destination_meal_Details/$navigationMealId")

            }
        }
        composable(
            route = "destination_meal_Details/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id") {
                type = NavType.StringType
            })
        ) {
            val viewModel:MealDetailsViewModel = viewModel()

            MealDetaisScreen(viewModel.mealState.value)
        }
    }
}
