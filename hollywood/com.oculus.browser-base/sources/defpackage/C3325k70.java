package defpackage;

import java.util.Iterator;

/* renamed from: k70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3325k70 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC3838n70 f10263a;

    public C3325k70(AbstractC3838n70 n70) {
        this.f10263a = n70;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AbstractC2260du0 du0 = (AbstractC2260du0) obj;
        Iterator it = this.f10263a.y0.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((QK) uq0.next()).a();
            } else {
                return;
            }
        }
    }
}
