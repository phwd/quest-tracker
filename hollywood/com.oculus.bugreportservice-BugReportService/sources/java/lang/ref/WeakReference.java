package java.lang.ref;

public class WeakReference extends Reference {
    public WeakReference(Object obj) {
        super(obj);
    }

    public WeakReference(Object obj, ReferenceQueue referenceQueue) {
        super(obj, referenceQueue);
    }
}
