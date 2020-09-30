package com
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.r.myapp.R
import kotlinx.android.synthetic.main.activity_welcome_page.*
//Welcome page for the user to input username, transfer username to the next activity-MainActivity
class WelcomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        title="Guess the number"

        click_to_begin.setOnClickListener{

            var username = input_user_name.text.toString()

            val intent:Intent= Intent(this, MainActivity::class.java)
         //send intent and "username" from here
            intent.putExtra(USERNAME, username)

            startActivity(intent)

        }
    }

    companion object{
        const val USERNAME ="username"
    }
}
