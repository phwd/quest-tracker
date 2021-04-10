package X;

import com.google.common.annotations.GwtIncompatible;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;

@GwtIncompatible
public final class Re {
    public static <T> Ra<T> A00(Class<T> cls, String str) {
        try {
            return new Ra<>(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    public static <K, V> void A01(Qy<K, V> qy, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(qy.A0q().size());
        for (Map.Entry<K, Collection<V>> entry : qy.A0q().entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(entry.getValue().size());
            for (V v : entry.getValue()) {
                objectOutputStream.writeObject(v);
            }
        }
    }

    public static <E> void A02(AbstractC0120Qz<E> qz, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(qz.entrySet().size());
        for (Mh mh : qz.entrySet()) {
            objectOutputStream.writeObject(mh.A01());
            objectOutputStream.writeInt(mh.A00());
        }
    }
}
