package com.example.composition.presentation.screens.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameResultBinding

class GameResultFragment : Fragment() {
    private var _binding: FragmentGameResultBinding? = null
    private val binding: FragmentGameResultBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    private val args by navArgs<GameResultFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickRetryGameListener()

        setResult()
    }

    private fun setResult() {

        binding.emojiResult.setImageResource(getImage())

        binding.textViewRequiredAnswers.text = String.format(
            getString(
                R.string.required_score,
                args.gameResult.gameSettings.minCountOfRightAnswers.toString()
            )
        )

        binding.textViewScoreAnswers.text = String.format(
            getString(
                R.string.score_answers,
                args.gameResult.countOfRightAnswers.toString()
            )
        )

        binding.textViewRequiredPercentage.text = String.format(
            getString(
                R.string.required_percentage,
                args.gameResult.gameSettings.minPercentOfRightAnswers.toString()
            )
        )

        binding.textViewScorePercentage.text = String.format(
            getString(
                R.string.score_percentage,
                getPercentOfRightAnswers().toString()
            )
        )
    }

    private fun getPercentOfRightAnswers() = with(args.gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }
    private fun getImage(): Int {
        return if (args.gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    private fun onClickRetryGameListener() {
        binding.buttonRetry.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}