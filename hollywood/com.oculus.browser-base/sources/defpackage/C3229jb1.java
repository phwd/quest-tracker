package defpackage;

import android.os.SystemClock;
import java.util.Locale;
import org.chromium.base.TraceEvent;

/* renamed from: jb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3229jb1 extends AbstractC2032cb {
    public final C4596rb1 i;
    public C0797Nb1 j;
    public long k = SystemClock.elapsedRealtime();
    public final /* synthetic */ C4766sb1 l;

    public C3229jb1(C4766sb1 sb1, C4596rb1 rb1) {
        this.l = sb1;
        this.i = rb1;
        TraceEvent.l0("LoadTabTask", (long) rb1.f11206a);
        TraceEvent.l0("LoadTabState", (long) rb1.f11206a);
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        if (this.l.l || h()) {
            return null;
        }
        try {
            return AbstractC1224Ub1.e(this.l.b(), this.i.f11206a);
        } catch (Exception e) {
            AbstractC1220Ua0.f("tabmodel", "Unable to read state: " + e, new Object[0]);
            return null;
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        C0797Nb1 nb1 = (C0797Nb1) obj;
        TraceEvent.g0("LoadTabState", (long) this.i.f11206a);
        Locale locale = Locale.US;
        Object[] objArr = new Object[1];
        objArr[0] = nb1 == null ? "Null" : "Exists";
        AbstractC3364kK0.k(String.format(locale, "Tabs.SavedTabLoadTime.TabState.%s", objArr), SystemClock.elapsedRealtime() - this.k);
        this.j = nb1;
        TraceEvent.g0("LoadTabTask", (long) this.i.f11206a);
        if (!this.l.l && !h()) {
            C4766sb1.c(this.l, this.i, this.j, null);
        }
    }
}
