package defpackage;

import java.util.Iterator;

/* renamed from: v70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5202v70 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final D70 f11461a;
    public final int b;
    public final boolean c;

    public C5202v70(D70 d70, int i, boolean z) {
        this.f11461a = d70;
        this.b = i;
        this.c = z;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        D70 d70 = this.f11461a;
        int i = this.b;
        boolean z = this.c;
        AbstractC2642g70 g70 = (AbstractC2642g70) obj;
        Iterator it = d70.Q.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((I70) uq0.next()).e(i, z);
            } else {
                return;
            }
        }
    }
}
