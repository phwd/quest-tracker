package com.oculus.vrcast.wfd;

import android.util.Log;
import com.oculus.vrcast.CastDevice;
import com.oculus.vrcast.DiscoverProtocol;
import com.oculus.vrcast.wfd.net.WifiP2pNetDevice;

public class WfdCastDevice extends CastDevice {
    private static final String TAG = "WfdCastDevice";
    private WifiP2pNetDevice mNetDevice;
    private CastDevice.State mState = CastDevice.State.FOUND;

    @Override // com.oculus.vrcast.CastDevice
    public String getModelName() {
        return "";
    }

    public WfdCastDevice(WifiP2pNetDevice wifiP2pNetDevice) {
        this.mDiscoverProtocol = DiscoverProtocol.WIFI_P2P;
        this.mNetDevice = wifiP2pNetDevice;
    }

    @Override // com.oculus.vrcast.CastDevice
    public CastDevice.State getState() {
        return this.mState;
    }

    @Override // com.oculus.vrcast.CastDevice
    public void setState(CastDevice.State state) {
        this.mState = state;
        String str = TAG;
        Log.d(str, "setState to " + state.name());
    }

    public boolean canStartSession() {
        String str = TAG;
        Log.d(str, "canStartSession hasDeviceInfo " + this.mNetDevice.hasDeviceInfo() + " supportWfd " + this.mNetDevice.supportWfd() + " ip " + this.mNetDevice.getIp() + " localDevInfo " + this.mNetDevice.getNetTypeManager().getLocalConnectionInfo());
        return this.mNetDevice.hasDeviceInfo() && this.mNetDevice.supportWfd() && this.mNetDevice.getNetTypeManager().getLocalConnectionInfo() != null;
    }

    @Override // com.oculus.vrcast.CastDevice
    public String getName() {
        return this.mNetDevice.getName();
    }

    @Override // com.oculus.vrcast.CastDevice
    public String getId() {
        return this.mNetDevice.getAddress();
    }

    public static boolean deviceEquals(WfdCastDevice wfdCastDevice, WfdCastDevice wfdCastDevice2) {
        if (wfdCastDevice == null || wfdCastDevice2 == null) {
            return wfdCastDevice == wfdCastDevice2;
        }
        return wfdCastDevice.isSameNetDevice(wfdCastDevice2.mNetDevice);
    }

    public boolean isSameNetDevice(WifiP2pNetDevice wifiP2pNetDevice) {
        return this.mNetDevice.getAddress().equals(wifiP2pNetDevice.getAddress());
    }

    public void updateNetDevice(WifiP2pNetDevice wifiP2pNetDevice) {
        this.mNetDevice = wifiP2pNetDevice;
    }

    public WifiP2pNetDevice getNetDevice() {
        return this.mNetDevice;
    }
}
