package fr.mekbal_dev.recipe_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.mekbal_dev.recipe_app.database.MealDataBase

class MealViewModelFactory(
    val mealDataBase: MealDataBase
) : ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return MealViewModel(mealDataBase) as T
    }
}