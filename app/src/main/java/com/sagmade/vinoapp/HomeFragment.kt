package com.sagmade.vinoapp

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : BaseFragment(), WineListAdapter.OnClickListener { // Cambia a WineListAdapter.OnClickListener

    private lateinit var adapter: WineListAdapter
    private lateinit var service: WineService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupRecyclerView()
        setupRetrofit()
    }

    private fun setupAdapter() {
        adapter = WineListAdapter()
        adapter.setOnClickListener(this) // Aquí, 'this' es HomeFragment que implementa la interfaz
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
            adapter = this@HomeFragment.adapter
        }
    }

    private fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(WineService::class.java)
    }

    protected override fun getWines() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val wines = service.getRedsWines()
                withContext(Dispatchers.Main) {
                    if (wines.isNotEmpty()) {
                        showRecyclerView(true)
                        showNoDataView(false)
                        adapter.submitList(wines)
                    } else {
                        showRecyclerView(false)
                        showNoDataView(true)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showMsg(R.string.common_request_fail)
                }
            } finally {
                showProgress(false)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showProgress(true)
        getWines()
    }

    /*
    OnClickListener
    */
    override fun onLongClick(wine: Wine) {
        val options = resources.getStringArray(R.array.array_dialog_add_options)
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.dialog_add_fav_title)
            .setItems(options) { _, index ->
                when (index) {
                    0 -> addToFavorites(wine)
                }
            }
            .show()
    }

    private fun addToFavorites(wine: Wine) {
        lifecycleScope.launch(Dispatchers.IO) {
            wine.isFavorite = true
            val result = WineApplication.database.WineDao().addWine(wine)
            if (result != -1L) {
                withContext(Dispatchers.Main) {
                    showMsg(R.string.room_save_success)
                }
            } else {
                withContext(Dispatchers.Main) {
                    showMsg(R.string.room_save_fail)
                }
            }
        }
    }

    override fun onFavourite(wine: Wine) {
        // Implementa la lógica para el evento de clic en el favorito aquí
    }
}
