package com

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.GuessingProcess.Companion.FAILURECOUNT
import com.GuessingProcess.Companion.PRODUCTPRICE
import com.GuessingProcess.Companion.SUMOFPRICES
import com.WelcomePage.Companion.USERNAME
import com.r.myapp.R
import kotlinx.android.synthetic.main.activity_result.*
import kotlin.math.roundToInt

class Result : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        //get the values of the variables from GuessingProcess
        val username = intent?.getStringExtra(USERNAME)
        val failureCount: Int = intent.getIntExtra(FAILURECOUNT, 0)
        val productPrice: Int = intent.getIntExtra(PRODUCTPRICE, 0)
        val sumOfPrices: Int = intent.getIntExtra(SUMOFPRICES, 0)
        title = "Congratulations! $username"
        //sumOfPrices is the sum of 3 products' prices
        setGuessResult(failureCount, productPrice, sumOfPrices)
    }


    private fun setGuessResult(failureCount: Int, productPrice: Int, sumOfPrices: Int) {
        val discount = when (failureCount) {
            // make the categories of discounts
            in 0..3 -> 50
            in 4..6 -> 70
            in 7..9 -> 90
            else -> 100
        }

        //roundToInt() change the double number to Int,
        val discountPrice = ((discount.toDouble() / 100) * sumOfPrices).roundToInt()


        result_textView.text =
            getString(
                R.string.guess_result,
                failureCount,
                discount.toString(),
                discountPrice.toString()
            )

    }
}