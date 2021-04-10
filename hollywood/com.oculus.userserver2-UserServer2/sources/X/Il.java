package X;

import com.facebook.infer.annotation.Nullsafe;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Il<K, V> {
    public final Ik<K, V> A00;
    public final WeakHashMap<K, WeakReference<V>> A01 = new WeakHashMap<>(16);

    public Il(Ik<K, V> ik) {
        this.A00 = ik;
    }
}
