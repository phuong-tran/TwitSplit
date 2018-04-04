package zalora.com.twitsplit.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import org.slf4j.LoggerFactory
import zalora.com.twitsplit.event.TweetEvent
import zalora.com.twitsplit.persistence.Tweet
import zalora.com.twitsplit.persistence.TweetDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TweetsViewModel @Inject constructor(
        private val tweetDao: TweetDao,
        private val disposable: CompositeDisposable,
        private val eventBus: EventBus) : ViewModel() {

    private val LOG = LoggerFactory.getLogger(TweetsViewModel::class.java)

    val tweets: MutableLiveData<MutableList<Tweet>> = MutableLiveData()
    val lastMessage: MutableLiveData<String> = MutableLiveData()

    init {
        lastMessage.value = ""
    }

    fun fetchTweets() {

        disposable.add(
                tweetDao.fetchMessagesFlowable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    tweets.value = it
                                    eventBus.post(TweetEvent(TweetEvent.ACTION_FETCH_DATA))
                                },
                                { error ->
                                    LOG.debug("Unable to fetch tweets", error)

                                }
                        ))

    }

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.clear()
        }
    }
}