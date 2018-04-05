package zalora.com.twitsplit

class Config {
    companion object {
        // DEMO we don't want to use EventBus for insert, delete from database
        const val USE_EVENT_BUS: Boolean = false
    }
}