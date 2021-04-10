package defpackage;

/* renamed from: i31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2973i31 extends AbstractC3719mR {
    public C2973i31(String str) {
        super(str);
    }

    @Override // defpackage.AbstractC3719mR
    public void b(Object obj, float f) {
        ((C3998o31) obj).t = f;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((C3998o31) obj).t);
    }
}
