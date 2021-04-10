package defpackage;

import android.graphics.drawable.Drawable;
import android.util.Property;

/* renamed from: Q6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q6 extends Property {
    public Q6(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        Drawable drawable = (Drawable) obj;
        return 0;
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        ((Drawable) obj).setAlpha(((Integer) obj2).intValue());
    }
}
