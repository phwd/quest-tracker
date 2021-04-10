package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.net.ProxyChangeListener;

/* renamed from: hI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2846hI0 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final ProxyChangeListener f10063a;

    public C2846hI0(ProxyChangeListener proxyChangeListener) {
        this.f10063a = proxyChangeListener;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PROXY_CHANGE")) {
            ProxyChangeListener proxyChangeListener = this.f10063a;
            proxyChangeListener.c(new RunnableC3017iI0(proxyChangeListener, intent));
        }
    }
}
