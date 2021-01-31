package com.example.recipe

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import com.example.recipe.databinding.FragmentDetailViewBinding
import com.example.recipe.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Detail_view.newInstance] factory method to
 * create an instance of this fragment.
 */
class Detail_view : Fragment() {

    private val recipeImage:ImageView by lazy {
        requireView().findViewById(R.id.imageView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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