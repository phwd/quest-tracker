package defpackage;

/* renamed from: XX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class XX0 extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f9214a = AbstractC5686xz.c(13);
    public final boolean b;
    public final int c;

    public XX0(int i) {
        int i2 = 0;
        this.b = i != 0 && i < 15;
        this.c = i != 0 ? Math.max(1, Math.min(10, Math.round(((float) (i - 8)) * 0.5f))) : i2;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return this.f9214a && this.b;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean b() {
        return this.b;
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        ((C4017oA) hz).b(18, Integer.valueOf(this.c));
    }

    @Override // defpackage.AbstractC5856yz
    public boolean h() {
        return true;
    }
}
