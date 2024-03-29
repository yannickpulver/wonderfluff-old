package com.yannickpulver.wonderfluff

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.yannickpulver.wonderfluff.ui.detail.PuppyDetail
import com.yannickpulver.wonderfluff.ui.list.PuppyList
import com.yannickpulver.wonderfluff.ui.theme.WonderfluffTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WonderfluffTheme {
                ProvideWindowInsets {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        AppNavigator()
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "puppyList") {
        composable("puppyList") { PuppyList(it.hiltViewModel(), navController) }
        composable(
            "puppyDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            PuppyDetail(
                it.arguments?.getInt("id"), it.hiltViewModel(), navController,
            )
        }
    }
}

@Composable
internal inline fun <reified T : ViewModel> NavBackStackEntry.hiltViewModel(): T {
    return ViewModelProvider(
        this.viewModelStore,
        HiltViewModelFactory(LocalContext.current, this)
    ).get(T::class.java)
}

