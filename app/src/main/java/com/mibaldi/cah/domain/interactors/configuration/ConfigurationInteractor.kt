package com.mibaldi.cah.domain.interactors.configuration


interface ConfigurationInteractor {

    fun changeUsername(username: String, param: (Pair<String?,Error?>) -> Unit)

}