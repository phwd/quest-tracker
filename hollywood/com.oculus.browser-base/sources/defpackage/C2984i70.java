package defpackage;

import java.util.Iterator;
import java.util.Objects;

/* renamed from: i70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2984i70 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC3838n70 f10121a;

    public C2984i70(AbstractC3838n70 n70) {
        this.f10121a = n70;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        AbstractC2260du0 du0 = (AbstractC2260du0) obj;
        Iterator it = this.f10121a.y0.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                Objects.requireNonNull((QK) uq0.next());
            } else {
                return;
            }
        }
    }
}
