package defpackage;

import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: Zs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1570Zs0 implements AbstractC1509Ys0 {
    public final C5232vH0 F = new C5232vH0();
    public final C1881bh1 G = new C1881bh1();

    public void a(Object obj) {
        Objects.requireNonNull(this.G);
        this.F.b(obj);
    }

    @Override // defpackage.AbstractC1509Ys0
    public Object g(Callback callback) {
        Objects.requireNonNull(this.G);
        this.F.g(callback);
        return get();
    }

    @Override // defpackage.Q31
    public Object get() {
        Objects.requireNonNull(this.G);
        if (this.F.d()) {
            return this.F.b;
        }
        return null;
    }
}
