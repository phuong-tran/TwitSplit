package zalora.com.twitsplit.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import org.slf4j.LoggerFactory
import zalora.com.twitsplit.R
import zalora.com.twitsplit.ui.common.MainActivityLifeCycleWatcher
import zalora.com.twitsplit.ui.common.NavigationController
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var navigationController: NavigationController

    private val LOG = LoggerFactory.getLogger(MainActivity::class.java)

    override fun supportFragmentInjector() = dispatchingAndroidInjector
    @Inject lateinit var mainActivityLifeCycleWatcher: MainActivityLifeCycleWatcher

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                navigationController.navigateToTwitSplitFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                navigationController.navigateToSettingFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            navigationController.navigateToTwitSplitFragment()
        }
        // DEMO MainActivity lifecycle watcher
        lifecycle.addObserver(mainActivityLifeCycleWatcher)
    }
}
