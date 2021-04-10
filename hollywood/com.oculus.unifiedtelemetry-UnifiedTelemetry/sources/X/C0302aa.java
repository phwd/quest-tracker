package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.HashMap;

@RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.aa  reason: case insensitive filesystem */
public final class C0302aa<K, V> extends AnonymousClass2U<K, V> {
    public HashMap<K, AnonymousClass2S<K, V>> A00 = new HashMap<>();

    @Override // X.AnonymousClass2U
    public final AnonymousClass2S<K, V> A00(K k) {
        return this.A00.get(k);
    }

    @Override // X.AnonymousClass2U
    public final V A01(@NonNull K k) {
        V v = (V) super.A01(k);
        this.A00.remove(k);
        return v;
    }

    @Override // X.AnonymousClass2U
    public final V A02(@NonNull K k, @NonNull V v) {
        AnonymousClass2S<K, V> A002 = A00(k);
        if (A002 != null) {
            return A002.A03;
        }
        HashMap<K, AnonymousClass2S<K, V>> hashMap = this.A00;
        AnonymousClass2S<K, V> r1 = new AnonymousClass2S<>(k, v);
        super.A00++;
        AnonymousClass2S<K, V> r0 = this.A01;
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
