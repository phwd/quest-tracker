package defpackage;

import android.util.Property;

/* renamed from: VH0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VH0 extends Property {

    /* renamed from: a  reason: collision with root package name */
    public final RH0 f9075a;

    public VH0(RH0 rh0) {
        super(Float.class, rh0.toString());
        this.f9075a = rh0;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((UH0) obj).e(this.f9075a));
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        ((UH0) obj).k(this.f9075a, ((Float) obj2).floatValue());
    }
}
