package com

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.r.myapp.R
import kotlinx.android.synthetic.main.activity_welcome_page.*

//Welcome page for the user to input username and begin games, transfer username to the next activity-MainActivitydtrt
class WelcomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)
        //Give the theme of the app.
        title = "Guess the price"

        click_to_begin.setOnClickListener {

            val username = input_user_name.text.toString()

            val intent: Intent = Intent(this, MainActivity::class.java)
            //send intent and "username" from here
            Log.v("wj", "the input username is $username")
            intent.putExtra(USERNAME, username)

            startActivity(intent)

        }
    }

    companion object {
        const val USERNAME = "username"
    }
}
