package sarankar.app.jetnewsapp

import android.app.Application
import sarankar.app.jetnewsapp.data.AppContainer
import sarankar.app.jetnewsapp.data.AppContainerImpl

class JetNewsApplication: Application() {
    companion object{
        const val JETNEWS_APP_URI = "https://sarankar.developers.apps/jetnews"
    }

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}