package com.devserrano.dragonballexplorer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devserrano.dragonballexplorer.R
import com.devserrano.dragonballexplorer.database.FavoriteCharacter

class FavoriteAdapter(
    private var favoriteList: List<FavoriteCharacter>,
    private val onDeleteClick: (FavoriteCharacter) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val imgCharacter = itemView.findViewById<ImageView>(R.id.imgCharacter)
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvRace = itemView.findViewById<TextView>(R.id.tvRace)
        val tvKi = itemView.findViewById<TextView>(R.id.tvKi)
        val btnDeleteFavorite = itemView.findViewById<TextView>(R.id.btnDeleteFavorite)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)

        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {

        val favorite = favoriteList[position]

        holder.tvName.text = favorite.name
        holder.tvRace.text = favorite.race
        holder.tvKi.text = "Ki: ${favorite.ki}"

        Glide.with(holder.itemView.context)
            .load(favorite.image)
            .into(holder.imgCharacter)

        holder.btnDeleteFavorite.setOnClickListener {
            onDeleteClick(favorite)
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun updateList(newList: List<FavoriteCharacter>) {
        favoriteList = newList
        notifyDataSetChanged()
    }
}