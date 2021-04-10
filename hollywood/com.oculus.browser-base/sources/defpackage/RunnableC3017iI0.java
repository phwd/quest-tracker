package defpackage;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.ProxyInfo;
import android.os.Build;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.net.ProxyChangeListener;

/* renamed from: iI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3017iI0 implements Runnable {
    public final ProxyChangeListener F;
    public final Intent G;

    public RunnableC3017iI0(ProxyChangeListener proxyChangeListener, Intent intent) {
        this.F = proxyChangeListener;
        this.G = intent;
    }

    public void run() {
        C3358kI0 ki0;
        ProxyChangeListener proxyChangeListener = this.F;
        Intent intent = this.G;
        Objects.requireNonNull(proxyChangeListener);
        ProxyInfo defaultProxy = ((ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")).getDefaultProxy();
        if (defaultProxy == null) {
            ki0 = C3358kI0.f10271a;
        } else if (Build.VERSION.SDK_INT == 29 && defaultProxy.getHost().equals("localhost") && defaultProxy.getPort() == -1) {
            ki0 = ProxyChangeListener.a(intent);
        } else {
            ki0 = C3358kI0.a(defaultProxy);
        }
        proxyChangeListener.b(ki0);
    }
}
