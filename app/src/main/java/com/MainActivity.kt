package com

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.WelcomeActivity.Companion.USERNAME
import com.r.myapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {

    //transfer "intent" and the username here
    private var username: String = ""
    private lateinit var exampleList: ArrayList<CategoryItem>
    private lateinit var adapter: CategoryAdapter
    private lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = intent?.getStringExtra(USERNAME) ?: ""

        //show the apps welcome information
        title = "Welcome $username !"

        //set recyclerviews features, for example, adapter, layoutManager, setHasFixedSize.
        initDataBase()

        adapter = CategoryAdapter(exampleList, this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.setHasFixedSize(true)

        insert_all.setOnClickListener{
            AppDatabase.getDatabase(this)
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
            Toast.makeText(this,"the data is BACK!!",Toast.LENGTH_SHORT).show()
        }

        remove_all.setOnClickListener {
            val it = (application as ProductsApplication)
            productViewModel = it.productViewModel
            productViewModel.removeAll()
            Toast.makeText(this,"the data is EMPTY!!",Toast.LENGTH_SHORT).show()
        }
    }

    private fun initDataBase() {
        exampleList = generateCategoryList(6)
    }



    /* Should delete or develop further functions afterwards.
    fun insertItem(view: View) {
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
        val intent: Intent = Intent(this, GuessingActivity::class.java)
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