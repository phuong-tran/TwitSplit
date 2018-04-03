package zalora.com.twitsplit.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import zalora.com.twitsplit.App
import zalora.com.twitsplit.di.module.AppModule
import zalora.com.twitsplit.di.module.DataBaseModule
import zalora.com.twitsplit.di.module.MainActivityBuilderModule
import javax.inject.Singleton

@Singleton
@Component
(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    AndroidInjectionModule::class,
    MainActivityBuilderModule::class,
    DataBaseModule::class]
)

interface AppComponent: AndroidInjector<App>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}
