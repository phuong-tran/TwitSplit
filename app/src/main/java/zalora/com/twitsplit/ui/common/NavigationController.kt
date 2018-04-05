package zalora.com.twitsplit.ui.common

import zalora.com.twitsplit.R
import zalora.com.twitsplit.ui.activity.MainActivity
import zalora.com.twitsplit.ui.fragment.AboutFragment
import zalora.com.twitsplit.ui.fragment.TwitSplitFragment
import javax.inject.Inject

class NavigationController @Inject
constructor(mainActivity: MainActivity) {
    private val containerId: Int =R.id.container
    private val fragmentManager = mainActivity.supportFragmentManager

    fun navigateToTwitSplitFragment() {
        fragmentManager.beginTransaction()
                .replace(containerId, TwitSplitFragment.newInstance())
                .commitAllowingStateLoss()
    }

    fun navigateToAboutFragment() {
        fragmentManager.beginTransaction()
                .replace(containerId, AboutFragment.newInstance())
                .commitAllowingStateLoss()
    }
}