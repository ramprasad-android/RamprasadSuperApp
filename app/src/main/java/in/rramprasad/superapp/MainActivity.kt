package `in`.rramprasad.superapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import `in`.rramprasad.superapp.core.navigation.Destination
import `in`.rramprasad.superapp.core.navigation.NavEntryProvider
import `in`.rramprasad.superapp.features.home.HomeNavEntryProvider
import `in`.rramprasad.superapp.features.devices.DevicesNavEntryProvider
import `in`.rramprasad.superapp.features.logs.LogsNavEntryProvider
import `in`.rramprasad.superapp.features.profile.ProfileNavEntryProvider
import `in`.rramprasad.superapp.features.notifications.NotificationsNavEntryProvider
import `in`.rramprasad.superapp.features.dashboard.DashboardNavEntryProvider
import `in`.rramprasad.superapp.ui.theme.RamprasadSuperAppTheme

/**
 * MainActivity for the Ramprasad Super App
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RamprasadSuperAppTheme {
                val backStack = remember { mutableStateListOf<Destination>(Destination.Dashboard) }
                
                val providers = remember {
                    listOf(
                        DashboardNavEntryProvider(onAppSelected = { backStack.add(it) }),
                        HomeNavEntryProvider(onScreenSelected = { backStack.add(it) }),
                        DevicesNavEntryProvider(onScreenSelected = { backStack.add(it) }),
                        LogsNavEntryProvider(onScreenSelected = { backStack.add(it) }),
                        ProfileNavEntryProvider(onScreenSelected = { backStack.add(it) }),
                        NotificationsNavEntryProvider(onBack = { 
                            if (backStack.size > 1) backStack.removeAt(backStack.lastIndex) 
                        })
                    )
                }

                NavDisplay(
                    backStack = backStack,
                    onBack = { 
                        if (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        } else {
                            finish()
                        }
                    },
                    entryProvider = { key ->
                        providers.firstNotNullOfOrNull { it.provideEntry(key) } as NavEntry<Destination>?
                            ?: throw IllegalArgumentException("Unknown key: $key")
                    }
                )
            }
        }
    }
}
