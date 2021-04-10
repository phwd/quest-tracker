package defpackage;

import android.os.Handler;
import android.os.Message;

/* renamed from: Eg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HandlerC0263Eg0 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC0446Hg0 f7972a;

    public HandlerC0263Eg0(AbstractC0446Hg0 hg0) {
        this.f7972a = hg0;
    }

    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            AbstractC0446Hg0 hg0 = this.f7972a;
            hg0.h = false;
            C1421Xg0 xg0 = hg0.d;
            if (xg0 != null) {
                C0507Ig0 ig0 = hg0.g;
                C1543Zg0 zg0 = xg0.f9227a;
                C2051ch0 d = zg0.d(hg0);
                if (d != null) {
                    zg0.o(d, ig0);
                }
            }
        } else if (i == 2) {
            AbstractC0446Hg0 hg02 = this.f7972a;
            hg02.f = false;
            hg02.f(hg02.e);
        }
    }
}
