package com.oculus.device;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class AccessTokenForwarder {
    public static final String TAG = "AccessTokenForwarder";
    public static AccessTokenForwarder instance;
    public String mAccessToken;
    public ReentrantLock mAccessTokenLock = new ReentrantLock();
    public List<IListener> mListeners = new ArrayList();

    public interface IListener {
        void onGetAccessToken(String str);
    }

    public static AccessTokenForwarder getInstance() {
        if (instance == null) {
            Log.e(TAG, "Singleton was not initialized before being accessed");
        }
        return instance;
    }

    public void addListener(IListener iListener) {
        this.mAccessTokenLock.lock();
        String str = this.mAccessToken;
        if (str == null) {
            this.mListeners.add(iListener);
        } else {
            iListener.onGetAccessToken(str);
        }
        this.mAccessTokenLock.unlock();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onGetAccessToken(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r1 = "AccessTokenForwarder"
            java.lang.String r0 = "onGetAccessToken"
            com.oculus.panellib.SystraceBlock r3 = new com.oculus.panellib.SystraceBlock
            r3.<init>(r1, r0)
            java.util.concurrent.locks.ReentrantLock r0 = r4.mAccessTokenLock     // Catch:{ all -> 0x0036 }
            r0.lock()     // Catch:{ all -> 0x0036 }
            r4.mAccessToken = r5     // Catch:{ all -> 0x0036 }
            java.util.List<com.oculus.device.AccessTokenForwarder$IListener> r0 = r4.mListeners     // Catch:{ all -> 0x0036 }
            int r2 = r0.size()     // Catch:{ all -> 0x0036 }
        L_0x0016:
            int r2 = r2 + -1
            if (r2 < 0) goto L_0x002d
            java.util.List<com.oculus.device.AccessTokenForwarder$IListener> r0 = r4.mListeners     // Catch:{ all -> 0x0036 }
            java.lang.Object r1 = r0.get(r2)     // Catch:{ all -> 0x0036 }
            com.oculus.device.AccessTokenForwarder$IListener r1 = (com.oculus.device.AccessTokenForwarder.IListener) r1     // Catch:{ all -> 0x0036 }
            java.lang.String r0 = r4.mAccessToken     // Catch:{ all -> 0x0036 }
            r1.onGetAccessToken(r0)     // Catch:{ all -> 0x0036 }
            java.util.List<com.oculus.device.AccessTokenForwarder$IListener> r0 = r4.mListeners     // Catch:{ all -> 0x0036 }
            r0.remove(r2)     // Catch:{ all -> 0x0036 }
            goto L_0x0016
        L_0x002d:
            java.util.concurrent.locks.ReentrantLock r0 = r4.mAccessTokenLock     // Catch:{ all -> 0x0036 }
            r0.unlock()     // Catch:{ all -> 0x0036 }
            r3.close()
            return
        L_0x0036:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r0 = move-exception
            r3.close()     // Catch:{ all -> 0x003c }
        L_0x003c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.device.AccessTokenForwarder.onGetAccessToken(java.lang.String):void");
    }

    public AccessTokenForwarder() {
        if (instance != null) {
            Log.e(TAG, "Singleton already initialized when constructed!");
        } else {
            instance = this;
        }
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }
}
