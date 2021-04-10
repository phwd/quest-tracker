package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* renamed from: AC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class AC implements AbstractC2675gI0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f7661a;
    public final /* synthetic */ BC b;

    public AC(BC bc, int i) {
        this.b = bc;
        this.f7661a = i;
    }

    @Override // defpackage.AbstractC2675gI0
    public Object get() {
        Object obj;
        Object obj2;
        int i = this.f7661a;
        if (i == 0) {
            Objects.requireNonNull(this.b.f7722a);
            PU0 pu0 = NU0.f8549a;
            Objects.requireNonNull(pu0, "Cannot return null from a non-@Nullable @Provides method");
            return pu0;
        } else if (i == 1) {
            BC bc = this.b;
            Object obj3 = bc.f;
            if (obj3 instanceof C2566fi0) {
                synchronized (obj3) {
                    obj = bc.f;
                    if (obj instanceof C2566fi0) {
                        C2756go1 h = bc.h();
                        Objects.requireNonNull(bc.f7722a);
                        MX0 mx0 = LX0.f8421a;
                        Objects.requireNonNull(mx0, "Cannot return null from a non-@Nullable @Provides method");
                        C0040Ap0 ap0 = new C0040Ap0(h, mx0);
                        KG.b(bc.f, ap0);
                        bc.f = ap0;
                        obj = ap0;
                    }
                }
                obj3 = obj;
            }
            return (C0040Ap0) obj3;
        } else if (i == 2) {
            BC bc2 = this.b;
            Object obj4 = bc2.n;
            if (obj4 instanceof C2566fi0) {
                synchronized (obj4) {
                    obj2 = bc2.n;
                    if (obj2 instanceof C2566fi0) {
                        obj2 = new QY0();
                        KG.b(bc2.n, obj2);
                        bc2.n = obj2;
                    }
                }
                obj4 = obj2;
            }
            return (QY0) obj4;
        } else if (i == 3) {
            CustomTabsConnection f = CustomTabsConnection.f();
            Objects.requireNonNull(f, "Cannot return null from a non-@Nullable @Provides method");
            return f;
        } else if (i == 4) {
            Objects.requireNonNull(this.b.f7722a);
            C1280Va va = AbstractC1341Wa.f9155a;
            Objects.requireNonNull(va, "Cannot return null from a non-@Nullable @Provides method");
            return va;
        } else {
            throw new AssertionError(this.f7661a);
        }
    }
}
