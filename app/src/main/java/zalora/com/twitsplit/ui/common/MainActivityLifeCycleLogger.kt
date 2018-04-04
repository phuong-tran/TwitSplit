package zalora.com.twitsplit.ui.common

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.slf4j.LoggerFactory
import zalora.com.twitsplit.event.TweetEvent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityLifeCycleLogger @Inject constructor(val eventBus: EventBus) : LifecycleObserver {

    private val LOG = LoggerFactory.getLogger(MainActivityLifeCycleLogger::class.java)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    internal fun onCreate() {
        LOG.debug("MainActivity onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    internal fun onStart() {
        LOG.debug("MainActivity onStart")
        eventBus.register(this)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    internal fun onStop() {
        LOG.debug("MainActivity onStop")
        eventBus.unregister(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    internal fun onPause() {
        LOG.debug("MainActivity onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun onResume() {
        LOG.debug("MainActivity onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun onDestroy() {
        LOG.debug("MainActivity onDestroy")
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: TweetEvent) {
        when(event.action) {
            TweetEvent.ACTION_FETCH_DATA -> {
                LOG.debug("Fetch Tweets: ACTION_FETCH_DATA")
            }

            TweetEvent.ACTION_INSERT_DATA -> {
                LOG.debug("Insert Tweets: ACTION_INSERT_DATA")
            }

            TweetEvent.ACTION_REMOVE_ALL_DATA -> {
                LOG.debug("Remove all Tweets: ACTION_REMOVE_ALL_DATA")
            }
        }
    }

}