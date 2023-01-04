package com.example.trivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://numbersapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val numberTriviaApi = retrofit.create(NumberTriviaApi::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // EditText number = findViewById<EditText>(R.id.editTextNumber)

       val editTextNumber: EditText = findViewById(R.id.editTextNumber)
       val button: Button = findViewById(R.id.button)
        button.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                //TODO("Not yet implemented")
                gettrivia()
            }

        })


    }

    fun gettrivia(): Unit {
        val call = numberTriviaApi.getTriviaForNumber(42)
        try {
            call.enqueue(object : Callback<NumberTrivia> {
                override fun onResponse(call: Call<NumberTrivia>, response: Response<NumberTrivia>) {

                    val trivia = response.body()
                    if (response.isSuccessful) {
                        if (trivia != null) {
                            print(trivia.text)
                        }
                        // Do something with the trivia object
                    } else {
                        Log.e("NumberTrivia", "Error: ${response.code()} ${response.message()}")
                    }
                }


                override fun onFailure(call: Call<NumberTrivia>, t: Throwable) {
                    // Handle the error
                    Log.e("NumberTrivia", "Error: asdfasdf")
                }
            })
        }
        catch (e:Exception){
            print(e.message)
        }

    }




}
//api
//http://numbersapi.com/number/type