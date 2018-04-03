
package zalora.com.twitsplit

import dagger.android.DaggerApplication
import zalora.com.twitsplit.di.AppLifecycleCallbacks
import zalora.com.twitsplit.di.applyAutoInjector
import zalora.com.twitsplit.di.component.DaggerAppComponent
import javax.inject.Inject

class App: DaggerApplication() {
    @Inject lateinit var appLifecycleCallbacks: AppLifecycleCallbacks

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
        appLifecycleCallbacks.onCreate(this)
    }

    override fun onTerminate() {
        appLifecycleCallbacks.onTerminate(this)
        super.onTerminate()
    }


}