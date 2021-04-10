package com.oculus.vrcast.wfd.net;

import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pWfdInfo;
import android.util.Log;
import com.qualcomm.wfd.WfdDevice;
import com.qualcomm.wfd.WfdEnums$NetType;
import com.qualcomm.wfd.WfdEnums$WFDDeviceType;

public class WifiP2pNetDevice {
    static final String TAG = "WifiP2pNetDevice";
    private WifiP2pConfig mConfig;
    WifiP2pDevice mDevice;
    WifiP2pNetManager mManager;

    WifiP2pNetDevice(WifiP2pNetManager wifiP2pNetManager, WifiP2pDevice wifiP2pDevice) {
        this.mManager = wifiP2pNetManager;
        this.mDevice = wifiP2pDevice;
    }

    public WfdEnums$NetType getNetType() {
        return WfdEnums$NetType.WIFI_P2P;
    }

    public WifiP2pNetManager getNetTypeManager() {
        return this.mManager;
    }

    public void setConfig(WifiP2pConfig wifiP2pConfig) {
        this.mConfig = wifiP2pConfig;
    }

    public void connect(final ResultListener resultListener) {
        Log.v(TAG, "connect");
        WifiP2pConfig wifiP2pConfig = this.mConfig;
        if (wifiP2pConfig == null) {
            this.mConfig = new WifiP2pConfig();
            WifiP2pConfig wifiP2pConfig2 = this.mConfig;
            wifiP2pConfig2.deviceAddress = this.mDevice.deviceAddress;
            wifiP2pConfig2.wps.setup = 0;
            Log.d(TAG, "group owner intent value: " + this.mConfig.groupOwnerIntent);
        } else {
            wifiP2pConfig.deviceAddress = this.mDevice.deviceAddress;
        }
        getNetTypeManager().getWifiManager().connect(getNetTypeManager().getChannel(), this.mConfig, new WifiP2pManager.ActionListener() {
            /* class com.oculus.vrcast.wfd.net.WifiP2pNetDevice.AnonymousClass1 */

            public void onSuccess() {
                resultListener.onSuccess(null);
            }

            public void onFailure(int i) {
                resultListener.onFailure(Integer.valueOf(i));
            }
        });
    }

    public void disconnect(ResultListener resultListener) {
        Log.v(TAG, "disconnect");
        getNetTypeManager().disconnect(resultListener);
    }

    public boolean isStatusConnected() {
        return this.mDevice.status == 0;
    }

    public boolean isStatusFailed() {
        return this.mDevice.status == 2;
    }

    public String getStatusString() {
        Log.d(TAG, "getDeviceStatus:" + this.mDevice.status);
        int i = this.mDevice.status;
        if (i == 0) {
            return "Connected";
        }
        if (i == 1) {
            return "Invited";
        }
        if (i == 2) {
            return "Failed";
        }
        if (i != 3) {
            return i != 4 ? "Unknown" : "Unavailable";
        }
        return "Available";
    }

    public boolean isLocalDevice() {
        String str;
        WifiP2pDevice wifiP2pDevice = this.mDevice;
        return (wifiP2pDevice == null || (str = wifiP2pDevice.deviceAddress) == null || !str.equals(getNetTypeManager().getLocalDevice().getAddress())) ? false : true;
    }

    public boolean isWifiDisplay() {
        WifiP2pWfdInfo wifiP2pWfdInfo = this.mDevice.wfdInfo;
        if (wifiP2pWfdInfo == null || !wifiP2pWfdInfo.isWfdEnabled() || (this.mDevice.wfdInfo.getDeviceType() != 1 && this.mDevice.wfdInfo.getDeviceType() != 3)) {
            return false;
        }
        return true;
    }

    public String getIp() {
        if (isLocalDevice()) {
            Log.w(TAG, "getIp for local device, return null");
            return null;
        }
        WifiP2pInfo localConnectionInfo = getNetTypeManager().getLocalConnectionInfo();
        if (localConnectionInfo == null || localConnectionInfo.isGroupOwner) {
            Log.v(TAG, "get ip from arp");
            return WifiP2pNetManager.getPeerIP(this.mDevice.deviceAddress);
        }
        Log.v(TAG, "get ip for group owner");
        return localConnectionInfo.groupOwnerAddress.getHostAddress();
    }

    public WfdDevice convertToWfdDevice() {
        return getNetTypeManager().convertToWfdDevice(this);
    }

    public String getName() {
        return this.mDevice.deviceName;
    }

    public String getAddress() {
        return this.mDevice.deviceAddress;
    }

    public boolean supportWfd() {
        WifiP2pDevice wifiP2pDevice = this.mDevice;
        return (wifiP2pDevice == null || wifiP2pDevice.wfdInfo == null) ? false : true;
    }

    public WfdEnums$WFDDeviceType getWfdType() {
        WifiP2pWfdInfo wifiP2pWfdInfo;
        WifiP2pDevice wifiP2pDevice = this.mDevice;
        if (wifiP2pDevice == null || (wifiP2pWfdInfo = wifiP2pDevice.wfdInfo) == null) {
            return WfdEnums$WFDDeviceType.UNKNOWN;
        }
        int deviceType = wifiP2pWfdInfo.getDeviceType();
        if (deviceType == 0) {
            return WfdEnums$WFDDeviceType.SOURCE;
        }
        if (deviceType == 1) {
            return WfdEnums$WFDDeviceType.PRIMARY_SINK;
        }
        if (deviceType == 2) {
            return WfdEnums$WFDDeviceType.SECONDARY_SINK;
        }
        if (deviceType != 3) {
            return WfdEnums$WFDDeviceType.UNKNOWN;
        }
        return WfdEnums$WFDDeviceType.SOURCE_PRIMARY_SINK;
    }

    public boolean hasDeviceInfo() {
        return this.mDevice != null;
    }

    public boolean isWfdSessionAvailable() {
        WifiP2pWfdInfo wifiP2pWfdInfo = this.mDevice.wfdInfo;
        return wifiP2pWfdInfo != null && wifiP2pWfdInfo.isSessionAvailable();
    }

    public boolean isGroupOwner() {
        WifiP2pDevice wifiP2pDevice = this.mDevice;
        return wifiP2pDevice != null && wifiP2pDevice.isGroupOwner();
    }

    public String toString() {
        return "WifiP2pNetDevice: " + this.mDevice.toString();
    }
}
