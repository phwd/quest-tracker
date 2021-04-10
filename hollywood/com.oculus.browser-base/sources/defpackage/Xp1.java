package defpackage;

import java.util.Iterator;

/* renamed from: Xp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Xp1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2249dq1 f9237a;

    public Xp1(C2249dq1 dq1) {
        this.f9237a = dq1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2249dq1 dq1 = this.f9237a;
        dq1.e = (C5321vq1) obj;
        dq1.c();
        Iterator it = dq1.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((Runnable) uq0.next()).run();
            } else {
                dq1.e();
                return;
            }
        }
    }
}
