package X;

import androidx.annotation.NonNull;
import java.util.Map;

/* renamed from: X.02Z  reason: invalid class name */
public class AnonymousClass02Z<K, V> implements Map.Entry<K, V> {
    public AnonymousClass02Z<K, V> A00;
    public AnonymousClass02Z<K, V> A01;
    @NonNull
    public final K A02;
    @NonNull
    public final V A03;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnonymousClass02Z)) {
            return false;
        }
        AnonymousClass02Z r4 = (AnonymousClass02Z) obj;
        return this.A02.equals(r4.A02) && this.A03.equals(r4.A03);
    }

    public final int hashCode() {
        return this.A02.hashCode() ^ this.A03.hashCode();
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) this.A02);
        sb.append("=");
        sb.append((Object) this.A03);
        return sb.toString();
    }

    public AnonymousClass02Z(@NonNull K k, @NonNull V v) {
        this.A02 = k;
        this.A03 = v;
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
}
