package com.kmozcan1.docebotest.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.kmozcan1.docebotest.R
import com.kmozcan1.docebotest.databinding.ActivityMainBinding
import com.kmozcan1.docebotest.ui.presentation.MainViewModel
import com.kmozcan1.docebotest.ui.viewstate.MainViewState
import com.kmozcan1.docebotest.ui.viewstate.MainViewState.State.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    var isConnectedToInternet: Boolean = false
        private set

    private lateinit var binding: ActivityMainBinding

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_DoceboTest)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.viewState.observe(this, observeViewState())
        viewModel.observeInternetConnection()
    }

    private fun observeViewState() = Observer<MainViewState> { viewState ->
        when (viewState.state) {
            ERROR -> {
                makeToast(viewState.errorMessage)
            }
            CONNECTION_CHANGE -> {
                isConnectedToInternet = viewState.isConnected
                val currentFragment = getCurrentFragment()
                // Handles BaseFragment connection change
                if (currentFragment is BaseFragment<*, *>) {
                    if (viewState.isConnected) {
                        currentFragment.onInternetConnected()
                    } else {
                        currentFragment.onInternetDisconnected()
                    }
                } else if (currentFragment is UserViewPagerFragment) {
                    // Handles ViewPager fragment connection change
                    val currentItemIndex = currentFragment.binding.userPager.currentItem
                    val childFragment = currentFragment.childFragmentManager.fragments[currentItemIndex] as BaseFragment<*, *>
                    if (viewState.isConnected) {
                        childFragment.onInternetConnected()
                    } else {
                        childFragment.onInternetDisconnected()
                    }
                }
            }
            LOADING -> TODO()
        }
    }

    fun makeToast(toastMessage: String?) {
        Toast.makeText(
            this,
            toastMessage,
            Toast.LENGTH_LONG
        ).show()
    }

    fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.fragments.first()?.childFragmentManager?.fragments?.get(0)
    }
}