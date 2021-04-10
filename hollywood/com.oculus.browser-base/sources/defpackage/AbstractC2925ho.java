package defpackage;

/* renamed from: ho  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2925ho extends AbstractC2990i90 implements AbstractC1649aL0 {
    public int G;

    @Override // defpackage.AbstractC1649aL0
    public void c(Object obj) {
    }

    @Override // defpackage.AbstractC1649aL0
    public int h() {
        return this.G;
    }

    @Override // defpackage.AbstractC2990i90
    public void n(int i, int i2, Object obj) {
        super.n(i, i2, obj);
    }

    @Override // defpackage.AbstractC2990i90
    public void o(int i, int i2) {
        this.G += i2;
        super.o(i, i2);
    }

    @Override // defpackage.AbstractC2990i90
    public void p(int i, int i2) {
        this.G -= i2;
        super.p(i, i2);
    }

    public void q(int i) {
        if (!r(i, 1)) {
            throw new IndexOutOfBoundsException(i + "/" + this.G);
        }
    }

    public final boolean r(int i, int i2) {
        return i >= 0 && i + i2 <= this.G;
    }
}
