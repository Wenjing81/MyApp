package com

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.GuessingProcess.Companion.FAILURECOUNT
import com.GuessingProcess.Companion.PRODUCTPRICE
import com.WelcomePage.Companion.USERNAME
import com.r.myapp.R
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username = intent?.getStringExtra(USERNAME)
        val failureCount: Int = intent.getIntExtra(FAILURECOUNT, 0)
        val productPrice: Int = intent.getIntExtra(PRODUCTPRICE, 0)
        title = "Congratulations! $username"

        setGuessResult(failureCount, productPrice)
    }


    private fun setGuessResult(failureCount: Int, productPrice: Int) {
        val discount = when (failureCount) {
            in 0..3 -> 50
            in 4..6 -> 70
            in 7..9 -> 90
            else -> 100
        }
        val discountPrice = ((discount.toDouble() / 100) * productPrice)

        result_textView.text =
            getString(
                R.string.guess_result,
                failureCount,
                discount.toString(),
                discountPrice.toString()
            )

    }
}