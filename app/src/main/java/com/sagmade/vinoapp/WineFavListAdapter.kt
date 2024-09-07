package com.sagmade.vinoapp

import android.view.View

class WineFavListAdapter : WineListAdapter() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val wine = getItem(position)
        holder.run {
            with(binding) {
                cbFavorite.apply {
                    isChecked = wine.isFavorite
                    visibility = View.VISIBLE
                }
            }
        }
    }
}
