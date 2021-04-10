package defpackage;

/* renamed from: j31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3143j31 extends AbstractC3719mR {
    public C3143j31(String str) {
        super(str);
    }

    @Override // defpackage.AbstractC3719mR
    public void b(Object obj, float f) {
        C3998o31 o31 = (C3998o31) obj;
        o31.w = f;
        o31.i();
        o31.y.right = o31.u + o31.w;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((C3998o31) obj).x);
    }
}
