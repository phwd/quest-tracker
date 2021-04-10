package defpackage;

import java.util.Iterator;

/* renamed from: j70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3154j70 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC3838n70 f10189a;
    public final boolean b;
    public final boolean c;

    public C3154j70(AbstractC3838n70 n70, boolean z, boolean z2) {
        this.f10189a = n70;
        this.b = z;
        this.c = z2;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AbstractC3838n70 n70 = this.f10189a;
        boolean z = this.b;
        boolean z2 = this.c;
        AbstractC2260du0 du0 = (AbstractC2260du0) obj;
        Iterator it = n70.y0.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((QK) uq0.next()).b(z, z2);
            } else {
                return;
            }
        }
    }
}
