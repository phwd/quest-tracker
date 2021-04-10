package com.oculus.companion.server.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.util.Log;
import java.util.concurrent.CountDownLatch;

public class BluetoothServiceConnection implements BluetoothProfile.ServiceListener {
    private final BluetoothAdapter mAdapter;
    private final int mProfileType;
    private BluetoothProfile mService;
    private CountDownLatch mServiceReadyLatch;

    public BluetoothServiceConnection(BluetoothAdapter bluetoothAdapter, int i) {
        this.mAdapter = bluetoothAdapter;
        this.mProfileType = i;
    }

    public synchronized void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
        if (Log.isLoggable("BtServiceConnection", 2)) {
            Log.v("BtServiceConnection", "Connected to bluetooth profile " + i);
        }
        if (this.mServiceReadyLatch.getCount() > 0) {
            this.mService = bluetoothProfile;
            this.mServiceReadyLatch.countDown();
        }
    }

    public synchronized void onServiceDisconnected(int i) {
        if (Log.isLoggable("BtServiceConnection", 2)) {
            Log.v("BtServiceConnection", "Disconnected to bluetooth profile " + i);
        }
        this.mService = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        if (r4.mServiceReadyLatch.await(10000, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        return r4.mService;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002b, code lost:
        android.util.Log.e("BtServiceConnection", "Interrupted while waiting for service connection", r4);
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        r4.mAdapter.getProfileProxy(r5, r4, r4.mProfileType);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.bluetooth.BluetoothProfile getServiceBlocking(android.content.Context r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            android.bluetooth.BluetoothProfile r0 = r4.mService     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0009
            android.bluetooth.BluetoothProfile r5 = r4.mService     // Catch:{ all -> 0x003a }
            monitor-exit(r4)     // Catch:{ all -> 0x003a }
            return r5
        L_0x0009:
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch     // Catch:{ all -> 0x003a }
            r1 = 1
            r0.<init>(r1)     // Catch:{ all -> 0x003a }
            r4.mServiceReadyLatch = r0     // Catch:{ all -> 0x003a }
            monitor-exit(r4)     // Catch:{ all -> 0x003a }
            android.bluetooth.BluetoothAdapter r0 = r4.mAdapter
            int r1 = r4.mProfileType
            r0.getProfileProxy(r5, r4, r1)
            r5 = 0
            java.util.concurrent.CountDownLatch r0 = r4.mServiceReadyLatch     // Catch:{ InterruptedException -> 0x002a }
            r1 = 10000(0x2710, double:4.9407E-320)
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x002a }
            boolean r0 = r0.await(r1, r3)     // Catch:{ InterruptedException -> 0x002a }
            if (r0 == 0) goto L_0x0029
            android.bluetooth.BluetoothProfile r4 = r4.mService     // Catch:{ InterruptedException -> 0x002a }
            return r4
        L_0x0029:
            return r5
        L_0x002a:
            r4 = move-exception
            java.lang.String r0 = "BtServiceConnection"
            java.lang.String r1 = "Interrupted while waiting for service connection"
            android.util.Log.e(r0, r1, r4)
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            r4.interrupt()
            return r5
        L_0x003a:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.utils.BluetoothServiceConnection.getServiceBlocking(android.content.Context):android.bluetooth.BluetoothProfile");
    }

    public synchronized boolean checkService() {
        return this.mService != null;
    }
}
