package defpackage;

/* renamed from: hg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2903hg1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC2732gg1 f10093a;

    public AbstractC2903hg1(AbstractC2732gg1 gg1) {
        this.f10093a = gg1;
    }

    public abstract boolean a();

    public boolean b(CharSequence charSequence, int i, int i2) {
        if (i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
            throw new IllegalArgumentException();
        }
        AbstractC2732gg1 gg1 = this.f10093a;
        if (gg1 == null) {
            return a();
        }
        int a2 = gg1.a(charSequence, i, i2);
        if (a2 == 0) {
            return true;
        }
        if (a2 != 1) {
            return a();
        }
        return false;
    }
}
