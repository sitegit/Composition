package com.example.composition.presentation.screens.level

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composition.R
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.domain.entities.Levels
import com.example.composition.presentation.screens.game.GameFragment

class ChooseLevelFragment : Fragment() {
    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.buttonLevelTest.setOnClickListener {
            launchGameFragment(Levels.TEST)
        }

        binding.buttonLevelEasy.setOnClickListener {
            launchGameFragment(Levels.EASY)
        }

        binding.buttonLevelNormal.setOnClickListener {
            launchGameFragment(Levels.NORMAL)
        }

        binding.buttonLevelHard.setOnClickListener {
            launchGameFragment(Levels.HARD)
        }
    }

    private fun launchGameFragment(level: Levels) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "ChooseLevelFragment"
        fun newInstance() = ChooseLevelFragment()
    }
}