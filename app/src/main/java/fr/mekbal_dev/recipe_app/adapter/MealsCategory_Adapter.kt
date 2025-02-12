package fr.mekbal_dev.recipe_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.mekbal_dev.recipe_app.databinding.MealItemBinding
import fr.mekbal_dev.recipe_app.pojo.Category
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.pojo.MealsByCategory

class MealsCategory_Adapter():RecyclerView.Adapter<MealsCategory_Adapter.CategoryMealsViewHolder>(){

    var mealsList = ArrayList<MealsByCategory>()
    var mealSelected : ((MealsByCategory)->Unit)? =null
    
    fun setMealsList(mealsList : List<MealsByCategory>){
        this.mealsList = mealsList as ArrayList<MealsByCategory>
        notifyDataSetChanged()
    }

    inner class CategoryMealsViewHolder(val binding: MealItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewHolder {
        return CategoryMealsViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: CategoryMealsViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imageMealsByCategory)
        holder.binding.nameItem.text = mealsList[position].strMeal
        holder.itemView.setOnClickListener {
            mealSelected?.invoke(mealsList[position])
        }
    }


}



