package defpackage;

/* renamed from: oZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4078oZ0 extends AbstractC3719mR {
    public C4078oZ0(String str) {
        super(str);
    }

    @Override // defpackage.AbstractC3719mR
    public void b(Object obj, float f) {
        ((AbstractC5780yZ0) obj).k0 = f;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((AbstractC5780yZ0) obj).k0);
    }
}
