package com.example.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entities.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

//GameFragment
@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, goodState: Boolean) {
    textView.setTextColor(getColorByState(textView.context, goodState))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, goodState: Boolean) {
    val color = getColorByState(progressBar.context, goodState)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}
private fun getColorByState(contenxt: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(contenxt, colorResId)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}





//GameResultFragment
@BindingAdapter("requiredAnswer")
fun bindRequiredAnswer(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(
            R.string.required_score,
            count.toString()
        )
    )
}

@BindingAdapter("scoredAnswer")
fun bindScoreAnswer(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(
            R.string.score_answers,
            count.toString()
        )
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(
            R.string.required_percentage,
            count.toString()
        )
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(
            R.string.score_percentage,
            getPercentOfRightAnswers(gameResult).toString()
        )
    )
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getImage(winner))
}

private fun getImage(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}