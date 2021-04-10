package defpackage;

import android.util.Log;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: mb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3742mb1 extends AbstractC2032cb {
    public Tab i;
    public int j;
    public C0797Nb1 k;
    public boolean l;
    public boolean m;
    public final /* synthetic */ C4766sb1 n;

    public C3742mb1(C4766sb1 sb1, Tab tab) {
        this.n = sb1;
        this.i = tab;
        this.j = tab.getId();
        this.l = tab.a();
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        C4766sb1 sb1 = this.n;
        int i2 = this.j;
        boolean z = this.l;
        C0797Nb1 nb1 = this.k;
        Objects.requireNonNull(sb1);
        boolean z2 = false;
        if (nb1 != null) {
            try {
                AbstractC1224Ub1.f(AbstractC1224Ub1.a(sb1.b(), i2, z), nb1, z);
                z2 = true;
            } catch (OutOfMemoryError unused) {
                Log.e("TabPersister", "Out of memory error while attempting to save tab state.  Erasing.");
                sb1.a(i2, z);
            }
        }
        this.m = z2;
        return null;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r3 = (Void) obj;
        if (!this.n.l && !h()) {
            if (this.m) {
                Tab tab = this.i;
                ((TabImpl) tab).U = false;
                tab.K(C4766sb1.i());
            }
            C4766sb1 sb1 = this.n;
            sb1.j = null;
            sb1.t();
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void l() {
        if (!this.n.l && !h()) {
            this.k = AbstractC1163Tb1.a(this.i);
        }
    }
}
