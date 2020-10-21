package com

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.MainActivity.Companion.PDTYPE
import com.WelcomePage.Companion.USERNAME
import com.r.myapp.R
import kotlinx.android.synthetic.main.activity_guessing_process.*

class GuessingProcess : AppCompatActivity() {

    private val productList = mutableListOf<ProductItem>()
    var successTimesOnOnePage: Int = 0

    //transfer "intent" and the username here
    var randomImageNumber = 0
    lateinit var productItem: ProductItem
    var failureCount = 0
    var sumOfPrices = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guessing_process)

        val username = intent?.getStringExtra(USERNAME)
        //Log.v("wj","the input username at Guessing is $username")

        val type = intent?.getStringExtra(PDTYPE)
        //Log.v("WJ","type is $type")

        productGenerator()
        //subList of productList is a List,not a MutableList
        val subList1 = productList.filter {
            it.productType == type
        }
        //So should change to mutableList, Otherwise, kan not use "remove" method
        val subList = subList1 as MutableList

        replaceNewFragment(subList)

        guess_button.setOnClickListener {

            if (productItem.productPrice > input_price.text.toString().toInt()) {
                Toast.makeText(this, "Lower", Toast.LENGTH_LONG).show()
                failureCount += 1
            } else if (productItem.productPrice < input_price.text.toString().toInt()) {
                Toast.makeText(this, "Higher", Toast.LENGTH_LONG).show()
                failureCount += 1
            } else {
                successTimesOnOnePage++
                sumOfPrices += input_price.text.toString().toInt()
                // Guess the prices right at most on 3 product
                if ((successTimesOnOnePage < 4) and (subList.size > 1)) {
                    subList.remove(productItem)
                    Toast.makeText(this, "next one", Toast.LENGTH_LONG).show()
                    replaceNewFragment(subList)

                } else {
                    Log.d("test1", "$failureCount")
                    toResult(username!!, failureCount, productItem)
                }

            }
        }
    }

    private fun replaceNewFragment(subList: List<ProductItem>) {
        Log.v("zhangwenjing", "size is ${subList.size}")
        if (subList.size <= 1) {
            randomImageNumber = 0
        } else {
            randomImageNumber = (subList.indices).random()
        }
        productItem = subList[randomImageNumber]
        val x = ImageFragment.newInstance(productItem.productImage)
        replaceFragment(x)
    }

    fun productGenerator() {
        productList.add(ProductItem(R.drawable.drink1_evian, "drink", 18, 2020001))
        productList.add(ProductItem(R.drawable.drink2_alpro_soyamilk, "drink", 20, 2020002))
        productList.add(ProductItem(R.drawable.drink3_mellanmilkeko, "drink", 12, 2020003))
        productList.add(ProductItem(R.drawable.drink4_havredryck, "drink", 12, 2020004))
        productList.add(ProductItem(R.drawable.drink5_spritezero, "drink", 17, 2020005))
        productList.add(ProductItem(R.drawable.fruit1_avcado, "fruit", 11, 2020006))
        productList.add(ProductItem(R.drawable.fruit2_mango, "fruit", 20, 2020007))
        productList.add(ProductItem(R.drawable.fruit3_grapefruit, "fruit", 15, 2020008))
        productList.add(ProductItem(R.drawable.hygien1_colgate, "hygien", 26, 2020009))
        productList.add(ProductItem(R.drawable.hygien2_neutral, "hygien", 25, 2020010))
        productList.add(ProductItem(R.drawable.meat1_chicken, "meat", 90, 2020011))
        productList.add(ProductItem(R.drawable.meat2_meatball, "meat", 53, 2020012))
        productList.add(ProductItem(R.drawable.seafood1_shrimp, "seafood", 65, 2020013))
        productList.add(ProductItem(R.drawable.seafood2_salmon, "seafood", 85, 2020014))
        productList.add(ProductItem(R.drawable.seafood3_friedfish, "seafood", 57, 2020015))
        productList.add(ProductItem(R.drawable.snack1_chips, "snack", 22, 2020016))
        productList.add(ProductItem(R.drawable.snack2_icecream, "snack", 52, 2020017))
        productList.add(ProductItem(R.drawable.snack3_chocolate, "snack", 29, 2020018))
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame_image, fragment)
        transaction.commit()
    }

    fun toResult(username: String, failureCount: Int, productItem: ProductItem) {
        val intent: Intent = Intent(this, Result::class.java)
        intent.putExtra(USERNAME, username)
        intent.putExtra(FAILURECOUNT, failureCount)
        intent.putExtra(PRODUCTPRICE, productItem.productPrice)
        intent.putExtra(SUMOFPRICES, sumOfPrices)
        Log.d("wj", "You have failed $failureCount times!")
        startActivity(intent)
    }

    companion object {
        const val FAILURECOUNT = "failureCount"
        const val PRODUCTPRICE = "productPrice"
        const val SUMOFPRICES = "sumOfPrices"
    }
}