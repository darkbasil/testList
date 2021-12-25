package com.example.testlist.front

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testlist.utils.Item

class ItemAdapter(fragment: FrontFragment, private val items: List<Item>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return ItemFragment.newInstance(items[position].name, items[position].price, items[position].amount)
    }
}