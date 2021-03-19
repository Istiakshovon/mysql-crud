package com.example.mysql_crud

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mysql_crud.model.Contacts
import com.example.mysql_crud.remote.ApiClient
import com.example.mysql_crud.remote.ApiInterface
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {

    var etxtUserName: EditText? = null
    var etxtContact:EditText? = null
    var etxtPassword:EditText? = null
    var btnSignUp: Button? = null
    var username: String? = null
    var contact:String? = null
    var password:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etxtUserName = findViewById(R.id.etxt_username);
        etxtContact = findViewById(R.id.etxt_contact);
        etxtPassword = findViewById(R.id.etxt_password);
        btnSignUp = findViewById(R.id.btn_registration);

        btnSignUp!!.setOnClickListener {
            username = etxtUserName!!.getText().toString();
            contact = etxtContact!!.getText().toString();
            password = etxtPassword!!.getText().toString();
            if(username!!.isEmpty()){
                etxtUserName!!.setError("Please enter your username");
                etxtUserName!!.requestFocus();
            }else if(contact!!.isEmpty()){
                etxtContact!!.setError("Please enter your username");
                etxtContact!!.requestFocus();
            }else if (password!!.isEmpty()){
                etxtPassword!!.setError("Please enter your username");
                etxtPassword!!.requestFocus();
            }else {
                signUp(username!!, contact!!, password!!);
            }
        }
    }

    private fun signUp(username: String, contact: String, password: String) {
        val apiInterface: ApiInterface? =
            ApiClient().getApiClient()?.create<ApiInterface>(ApiInterface::class.java)
        val call: retrofit2.Call<Contacts?>? = apiInterface?.signUp(username, contact, password)
        call?.enqueue(object : Callback<Contacts?> {
            override fun onResponse(call: retrofit2.Call<Contacts?>?, response: Response<Contacts?>) {
                val value: String = response.body()?.getValue()!!
                val message: String = response.body()?.getMassage()!!
                if (value == "success") {
                    Toast.makeText(this@SignUpActivity, message, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else if (value == "exists") {
                    Toast.makeText(this@SignUpActivity, message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@SignUpActivity, message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<Contacts?>?, t: Throwable) {
                
                Toast.makeText(this@SignUpActivity, "Error! $t", Toast.LENGTH_SHORT).show()
            }
        })

    }
}