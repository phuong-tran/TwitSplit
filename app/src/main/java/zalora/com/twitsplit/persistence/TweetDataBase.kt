package zalora.com.twitsplit.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(Tweet::class)], version = 1)
abstract class TweetDataBase : RoomDatabase() {
    abstract fun tweeDao(): TweetDao
}

