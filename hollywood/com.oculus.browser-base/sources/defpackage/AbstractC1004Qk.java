package defpackage;

import java.util.Iterator;

/* renamed from: Qk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1004Qk implements Iterator {
    public abstract byte a();

    @Override // java.util.Iterator
    public Object next() {
        return Byte.valueOf(a());
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
