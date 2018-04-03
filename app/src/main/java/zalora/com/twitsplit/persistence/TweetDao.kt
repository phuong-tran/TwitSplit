package zalora.com.twitsplit.persistence

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import io.reactivex.Flowable
import android.arch.persistence.room.OnConflictStrategy



@Dao
interface TweetDao {
    companion object {
       const val SELECT_ALL_TWEETS:String = "SELECT * FROM tweets"
       const val COUNT:String = "SELECT COUNT(*) from tweets"
       const val DELETE_ALL_TWEET:String = "DELETE FROM tweets"
       const val DELETE_TWEET:String = "DELETE FROM tweets WHERE id = :id"
    }

    @Query(SELECT_ALL_TWEETS)
    fun fetchMessagesFlowable(): Flowable<MutableList<Tweet>>

    @Query(SELECT_ALL_TWEETS)
    fun fetchMessagesLiveData(): LiveData<MutableList<Tweet>>

    @Query(SELECT_ALL_TWEETS)
    fun getAll(): List<Tweet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweet(tweet: Tweet)

    @Delete
    fun deleteTweet(tweet: Tweet)

    @Query(COUNT)
    fun getCount(): Int

    @Query(DELETE_ALL_TWEET)
    fun deleteAll()

    @Query(DELETE_TWEET)
    fun deleteById(id:Long) :Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweets(tweet: List<Tweet>)

}