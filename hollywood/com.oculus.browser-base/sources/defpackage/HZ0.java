package defpackage;

/* renamed from: HZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HZ0 extends AbstractC3719mR {
    public HZ0(String str) {
        super(str);
    }

    @Override // defpackage.AbstractC3719mR
    public void b(Object obj, float f) {
        ((IZ0) obj).m = f;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((IZ0) obj).m);
    }
}
