package com.oculus.device;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class AccessTokenForwarder {
    private static String TAG = AccessTokenForwarder.class.getSimpleName();
    private static AccessTokenForwarder instance;
    private String mAccessToken;
    private ReentrantLock mAccessTokenLock = new ReentrantLock();
    private List<IListener> mListeners;

    public interface IListener {
        void onGetAccessToken(String str);
    }

    public AccessTokenForwarder() {
        Log.d(TAG, "Constructed");
        this.mListeners = new ArrayList();
        if (instance != null) {
            Log.e(TAG, "Singleton already initialized when constructed!");
        } else {
            instance = this;
        }
    }

    public static AccessTokenForwarder getInstance() {
        if (instance == null) {
            Log.e(TAG, "Singleton was not initialized before being accessed");
        }
        return instance;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0050, code lost:
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0064, code lost:
        r2 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onGetAccessToken(java.lang.String r7) {
        /*
        // Method dump skipped, instructions count: 102
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.device.AccessTokenForwarder.onGetAccessToken(java.lang.String):void");
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public void addListener(IListener listener) {
        this.mAccessTokenLock.lock();
        if (this.mAccessToken == null) {
            this.mListeners.add(listener);
        } else {
            listener.onGetAccessToken(this.mAccessToken);
        }
        this.mAccessTokenLock.unlock();
    }
}
