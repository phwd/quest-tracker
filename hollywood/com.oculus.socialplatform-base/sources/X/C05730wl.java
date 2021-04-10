package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.HashMap;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0wl  reason: invalid class name and case insensitive filesystem */
public final class C05730wl<K, V> extends AnonymousClass02b<K, V> {
    public HashMap<K, AnonymousClass02Z<K, V>> A00 = new HashMap<>();

    @Override // X.AnonymousClass02b
    public final AnonymousClass02Z<K, V> A00(K k) {
        return this.A00.get(k);
    }

    @Override // X.AnonymousClass02b
    public final V A01(@NonNull K k) {
        V v = (V) super.A01(k);
        this.A00.remove(k);
        return v;
    }

    @Override // X.AnonymousClass02b
    public final V A02(@NonNull K k, @NonNull V v) {
        AnonymousClass02Z<K, V> A002 = A00(k);
        if (A002 != null) {
            return A002.A03;
        }
        HashMap<K, AnonymousClass02Z<K, V>> hashMap = this.A00;
        AnonymousClass02Z<K, V> r1 = new AnonymousClass02Z<>(k, v);
        super.A00++;
        AnonymousClass02Z<K, V> r0 = this.A01;
        if (r0 == null) {
            this.A02 = r1;
        } else {
            r0.A00 = r1;
            r1.A01 = r0;
        }
        this.A01 = r1;
        hashMap.put(k, r1);
        return null;
    }
}
