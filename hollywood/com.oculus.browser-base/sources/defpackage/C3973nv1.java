package defpackage;

import android.util.Property;
import android.view.View;

/* renamed from: nv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3973nv1 extends Property {
    public C3973nv1(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(AbstractC4315pv1.a((View) obj));
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        float floatValue = ((Float) obj2).floatValue();
        AbstractC4315pv1.f11100a.e((View) obj, floatValue);
    }
}
