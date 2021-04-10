package defpackage;

import java.util.Iterator;

/* renamed from: i10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2966i10 implements AbstractC2282e10 {
    public final /* synthetic */ C3649m10 F;

    public C2966i10(C3649m10 m10) {
        this.F = m10;
    }

    @Override // defpackage.AbstractC2282e10
    public void b(int i) {
        Iterator it = this.F.K.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC2282e10) uq0.next()).b(i);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC2282e10
    public void d(C10 c10) {
        Iterator it = this.F.K.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC2282e10) uq0.next()).d(c10);
            } else {
                return;
            }
        }
    }
}
