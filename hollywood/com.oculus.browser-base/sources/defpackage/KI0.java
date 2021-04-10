package defpackage;

import com.oculus.browser.PwaAppListFetcher;

/* renamed from: KI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KI0 {

    /* renamed from: a  reason: collision with root package name */
    public long f8358a;

    public KI0(PwaAppListFetcher pwaAppListFetcher, long j) {
        this.f8358a = j;
    }

    public boolean a() {
        return System.currentTimeMillis() - this.f8358a > 86400000;
    }
}
