package `in`.rramprasad.superapp.core.navigation

import kotlinx.serialization.Serializable
import androidx.navigation3.runtime.NavEntry

@Serializable
sealed interface Destination {
    @Serializable data object Dashboard : Destination
    @Serializable data object Home : Destination
    @Serializable data object Devices : Destination
    @Serializable data object Logs : Destination
    @Serializable data object Profile : Destination
    @Serializable data object Notifications : Destination
    
    // New Apps for the Super App
    @Serializable data object Maps : Destination
    @Serializable data object SmartHome : Destination
    @Serializable data object PersonalFinance : Destination
}

/**
 * Interface to be implemented by feature modules to provide their navigation entries.
 */
interface NavEntryProvider {
    fun provideEntry(key: Destination): NavEntry<out Destination>?
}
