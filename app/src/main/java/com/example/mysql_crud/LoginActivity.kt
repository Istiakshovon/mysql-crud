package com.example.mysql_crud

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mysql_crud.model.Contacts
import com.example.mysql_crud.remote.ApiClient
import com.example.mysql_crud.remote.ApiInterface
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    var btnLogin: Button? = null; var btnSignUp:Button? = null
    private val loading: ProgressDialog? = null
    var etxtCell: EditText? = null; var etxtPassword:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin=findViewById(R.id.btn_login);
        btnSignUp=findViewById(R.id.btn_signup);
        etxtCell=findViewById(R.id.etxt_cell);
        etxtPassword=findViewById(R.id.etxt_password);


        btnLogin?.setOnClickListener {

                var cell=etxtCell?.text.toString();
                var password=etxtPassword?.text.toString();

                //validation


                if (cell?.length!=11)
                {
                    etxtCell?.setError("Invalid cell!");
                    etxtCell?.requestFocus();

                }


                else if (password?.isEmpty())
                {
                    etxtPassword?.setError("Password can not be empty! ");
                    etxtPassword?.requestFocus();

                }

                else
                {
                    //call login method
                    login(cell,password);
                }

        }
        
        btnSignUp?.setOnClickListener{
            var intent:Intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }




    }



    //signup method
    fun login(cell:String,password:String) {
        val apiInterface: ApiInterface? =
            ApiClient().getApiClient()?.create<ApiInterface>(ApiInterface::class.java)
        val call: retrofit2.Call<Contacts?>? = apiInterface?.login(cell, password)
        call?.enqueue(object : Callback<Contacts?> {
            override fun onResponse(call: retrofit2.Call<Contacts?>?, response: Response<Contacts?>) {
                val value: String = response.body()?.getValue()!!
                val message: String = response.body()?.getMassage()!!

                if (value.equals("success"))
                {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this@LoginActivity, "$message", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<Contacts?>?, t: Throwable) {

                Toast.makeText(this@LoginActivity, "Error! $t", Toast.LENGTH_SHORT).show()
            }
        });
    }

}