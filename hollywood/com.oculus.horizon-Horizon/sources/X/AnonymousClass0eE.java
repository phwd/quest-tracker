package X;

import java.util.Iterator;

/* renamed from: X.0eE  reason: invalid class name */
public class AnonymousClass0eE extends AbstractC06670pU<T> {
    public final /* synthetic */ Iterable A00;

    public AnonymousClass0eE(Iterable iterable) {
        this.A00 = iterable;
    }

    @Override // java.lang.Iterable
    public final Iterator<T> iterator() {
        return new AnonymousClass0qI(new C03660dt(this.A00.iterator(), new C03680dv()));
    }
}
