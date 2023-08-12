package com.example.composition.domain.repository

import com.example.composition.domain.entities.GameSettings
import com.example.composition.domain.entities.Levels
import com.example.composition.domain.entities.Question

interface GameRepository {
    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Levels): GameSettings
}