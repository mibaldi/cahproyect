package com.mibaldi.cah.domain.interactors.main

import com.mibaldi.cah.data.repositories.GameRepository
import com.mibaldi.cah.data.repositories.UserRepository


class MainInteractorImpl(val userRepository: UserRepository,val gameRepository: GameRepository) : MainInteractor {
    override fun getAllPlays() {
        gameRepository.getAllGames()
    }

    override fun getCurrentPlayer(param: (Pair<String?,Error?>) -> Unit) {
        userRepository.changeUsername("Olga", param)
    }


}