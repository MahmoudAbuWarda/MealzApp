package net.mahmoudaw.model.response

import com.google.gson.annotations.SerializedName

data class MealsCategoriesResponse( val categories:List<MealsResponse>)

data class MealsResponse(
    @SerializedName("idCategory") var id: String,
    @SerializedName("strCategory")  val  name: String,
    @SerializedName("strCategoryDescription")   val  description: String,
    @SerializedName("strCategoryThumb")  val  imageUrl: String)