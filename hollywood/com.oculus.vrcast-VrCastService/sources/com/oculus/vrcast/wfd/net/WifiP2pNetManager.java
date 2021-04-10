package com.oculus.vrcast.wfd.net;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pWfdInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.qualcomm.wfd.WfdDevice;
import com.qualcomm.wfd.WfdEnums$NetType;
import com.qualcomm.wfd.WfdEnums$WFDDeviceType;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

public class WifiP2pNetManager {
    static final String TAG = "WifiP2pNetManager";
    private WifiP2pManager.Channel mChannel;
    private volatile boolean mConnected;
    private final Collection<WifiP2pNetDevice> mConnectedPeers = new ArrayList();
    private Context mContext;
    private volatile boolean mEnabled;
    private Handler mEventHandler;
    private volatile WifiP2pInfo mLocalConnectionInfo;
    private final WifiP2pNetDevice mLocalDevice = new WifiP2pNetDevice(this, null);
    private WifiP2pManager mManager;
    private final Collection<WifiP2pNetDevice> mPeers = new ArrayList();
    private HandlerThread mProcessThread;
    private final WifiDirectBroadcastReceiver mReceiver;

    public WifiP2pNetManager(Handler handler, Context context) {
        Log.v(TAG, "constructor");
        this.mContext = context;
        this.mEventHandler = handler;
        this.mProcessThread = new HandlerThread(TAG);
        this.mProcessThread.start();
        this.mManager = (WifiP2pManager) this.mContext.getSystemService("wifip2p");
        this.mChannel = this.mManager.initialize(this.mContext, this.mProcessThread.getLooper(), new WifiP2pManager.ChannelListener() {
            /* class com.oculus.vrcast.wfd.net.WifiP2pNetManager.AnonymousClass1 */
            private boolean retryChannel = false;

            public void onChannelDisconnected() {
                Log.d(WifiP2pNetManager.TAG, "onChannelDisconnected() called");
                if (WifiP2pNetManager.this.mManager == null || this.retryChannel) {
                    synchronized (WifiP2pNetManager.this) {
                        WifiP2pNetManager.this.mPeers.clear();
                        WifiP2pNetManager.this.mConnectedPeers.clear();
                        WifiP2pNetManager.this.mEventHandler.obtainMessage(WifiConstants.WIFI_UTIL_CHANNAL_LOST_PERMANENTLY, WifiP2pNetManager.this.getNetType().ordinal(), 0).sendToTarget();
                    }
                    return;
                }
                WifiP2pNetManager.this.mEventHandler.obtainMessage(WifiConstants.WIFI_UTIL_RETRY_CHANNEL, WifiP2pNetManager.this.getNetType().ordinal(), 0).sendToTarget();
                this.retryChannel = true;
                WifiP2pNetManager wifiP2pNetManager = WifiP2pNetManager.this;
                wifiP2pNetManager.mChannel = wifiP2pNetManager.mManager.initialize(WifiP2pNetManager.this.mContext, WifiP2pNetManager.this.mProcessThread.getLooper(), this);
            }
        });
        this.mReceiver = new WifiDirectBroadcastReceiver(this);
    }

    public void destroy() {
        stopPeerDiscovery(ResultListener.NullListener);
        this.mProcessThread.quitSafely();
    }

    /* access modifiers changed from: package-private */
    public WfdDevice convertToWfdDevice(WifiP2pNetDevice wifiP2pNetDevice) {
        WifiP2pDevice wifiP2pDevice = wifiP2pNetDevice.mDevice;
        if (wifiP2pDevice == null) {
            Log.e(TAG, "convertToWfdDevice Something amiss!! wifiP2pDevice is null");
            return null;
        } else if (wifiP2pDevice.wfdInfo == null) {
            Log.e(TAG, "convertToWfdDevice Something fishy!! WFDInfo is null for device");
            return null;
        } else {
            WfdDevice wfdDevice = new WfdDevice();
            wfdDevice.netType = getNetType().ordinal();
            wfdDevice.deviceType = wifiP2pDevice.wfdInfo.getDeviceType();
            wfdDevice.macAddress = wifiP2pDevice.deviceAddress;
            Log.d(TAG, "convertToWfdDevice: device macAddress= " + wfdDevice.macAddress);
            wfdDevice.deviceName = wifiP2pDevice.deviceName;
            wfdDevice.ipAddress = wifiP2pNetDevice.getIp();
            if (wfdDevice.ipAddress == null) {
                Log.e(TAG, "convertToWfdDevice- no ipAddress was found");
            }
            wfdDevice.rtspPort = wifiP2pDevice.wfdInfo.getControlPort();
            wfdDevice.isAvailableForSession = wifiP2pDevice.wfdInfo.isSessionAvailable();
            wfdDevice.addressOfAP = null;
            wfdDevice.coupledSinkStatus = 0;
            wfdDevice.preferredConnectivity = 0;
            return wfdDevice;
        }
    }

    public void send_wfd_set(boolean z, WfdEnums$WFDDeviceType wfdEnums$WFDDeviceType) {
        int i;
        if (wfdEnums$WFDDeviceType != WfdEnums$WFDDeviceType.SOURCE) {
            if (wfdEnums$WFDDeviceType == WfdEnums$WFDDeviceType.PRIMARY_SINK) {
                i = 1;
            } else if (wfdEnums$WFDDeviceType == WfdEnums$WFDDeviceType.SECONDARY_SINK) {
                i = 2;
            } else if (wfdEnums$WFDDeviceType == WfdEnums$WFDDeviceType.SOURCE_PRIMARY_SINK) {
                i = 3;
            }
            int i2 = i | WifiConstants.CP_SUPPORTED;
            if (wfdEnums$WFDDeviceType == WfdEnums$WFDDeviceType.SOURCE || wfdEnums$WFDDeviceType == WfdEnums$WFDDeviceType.SOURCE_PRIMARY_SINK) {
                i2 |= WifiConstants.AUDIO_ONLY_SUPPORTED_AT_SOURCE;
            }
            WifiP2pWfdInfo wifiP2pWfdInfo = new WifiP2pWfdInfo(i2, (int) WifiConstants.DEFAULT_SESSION_MGMT_CTRL_PORT, 54);
            wifiP2pWfdInfo.setWfdEnabled(true);
            wifiP2pWfdInfo.setSessionAvailable(z);
            if (wfdEnums$WFDDeviceType == WfdEnums$WFDDeviceType.SOURCE || wfdEnums$WFDDeviceType == WfdEnums$WFDDeviceType.SOURCE_PRIMARY_SINK) {
                wifiP2pWfdInfo.setCoupledSinkSupportAtSource(false);
            }
            if (wfdEnums$WFDDeviceType == WfdEnums$WFDDeviceType.PRIMARY_SINK || wfdEnums$WFDDeviceType == WfdEnums$WFDDeviceType.SECONDARY_SINK || wfdEnums$WFDDeviceType == WfdEnums$WFDDeviceType.SOURCE_PRIMARY_SINK) {
                wifiP2pWfdInfo.setCoupledSinkSupportAtSink(true);
            }
            this.mManager.setWFDInfo(this.mChannel, wifiP2pWfdInfo, new WifiP2pManager.ActionListener() {
                /* class com.oculus.vrcast.wfd.net.WifiP2pNetManager.AnonymousClass2 */

                public void onSuccess() {
                    Log.d(WifiP2pNetManager.TAG, "Successfully set WFD IE Params");
                }

                public void onFailure(int i) {
                    Log.d(WifiP2pNetManager.TAG, "Failed to set WFD IE Params: " + i + ".");
                }
            });
        }
        i = 0;
        int i22 = i | WifiConstants.CP_SUPPORTED;
        i22 |= WifiConstants.AUDIO_ONLY_SUPPORTED_AT_SOURCE;
        WifiP2pWfdInfo wifiP2pWfdInfo2 = new WifiP2pWfdInfo(i22, (int) WifiConstants.DEFAULT_SESSION_MGMT_CTRL_PORT, 54);
        wifiP2pWfdInfo2.setWfdEnabled(true);
        wifiP2pWfdInfo2.setSessionAvailable(z);
        wifiP2pWfdInfo2.setCoupledSinkSupportAtSource(false);
        wifiP2pWfdInfo2.setCoupledSinkSupportAtSink(true);
        this.mManager.setWFDInfo(this.mChannel, wifiP2pWfdInfo2, new WifiP2pManager.ActionListener() {
            /* class com.oculus.vrcast.wfd.net.WifiP2pNetManager.AnonymousClass2 */

            public void onSuccess() {
                Log.d(WifiP2pNetManager.TAG, "Successfully set WFD IE Params");
            }

            public void onFailure(int i) {
                Log.d(WifiP2pNetManager.TAG, "Failed to set WFD IE Params: " + i + ".");
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00a2 A[SYNTHETIC, Splitter:B:51:0x00a2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getPeerIP(java.lang.String r13) {
        /*
        // Method dump skipped, instructions count: 171
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrcast.wfd.net.WifiP2pNetManager.getPeerIP(java.lang.String):java.lang.String");
    }

    public static String getLocalIp() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp() && nextElement.getName().contains("p2p")) {
                    Enumeration<NetworkInterface> subInterfaces = nextElement.getSubInterfaces();
                    while (subInterfaces.hasMoreElements()) {
                        Enumeration<InetAddress> inetAddresses = subInterfaces.nextElement().getInetAddresses();
                        while (true) {
                            if (inetAddresses.hasMoreElements()) {
                                InetAddress nextElement2 = inetAddresses.nextElement();
                                if (nextElement2 instanceof Inet4Address) {
                                    Log.e(TAG, "IP address of device on virtual interface " + nextElement.getName() + " is " + nextElement2.getHostAddress());
                                    return nextElement2.getHostAddress();
                                }
                            }
                        }
                    }
                    Enumeration<InetAddress> inetAddresses2 = nextElement.getInetAddresses();
                    while (inetAddresses2.hasMoreElements()) {
                        InetAddress nextElement3 = inetAddresses2.nextElement();
                        if (nextElement3 instanceof Inet4Address) {
                            Log.e(TAG, "IP address of device on physical interface " + nextElement.getName() + " is " + nextElement3.getHostAddress());
                            return nextElement3.getHostAddress();
                        }
                    }
                    continue;
                }
            }
            return null;
        } catch (SocketException unused) {
            Log.e(TAG, "SocketException");
            return null;
        }
    }

    public void disconnect(ResultListener resultListener) {
        Log.d(TAG, "disconnect() called");
        this.mManager.removeGroup(this.mChannel, wrapResultListener(resultListener));
    }

    public WfdEnums$NetType getNetType() {
        return WfdEnums$NetType.WIFI_P2P;
    }

    public void startBroadcastListening() {
        Log.v(TAG, "startBroadcastListening");
        this.mReceiver.registerReceiver(this.mContext);
    }

    public void stopBroadcastListening() {
        Log.v(TAG, "stopBroadcastListening");
        this.mReceiver.unregisterReceiver(this.mContext);
    }

    /* access modifiers changed from: package-private */
    public void onDisconnected() {
        Log.v(TAG, "onDisconnected");
        synchronized (this) {
            this.mPeers.clear();
            this.mConnectedPeers.clear();
        }
        this.mConnected = false;
        this.mEventHandler.obtainMessage(WifiConstants.WIFI_UTIL_DISCONNECTED, getNetType().ordinal(), 0).sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
        Log.v(TAG, "onConnectionInfoAvailable " + wifiP2pInfo);
        this.mLocalConnectionInfo = wifiP2pInfo;
        this.mConnected = true;
        this.mEventHandler.obtainMessage(WifiConstants.WIFI_UTIL_CONNECTION_INFO, getNetType().ordinal(), 0, wifiP2pInfo).sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void onPeerListUpdate(Collection<WifiP2pDevice> collection) {
        ArrayList arrayList;
        Log.v(TAG, "onPeerListUpdate");
        synchronized (this) {
            this.mPeers.clear();
            for (WifiP2pDevice wifiP2pDevice : collection) {
                this.mPeers.add(new WifiP2pNetDevice(this, wifiP2pDevice));
            }
            arrayList = new ArrayList(this.mPeers);
        }
        this.mEventHandler.obtainMessage(WifiConstants.WIFI_UTIL_PEERS_CHANGED, getNetType().ordinal(), 0, arrayList).sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void onConnectedListUpdate(Collection<WifiP2pDevice> collection) {
        ArrayList arrayList;
        Log.v(TAG, "onConnectedListUpdate");
        synchronized (this) {
            this.mConnectedPeers.clear();
            for (WifiP2pDevice wifiP2pDevice : collection) {
                this.mConnectedPeers.add(new WifiP2pNetDevice(this, wifiP2pDevice));
            }
            arrayList = new ArrayList(this.mConnectedPeers);
        }
        this.mEventHandler.obtainMessage(WifiConstants.WIFI_UTIL_CONNECTED_PEERS_CHANGED, getNetType().ordinal(), 0, arrayList).sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void onLocalDeviceChanged(WifiP2pDevice wifiP2pDevice) {
        Log.v(TAG, "onLocalDeviceChanged");
        synchronized (this) {
            this.mLocalDevice.mDevice = wifiP2pDevice;
        }
        this.mEventHandler.obtainMessage(WifiConstants.WIFI_UTIL_LOCAL_DEVICE_CHANGED, getNetType().ordinal(), 0, this.mLocalDevice).sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void onStateChanged(int i) {
        Log.v(TAG, "onStateChanged " + i);
        synchronized (this) {
            this.mEnabled = i == 2;
        }
        this.mEventHandler.obtainMessage(WifiConstants.WIFI_UTIL_P2P_STATE_CHANGED, getNetType().ordinal(), i).sendToTarget();
    }

    public void setMiracastMode(int i) {
        this.mManager.setMiracastMode(i);
    }

    public void createGroup(ResultListener resultListener) {
        this.mManager.createGroup(this.mChannel, wrapResultListener(resultListener));
    }

    public void removeGroup(ResultListener resultListener) {
        this.mManager.removeGroup(this.mChannel, wrapResultListener(resultListener));
    }

    public void discoveryPeers(ResultListener resultListener) {
        this.mManager.discoverPeers(this.mChannel, wrapResultListener(resultListener));
    }

    public void requestPeers() {
        this.mManager.requestPeers(this.mChannel, new WifiP2pManager.PeerListListener() {
            /* class com.oculus.vrcast.wfd.net.WifiP2pNetManager.AnonymousClass3 */

            public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
                WifiP2pNetManager.this.onPeerListUpdate(wifiP2pDeviceList.getDeviceList());
            }
        });
    }

    public void stopPeerDiscovery(ResultListener resultListener) {
        this.mManager.stopPeerDiscovery(this.mChannel, wrapResultListener(resultListener));
    }

    public void cancelConnect(ResultListener resultListener) {
        if (!this.mLocalDevice.hasDeviceInfo() || this.mLocalDevice.mDevice.status != 0) {
            this.mManager.cancelConnect(this.mChannel, wrapResultListener(resultListener));
        } else {
            disconnect(resultListener);
        }
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public boolean isConnected() {
        return this.mConnected;
    }

    public WifiP2pNetDevice getLocalDevice() {
        return this.mLocalDevice;
    }

    public WifiP2pInfo getLocalConnectionInfo() {
        return this.mLocalConnectionInfo;
    }

    private static WifiP2pManager.ActionListener wrapResultListener(final ResultListener resultListener) {
        return new WifiP2pManager.ActionListener() {
            /* class com.oculus.vrcast.wfd.net.WifiP2pNetManager.AnonymousClass4 */

            public void onSuccess() {
                ResultListener.this.onSuccess(null);
            }

            public void onFailure(int i) {
                ResultListener.this.onFailure(Integer.valueOf(i));
            }
        };
    }

    public WifiP2pManager getWifiManager() {
        return this.mManager;
    }

    public WifiP2pManager.Channel getChannel() {
        return this.mChannel;
    }
}
