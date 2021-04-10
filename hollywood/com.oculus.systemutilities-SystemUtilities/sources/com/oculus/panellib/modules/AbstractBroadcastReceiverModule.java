package com.oculus.panellib.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBroadcastReceiverModule extends RCTBaseJavaModule {
    private Context mContext = null;
    private int mRegisteredRefCount = 0;

    /* access modifiers changed from: protected */
    public abstract IntentFilter getIntentFilter();

    /* access modifiers changed from: protected */
    public abstract String getModuleName();

    /* access modifiers changed from: protected */
    public abstract void processIntent(Intent intent, boolean z);

    /* access modifiers changed from: protected */
    public void onStartReceiver() {
    }

    /* access modifiers changed from: protected */
    public void onNoStickyIntent() {
    }

    public AbstractBroadcastReceiverModule(Context context) {
        this.mContext = context;
    }

    protected static List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("startReceiver", ""));
        list.add(new Pair<>("stopReceiver", ""));
        return list;
    }

    public void shutdownModule() {
        if (this.mRegisteredRefCount > 0) {
            try {
                this.mContext.unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
            }
            this.mRegisteredRefCount = 0;
        }
    }

    private void tryHandleIntent(Intent intent, boolean forceNativeUpdate) {
        if (intent == null) {
            Log.e(getModuleName(), "Cannot process null intent");
        } else {
            processIntent(intent, forceNativeUpdate);
        }
    }

    public final void startReceiver() {
        Log.d(getModuleName(), "startReceiver()");
        this.mRegisteredRefCount++;
        if (this.mRegisteredRefCount == 1) {
            this.mContext.registerReceiver(this, getIntentFilter());
        }
        onStartReceiver();
        Intent stickyIntent = this.mContext.registerReceiver(null, getIntentFilter());
        if (stickyIntent != null) {
            tryHandleIntent(stickyIntent, true);
        } else {
            onNoStickyIntent();
        }
    }

    public final void stopReceiver() {
        Log.d(getModuleName(), "stopReceiver()");
        if (this.mRegisteredRefCount == 0) {
            Log.e(getModuleName(), "Cannot call stopReceiver() without a matching startReceiver()");
            return;
        }
        this.mRegisteredRefCount--;
        if (this.mRegisteredRefCount == 0) {
            try {
                this.mContext.unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
            }
        }
    }

    @Override // com.oculus.panellib.modules.RCTBaseJavaModule
    public void onReceive(Context context, Intent intent) {
        Log.d(getModuleName(), "onReceive()");
        tryHandleIntent(intent, false);
    }
}
