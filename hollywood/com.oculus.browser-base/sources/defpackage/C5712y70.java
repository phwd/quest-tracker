package defpackage;

import java.util.Iterator;

/* renamed from: y70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5712y70 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final D70 f11665a;
    public final int b;

    public C5712y70(D70 d70, int i) {
        this.f11665a = d70;
        this.b = i;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        D70 d70 = this.f11665a;
        int i = this.b;
        AbstractC2642g70 g70 = (AbstractC2642g70) obj;
        Iterator it = d70.Q.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((I70) uq0.next()).a(i);
            } else {
                return;
            }
        }
    }
}
