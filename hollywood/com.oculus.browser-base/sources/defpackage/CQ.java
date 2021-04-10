package defpackage;

import java.util.Iterator;

/* renamed from: CQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CQ implements EQ {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DQ f7807a;

    public CQ(DQ dq) {
        this.f7807a = dq;
    }

    @Override // defpackage.EQ
    public void a() {
        Iterator it = this.f7807a.f.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((EQ) uq0.next()).a();
            } else {
                return;
            }
        }
    }

    @Override // defpackage.EQ
    public void b() {
        Iterator it = this.f7807a.f.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((EQ) uq0.next()).b();
            } else {
                return;
            }
        }
    }
}
