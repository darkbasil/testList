package com.example.testlist.front

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.testlist.R
import com.example.testlist.databinding.FragmentFrontBinding
import com.example.testlist.utils.Repository

class FrontFragment : Fragment() {

    private var _binding: FragmentFrontBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ItemAdapter
    private lateinit var viewPager: ViewPager2

    private var _position: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFrontBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = this.activityViewModels<Repository>().value
        viewPager = binding.pager

        repository.items.observe(viewLifecycleOwner) {
            adapter = ItemAdapter(this, it)

//            if (viewPager.adapter == null) {
                viewPager.adapter = adapter
//            }
            if (_position != null) {
                viewPager.currentItem = _position!!
            }
        }

        repository.position.observe(viewLifecycleOwner) {
            viewPager.adapter?.notifyItemChanged(it)
        }

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                _position = position
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}