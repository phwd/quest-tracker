package org.webrtc;

import X.AnonymousClass006;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import java.util.ArrayList;
import java.util.List;

public class NetworkMonitorAutoDetect extends BroadcastReceiver {
    public static final int INVALID_NET_ID = -1;
    public static final String TAG = "NetworkMonitorAutoDetect";
    public final ConnectivityManager.NetworkCallback allNetworkCallback;
    public ConnectionType connectionType;
    public ConnectivityManagerDelegate connectivityManagerDelegate;
    public final Context context;
    public final IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    public boolean isRegistered;
    public final ConnectivityManager.NetworkCallback mobileNetworkCallback;
    public final Observer observer;
    public WifiManagerDelegate wifiManagerDelegate;
    public String wifiSSID;

    public enum ConnectionType {
        CONNECTION_UNKNOWN,
        CONNECTION_ETHERNET,
        CONNECTION_WIFI,
        CONNECTION_4G,
        CONNECTION_3G,
        CONNECTION_2G,
        CONNECTION_BLUETOOTH,
        CONNECTION_NONE
    }

    public static class ConnectivityManagerDelegate {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public final ConnectivityManager connectivityManager;

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        @SuppressLint({"NewApi"})
        private NetworkInformation networkToInfo(Network network) {
            LinkProperties linkProperties = this.connectivityManager.getLinkProperties(network);
            return new NetworkInformation(linkProperties.getInterfaceName(), NetworkMonitorAutoDetect.getConnectionType(getNetworkState(network)), NetworkMonitorAutoDetect.networkToNetId(network), getIPAddresses(linkProperties));
        }

        @SuppressLint({"NewApi"})
        public Network[] getAllNetworks() {
            ConnectivityManager connectivityManager2 = this.connectivityManager;
            if (connectivityManager2 == null) {
                return new Network[0];
            }
            return connectivityManager2.getAllNetworks();
        }

        @SuppressLint({"NewApi"})
        public boolean hasInternetCapability(Network network) {
            NetworkCapabilities networkCapabilities;
            ConnectivityManager connectivityManager2 = this.connectivityManager;
            if (connectivityManager2 == null || (networkCapabilities = connectivityManager2.getNetworkCapabilities(network)) == null || !networkCapabilities.hasCapability(12)) {
                return false;
            }
            return true;
        }

        @SuppressLint({"NewApi"})
        public void registerNetworkCallback(ConnectivityManager.NetworkCallback networkCallback) {
            this.connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).build(), networkCallback);
        }

        @SuppressLint({"NewApi"})
        public void requestMobileNetwork(ConnectivityManager.NetworkCallback networkCallback) {
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addCapability(12).addTransportType(0);
            this.connectivityManager.requestNetwork(builder.build(), networkCallback);
        }

        public boolean supportNetworkCallback() {
            if (this.connectivityManager != null) {
                return true;
            }
            return false;
        }

        public List<NetworkInformation> getActiveNetworkList() {
            ConnectionType connectionType;
            if (!supportNetworkCallback()) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Network network : getAllNetworks()) {
                NetworkInformation networkToInfo = networkToInfo(network);
                if (!(networkToInfo.name == null || (connectionType = networkToInfo.type) == ConnectionType.CONNECTION_NONE || connectionType == ConnectionType.CONNECTION_UNKNOWN)) {
                    arrayList.add(networkToInfo);
                }
            }
            return arrayList;
        }

        @SuppressLint({"NewApi"})
        public int getDefaultNetId() {
            NetworkInfo activeNetworkInfo;
            NetworkInfo networkInfo;
            int i = -1;
            if (supportNetworkCallback() && (activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo()) != null) {
                Network[] allNetworks = getAllNetworks();
                for (Network network : allNetworks) {
                    if (hasInternetCapability(network) && (networkInfo = this.connectivityManager.getNetworkInfo(network)) != null && networkInfo.getType() == activeNetworkInfo.getType()) {
                        i = NetworkMonitorAutoDetect.networkToNetId(network);
                    }
                }
            }
            return i;
        }

        @SuppressLint({"NewApi"})
        public IPAddress[] getIPAddresses(LinkProperties linkProperties) {
            IPAddress[] iPAddressArr = new IPAddress[linkProperties.getLinkAddresses().size()];
            int i = 0;
            for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
                iPAddressArr[i] = new IPAddress(linkAddress.getAddress().getAddress());
                i++;
            }
            return iPAddressArr;
        }

        @SuppressLint({"NewApi"})
        public void releaseCallback(ConnectivityManager.NetworkCallback networkCallback) {
            if (supportNetworkCallback()) {
                Logging.d(NetworkMonitorAutoDetect.TAG, "Unregister network callback");
                this.connectivityManager.unregisterNetworkCallback(networkCallback);
            }
        }

        public ConnectivityManagerDelegate() {
            this.connectivityManager = null;
        }

        public ConnectivityManagerDelegate(Context context) {
            this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        }

        public NetworkState getNetworkState() {
            ConnectivityManager connectivityManager2 = this.connectivityManager;
            if (connectivityManager2 == null) {
                return new NetworkState(false, -1, -1);
            }
            return getNetworkState(connectivityManager2.getActiveNetworkInfo());
        }

        @SuppressLint({"NewApi"})
        public NetworkState getNetworkState(Network network) {
            ConnectivityManager connectivityManager2 = this.connectivityManager;
            if (connectivityManager2 == null) {
                return new NetworkState(false, -1, -1);
            }
            return getNetworkState(connectivityManager2.getNetworkInfo(network));
        }

        public NetworkState getNetworkState(NetworkInfo networkInfo) {
            if (networkInfo == null || !networkInfo.isConnected()) {
                return new NetworkState(false, -1, -1);
            }
            return new NetworkState(true, networkInfo.getType(), networkInfo.getSubtype());
        }
    }

    public interface Observer {
        void onConnectionTypeChanged(ConnectionType connectionType);

        void onNetworkConnect(NetworkInformation networkInformation);

        void onNetworkDisconnect(int i);
    }

    @SuppressLint({"NewApi"})
    public class SimpleNetworkCallback extends ConnectivityManager.NetworkCallback {
        private void onNetworkChanged(Network network) {
            NetworkInformation networkToInfo = NetworkMonitorAutoDetect.this.connectivityManagerDelegate.networkToInfo(network);
            ConnectionType connectionType = networkToInfo.type;
            if (connectionType != ConnectionType.CONNECTION_UNKNOWN && connectionType != ConnectionType.CONNECTION_NONE) {
                NetworkMonitorAutoDetect.this.observer.onNetworkConnect(networkToInfo);
            }
        }

        public void onAvailable(Network network) {
            Logging.d(NetworkMonitorAutoDetect.TAG, AnonymousClass006.A05("Network becomes available: ", network.toString()));
            onNetworkChanged(network);
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            Logging.d(NetworkMonitorAutoDetect.TAG, AnonymousClass006.A05("capabilities changed: ", networkCapabilities.toString()));
            onNetworkChanged(network);
        }

        public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            Logging.d(NetworkMonitorAutoDetect.TAG, AnonymousClass006.A05("link properties changed: ", linkProperties.toString()));
            onNetworkChanged(network);
        }

        public void onLosing(Network network, int i) {
            StringBuilder sb = new StringBuilder("Network with handle ");
            sb.append(NetworkMonitorAutoDetect.networkToNetId(network));
            sb.append(" is about to lose in ");
            sb.append(i);
            sb.append("ms");
            Logging.d(NetworkMonitorAutoDetect.TAG, sb.toString());
        }

        public void onLost(Network network) {
            int networkToNetId = NetworkMonitorAutoDetect.networkToNetId(network);
            Logging.d(NetworkMonitorAutoDetect.TAG, AnonymousClass006.A02("Network with handle ", networkToNetId, " is disconnected"));
            NetworkMonitorAutoDetect.this.observer.onNetworkDisconnect(networkToNetId);
        }

        public SimpleNetworkCallback() {
        }
    }

    public static class WifiManagerDelegate {
        public final Context context;

        public String getWifiSSID() {
            WifiInfo wifiInfo;
            String ssid;
            Intent registerReceiver = this.context.registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            if (registerReceiver == null || (wifiInfo = (WifiInfo) registerReceiver.getParcelableExtra("wifiInfo")) == null || (ssid = wifiInfo.getSSID()) == null) {
                return "";
            }
            return ssid;
        }

        public WifiManagerDelegate() {
            this.context = null;
        }

        public WifiManagerDelegate(Context context2) {
            this.context = context2;
        }
    }

    public static class IPAddress {
        public final byte[] address;

        public IPAddress(byte[] bArr) {
            this.address = bArr;
        }
    }

    public static class NetworkInformation {
        public final int handle;
        public final IPAddress[] ipAddresses;
        public final String name;
        public final ConnectionType type;

        public NetworkInformation(String str, ConnectionType connectionType, int i, IPAddress[] iPAddressArr) {
            this.name = str;
            this.type = connectionType;
            this.handle = i;
            this.ipAddresses = iPAddressArr;
        }
    }

    public static class NetworkState {
        public final boolean connected;
        public final int subtype;
        public final int type;

        public NetworkState(boolean z, int i, int i2) {
            this.connected = z;
            this.type = i;
            this.subtype = i2;
        }

        public int getNetworkSubType() {
            return this.subtype;
        }

        public int getNetworkType() {
            return this.type;
        }

        public boolean isConnected() {
            return this.connected;
        }
    }

    public static ConnectionType getConnectionType(NetworkState networkState) {
        if (!networkState.connected) {
            return ConnectionType.CONNECTION_NONE;
        }
        int i = networkState.type;
        if (i == 0) {
            switch (networkState.subtype) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return ConnectionType.CONNECTION_2G;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return ConnectionType.CONNECTION_3G;
                case 13:
                    return ConnectionType.CONNECTION_4G;
            }
        } else if (i == 1) {
            return ConnectionType.CONNECTION_WIFI;
        } else {
            if (i != 6) {
                if (i == 7) {
                    return ConnectionType.CONNECTION_BLUETOOTH;
                }
                if (i == 9) {
                    return ConnectionType.CONNECTION_ETHERNET;
                }
            }
            return ConnectionType.CONNECTION_4G;
        }
        return ConnectionType.CONNECTION_UNKNOWN;
    }

    private void registerReceiver() {
        if (!this.isRegistered) {
            this.isRegistered = true;
            this.context.registerReceiver(this, this.intentFilter);
        }
    }

    private void unregisterReceiver() {
        if (this.isRegistered) {
            this.isRegistered = false;
            this.context.unregisterReceiver(this);
        }
    }

    public void destroy() {
        ConnectivityManager.NetworkCallback networkCallback = this.allNetworkCallback;
        if (networkCallback != null) {
            this.connectivityManagerDelegate.releaseCallback(networkCallback);
        }
        ConnectivityManager.NetworkCallback networkCallback2 = this.mobileNetworkCallback;
        if (networkCallback2 != null) {
            this.connectivityManagerDelegate.releaseCallback(networkCallback2);
        }
        unregisterReceiver();
    }

    public List<NetworkInformation> getActiveNetworkList() {
        return this.connectivityManagerDelegate.getActiveNetworkList();
    }

    public NetworkState getCurrentNetworkState() {
        return this.connectivityManagerDelegate.getNetworkState();
    }

    public int getDefaultNetId() {
        return this.connectivityManagerDelegate.getDefaultNetId();
    }

    public void onReceive(Context context2, Intent intent) {
        NetworkState networkState = this.connectivityManagerDelegate.getNetworkState();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            connectionTypeChanged(networkState);
        }
    }

    @SuppressLint({"NewApi"})
    public NetworkMonitorAutoDetect(Observer observer2, Context context2) {
        this.observer = observer2;
        this.context = context2;
        ConnectivityManagerDelegate connectivityManagerDelegate2 = new ConnectivityManagerDelegate(context2);
        this.connectivityManagerDelegate = connectivityManagerDelegate2;
        this.wifiManagerDelegate = new WifiManagerDelegate(context2);
        NetworkState networkState = connectivityManagerDelegate2.getNetworkState();
        this.connectionType = getConnectionType(networkState);
        this.wifiSSID = getWifiSSID(networkState);
        registerReceiver();
        if (this.connectivityManagerDelegate.supportNetworkCallback()) {
            ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback();
            this.mobileNetworkCallback = networkCallback;
            this.connectivityManagerDelegate.requestMobileNetwork(networkCallback);
            SimpleNetworkCallback simpleNetworkCallback = new SimpleNetworkCallback();
            this.allNetworkCallback = simpleNetworkCallback;
            this.connectivityManagerDelegate.registerNetworkCallback(simpleNetworkCallback);
            return;
        }
        this.mobileNetworkCallback = null;
        this.allNetworkCallback = null;
    }

    private void connectionTypeChanged(NetworkState networkState) {
        ConnectionType connectionType2 = getConnectionType(networkState);
        String wifiSSID2 = getWifiSSID(networkState);
        if (connectionType2 != this.connectionType || !wifiSSID2.equals(this.wifiSSID)) {
            this.connectionType = connectionType2;
            this.wifiSSID = wifiSSID2;
            StringBuilder sb = new StringBuilder("Network connectivity changed, type is: ");
            sb.append(connectionType2);
            Logging.d(TAG, sb.toString());
            this.observer.onConnectionTypeChanged(connectionType2);
        }
    }

    private String getWifiSSID(NetworkState networkState) {
        if (getConnectionType(networkState) != ConnectionType.CONNECTION_WIFI) {
            return "";
        }
        return this.wifiManagerDelegate.getWifiSSID();
    }

    @SuppressLint({"NewApi"})
    public static int networkToNetId(Network network) {
        return Integer.parseInt(network.toString());
    }

    public boolean isReceiverRegisteredForTesting() {
        return this.isRegistered;
    }

    public void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate connectivityManagerDelegate2) {
        this.connectivityManagerDelegate = connectivityManagerDelegate2;
    }

    public void setWifiManagerDelegateForTests(WifiManagerDelegate wifiManagerDelegate2) {
        this.wifiManagerDelegate = wifiManagerDelegate2;
    }
}
