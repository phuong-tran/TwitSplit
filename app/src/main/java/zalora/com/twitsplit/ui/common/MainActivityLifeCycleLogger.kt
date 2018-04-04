package zalora.com.twitsplit.ui.common

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import org.slf4j.LoggerFactory


class MainActivityLifeCycleLogger : LifecycleObserver {

    private val LOG = LoggerFactory.getLogger(MainActivityLifeCycleLogger::class.java)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    internal fun onCreate() {
        LOG.debug("MainActivity onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    internal fun onStart() {
        LOG.debug("MainActivity onStart")
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    internal fun onStop() {
        LOG.debug("MainActivity onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    internal fun onPause() {
        LOG.debug("MainActivity onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun onResume() {
        LOG.debug("MainActivity onResume")
    }

}