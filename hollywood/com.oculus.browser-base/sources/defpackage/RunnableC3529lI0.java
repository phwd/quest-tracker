package defpackage;

import android.content.Intent;
import org.chromium.net.ProxyChangeListener;

/* renamed from: lI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3529lI0 implements Runnable {
    public final ProxyChangeListener.ProxyReceiver F;
    public final Intent G;

    public RunnableC3529lI0(ProxyChangeListener.ProxyReceiver proxyReceiver, Intent intent) {
        this.F = proxyReceiver;
        this.G = intent;
    }

    public void run() {
        ProxyChangeListener.this.b(ProxyChangeListener.a(this.G));
    }
}
