package defpackage;

import android.util.Pair;
import org.chromium.components.signin.base.CoreAccountInfo;

/* renamed from: YV0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YV0 extends AbstractC2032cb {
    public final /* synthetic */ CoreAccountInfo i;
    public final /* synthetic */ C1851bW0 j;

    public YV0(C1851bW0 bw0, CoreAccountInfo coreAccountInfo) {
        this.j = bw0;
        this.i = coreAccountInfo;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        C1671aW0 aw0 = new C1671aW0(null);
        String email = this.i.getEmail();
        C4072oW0 ow0 = C4072oW0.f10556a;
        int f = ow0.b.f("prefs_sync_account_rename_event_index", 0);
        Pair b = C1851bW0.b(aw0, f, email);
        if (f != ((Integer) b.first).intValue()) {
            ow0.b.n("prefs_sync_account_rename_event_index", ((Integer) b.first).intValue());
        }
        if (!email.equals(b.second)) {
            ow0.b.p("prefs_sync_account_renamed", (String) b.second);
        }
        return null;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r3 = (Void) obj;
        if (this.j.c.b.i("prefs_sync_account_renamed", null) != null || this.j.f9545a.o()) {
            this.j.d(true);
        } else {
            this.j.f9545a.u(9);
        }
    }
}
