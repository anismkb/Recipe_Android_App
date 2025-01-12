package fr.mekbal_dev.recipe_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.mekbal_dev.recipe_app.databinding.CategoryItemsBinding
import fr.mekbal_dev.recipe_app.pojo.Category

class Category_Adapter: RecyclerView.Adapter<Category_Adapter.CategoryViewHolder>(){

    private var categoriesList = ArrayList<Category>()

    fun setCategoryList(categoriesList : List<Category>){
        this.categoriesList=categoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val binding: CategoryItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoriesList[position].strCategoryThumb)
            .into(holder.binding.categoryImage)
        holder.binding.categoryName.text = categoriesList[position].strCategory
    }

}
