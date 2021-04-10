package X;

import com.google.common.annotations.GwtIncompatible;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;

@GwtIncompatible
/* renamed from: X.0tW  reason: invalid class name */
public final class AnonymousClass0tW {
    public static <T> AnonymousClass0tV<T> A00(Class<T> cls, String str) {
        try {
            return new AnonymousClass0tV<>(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    public static <K, V> void A01(AbstractC07440t7<K, V> r2, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(r2.A18().size());
        for (Map.Entry<K, Collection<V>> entry : r2.A18().entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(entry.getValue().size());
            for (V v : entry.getValue()) {
                objectOutputStream.writeObject(v);
            }
        }
    }

    public static <E> void A02(AnonymousClass0tC<E> r2, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(r2.entrySet().size());
        for (AnonymousClass0Y0 r1 : r2.entrySet()) {
            objectOutputStream.writeObject(r1.A01());
            objectOutputStream.writeInt(r1.A00());
        }
    }
}
