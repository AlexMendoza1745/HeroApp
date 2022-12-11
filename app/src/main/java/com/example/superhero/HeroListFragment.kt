package com.example.superhero

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superhero.databinding.ActivityMainBinding
import com.example.superhero.databinding.FragmentHeroListBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Exception

class HeroListFragment : Fragment(), SearchView.OnQueryTextListener,
    OnHeroItemClicListener, HeroesAdapter.OnHeroClickListener {
private lateinit var binding: FragmentHeroListBinding
private lateinit var adapter: HeroesAdapter
private val itemsHeroes=mutableListOf<Array<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initRecyclerView() {
adapter= HeroesAdapter(itemsHeroes,this)
        binding.rvHeroes.layoutManager=LinearLayoutManager(this.activity)
        binding.rvHeroes.adapter=adapter
    }

    private fun getRetrofit():Retrofit {
return  Retrofit.Builder().
baseUrl("https://www.superheroapi.com/api.php/4565382966872714/").
addConverterFactory(GsonConverterFactory.create()).
build()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentHeroListBinding.inflate(layoutInflater)
        activity?.setContentView(binding.root)

        //Type mismatch: inferred type is HeroListFragment but SearchView.OnQueryTextListener! was expected
        binding.svHeroes.setOnQueryTextListener(this)
        initRecyclerView()
        loadList()

        val mGestureDetector = GestureDetector(activity, object:GestureDetector.SimpleOnGestureListener(){
            override fun onSingleTapUp (e: MotionEvent): Boolean = true})
        binding.rvHeroes.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(
                rv:RecyclerView,
                motionEvent: MotionEvent
            ): Boolean {
                try {
                    val child =
                        binding.rvHeroes!!.findChildViewUnder(motionEvent.x, motionEvent.y)
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        val position = binding.rvHeroes!!.getChildAdapterPosition(child)
                        val heroId = itemsHeroes[position][2]
                        val snackBar = Snackbar.make(requireActivity().findViewById(android.R.id.content),
                            "${itemsHeroes[position][1]}\nID: ${itemsHeroes[position][2]}, Poder: ${itemsHeroes[position][3]}",Snackbar.LENGTH_SHORT )
                        snackBar.show()

                        return true
                    }
                } catch (e: Exception){

                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

        })


        return inflater.inflate(R.layout.fragment_hero_list, container, false)

    }
    private fun searchByName(p0: String){
        lifecycleScope.launch {
            val call=getRetrofit().create(APIService::class.java).getHeroesByName("search/$p0")
            val heroes:HeroesResponse?=call.body()
            activity?.runOnUiThread{
                if(call.isSuccessful){
                    val heroes:List<Heroes?> = heroes?.results ?: emptyList()
                    itemsHeroes.clear()
                    for (item in heroes){
                        val itemHero:Array<String> = arrayOf(item!!.image.url,item.name,item.id,item.powerstats.power)
                        itemsHeroes.add(itemHero)
                    }
                    adapter.notifyDataSetChanged()
                }
                else{
                    showError()
                }
            }
        }
    }

    private fun loadList(){
        lifecycleScope.launch {
            var id:Int=1
            itemsHeroes.clear()
            do {
                val call=getRetrofit().create(APIService::class.java).getAllHeroes("$id")
                val hero:Heroes?=call.body()
                activity?.runOnUiThread(){
                    if(call.isSuccessful){
                        val heroes:List<Heroes?> = arrayListOf(hero)
                        for (item in heroes){
                            val itemHero:Array<String> = arrayOf(item!!.image.url,item.name,item.id,item.powerstats.power)
                            itemsHeroes.add(itemHero)
                        }
                        adapter.notifyDataSetChanged()
                    }
                    else{
                        showError()
                    }
                    id++
                }

            }while (call.isSuccessful)
        }
    }

    private fun showError(){
        Toast.makeText(this.activity,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }


    override fun onQueryTextSubmit(p0: String?): Boolean {
        // en lugar de p0 es query
        if(!p0.isNullOrEmpty()){
            searchByName(p0.lowercase())
        }else{
            loadList()
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean =true

    override fun onItemClic(item: Heroes, id: Int) {
        ////////////
    }

    override fun onHeroItemClick(name: String, id: String, power: String) {
        ////////
    }


}


