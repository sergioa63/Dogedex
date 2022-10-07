package com.example.dogedex.doglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dogedex.api.ApiResponceStatus
import com.example.dogedex.databinding.ActivityDogListBinding
import com.example.dogedex.dogdetail.DogDetailActivity
import com.example.dogedex.dogdetail.DogDetailActivity.Companion.DOG_KEY


class DogListActivity : AppCompatActivity() {

    private  val dogListViewModel : DogListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val loadingWheel = binding.loadingWheel
        val recycler = binding.dogRecycler
        recycler.layoutManager = GridLayoutManager(this, 3)
        val dogAdapter = DogAdapter()
        recycler.adapter = dogAdapter
        dogAdapter.setOnItemClickListerner { dog ->
            val intent = Intent(this, DogDetailActivity::class.java)
            intent.putExtra(DOG_KEY, dog)
            startActivity(intent)
        }
        dogListViewModel.dogList.observe(this) { doglist ->
            dogAdapter.submitList(doglist)
        }
        dogListViewModel.status.observe(this) { status ->
            when(status) {
                is ApiResponceStatus.Error ->  {
                    loadingWheel.visibility = View.GONE
                    Toast.makeText(this, getText(status.msn), Toast.LENGTH_LONG).show()
                }
                is ApiResponceStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                is ApiResponceStatus.Success -> loadingWheel.visibility = View.GONE
            }
        }
    }

}