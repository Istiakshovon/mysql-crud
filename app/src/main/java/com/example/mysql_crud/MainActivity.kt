package com.example.mysql_crud

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysql_crud.model.Contacts
import com.example.mysql_crud.remote.ApiClient
import com.example.mysql_crud.remote.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val recyclerView = findViewById<RecyclerView>(R.id.recycler)
    val layoutManager = LinearLayoutManager(this)
    private var adapter: Adapter? = null
    private var contactsList: List<Contacts>? = null
    private var apiInterface: ApiInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface = ApiClient().getApiClient()?.create<ApiInterface>(ApiInterface::class.java)


        fetchData()

    }
    fun fetchData() {
        val call: Call<List<Contacts?>?>? = apiInterface!!.getContact()
        call?.enqueue(object : Callback<List<Contacts?>?> {
            override fun onResponse(
                call: Call<List<Contacts?>?>?,
                response: Response<List<Contacts?>?>
            ) {
                contactsList = response.body() as List<Contacts>?
                Log.d("RESPONSE", contactsList.toString())
                adapter = Adapter(this@MainActivity, contactsList!!)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<List<Contacts?>?>?, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error : $t", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }


    //menu item selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.add -> {
                startActivity(Intent(this, EditorActivity::class.java))
                true
            }
            R.id.logout -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    //when activity is resumed this method is called
    override fun onResume() {
        super.onResume()
        fetchData()
    }
}