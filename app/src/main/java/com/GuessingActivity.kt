package com

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStoreOwner
import com.MainActivity.Companion.PDTYPE
import com.WelcomeActivity.Companion.USERNAME
import com.r.myapp.R
import kotlinx.android.synthetic.main.activity_guessing_process.*

class GuessingActivity : AppCompatActivity() {

    private var productList = mutableListOf<ProductItem>()
    var successTimesOnOnePage: Int = 0
    private lateinit var productViewModel: ProductViewModel

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
        Log.v("WJ", "type is $type")
        val it = (application as ProductsApplication)
        productViewModel = it.productViewModel

        getProductListFromDB()
        //subList of productList is a List,not a MutableList
        val subList1 = productList.filter {
            it.productType == type
        }
        //So should change to mutableList, Otherwise, kan not use "remove" method
        val subList = subList1 as MutableList

        replaceNewFragment(subList)

        guess_button.setOnClickListener {

            if (productItem.productPrice > input_price.text.toString().toInt()) {
                Toast.makeText(this, "Lower", Toast.LENGTH_SHORT).show()
                failureCount += 1
            } else if (productItem.productPrice < input_price.text.toString().toInt()) {
                Toast.makeText(this, "Higher", Toast.LENGTH_SHORT).show()
                failureCount += 1
            } else {
                successTimesOnOnePage++
                sumOfPrices += input_price.text.toString().toInt()
                // Guess the prices right at most on 3 product
                if ((successTimesOnOnePage < 4) and (subList.size > 1)) {
                    subList.remove(productItem)
                    Toast.makeText(this, "next one", Toast.LENGTH_SHORT).show()
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

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame_image, fragment)
        transaction.commit()
    }

    fun toResult(username: String, failureCount: Int, productItem: ProductItem) {
        val intent: Intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(USERNAME, username)
        intent.putExtra(FAILURECOUNT, failureCount)
        intent.putExtra(PRODUCTPRICE, productItem.productPrice)
        intent.putExtra(SUMOFPRICES, sumOfPrices)
        Log.d("wj", "You have failed $failureCount times!")
        startActivity(intent)
    }

    private fun getProductListFromDB() {
        val x = productViewModel.getAll()
        productList = x as MutableList
    }

    companion object {
        const val FAILURECOUNT = "failureCount"
        const val PRODUCTPRICE = "productPrice"
        const val SUMOFPRICES = "sumOfPrices"
    }
}