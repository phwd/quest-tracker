package defpackage;

import java.util.Iterator;

/* renamed from: E01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class E01 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final M01 f7932a;

    public E01(M01 m01) {
        this.f7932a = m01;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        M01 m01 = this.f7932a;
        AbstractC2451f01 f01 = (AbstractC2451f01) obj;
        Iterator it = m01.L.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                AbstractC2280e01 e01 = (AbstractC2280e01) uq0.next();
                int i = m01.T;
                boolean z = true;
                if (i != 2) {
                    if (i == 3 && m01.I.h(N01.e)) {
                        z = false;
                    } else if (m01.I.h(N01.f)) {
                        UH0 uh0 = m01.O;
                        if (uh0 != null) {
                            z = uh0.h(AbstractC5798yf1.f11692a);
                        }
                    } else {
                        z = m01.I.h(AbstractC5798yf1.f11692a);
                    }
                }
                e01.a(i, z);
            } else {
                return;
            }
        }
    }
}
