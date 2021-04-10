package defpackage;

import android.os.SystemClock;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;

/* renamed from: e3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2287e3 {

    /* renamed from: a  reason: collision with root package name */
    public final long f9827a = SystemClock.uptimeMillis();
    public long b;
    public String c;
    public AbstractC1099Sa1 d;
    public AbstractC0601Jv0 e;
    public boolean f;
    public boolean g;

    public C2287e3(AbstractC0956Pq0 pq0) {
        ((C1078Rq0) pq0).l(new C1775b3(this));
    }

    public void a() {
        this.f = false;
        AbstractC1099Sa1 sa1 = this.d;
        if (sa1 != null) {
            sa1.destroy();
            this.d = null;
        }
        AbstractC0601Jv0 jv0 = this.e;
        if (jv0 != null) {
            Object obj = ThreadUtils.f10596a;
            C1322Vq0 vq0 = PageLoadMetrics.f10695a;
            if (vq0 != null) {
                vq0.c(jv0);
            }
            this.e = null;
        }
    }
}
