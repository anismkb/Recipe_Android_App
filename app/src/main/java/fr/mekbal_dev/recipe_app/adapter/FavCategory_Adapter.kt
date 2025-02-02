package fr.mekbal_dev.recipe_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.mekbal_dev.recipe_app.databinding.MealItemBinding
import fr.mekbal_dev.recipe_app.pojo.Meal

class FavCategory_Adapter: RecyclerView.Adapter<FavCategory_Adapter.FavoritesMealAdapterViewHolder>(){

    inner class FavoritesMealAdapterViewHolder(val binding: MealItemBinding): RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Meal>(){
        //compare juste the primarykey
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            TODO("Not yet implemented")
            return oldItem.idMeal == newItem.idMeal
        }

        //compare items
        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            TODO("Not yet implemented")
            return oldItem == newItem
        }
    }

    val differ  = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesMealAdapterViewHolder {
        return FavoritesMealAdapterViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(parent.context),
            )
        )
    }

    override fun onBindViewHolder(holder: FavoritesMealAdapterViewHolder, position: Int) {
        val meal = differ.currentList[position]
        Glide.with(holder.itemView).load(meal.strMealThumb).into(holder.binding.imageMealsByCategory)
        holder.binding.nameItem.text = meal.strMeal
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}