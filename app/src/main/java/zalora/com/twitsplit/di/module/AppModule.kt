package zalora.com.twitsplit.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import zalora.com.twitsplit.App
import zalora.com.twitsplit.di.scope.ApplicationContext
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides

    @ApplicationContext
    fun provideApplication(app: App): Context = app

    @Singleton
    @Provides

    fun provideDisposable() = CompositeDisposable()


    @Singleton
    @Provides
    fun provideEventBus() = EventBus.getDefault()

}