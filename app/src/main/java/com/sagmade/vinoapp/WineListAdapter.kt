package com.sagmade.vinoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sagmade.vinoapp.databinding.ItemWineBinding

open class WineListAdapter : ListAdapter<Wine, WineListAdapter.ViewHolder>(WineDiff()) {

    private lateinit var context: Context
    private var listener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_wine, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wine = getItem(position)
        holder.bind(wine, listener)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemWineBinding.bind(view) // Cambiar a 'val' para hacerlo accesible

        fun bind(wine: Wine, listener: OnClickListener?) {
            with(binding) {
                tvWine.text = wine.wine
                tvWinery.text = wine.winery
                tvLocation.text = wine.location
                Rating.rating = wine.rating.average.toFloat()

                Glide.with(itemView.context)
                    .load(wine.image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imgWine)

                root.setOnLongClickListener {
                    listener?.onLongClick(wine)
                    true
                }

                cbFavorite.setOnClickListener {
                    listener?.onFavourite(wine)
                }
            }
        }
    }

    private class WineDiff : DiffUtil.ItemCallback<Wine>() {
        override fun areItemsTheSame(oldItem: Wine, newItem: Wine): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Wine, newItem: Wine): Boolean {
            return oldItem == newItem
        }
    }

    interface OnClickListener {
        fun onLongClick(wine: Wine)
        fun onFavourite(wine: Wine)
    }
}
