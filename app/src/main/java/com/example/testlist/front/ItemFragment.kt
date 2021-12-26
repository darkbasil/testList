package com.example.testlist.front

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testlist.R
import com.example.testlist.databinding.FragmentItemBinding
import com.google.android.material.snackbar.Snackbar

private const val ARG_NAME = "name"
private const val ARG_PRICE = "price"
private const val ARG_AMOUNT = "amount"

class ItemFragment : Fragment() {
    private var name: String? = null
    private var price: Float? = null
    private var amount: Int? = null

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME)
            price = it.getFloat(ARG_PRICE)
            amount = it.getInt(ARG_AMOUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemName.text = name
        binding.itemPrice.text = getString(R.string.currency, price.toString())
        binding.itemAmount.text = getString(R.string.num, amount.toString())

        binding.buttonBuy.setOnClickListener {
//            TODO getCurrentItem -> item.amount-- ? know position???
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String, price: Float, amount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putFloat(ARG_PRICE, price)
                    putInt(ARG_AMOUNT, amount)
                }
            }
    }
}