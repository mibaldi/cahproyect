package com.mibaldi.cah.application

import com.mibaldi.cah.data.repositories.Repository
import com.mibaldi.cah.router.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {


    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideRepository(): Repository {
        val repo = Repository()
        return repo
    }

    @Provides
    @Singleton
    fun provideRouter(): Router {
        val router = Router(app.applicationContext)
        return router
    }

}