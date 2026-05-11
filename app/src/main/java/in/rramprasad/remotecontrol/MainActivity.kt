package `in`.rramprasad.remotecontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import `in`.rramprasad.remotecontrol.ui.screens.DevicesScreen
import `in`.rramprasad.remotecontrol.ui.screens.EspRemoteScreen
import `in`.rramprasad.remotecontrol.ui.screens.LogsScreen
import `in`.rramprasad.remotecontrol.ui.screens.ProfileScreen
import `in`.rramprasad.remotecontrol.ui.theme.RemoteControlTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import `in`.rramprasad.remotecontrol.ui.screens.NotificationsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RemoteControlTheme {
                var currentScreen by remember { mutableStateOf("home") }
                
                when (currentScreen) {
                    "home" -> EspRemoteScreen(onScreenSelected = { currentScreen = it })
                    "devices" -> DevicesScreen(onScreenSelected = { currentScreen = it })
                    "logs" -> LogsScreen(onScreenSelected = { currentScreen = it })
                    "profile" -> ProfileScreen(onScreenSelected = { currentScreen = it })
                    "notifications" -> NotificationsScreen(onBack = { currentScreen = "home" })
                }
            }
        }
    }
}
