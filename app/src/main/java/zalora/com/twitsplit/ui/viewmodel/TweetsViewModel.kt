package zalora.com.twitsplit.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import org.slf4j.LoggerFactory
import zalora.com.twitsplit.event.TweetEvent
import zalora.com.twitsplit.persistence.Tweet
import zalora.com.twitsplit.persistence.TweetDao
import zalora.com.twitsplit.utils.TwitSplitString
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TweetsViewModel @Inject constructor(
        private val tweetDao: TweetDao,
        private val twitSplitString: TwitSplitString,
        private val disposable: CompositeDisposable,
        private val eventBus: EventBus) : ViewModel() {

    private val LOG = LoggerFactory.getLogger(TweetsViewModel::class.java)

    val tweets: MutableLiveData<MutableList<Tweet>> = MutableLiveData()
    val lastMessage: MutableLiveData<String> = MutableLiveData()

    init {
        lastMessage.value = ""
    }

    fun fetchTweetsFlowable() {

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


    fun fetchTweetsMaybe() {
        disposable.add(tweetDao.fetchMessagesMaybe().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    tweets.value = it
                    eventBus.post(TweetEvent(TweetEvent.ACTION_FETCH_DATA))
                },
                {error ->
                    LOG.debug("Unable to fetch tweets", error)
                }
        ))


    }

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.clear()
        }
    }

    // In case we want to use EventBus
    fun insertMessage(text: String) {
        if (twitSplitString.isSingleLine(text, TwitSplitString.LIMIT_CHARACTERS)) {
            LOG.debug("Single Message")
            disposable.add(Completable.fromAction {
                tweetDao.insertTweet(Tweet.buildTweet(text))
                tweets.value!!.add(0, Tweet.buildTweet(text))
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
                eventBus.post(TweetEvent(TweetEvent.ACTION_INSERT_DATA))
                LOG.debug("Insert Single Message DONE")
            })
        } else {
            LOG.debug("Multi Messages")
            disposable.add(Completable.fromAction {
                val messages = mutableListOf<Tweet>()
                val estimateLines = twitSplitString.estimateLines(text, TwitSplitString.LIMIT_CHARACTERS)
                val lines = twitSplitString.splitMessage(twitSplitString.getWordArray(text), estimateLines, TwitSplitString.LIMIT_CHARACTERS)
                lines.forEach { it ->
                    messages.add(Tweet.buildTweet(it))
                }
                tweetDao.insertTweets(messages)
                tweets.value!!.addAll(0, messages)
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
                LOG.debug("Insert Multi Messages DONE")
                eventBus.post(TweetEvent(TweetEvent.ACTION_INSERT_DATA))
            })
        }

    }

    // In case we want to use EventBus
    fun removeAllMessages() {
        disposable.add(Completable.fromAction {
            tweetDao.deleteAll()
            tweets.value!!.clear()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            eventBus.post(TweetEvent(TweetEvent.ACTION_REMOVE_ALL_DATA))
        })
    }
}