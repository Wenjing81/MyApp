package com

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.r.myapp.R

class ImageFragment : Fragment() {

    private var imageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageId = it.getInt(IMAGE_NUM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val x = inflater.inflate(R.layout.fragment_image, container, false)
        val y = x.findViewById<ImageView>(R.id.product_image)
        y.setImageResource(imageId)
        return x
    }

    companion object {

        const val IMAGE_NUM = "image_num"

        @JvmStatic
        fun newInstance(imageId: Int) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putInt(IMAGE_NUM, imageId)
                }
            }
    }
}