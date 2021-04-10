package X;

import com.google.common.collect.TreeMultiset;
import java.util.Iterator;

/* renamed from: X.Dh  reason: case insensitive filesystem */
public final class C0143Dh extends AbstractC1181uc<E> {
    public final /* synthetic */ YG A00;

    public C0143Dh(YG yg) {
        this.A00 = yg;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator iterator() {
        return new C0366Ub((TreeMultiset) ((AnonymousClass24) this.A00).A00);
    }

    public final int size() {
        return ((AnonymousClass24) this.A00).A00.entrySet().size();
    }
}
