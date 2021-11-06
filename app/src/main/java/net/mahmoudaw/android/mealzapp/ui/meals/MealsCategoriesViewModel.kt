package net.mahmoudaw.android.mealzapp.ui.meals

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.mahmoudaw.model.MealsRepository
import net.mahmoudaw.model.response.MealsResponse

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {

//without suspend function
//    fun getMeals(successCallBack:(response: MealsCategoriesResponse?)->Unit){
//        repository.getMeals {response->
//            successCallBack(response)
//        }

    // using job
    // private val mealsJob= Job()
    init {
        // val scope= CoroutineScope(mealsJob+Dispatchers.IO)
        //  scope.launch(){
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals

        }
    }


    val mealsState: MutableState<List<MealsResponse>> = mutableStateOf(emptyList<MealsResponse>())
//    override fun onCleared() {
//        super.onCleared()
//        mealsJob.cancel()
//    }

    //with suspend function
    private suspend fun getMeals(): List<MealsResponse> {
        return repository.getMeals().categories
    }
}