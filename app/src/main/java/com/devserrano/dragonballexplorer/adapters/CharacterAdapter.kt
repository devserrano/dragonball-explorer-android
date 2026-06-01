package com.devserrano.dragonballexplorer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devserrano.dragonballexplorer.R
import com.devserrano.dragonballexplorer.models.DragonBallCharacter
import android.widget.ImageView
import com.bumptech.glide.Glide

class CharacterAdapter(
    private val characterList: List<DragonBallCharacter>
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvRace = itemView.findViewById<TextView>(R.id.tvRace)
        val tvKi = itemView.findViewById<TextView>(R.id.tvKi)
        val imgCharacter = itemView.findViewById<ImageView>(R.id.imgCharacter)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)

        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        position: Int
    ) {
        val character = characterList[position]


        holder.tvName.text = character.name

        holder.tvRace.text = character.race

        holder.tvKi.text = "Ki: ${character.ki}"

        Glide.with(holder.itemView.context)
            .load(character.image)
            .into(holder.imgCharacter)

    }

    override fun getItemCount(): Int {

        return characterList.size
    }
}
