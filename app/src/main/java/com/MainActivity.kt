package com

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.WelcomePage.Companion.USERNAME
import com.r.myapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {

    //transfer "intent" and the username here
    private var username: String = ""
    private lateinit var exampleList: ArrayList<CategoryItem>
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = intent?.getStringExtra(USERNAME) ?: ""

        //show the apps welcome information
        title = "Welcome $username !"

        //set recyclerviews features, for example, adapter, layoutManager, setHasFixedSize.

        //
        initDataBase()

        adapter = CategoryAdapter(exampleList, this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.setHasFixedSize(true)
    }

    private fun initDataBase() {
        exampleList = generateCategoryList(6)
    }

    /*fun insertItem(view: View) {
        val index = Random.nextInt(8)

        val newItem = CategoryItem(
            R.drawable.ic_android,
            "New item at position $index",
            "in the store"
        )

        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }

    fun removeItem(view: View) {
        val index = Random.nextInt(8)

        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }
*/
    override fun onItemClick(position: Int) {
        val clickedItem = exampleList[position]
        clickedItem.status = "clicked"
        adapter.notifyItemChanged(position)
        val intent: Intent = Intent(this, GuessingProcess::class.java)
        intent.putExtra(USERNAME, username)
        intent.putExtra(PDTYPE, clickedItem.type)
        startActivity(intent)

    }

    private fun generateCategoryList(size: Int): ArrayList<CategoryItem> {
        val list = ArrayList<CategoryItem>()
        var drawable: Int
        //var status: String
        var type: String
        for (i in 0 until size) {
            when (i) {
                0 -> {
                    drawable = R.drawable.type0_drink

                    type = "drink"

                }

                1 -> {
                    drawable = R.drawable.type1_fruit

                    type = "fruit"

                }
                2 -> {
                    drawable = R.drawable.type2_hygien
                    type = "hygien"

                }
                3 -> {
                    drawable = R.drawable.type3_meat
                    type = "meat"

                }
                4 -> {
                    drawable = R.drawable.type4_seafood
                    type = "seafood"

                }
                else -> {
                    drawable = R.drawable.type5_snack
                    type = "snack"

                }
            }
            val item = CategoryItem(drawable, type, "not clicked")
            list.add(item)
        }
        return list
    }

    companion object {
        const val PDTYPE = "type"
    }
}