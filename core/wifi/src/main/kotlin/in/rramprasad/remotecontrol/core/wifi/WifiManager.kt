package `in`.rramprasad.remotecontrol.core.wifi

interface WifiManager {
    fun scanNetworks()
    fun connect(ssid: String, password: String)
}
