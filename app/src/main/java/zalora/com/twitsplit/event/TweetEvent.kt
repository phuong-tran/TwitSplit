package zalora.com.twitsplit.event

class TweetEvent(val action:Int) {
    companion object {
        const val ACTION_FETCH_DATA:Int = 1
        const val ACTION_INSERT_DATA:Int = 2
        const val ACTION_REMOVE_ALL_DATA = 3
    }


}