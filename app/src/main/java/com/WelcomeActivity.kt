package com

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.AppDatabase.Companion.getDatabase
import com.r.myapp.R
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Welcome page for the user to input username and begin games, transfer username to the next activity-MainActivitydtrt
class WelcomeActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        //Give the theme of the app.
        title = "Guess the price"
        input_user_name.setText("")
        initDatabase()
        click_to_begin.setOnClickListener {

            val username = input_user_name.text.toString()

            val intent: Intent = Intent(this, MainActivity::class.java)
            //send intent and "username" from here
            //Log.v("wj", "the input username is $username")
            intent.putExtra(USERNAME, username)

            startActivity(intent)
        }
    }

    private fun initDatabase() {

        val it = (application as ProductsApplication)
        productViewModel = it.productViewModel

        getDatabase(this)

        lifecycleScope.launch(Dispatchers.IO) {
            lateinit var product: ProductItem
            product = ProductItem(2020001, R.drawable.drink1_evian, "drink", 18)
            productViewModel.insert(product)
            product = ProductItem(2020002, R.drawable.drink2_alpro_soyamilk, "drink", 20)
            productViewModel.insert(product)
            product = ProductItem(2020003, R.drawable.drink3_mellanmilkeko, "drink", 12)
            productViewModel.insert(product)
            product = ProductItem(2020004, R.drawable.drink4_havredryck, "drink", 12)
            productViewModel.insert(product)
            product = ProductItem(2020006, R.drawable.fruit1_avcado, "fruit", 11)
            productViewModel.insert(product)
            product = ProductItem(2020007, R.drawable.fruit2_mango, "fruit", 20)
            productViewModel.insert(product)
            product = ProductItem(2020008, R.drawable.fruit3_grapefruit, "fruit", 15)
            productViewModel.insert(product)
            product = ProductItem(2020009, R.drawable.hygien1_colgate, "hygien", 26)
            productViewModel.insert(product)
            product = ProductItem(2020010, R.drawable.hygien2_neutral, "hygien", 25)
            productViewModel.insert(product)
            product = ProductItem(2020011, R.drawable.meat1_chicken, "meat", 90)
            productViewModel.insert(product)
            product = ProductItem(2020012, R.drawable.meat2_meatball, "meat", 53)
            productViewModel.insert(product)
            product = ProductItem(2020013, R.drawable.seafood1_shrimp, "seafood", 65)
            productViewModel.insert(product)
            product = ProductItem(2020014, R.drawable.seafood2_salmon, "seafood", 85)
            productViewModel.insert(product)
            product = ProductItem(2020015, R.drawable.seafood3_friedfish, "seafood", 57)
            productViewModel.insert(product)
            product = ProductItem(2020016, R.drawable.snack1_chips, "snack", 22)
            productViewModel.insert(product)
            product = ProductItem(2020017, R.drawable.snack2_icecream, "snack", 52)
            productViewModel.insert(product)
            product = ProductItem(2020018, R.drawable.snack3_chocolate, "snack", 29)
            productViewModel.insert(product)
        }
    }

    companion object {
        const val USERNAME = "username"

        fun startWelcomeActivity(context: Context) {
            val intent = Intent(context, WelcomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}
