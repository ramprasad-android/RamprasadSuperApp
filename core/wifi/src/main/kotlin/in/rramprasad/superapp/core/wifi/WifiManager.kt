package `in`.rramprasad.superapp.core.wifi

interface WifiManager {
    fun scanNetworks()
    fun connect(ssid: String, password: String)
}
