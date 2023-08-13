package com.example.composition.presentation.screens.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.composition.R
import com.example.composition.databinding.FragmentGameResultBinding
import com.example.composition.domain.entities.GameResult
import com.example.composition.presentation.screens.game.GameFragment
import com.example.composition.util.parcelable

class GameResultFragment : Fragment() {
    private var _binding: FragmentGameResultBinding? = null
    private val binding: FragmentGameResultBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    private lateinit var result: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

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
                result.gameSettings.minCountOfRightAnswers.toString()
            )
        )

        binding.textViewScoreAnswers.text = String.format(
            getString(
                R.string.score_answers,
                result.countOfRightAnswers.toString()
            )
        )

        binding.textViewRequiredPercentage.text = String.format(
            getString(
                R.string.required_percentage,
                result.gameSettings.minPercentOfRightAnswers.toString()
            )
        )

        binding.textViewScorePercentage.text = String.format(
            getString(
                R.string.score_percentage,
                getPercentOfRightAnswers().toString()
            )
        )
    }

    private fun getPercentOfRightAnswers() = with(result) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }
    private fun getImage(): Int {
        return if (result.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    private fun onClickRetryGameListener() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            }
        )

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager
            .popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun parseArgs() {
        requireArguments().parcelable<GameResult>(KEY_RESULT)?.let {
            result = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY_RESULT = "result"

        fun newInstance(gameResult: GameResult): GameResultFragment {
            return GameResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_RESULT, gameResult)
                }
            }
        }
    }

}