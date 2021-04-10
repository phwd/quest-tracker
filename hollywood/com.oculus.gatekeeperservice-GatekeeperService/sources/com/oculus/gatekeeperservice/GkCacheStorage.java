package com.oculus.gatekeeperservice;

import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import oculus.internal.IGatekeeperService;

public class GkCacheStorage {
    private static final String TAG = "GkCacheStorage";
    private final IGatekeeperService mService = IGatekeeperService.Stub.asInterface(ServiceManager.checkService("GatekeeperService"));
    private final int mUserId;

    public GkCacheStorage(Context context, int userId) {
        this.mUserId = userId;
    }

    public void add(String gk, boolean value) {
        try {
            this.mService.setGatekeeper(gk, this.mUserId, value);
        } catch (RemoteException e) {
            Log.w(TAG, "GatekeeperService not available", e);
        }
    }

    public void remove(String gk) {
        try {
            this.mService.clearGatekeeper(gk, this.mUserId);
        } catch (RemoteException e) {
            Log.w(TAG, "GatekeeperService not available", e);
        }
    }

    public String[] getGksList() {
        try {
            return this.mService.listGatekeepers();
        } catch (RemoteException e) {
            Log.w(TAG, "GatekeeperService not available", e);
            return new String[0];
        }
    }

    public String[] getDynamicGksList() {
        try {
            return this.mService.listDynamicGatekeepers();
        } catch (RemoteException e) {
            Log.w(TAG, "GatekeeperService not available", e);
            return new String[0];
        }
    }
}
