package java.lang.ref;

public class PhantomReference extends Reference {
    @Override // java.lang.ref.Reference
    public Object get() {
        return null;
    }

    public PhantomReference(Object obj, ReferenceQueue referenceQueue) {
        super(obj, referenceQueue);
    }
}
