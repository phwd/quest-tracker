package com.facebook.internal;

import X.AnonymousClass006;
import X.AnonymousClass0B0;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;

public class BoltsMeasurementEventListener extends BroadcastReceiver {
    public static final String BOLTS_MEASUREMENT_EVENT_PREFIX = "bf_";
    public static final String MEASUREMENT_EVENT_ARGS_KEY = "event_args";
    public static final String MEASUREMENT_EVENT_NAME_KEY = "event_name";
    public static final String MEASUREMENT_EVENT_NOTIFICATION_NAME = "com.parse.bolts.measurement_event";
    public static BoltsMeasurementEventListener _instance;
    public Context applicationContext;

    public void onReceive(Context context, Intent intent) {
        AppEventsLogger appEventsLogger = new AppEventsLogger(context, null);
        String A05 = AnonymousClass006.A05(BOLTS_MEASUREMENT_EVENT_PREFIX, intent.getStringExtra("event_name"));
        Bundle bundleExtra = intent.getBundleExtra(MEASUREMENT_EVENT_ARGS_KEY);
        Bundle bundle = new Bundle();
        for (String str : bundleExtra.keySet()) {
            bundle.putString(str.replaceAll("[^0-9a-zA-Z _-]", "-").replaceAll("^[ -]*", "").replaceAll("[ -]*$", ""), (String) bundleExtra.get(str));
        }
        AppEventsLogger.A02(appEventsLogger, A05, bundle, false);
    }

    private void close() {
        AnonymousClass0B0.A00(this.applicationContext).A01(this);
    }

    public static BoltsMeasurementEventListener getInstance(Context context) {
        BoltsMeasurementEventListener boltsMeasurementEventListener = _instance;
        if (boltsMeasurementEventListener != null) {
            return boltsMeasurementEventListener;
        }
        BoltsMeasurementEventListener boltsMeasurementEventListener2 = new BoltsMeasurementEventListener(context);
        _instance = boltsMeasurementEventListener2;
        boltsMeasurementEventListener2.open();
        return _instance;
    }

    private void open() {
        AnonymousClass0B0.A00(this.applicationContext).A02(this, new IntentFilter(MEASUREMENT_EVENT_NOTIFICATION_NAME));
    }

    public BoltsMeasurementEventListener(Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    @Override // java.lang.Object
    public void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
