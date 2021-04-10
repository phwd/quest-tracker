package X;

import java.util.ConcurrentModificationException;

/* renamed from: X.Uf  reason: case insensitive filesystem */
public final class C0369Uf {
    public Object A00;

    public final void A00(Object obj, Object obj2) {
        if (this.A00 == obj) {
            this.A00 = obj2;
            return;
        }
        throw new ConcurrentModificationException();
    }
}
