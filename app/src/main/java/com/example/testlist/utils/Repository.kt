package com.example.testlist.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Repository: ViewModel() {
    private val _items = MutableLiveData<List<Item>>().apply {
        value
    }
    val items: LiveData<List<Item>> = _items

    fun fillItems(context: Context) {
        val csvString = context.assets
            .open("data.csv")
            .bufferedReader().use{
                it.readLines()
            }

        val list: MutableList<Item> = mutableListOf()
        for(line in csvString) {
            val lineArray = line.trim('"').split("\", \"")
            val item = Item(lineArray[0], lineArray[1].toFloat(), lineArray[2].toInt())
            list.add(item)
        }
        _items.value = list
    }

    private val _position = MutableLiveData<Int>().apply {
        value = 0
    }
    val position: LiveData<Int> = _position

    fun subAmount(pos: Int) {
        val newItems = items.value
        _position.value = pos
//        _items.value?.get(pos)?.amount = _items.value?.get(pos)?.amount?.minus(1)!!
        newItems?.get(pos)?.amount = newItems?.get(pos)?.amount?.minus(1)!!
        _items.value = newItems
    }
}