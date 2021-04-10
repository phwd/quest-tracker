package defpackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: dC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2145dC0 extends Pr1 {
    public static final Map F = new HashMap();
    public final Tab G;
    public final AbstractC3511lC0 H;
    public final String I;

    /* renamed from: J  reason: collision with root package name */
    public long f9756J = 0;
    public C1078Rq0 K;
    public Callback L;
    public boolean M;

    public AbstractC2145dC0(Tab tab, AbstractC3511lC0 lc0, String str) {
        this.G = tab;
        this.H = lc0;
        this.I = str;
    }

    public static void m(AbstractC2145dC0 dc0, Tab tab, Class cls, String str) {
        if (dc0 != null) {
            AbstractC2145dC0 dc02 = (AbstractC2145dC0) tab.M().e(cls, dc0);
        }
        for (Callback callback : (List) F.get(str)) {
            callback.onResult(dc0);
        }
        F.remove(str);
    }

    public static void p(AbstractC2145dC0 dc0) {
        if (dc0 != null) {
            dc0.f9756J = System.currentTimeMillis();
        }
    }

    public void c() {
        this.H.b(this.G.getId(), this.I);
    }

    public abstract boolean e(byte[] bArr);

    public void h(byte[] bArr) {
        TraceEvent j0 = TraceEvent.j0("PersistedTabData.Deserialize");
        try {
            boolean e = e(bArr);
            if (j0 != null) {
                j0.close();
            }
            StringBuilder i = AbstractC2531fV.i("Tabs.PersistedTabData.Deserialize.");
            i.append(k());
            AbstractC3100ip1.f10165a.a(i.toString(), e);
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public long j() {
        return Long.MAX_VALUE;
    }

    public abstract String k();

    public boolean l() {
        if (j() == Long.MAX_VALUE) {
            return false;
        }
        long j = this.f9756J;
        if (j == 0) {
            return true;
        }
        if (j() + j < System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    public void n() {
        byte[] bArr;
        C1078Rq0 rq0 = this.K;
        if (rq0 != null && ((Boolean) rq0.H).booleanValue()) {
            boolean z = false;
            try {
                TraceEvent j0 = TraceEvent.j0("PersistedTabData.Serialize");
                try {
                    bArr = o();
                    if (j0 != null) {
                        j0.close();
                    }
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            } catch (OutOfMemoryError unused) {
                AbstractC1220Ua0.a("PTD", "Out of memory error when attempting to save PersistedTabData", new Object[0]);
                bArr = null;
            }
            StringBuilder i = AbstractC2531fV.i("Tabs.PersistedTabData.Serialize.");
            i.append(k());
            String sb = i.toString();
            if (bArr != null) {
                z = true;
            }
            AbstractC3100ip1.f10165a.a(sb, z);
            if (bArr != null) {
                this.H.d(this.G.getId(), this.I, bArr);
                return;
            }
            return;
        }
        return;
        throw th;
    }

    public abstract byte[] o();
}
