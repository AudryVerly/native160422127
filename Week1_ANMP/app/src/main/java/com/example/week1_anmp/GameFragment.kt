package com.example.week1_anmp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.week1_anmp.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var binding : FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //player name itu dari argument yang dibuat di navigation
        val name = GameFragmentArgs.fromBundle(requireArguments()).playerName
        binding.txtTurn.text = name + "'s turn"
        binding.btnBack.setOnClickListener {

            val action = GameFragmentDirections.actionMainFragment()
            it.findNavController().navigate(action)
        }
    }
}