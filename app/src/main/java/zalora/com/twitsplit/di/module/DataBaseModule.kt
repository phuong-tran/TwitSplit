package zalora.com.twitsplit.di.module

import android.arch.persistence.room.Room
import android.content.Context
import zalora.com.twitsplit.di.scope.ApplicationContext
import dagger.Module
import dagger.Provides
import zalora.com.twitsplit.persistence.TweetDao
import zalora.com.twitsplit.persistence.TweetDataBase
import javax.inject.Singleton

@Suppress("unused")
@Module(includes = [(AppModule::class)])
class DataBaseModule {
    companion object {
        const val DATABASE_NAME = "TweetDB.dat"
    }
    @Singleton
    @Provides
    //@JvmStatic
    internal fun provideFeedMeDatabase(@ApplicationContext context: Context): TweetDataBase {
        return Room.databaseBuilder(context.applicationContext, TweetDataBase::class.java, DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    //@JvmStatic
    internal fun provideTweetDao(db: TweetDataBase): TweetDao {
        return db.tweeDao()
    }
}
