package com.example.esoftwsr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.esoftwsr.Activity.Activity
import com.example.esoftwsr.Adapters.ActivityRecyclerAdapter
import com.example.esoftwsr.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val Activities = MutableLiveData<MutableList<Activity>>()
    private fun loadData() = CoroutineScope(Dispatchers.IO).launch {
        Activities.postValue(Activity.GetActivity())
    }

    private fun PostData(activity: Activity) = CoroutineScope(Dispatchers.IO).launch {
        activity.SendActivity(activity)
        loadData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvactivity.layoutManager = LinearLayoutManager(this)
        loadData()
        Activities.observe(this)
        {
            binding.rvactivity.adapter = ActivityRecyclerAdapter(it)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Add")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.toString() == "Add") {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("Добавление")

        }
        return super.onOptionsItemSelected(item)
    }
}