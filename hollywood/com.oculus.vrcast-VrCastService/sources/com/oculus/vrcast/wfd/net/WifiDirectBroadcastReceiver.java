package com.oculus.vrcast.wfd.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pInfo;
import android.util.Log;
import java.util.Collection;

public class WifiDirectBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiDirectBroadcastReceiver";
    private WifiP2pNetManager wifiUtil;

    public WifiDirectBroadcastReceiver(WifiP2pNetManager wifiP2pNetManager) {
        this.wifiUtil = wifiP2pNetManager;
    }

    public void registerReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.PEERS_CHANGED");
        context.registerReceiver(this, intentFilter);
    }

    public void unregisterReceiver(Context context) {
        context.unregisterReceiver(this);
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.v(TAG, "onReceive: " + intent);
        if ("android.net.wifi.p2p.STATE_CHANGED".equals(action)) {
            Log.d(TAG, "WIFI_P2P_STATE_CHANGED_ACTION");
            int intExtra = intent.getIntExtra("wifi_p2p_state", -1);
            this.wifiUtil.onStateChanged(intExtra);
            Log.d(TAG, "P2P state changed - " + intExtra);
        } else if ("android.net.wifi.p2p.PEERS_CHANGED".equals(action)) {
            Log.d(TAG, "WIFI_P2P_PEERS_CHANGED_ACTION");
            this.wifiUtil.onPeerListUpdate(((WifiP2pDeviceList) intent.getParcelableExtra("wifiP2pDeviceList")).getDeviceList());
        } else if ("android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(action)) {
            Log.d(TAG, "WIFI_P2P_CONNECTION_CHANGED_ACTION");
            if (((NetworkInfo) intent.getParcelableExtra("networkInfo")).isConnected()) {
                WifiP2pInfo wifiP2pInfo = (WifiP2pInfo) intent.getParcelableExtra("wifiP2pInfo");
                if (wifiP2pInfo.groupFormed) {
                    WifiP2pGroup wifiP2pGroup = (WifiP2pGroup) intent.getParcelableExtra("p2pGroupInfo");
                    Collection<WifiP2pDevice> clientList = wifiP2pGroup.getClientList();
                    this.wifiUtil.onConnectionInfoAvailable(wifiP2pInfo);
                    this.wifiUtil.onConnectedListUpdate(clientList);
                    if (wifiP2pInfo.isGroupOwner && wifiP2pGroup.getClientList().isEmpty()) {
                        Log.e(TAG, "Group formed but client list is null");
                        return;
                    }
                    return;
                }
                return;
            }
            Log.d(TAG, "It's a disconnect.");
            this.wifiUtil.onDisconnected();
        } else if ("android.net.wifi.p2p.THIS_DEVICE_CHANGED".equals(action)) {
            Log.d(TAG, "WIFI_P2P_THIS_DEVICE_CHANGED_ACTION");
            this.wifiUtil.onLocalDeviceChanged((WifiP2pDevice) intent.getParcelableExtra("wifiP2pDevice"));
            Log.d(TAG, "This Device Changed" + ((WifiP2pDevice) intent.getParcelableExtra("wifiP2pDevice")).toString());
        }
    }
}
