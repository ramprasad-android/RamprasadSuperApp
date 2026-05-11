package `in`.rramprasad.remotecontrol.core.bluetooth

interface BluetoothManager {
    fun startDiscovery()
    fun stopDiscovery()
    fun pair(deviceId: String)
}
