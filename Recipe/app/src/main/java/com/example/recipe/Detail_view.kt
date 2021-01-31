package com.example.recipe

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.annotation.RequiresApi

class Detail_view : Fragment() {

    private val recipeImage:ImageView by lazy {
        requireView().findViewById(R.id.imageView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater!!.inflate(R.menu.detail_page_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val binding = DataBindingUtil.inflate<FragmentDetailViewBinding>(inflater, R.layout.fragment_detail_view, container, false)
        return inflater.inflate(R.layout.fragment_detail_view, container, false)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeImage.clipToOutline = true;
    }

}