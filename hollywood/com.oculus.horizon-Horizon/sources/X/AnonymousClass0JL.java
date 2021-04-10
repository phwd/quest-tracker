package X;

import com.facebook.infer.annotation.Nullsafe;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0JL  reason: invalid class name */
public final class AnonymousClass0JL<K, V> {
    public final AnonymousClass0JK<K, V> A00;
    public final WeakHashMap<K, WeakReference<V>> A01 = new WeakHashMap<>(16);

    public AnonymousClass0JL(AnonymousClass0JK<K, V> r3) {
        this.A00 = r3;
    }
}
