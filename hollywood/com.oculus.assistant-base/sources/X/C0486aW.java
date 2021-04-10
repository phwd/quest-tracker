package X;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: X.aW  reason: case insensitive filesystem */
public final class C0486aW implements Map, Serializable {
    public static final C0486aW A00 = new C0486aW();
    public static final long serialVersionUID = 8246714829545688274L;

    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean containsKey(Object obj) {
        return false;
    }

    public final int hashCode() {
        return 0;
    }

    public final boolean isEmpty() {
        return true;
    }

    @Override // java.util.Map
    public final /* synthetic */ Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final String toString() {
        return "{}";
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Map) || !((Map) obj).isEmpty()) {
            return false;
        }
        return true;
    }

    private final Object readResolve() {
        return A00;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return C0487aX.A00;
    }

    @Override // java.util.Map
    public final Set keySet() {
        return C0487aX.A00;
    }

    public final int size() {
        return 0;
    }

    @Override // java.util.Map
    public final Collection values() {
        return C0485aV.A00;
    }

    public final /* bridge */ boolean containsValue(Object obj) {
        return false;
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        return null;
    }
}
