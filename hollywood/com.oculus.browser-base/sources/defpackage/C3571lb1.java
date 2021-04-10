package defpackage;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

/* renamed from: lb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3571lb1 extends AbstractC2032cb {
    public C4426qb1 i;
    public final /* synthetic */ C4766sb1 j;

    public C3571lb1(C4766sb1 sb1, RunnableC2034cb1 cb1) {
        this.j = sb1;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        if (this.i == null || h()) {
            return null;
        }
        this.j.s(this.i.f11150a);
        return null;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r4 = (Void) obj;
        if (this.j.l || h()) {
            this.i = null;
            return;
        }
        C4766sb1 sb1 = this.j;
        if (sb1.k == this) {
            sb1.k = null;
            Iterator it = sb1.e.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    Objects.requireNonNull((C0307Fa1) uq0.next());
                } else {
                    this.i = null;
                    return;
                }
            }
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void l() {
        if (!this.j.l && !h()) {
            try {
                this.i = this.j.v();
            } catch (IOException unused) {
                this.i = null;
            }
        }
    }
}
