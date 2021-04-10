package defpackage;

/* renamed from: gZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2711gZ0 extends AbstractC3719mR {
    public C2711gZ0(String str) {
        super(str);
    }

    @Override // defpackage.AbstractC3719mR
    public void b(Object obj, float f) {
        ((AbstractC2882hZ0) obj).P(f, true);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((AbstractC2882hZ0) obj).r);
    }
}
