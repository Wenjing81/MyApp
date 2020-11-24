package com

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private var exampleList: MutableList<CategoryItem> = mutableListOf()
    private lateinit var adapter: CategoryAdapter
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = intent?.getStringExtra(USERNAME) ?: ""

        //show the apps welcome information
        title = "Welcome $username !"

        val it = (application as ProductsApplication)
        productViewModel = it.productViewModel

        val temp = productViewModel.getAll()
        if (temp.isEmpty()) {
            text_view.visibility = View.VISIBLE
            recycler_view.visibility = View.GONE
        } else {
            text_view.visibility = View.GONE
            recycler_view.visibility = View.VISIBLE
            //set recyclerviews features, for example, adapter, layoutManager, setHasFixedSize.
            initLocalListData()
        }

        adapter = CategoryAdapter(exampleList, this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.setHasFixedSize(true)

        insert_all.setOnClickListener {
            val it = (application as ProductsApplication)
            productViewModel = it.productViewModel

            val temp = productViewModel.getAll()
            //Justify if the list is empty or not!!
            if (temp.isEmpty()) {
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
                Toast.makeText(this, "The data is BACK!!", Toast.LENGTH_SHORT).show()

                initLocalListData()
                text_view.visibility = View.GONE
                recycler_view.visibility = View.VISIBLE

                if (recycler_view.adapter!=null){
                    adapter.updateDataList()
                }
            } else {
                Toast.makeText(this, "Nothing need to do!", Toast.LENGTH_SHORT).show()
            }
        }

        remove_all.setOnClickListener {
            val it = (application as ProductsApplication)
            productViewModel = it.productViewModel
            val temp = productViewModel.getAll()
            if (temp.isEmpty() == false) {
                productViewModel.removeAll()
                Toast.makeText(this, "The data is EMPTY!!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nothing need to do!", Toast.LENGTH_SHORT).show()
            }
            exampleList.clear()
            text_view.visibility = View.VISIBLE
            recycler_view.visibility = View.GONE
            if (recycler_view.adapter!=null){
                adapter.updateDataList()
            }


        }
    }



    private fun initLocalListData() {
        exampleList = generateCategoryList()
    }


    override fun onItemClick(position: Int) {
        val clickedItem = exampleList[position]
        clickedItem.status = "clicked"
        adapter.notifyItemChanged(position)
        val intent: Intent = Intent(this, GuessingActivity::class.java)
        intent.putExtra(USERNAME, username)
        intent.putExtra(PDTYPE, clickedItem.type)
        startActivity(intent)

    }

    private fun generateCategoryList(): MutableList<CategoryItem> {
        exampleList.clear()
        var drawable: Int
        //var status: String
        var type: String
        for (i in 0 until 6) {
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
            exampleList.add(item)
        }
        return exampleList
    }

    companion object {
        const val PDTYPE = "type"
    }
}