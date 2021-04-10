package defpackage;

import java.util.Objects;

/* renamed from: r0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4499r0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4670s0 f11176a;
    public final C2978i50 b;

    public C4499r0(C4670s0 s0Var, C2978i50 i50) {
        this.f11176a = s0Var;
        this.b = i50;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4670s0 s0Var = this.f11176a;
        C2978i50 i50 = this.b;
        Boolean bool = (Boolean) obj;
        Objects.requireNonNull(s0Var);
        AbstractC3364kK0.g("KeyboardAccessory.AccessoryToggleClicked", i50.d == 6 ? !i50.b ? 1 : 0 : 2, 2);
        int i = 0;
        while (true) {
            if (i >= s0Var.F.size()) {
                break;
            }
            C4840t0 t0Var = (C4840t0) s0Var.F.get(i);
            if (t0Var.b == 8) {
                C2978i50 i502 = (C2978i50) t0Var.f11315a;
                C2978i50 i503 = new C2978i50(i502.f10120a, !i502.b, i502.d, i502.c);
                C5010u0 u0Var = s0Var.F;
                u0Var.G.set(i, new C4840t0(i503, 8));
                u0Var.n(i, 1, null);
                break;
            }
            i++;
        }
        s0Var.f11243J.a(bool.booleanValue());
        i50.c.onResult(bool);
    }
}
