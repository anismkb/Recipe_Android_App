package fr.mekbal_dev.recipe_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fr.mekbal_dev.recipe_app.activities.MainActivity
import fr.mekbal_dev.recipe_app.adapter.FavCategory_Adapter
import fr.mekbal_dev.recipe_app.databinding.FragmentFavoriteBinding
import fr.mekbal_dev.recipe_app.databinding.FragmentHomeBinding
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.pojo.meal_list
import fr.mekbal_dev.recipe_app.viewmodel.HomeViewModel


class FavoriteFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding:FragmentFavoriteBinding
    private lateinit var ViewModel : HomeViewModel
    private lateinit var favoritesAdapter: FavCategory_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareReycleView()
        observeFavMealLiveData()

        val ItemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                ViewModel.deleteFavMeal(favoritesAdapter.differ.currentList[position])

                Snackbar.make(requireView(), "Meal deleted succefuly", Snackbar.LENGTH_LONG).show()
            }
        }
        ItemTouchHelper(ItemTouchHelperCallback).attachToRecyclerView(binding.reycleFav)
    }

    private fun prepareReycleView() {
        favoritesAdapter = FavCategory_Adapter()
        binding.reycleFav.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = favoritesAdapter
        }
    }

    private fun observeFavMealLiveData(){
        ViewModel.observeFavoritMealLiveData().observe(viewLifecycleOwner, Observer { meals->
            favoritesAdapter.differ.submitList(meals)
        })
    }

    private fun gestureDeleteFavorit(){

    }


}