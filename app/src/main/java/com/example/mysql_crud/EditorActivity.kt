package com.example.mysql_crud

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mysql_crud.model.Contacts
import com.example.mysql_crud.remote.ApiClient
import com.example.mysql_crud.remote.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditorActivity : AppCompatActivity() {

    private var apiInterface: ApiInterface? = null

    private var etxtName: EditText? = null; private  var etxtEmail:EditText? = null
    private var btnSave: Button? = null; private  var btnDelete:Button? = null

    var getID: String? = null
    var getName: String? = null
    var getEmail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        val i = intent
        getID = i.getStringExtra("id")
        getName = i.getStringExtra("name")
        getEmail = i.getStringExtra("email")

        etxtName = findViewById(R.id.input_name)
        etxtEmail = findViewById(R.id.input_email)
        btnSave = findViewById(R.id.bSimpan)
        btnDelete = findViewById(R.id.btn_delete)



        condition(getID)

    }

    private fun insertUser() {
        val sname = etxtName!!.text.toString()
        val semail = etxtEmail!!.text.toString()
        apiInterface = ApiClient().getApiClient()?.create<ApiInterface>(ApiInterface::class.java)
        val call: Call<Contacts?>? = apiInterface?.insertUser(sname, semail)
        call?.enqueue(object : Callback<Contacts?> {
            override fun onResponse(call: Call<Contacts?>?, response: Response<Contacts?>) {
                val value: String = response.body()?.getValue()!!
                val message: String = response.body()?.getMassage()!!
                if (value == "1") {
                    Toast.makeText(this@EditorActivity, message, Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@EditorActivity, message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Contacts?>?, t: Throwable) {
                Toast.makeText(this@EditorActivity, "Error! $t", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun editUser() {
        val sid = getID!!
        val sname = etxtName!!.text.toString()
        val semail = etxtEmail!!.text.toString()
        apiInterface = ApiClient().getApiClient()?.create<ApiInterface>(ApiInterface::class.java)
        val call: Call<Contacts?>? = apiInterface?.editUser(sid, sname, semail)
        call?.enqueue(object : Callback<Contacts?> {
            override fun onResponse(call: Call<Contacts?>?, response: Response<Contacts?>) {
                val value: String = response.body()?.getValue()!!
                val message: String = response.body()?.getMassage()!!
                if (value == "1") {
                    Toast.makeText(this@EditorActivity, message, Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@EditorActivity, message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Contacts?>?, t: Throwable) {
                Toast.makeText(this@EditorActivity, "Error! $t", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun condition(kond: String?) {
        if (kond == null) {
            btnDelete!!.visibility = View.INVISIBLE
            btnSave!!.setOnClickListener{
                    insertUser()
            }
        } else {
            etxtName!!.setText(getName)
            etxtEmail!!.setText(getEmail)
            Log.d("ID", getID!!)
            btnSave!!.setOnClickListener{
                    editUser()
            }
            btnDelete!!.setOnClickListener{
                    deleteUser()
            }
        }
    }


    private fun deleteUser() {
        val id = getID!!.toInt()
        apiInterface = ApiClient().getApiClient()?.create<ApiInterface>(ApiInterface::class.java)
        val call: Call<Contacts?>? = apiInterface?.deleteUser(id)
        call?.enqueue(object : Callback<Contacts?> {
            override fun onResponse(call: Call<Contacts?>?, response: Response<Contacts?>) {
                val value: String = response.body()?.getValue()!!
                val message: String = response.body()?.getMassage()!!
                if (value == "1") {
                    Toast.makeText(this@EditorActivity, message, Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@EditorActivity, message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Contacts?>?, t: Throwable) {
                Toast.makeText(this@EditorActivity, "Error! $t", Toast.LENGTH_SHORT).show()
            }
        })
    }
}