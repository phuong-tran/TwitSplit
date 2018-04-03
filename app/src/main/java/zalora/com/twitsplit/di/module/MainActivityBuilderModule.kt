package zalora.com.twitsplit.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import zalora.com.twitsplit.ui.activity.MainActivity
@Suppress("unused")
@Module
abstract class MainActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun bindMainActivity(): MainActivity
}