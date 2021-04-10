package defpackage;

import android.util.Property;

/* renamed from: mR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3719mR extends Property {
    public AbstractC3719mR(String str) {
        super(Float.class, str);
    }

    /* renamed from: a */
    public final void set(Object obj, Float f) {
        b(obj, f.floatValue());
    }

    public abstract void b(Object obj, float f);
}
