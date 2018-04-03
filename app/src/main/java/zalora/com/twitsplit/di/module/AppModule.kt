package zalora.com.twitsplit.di.module

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import zalora.com.twitsplit.App
import zalora.com.twitsplit.di.AppLifecycleCallbacksImpl
import zalora.com.twitsplit.di.AppLifecycleCallbacks
import zalora.com.twitsplit.di.scope.ApplicationContext
import zalora.com.twitsplit.ui.viewmodel.ViewModelFactory
import zalora.com.twitsplit.utils.TwitSplitString
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    //@JvmStatic
    @ApplicationContext
    fun provideApplication(app: App): Context = app

    @Singleton
    @Provides
    //@JvmStatic
    fun provideTwitSplitString(): TwitSplitString = TwitSplitString()

    @Singleton
    @Provides
    //@JvmStatic
    fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = AppLifecycleCallbacksImpl()

    @Singleton
    @Provides
    //@JvmStatic
    fun provideDisposable() = CompositeDisposable()

}