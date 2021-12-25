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

        var list: List<Item> = listOf()
        for(line in csvString) {
            val l = line.split(", ").map{ it.trim('"') }
            val item = Item(l[0], l[1].toFloat(), l[2].toInt())
            list = list.plus(item)
        }
        _items.value = list
    }
}