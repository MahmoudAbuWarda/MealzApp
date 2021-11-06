package net.mahmoudaw.model

import net.mahmoudaw.model.api.MealsWebService
import net.mahmoudaw.model.response.MealsCategoriesResponse
import net.mahmoudaw.model.response.MealsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
//without suspend function
//    fun getMeals(successCallBack:(response: MealsCategoriesResponse?)->Unit){
//      return  webService.getMeals().enqueue(object : Callback<MealsCategoriesResponse> {
////          override fun onResponse(
////              call: Call<MealsCategoriesResponse>,
////              response: Response<MealsCategoriesResponse>
////          ) {
////              if(response.isSuccessful){
////                  successCallBack(response.body())
////              }
////
////          }
////
////          override fun onFailure(call: Call<MealsCategoriesResponse>, t: Throwable) {
////
////          }
//      })
//    }

    //with suspend funcction
    private var cachedMeal= listOf<MealsResponse>()
    suspend fun getMeals(): MealsCategoriesResponse {
        val response=webService.getMeals()
        cachedMeal=response.categories
        return response
    }
    fun getMeal(id:String):MealsResponse?{
      return  cachedMeal.firstOrNull{
            it.id==id
        }
    }

    companion object{
        private var instance:MealsRepository?=null
        fun getInstance()= instance?: synchronized(this){
            instance ?:MealsRepository().also { instance=it }
        }
    }

}