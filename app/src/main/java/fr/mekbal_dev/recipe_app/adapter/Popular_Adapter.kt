package fr.mekbal_dev.recipe_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.mekbal_dev.recipe_app.databinding.PopularItemsBinding
import fr.mekbal_dev.recipe_app.pojo.CategoryMeal

class Popular_Adapter(): RecyclerView.Adapter<Popular_Adapter.PopularMealViewHolder>() {

    private var mealList = ArrayList<CategoryMeal>()

    fun setMeals(mealsList: List<CategoryMeal>){
        this.mealList = mealsList as ArrayList<CategoryMeal>
        notifyDataSetChanged() // notify to update the views
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return this.mealList.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealList[position].strMealThumb)
            .into(holder.binding.imgPopular)
    }

    class PopularMealViewHolder(val binding: PopularItemsBinding):RecyclerView.ViewHolder(binding.root){

    }
}