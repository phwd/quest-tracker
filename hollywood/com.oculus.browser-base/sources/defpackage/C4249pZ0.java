package defpackage;

/* renamed from: pZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4249pZ0 extends AbstractC3719mR {
    public C4249pZ0(String str) {
        super(str);
    }

    @Override // defpackage.AbstractC3719mR
    public void b(Object obj, float f) {
        AbstractC5780yZ0 yz0 = (AbstractC5780yZ0) obj;
        yz0.g0 = f;
        yz0.h0 = f;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        AbstractC5780yZ0 yz0 = (AbstractC5780yZ0) obj;
        float f = yz0.g0;
        if (f == yz0.h0) {
            return Float.valueOf(f);
        }
        return null;
    }
}
