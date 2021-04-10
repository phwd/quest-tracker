package X;

import androidx.annotation.NonNull;
import java.util.Map;

/* renamed from: X.05r  reason: invalid class name */
public class AnonymousClass05r<K, V> implements Map.Entry<K, V> {
    public AnonymousClass05r<K, V> A00;
    public AnonymousClass05r<K, V> A01;
    @NonNull
    public final K A02;
    @NonNull
    public final V A03;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnonymousClass05r)) {
            return false;
        }
        AnonymousClass05r r4 = (AnonymousClass05r) obj;
        return this.A02.equals(r4.A02) && this.A03.equals(r4.A03);
    }

    @Override // java.util.Map.Entry
    @NonNull
    public final K getKey() {
        return this.A02;
    }

    @Override // java.util.Map.Entry
    @NonNull
    public final V getValue() {
        return this.A03;
    }

    public final int hashCode() {
        return this.A02.hashCode() ^ this.A03.hashCode();
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        return ((Object) this.A02) + "=" + ((Object) this.A03);
    }

    public AnonymousClass05r(@NonNull K k, @NonNull V v) {
        this.A02 = k;
        this.A03 = v;
    }
}
