package com.example.week1_anmp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.week1_anmp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding

    //untuk ngeload layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container,
                    false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOption.setOnClickListener {
            val action = MainFragmentDirections.actionItemHomeToOptionFragment()
            it.findNavController().navigate(action)
        }
        //berguna untuk berpindah halaman, untuk mereplace fragment
        binding.btnStart.setOnClickListener {
            // ini yang berguna sebagai tempat input nama
            val name = binding.txtName.text.toString()
            //action berguna untuk mendireksi ke gamefragment
            val action = MainFragmentDirections.actionGameFragment(name)
            it.findNavController().navigate(action)
        }
    }
}