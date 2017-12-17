package com.mibaldi.cah.application

class App: android.app.Application() {

   val component: AppComponent by lazy {

        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
    override fun onCreate(){
        super.onCreate()
        component.inject(this)
    }
}