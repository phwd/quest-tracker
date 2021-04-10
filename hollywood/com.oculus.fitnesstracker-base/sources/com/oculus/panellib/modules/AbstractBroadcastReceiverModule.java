package com.oculus.panellib.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.util.Pair;
import com.oculus.common.build.BuildConfig;
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
    public void onNoStickyIntent() {
    }

    /* access modifiers changed from: protected */
    public void onStartReceiver() {
    }

    /* access modifiers changed from: protected */
    public abstract void processIntent(Intent intent, boolean z);

    public AbstractBroadcastReceiverModule(Context context) {
        this.mContext = context;
    }

    public static List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("startReceiver", BuildConfig.PROVIDER_SUFFIX));
        arrayList.add(new Pair("stopReceiver", BuildConfig.PROVIDER_SUFFIX));
        return arrayList;
    }

    public void shutdownModule() {
        if (this.mRegisteredRefCount > 0) {
            try {
                this.mContext.unregisterReceiver(this);
            } catch (IllegalArgumentException unused) {
            }
            this.mRegisteredRefCount = 0;
        }
    }

    private void tryHandleIntent(Intent intent, boolean z) {
        if (intent == null) {
            Log.e(getModuleName(), "Cannot process null intent");
        } else {
            processIntent(intent, z);
        }
    }

    public final void startReceiver() {
        Log.d(getModuleName(), "startReceiver()");
        this.mRegisteredRefCount++;
        if (this.mRegisteredRefCount == 1) {
            this.mContext.registerReceiver(this, getIntentFilter());
        }
        onStartReceiver();
        Intent registerReceiver = this.mContext.registerReceiver(null, getIntentFilter());
        if (registerReceiver != null) {
            tryHandleIntent(registerReceiver, true);
        } else {
            onNoStickyIntent();
        }
    }

    public final void stopReceiver() {
        Log.d(getModuleName(), "stopReceiver()");
        int i = this.mRegisteredRefCount;
        if (i == 0) {
            Log.e(getModuleName(), "Cannot call stopReceiver() without a matching startReceiver()");
            return;
        }
        this.mRegisteredRefCount = i - 1;
        if (this.mRegisteredRefCount == 0) {
            try {
                this.mContext.unregisterReceiver(this);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // com.oculus.panellib.modules.RCTBaseJavaModule
    public void onReceive(Context context, Intent intent) {
        Log.d(getModuleName(), "onReceive()");
        tryHandleIntent(intent, false);
    }
}
