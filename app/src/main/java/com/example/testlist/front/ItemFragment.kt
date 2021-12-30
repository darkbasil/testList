package com.example.testlist.front

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.testlist.R
import com.example.testlist.databinding.FragmentItemBinding
import com.example.testlist.utils.Repository
import com.google.android.material.snackbar.Snackbar

private const val ARG_NAME = "name"
private const val ARG_PRICE = "price"
private const val ARG_AMOUNT = "amount"
private const val ARG_POSITION = "position"

private lateinit var repository: Repository

class ItemFragment : Fragment() {
    private var name: String? = null
    private var price: Float? = null
    private var amount: Int? = null
    private var position: Int? = null

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME)
            price = it.getFloat(ARG_PRICE)
            amount = it.getInt(ARG_AMOUNT)
            position = it.getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)

        repository = this.activityViewModels<Repository>().value

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemName.text = name
        binding.itemPrice.text = getString(R.string.currency, price.toString())
        binding.itemAmount.text = getString(R.string.num, amount.toString())

        binding.buttonBuy.setOnClickListener {
            repository.subAmount(position!!)
//            binding.itemAmount.text = getString(R.string.num, repository.items.value?.get(this.position!!)?.amount)
//            Snackbar.make(it, "${this.position}", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String, price: Float, amount: Int, position: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putFloat(ARG_PRICE, price)
                    putInt(ARG_AMOUNT, amount)
                    putInt(ARG_POSITION, position)
                }
            }
    }
}