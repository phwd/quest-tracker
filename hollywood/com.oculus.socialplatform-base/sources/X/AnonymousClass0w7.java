package X;

import com.google.common.annotations.GwtIncompatible;
import java.io.IOException;
import java.io.ObjectOutputStream;

@GwtIncompatible
/* renamed from: X.0w7  reason: invalid class name */
public final class AnonymousClass0w7 {
    public static <T> AnonymousClass0w6<T> A00(Class<T> cls, String str) {
        try {
            return new AnonymousClass0w6<>(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    public static <E> void A01(AbstractC05490vp<E> r2, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(r2.entrySet().size());
        for (AnonymousClass0f2 r1 : r2.entrySet()) {
            objectOutputStream.writeObject(r1.A01());
            objectOutputStream.writeInt(r1.A00());
        }
    }
}
