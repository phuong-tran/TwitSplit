
package zalora.com.twitsplit

import dagger.android.DaggerApplication
import zalora.com.twitsplit.di.applyAutoInjector
import zalora.com.twitsplit.di.component.DaggerAppComponent

class App: DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
    }


}