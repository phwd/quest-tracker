package org.chromium.net;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ProxyInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ProxyChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final Looper f11005a;
    public final Handler b;
    public long c;
    public ProxyReceiver d;
    public BroadcastReceiver e;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ProxyReceiver extends BroadcastReceiver {
        public ProxyReceiver(AbstractC3187jI0 ji0) {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PROXY_CHANGE")) {
                ProxyChangeListener.this.c(new RunnableC3529lI0(this, intent));
            }
        }
    }

    public ProxyChangeListener() {
        Looper myLooper = Looper.myLooper();
        this.f11005a = myLooper;
        this.b = new Handler(myLooper);
    }

    public static C3358kI0 a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return null;
        }
        return C3358kI0.a((ProxyInfo) extras.get("android.intent.extra.PROXY_INFO"));
    }

    public static ProxyChangeListener create() {
        return new ProxyChangeListener();
    }

    public static String getProperty(String str) {
        return System.getProperty(str);
    }

    public final void b(C3358kI0 ki0) {
        long j = this.c;
        if (j != 0) {
            if (ki0 != null) {
                N.MyoFZt$2(j, this, ki0.b, ki0.c, ki0.d, ki0.e);
            } else {
                N.MCIk73GZ(j, this);
            }
        }
    }

    public final void c(Runnable runnable) {
        if (this.f11005a == Looper.myLooper()) {
            runnable.run();
        } else {
            this.b.post(runnable);
        }
    }

    public void start(long j) {
        this.c = j;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PROXY_CHANGE");
        this.d = new ProxyReceiver(null);
        ContextUtils.getApplicationContext().registerReceiver(this.d, new IntentFilter());
        this.e = new C2846hI0(this);
        ContextUtils.getApplicationContext().registerReceiver(this.e, intentFilter);
    }

    public void stop() {
        this.c = 0;
        ContextUtils.getApplicationContext().unregisterReceiver(this.d);
        if (this.e != null) {
            ContextUtils.getApplicationContext().unregisterReceiver(this.e);
        }
        this.d = null;
        this.e = null;
    }
}
