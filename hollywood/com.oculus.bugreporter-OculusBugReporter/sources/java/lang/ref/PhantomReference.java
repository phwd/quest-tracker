package java.lang.ref;

public class PhantomReference<T> extends Reference<T> {
    @Override // java.lang.ref.Reference
    public T get() {
        return null;
    }

    public PhantomReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }
}
