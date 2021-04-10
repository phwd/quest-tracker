package defpackage;

import java.util.Iterator;

/* renamed from: x70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5542x70 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final D70 f11591a;
    public final int b;
    public final boolean c;
    public final boolean d;

    public C5542x70(D70 d70, int i, boolean z, boolean z2) {
        this.f11591a = d70;
        this.b = i;
        this.c = z;
        this.d = z2;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        D70 d70 = this.f11591a;
        int i = this.b;
        boolean z = this.c;
        boolean z2 = this.d;
        AbstractC2642g70 g70 = (AbstractC2642g70) obj;
        Iterator it = d70.Q.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((I70) uq0.next()).d(i, z, z2);
            } else {
                return;
            }
        }
    }
}
