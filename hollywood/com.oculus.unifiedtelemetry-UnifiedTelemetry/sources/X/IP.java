package X;

import com.facebook.infer.annotation.Nullsafe;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class IP<K, V> {
    public final IO<K, V> A00;
    public final WeakHashMap<K, WeakReference<V>> A01 = new WeakHashMap<>(16);

    public IP(IO<K, V> io) {
        this.A00 = io;
    }
}
