package com.example.superhero

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superhero.databinding.ItemHeroBinding
import com.squareup.picasso.Picasso

class HeroesAdapter(private val listHeroes:List<Array<String>>,
                    private val heroItemClickListener: OnHeroClickListener):RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>(){

                        interface  OnHeroClickListener{
                            fun onHeroItemClick(name:String, id:String, power:String)
                        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeroesViewHolder(layoutInflater.inflate(R.layout.item_hero,parent,false))
    }

    override fun getItemCount(): Int = listHeroes.size

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val item:Array<String> = listHeroes[position]
        holder.bind(item)
    }

    inner class HeroesViewHolder(view:View):RecyclerView.ViewHolder(view){
        private val binding = ItemHeroBinding.bind(view)
        private var x:String = ""

        fun bind(itemHeroe:Array<String>){
            binding.card.setOnClickListener{
                heroItemClickListener.onHeroItemClick(itemHeroe[1],itemHeroe[2],itemHeroe[3])
            }

            Picasso.get().load(itemHeroe[0]).into(binding.ivHero)
            binding.tvHero.text = itemHeroe[1]
            x = itemHeroe[2]
        }
    }


    }