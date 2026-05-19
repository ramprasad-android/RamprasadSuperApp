package `in`.rramprasad.superapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination {
    @Serializable
    data object Home : Destination
    
    @Serializable
    data object Devices : Destination
    
    @Serializable
    data object Logs : Destination
    
    @Serializable
    data object Profile : Destination
    
    @Serializable
    data object Notifications : Destination
}