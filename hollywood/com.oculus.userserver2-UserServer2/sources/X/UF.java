package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.HashMap;

@RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
public final class UF<K, V> extends AnonymousClass2h<K, V> {
    public HashMap<K, AnonymousClass2f<K, V>> A00 = new HashMap<>();

    @Override // X.AnonymousClass2h
    public final V A01(@NonNull K k) {
        V v = (V) super.A01(k);
        this.A00.remove(k);
        return v;
    }
}
