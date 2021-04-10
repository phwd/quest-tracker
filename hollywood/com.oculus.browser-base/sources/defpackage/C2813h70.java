package defpackage;

import java.util.Iterator;

/* renamed from: h70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2813h70 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC3838n70 f10048a;
    public final boolean b;

    public C2813h70(AbstractC3838n70 n70, boolean z) {
        this.f10048a = n70;
        this.b = z;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AbstractC3838n70 n70 = this.f10048a;
        boolean z = this.b;
        AbstractC2260du0 du0 = (AbstractC2260du0) obj;
        Iterator it = n70.y0.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((QK) uq0.next()).c(z);
            } else {
                return;
            }
        }
    }
}
