package com.example.composition.domain.usecases

import com.example.composition.domain.entities.GameSettings
import com.example.composition.domain.entities.Levels
import com.example.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository)  {

    operator fun invoke(level: Levels): GameSettings {
        return repository.getGameSettings(level)
    }
}