package zalora.com.twitsplit.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import zalora.com.twitsplit.App
import zalora.com.twitsplit.di.scope.ApplicationContext
import zalora.com.twitsplit.utils.TwitSplitString
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides

    @ApplicationContext
    fun provideApplication(app: App): Context = app

    @Singleton
    @Provides

    fun provideTwitSplitString(): TwitSplitString = TwitSplitString()

  /*@Singleton
    @Provides

    fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = AppLifecycleCallbacksImpl()*/

    @Singleton
    @Provides

    fun provideDisposable() = CompositeDisposable()

}