package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.HashMap;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0dn  reason: invalid class name and case insensitive filesystem */
public final class C04000dn<K, V> extends C005905t<K, V> {
    public HashMap<K, AnonymousClass05r<K, V>> A00 = new HashMap<>();

    @Override // X.C005905t
    public final AnonymousClass05r<K, V> A00(K k) {
        return this.A00.get(k);
    }

    @Override // X.C005905t
    public final V A01(@NonNull K k) {
        V v = (V) super.A01(k);
        this.A00.remove(k);
        return v;
    }

    @Override // X.C005905t
    public final V A02(@NonNull K k, @NonNull V v) {
        AnonymousClass05r<K, V> A002 = A00(k);
        if (A002 != null) {
            return A002.A03;
        }
        HashMap<K, AnonymousClass05r<K, V>> hashMap = this.A00;
        AnonymousClass05r<K, V> r1 = new AnonymousClass05r<>(k, v);
        super.A00++;
        AnonymousClass05r<K, V> r0 = this.A01;
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
