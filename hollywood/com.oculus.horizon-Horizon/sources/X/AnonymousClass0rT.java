package X;

import com.google.common.annotations.GwtIncompatible;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;

@GwtIncompatible
/* renamed from: X.0rT  reason: invalid class name */
public final class AnonymousClass0rT {
    public static <T> AnonymousClass0rS<T> A00(Class<T> cls, String str) {
        try {
            return new AnonymousClass0rS<>(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    public static <K, V> void A01(AbstractC07090r4<K, V> r2, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(r2.A1H().size());
        for (Map.Entry<K, Collection<V>> entry : r2.A1H().entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(entry.getValue().size());
            for (V v : entry.getValue()) {
                objectOutputStream.writeObject(v);
            }
        }
    }

    public static <E> void A02(AnonymousClass0r9<E> r2, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(r2.entrySet().size());
        for (AnonymousClass0dN r1 : r2.entrySet()) {
            objectOutputStream.writeObject(r1.A01());
            objectOutputStream.writeInt(r1.A00());
        }
    }
}
