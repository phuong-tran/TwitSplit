package zalora.com.twitsplit.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import zalora.com.twitsplit.persistence.converters.DateConverter
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "tweets")
@TypeConverters(DateConverter::class)

class Tweet {
    companion object {
        fun buildTweet(message: String) : Tweet {
            var t = Tweet()
            t.message = message
            return t
        }
        const val FIELD_ID:String = "id"
        const val FIELD_MESSAGE:String = "message"
        const val FIELD_CREATED_DATE:String = "createdDate"
    }
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = FIELD_ID) var id: Long = 0
    @ColumnInfo(name = FIELD_MESSAGE) var message: String = ""
    @ColumnInfo(name = FIELD_CREATED_DATE) var createdDate: Date = Date()

}