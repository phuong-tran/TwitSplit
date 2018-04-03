package zalora.com.twitsplit.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.slf4j.LoggerFactory
import zalora.com.twitsplit.persistence.Tweet
import zalora.com.twitsplit.persistence.TweetDao
import zalora.com.twitsplit.utils.TwitSplitString
import javax.inject.Inject
import javax.inject.Singleton
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe


@Singleton
class TweetsViewModel @Inject constructor(private val tweetDao: TweetDao, private val disposable: CompositeDisposable, private val twitSplitString: TwitSplitString) : ViewModel() {
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
                                    tweets!!.value = it
                                },
                                { error ->
                                    LOG.debug("Unable to fetch tweets", error)

                                }
                        ))

    }

    override fun onCleared() {
        if (disposable != null && !disposable.isDisposed) {
            disposable.clear()
        }
    }
}