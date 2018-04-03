package zalora.com.twitsplit.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import zalora.com.twitsplit.di.scope.ViewModelKey
import zalora.com.twitsplit.ui.fragment.SettingsFragment
import zalora.com.twitsplit.ui.fragment.TwitSplitFragment
import zalora.com.twitsplit.ui.viewmodel.TweetsViewModel
import zalora.com.twitsplit.ui.viewmodel.ViewModelFactory

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    internal abstract fun contributeTwitSplitFragment(): TwitSplitFragment

    @ContributesAndroidInjector
    internal abstract fun contributeSettingsFragment(): SettingsFragment


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TweetsViewModel::class)
    abstract fun bindTweetsViewModel(viewModel: TweetsViewModel): ViewModel
}