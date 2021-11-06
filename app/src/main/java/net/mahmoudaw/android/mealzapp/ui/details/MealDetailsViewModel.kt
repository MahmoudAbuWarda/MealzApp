package net.mahmoudaw.android.mealzapp.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import net.mahmoudaw.model.MealsRepository
import net.mahmoudaw.model.response.MealsResponse

class MealDetailsViewModel(private val saveStateHandel: SavedStateHandle) : ViewModel() {
    private val repository: MealsRepository = MealsRepository.getInstance()
    var mealState = mutableStateOf<MealsResponse?>(null)

    init {
        val mealId = saveStateHandel.get<String>("meal_category_id") ?: ""
        mealState.value = repository.getMeal(mealId)
    }
}