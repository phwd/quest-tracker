package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: U0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class U0 implements W1 {
    public final C1769b1 F;

    public U0(C1769b1 b1Var) {
        this.F = b1Var;
    }

    @Override // defpackage.W1
    public void e() {
        C1769b1 b1Var = this.F;
        Objects.requireNonNull(b1Var);
        Object obj = ThreadUtils.f10596a;
        new C1589a1(b1Var, null).d(AbstractC2032cb.b);
    }
}
