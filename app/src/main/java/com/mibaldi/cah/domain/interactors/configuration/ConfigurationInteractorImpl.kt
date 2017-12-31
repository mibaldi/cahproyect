package com.mibaldi.cah.domain.interactors.configuration

import com.mibaldi.cah.data.repositories.UserRepository


class ConfigurationInteractorImpl(val userRepository: UserRepository) : ConfigurationInteractor {
    override fun changeUsername(username: String, param: (Pair<String?,Error?>) -> Unit) {
        userRepository.changeUsername(username,param)
    }

}