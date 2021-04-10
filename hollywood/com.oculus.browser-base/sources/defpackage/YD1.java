package defpackage;

import java.util.NoSuchElementException;

/* renamed from: YD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class YD1 extends AE1 {
    public boolean F;
    public final /* synthetic */ Object G;

    public YD1(Object obj) {
        this.G = obj;
    }

    public final boolean hasNext() {
        return !this.F;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.F) {
            this.F = true;
            return this.G;
        }
        throw new NoSuchElementException();
    }
}
