package defpackage;

/* renamed from: FZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FZ0 extends AbstractC3719mR {
    public FZ0(String str) {
        super(str);
    }

    @Override // defpackage.AbstractC3719mR
    public void b(Object obj, float f) {
        ((IZ0) obj).l = f;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((IZ0) obj).l);
    }
}
