package defpackage;

import java.util.HashSet;
import java.util.Set;
import org.chromium.base.Callback;

/* renamed from: ga  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2712ga extends C1078Rq0 {

    /* renamed from: J  reason: collision with root package name */
    public final Set f10005J = new HashSet();
    public final Callback K = new C2541fa(this);

    public C2712ga() {
        super.m(0);
    }

    @Override // defpackage.C1078Rq0
    public void m(Object obj) {
        Integer num = (Integer) obj;
        throw new IllegalStateException("#set(...) should not be called directly on ApplicationViewportInsetSupplier.");
    }

    public final void n() {
        int i = 0;
        for (AbstractC0956Pq0 pq0 : this.f10005J) {
            i = Math.max(i, ((C1078Rq0) pq0).H == null ? 0 : ((Integer) ((C1078Rq0) pq0).H).intValue());
        }
        super.m(Integer.valueOf(i));
    }
}
