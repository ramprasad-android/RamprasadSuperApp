package `in`.rramprasad.superapp.core.bluetooth

interface BluetoothManager {
    fun startDiscovery()
    fun stopDiscovery()
    fun pair(deviceId: String)
}
