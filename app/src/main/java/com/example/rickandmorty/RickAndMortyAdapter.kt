package com.example.rickandmorty

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RickAndMortyAdapter: ListAdapter<RickAndMortyNW, RecyclerView.ViewHolder>(RickAndMortyDiffCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rick_and_morty_holder , parent, false)
        return RickAndMortyHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RickAndMortyHolder).bind(getItem(position))

        holder.itemView.setOnClickListener(){
            val intent = Intent(holder.itemView.context, EpisodesActivity::class.java)

            val episodeList = RickAndMortyObject.rickAndMortyList[position].episode
            intent.putStringArrayListExtra("episodeList", ArrayList(episodeList))

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}
class RickAndMortyHolder(private val view: View):
    RecyclerView.ViewHolder(view.rootView){
    fun bind(rickAndMorty: RickAndMortyNW){
        view.findViewById<TextView>(R.id.tvName).text = rickAndMorty.name
        view.findViewById<TextView>(R.id.tvGender).text = rickAndMorty.gender
        Glide
            .with(view.rootView)
            .load(rickAndMorty.image)
            .into(view.findViewById(R.id.ivIcon))
    }
}
class RickAndMortyDiffCallBack : DiffUtil.ItemCallback<RickAndMortyNW>() {
    override fun areItemsTheSame(
        oldItem: RickAndMortyNW,
        newItem: RickAndMortyNW
    ): Boolean = oldItem == newItem
    override fun areContentsTheSame(
        oldItem: RickAndMortyNW,
        newItem: RickAndMortyNW
    ): Boolean = oldItem == newItem
}