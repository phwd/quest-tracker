package com.oculus.panellib.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBroadcastReceiverModule extends RCTBaseJavaModule implements EarlyDestroyable {
    public Context mContext = null;
    public int mRegisteredRefCount = 0;

    public abstract IntentFilter getIntentFilter();

    public abstract String getModuleName();

    public void onNoStickyIntent() {
    }

    @Override // com.oculus.panellib.modules.RCTBaseJavaModule
    public void onReceive(Context context, Intent intent) {
        tryHandleIntent(intent, false);
    }

    public void onStartReceiver() {
    }

    public abstract void processIntent(Intent intent, boolean z);

    public static List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("startReceiver", ""));
        arrayList.add(new Pair("stopReceiver", ""));
        return arrayList;
    }

    private void tryHandleIntent(Intent intent, boolean z) {
        if (intent == null) {
            Log.e(getModuleName(), "Cannot process null intent");
        } else {
            processIntent(intent, z);
        }
    }

    @Override // com.oculus.panellib.modules.EarlyDestroyable
    public void shutdownModule() {
        if (this.mRegisteredRefCount > 0) {
            try {
                this.mContext.unregisterReceiver(this);
            } catch (IllegalArgumentException unused) {
            }
            this.mRegisteredRefCount = 0;
        }
    }

    public final void startReceiver() {
        int i = this.mRegisteredRefCount + 1;
        this.mRegisteredRefCount = i;
        if (i == 1) {
            this.mContext.registerReceiver(this, getIntentFilter());
        }
        Intent registerReceiver = this.mContext.registerReceiver(null, getIntentFilter());
        if (registerReceiver != null) {
            tryHandleIntent(registerReceiver, true);
        } else {
            onNoStickyIntent();
        }
    }

    public final void stopReceiver() {
        int i = this.mRegisteredRefCount;
        if (i == 0) {
            Log.e(getModuleName(), "Cannot call stopReceiver() without a matching startReceiver()");
            return;
        }
        int i2 = i - 1;
        this.mRegisteredRefCount = i2;
        if (i2 == 0) {
            try {
                this.mContext.unregisterReceiver(this);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    public AbstractBroadcastReceiverModule(Context context) {
        this.mContext = context;
    }
}
