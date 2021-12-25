package com.example.testlist.front

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.testlist.R
import com.example.testlist.databinding.FragmentItemBinding
import kotlinx.coroutines.flow.combine

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_NAME = "name"
private const val ARG_COST = "cost"
private const val ARG_AMOUNT = "amount"

class ItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var name: String? = null
    private var cost: String? = null
    private var amount: String? = null

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME)
            cost = it.getString(ARG_COST)
            amount = it.getString(ARG_AMOUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_item, container, false)
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemId.text = name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String, cost: String, amount: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putString(ARG_COST, cost)
                    putString(ARG_AMOUNT, amount)
                }
            }
    }
}