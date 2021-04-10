package defpackage;

/* renamed from: yw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5847yw extends AbstractC3719mR {
    public C5847yw(String str) {
        super(str);
    }

    @Override // defpackage.AbstractC3719mR
    public void b(Object obj, float f) {
        ((C0053Aw) obj).i = f;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((C0053Aw) obj).i);
    }
}
