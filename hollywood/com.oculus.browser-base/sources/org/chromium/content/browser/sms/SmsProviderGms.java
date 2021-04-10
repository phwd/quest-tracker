package org.chromium.content.browser.sms;

import J.N;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SmsProviderGms {

    /* renamed from: a  reason: collision with root package name */
    public final long f10933a;
    public final int b;
    public C3221jY0 c;
    public C3905nY0 d;
    public C5688xz1 e = new C5688xz1(ContextUtils.getApplicationContext());
    public WindowAndroid f;
    public Wrappers$SmsRetrieverClientWrapper g;

    public SmsProviderGms(long j, int i) {
        this.f10933a = j;
        this.b = i;
        if (i == 0 || i == 2) {
            this.d = new C3905nY0(this, this.e);
        }
        if (i == 0 || i == 1) {
            this.c = new C3221jY0(this, this.e);
        }
        AbstractC1220Ua0.d("SmsProviderGms", "construction successfull %s, %s", this.d, this.c);
    }

    public static SmsProviderGms create(long j, int i) {
        return new SmsProviderGms(j, i);
    }

    public Wrappers$SmsRetrieverClientWrapper a() {
        Wrappers$SmsRetrieverClientWrapper wrappers$SmsRetrieverClientWrapper = this.g;
        if (wrappers$SmsRetrieverClientWrapper != null) {
            return wrappers$SmsRetrieverClientWrapper;
        }
        C3221jY0 jy0 = this.c;
        GI1 gi1 = null;
        C5388wC1 wc1 = jy0 != null ? new C5388wC1(jy0.c) : null;
        C3905nY0 ny0 = this.d;
        if (ny0 != null) {
            gi1 = new GI1(ny0.c);
        }
        Wrappers$SmsRetrieverClientWrapper wrappers$SmsRetrieverClientWrapper2 = new Wrappers$SmsRetrieverClientWrapper(wc1, gi1);
        this.g = wrappers$SmsRetrieverClientWrapper2;
        return wrappers$SmsRetrieverClientWrapper2;
    }

    public void b() {
        if (this.b == 0) {
            this.c.a();
        } else {
            N.M$UJTj5O(this.f10933a);
        }
    }

    public final void destroy() {
        C3905nY0 ny0 = this.d;
        if (ny0 != null) {
            ny0.b = true;
            ny0.c.unregisterReceiver(ny0);
        }
        C3221jY0 jy0 = this.c;
        if (jy0 != null) {
            jy0.b = true;
            jy0.c.unregisterReceiver(jy0);
        }
    }

    public final void listen(WindowAndroid windowAndroid) {
        this.f = windowAndroid;
        int i = this.b;
        if (i != 0) {
            if (i == 1) {
                this.c.a();
                return;
            } else if (i != 2) {
                return;
            }
        }
        this.d.a(windowAndroid);
    }

    public final void setClientAndWindow(Wrappers$SmsRetrieverClientWrapper wrappers$SmsRetrieverClientWrapper, WindowAndroid windowAndroid) {
        this.g = wrappers$SmsRetrieverClientWrapper;
        this.f = windowAndroid;
        Objects.requireNonNull(wrappers$SmsRetrieverClientWrapper);
    }
}
