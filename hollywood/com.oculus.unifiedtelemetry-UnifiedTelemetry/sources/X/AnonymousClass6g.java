package X;

import com.google.common.annotations.GwtIncompatible;
import java.io.IOException;
import java.io.ObjectOutputStream;

@GwtIncompatible
/* renamed from: X.6g  reason: invalid class name */
public final class AnonymousClass6g {
    public static <E> void A00(AnonymousClass34<E> r2, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(r2.entrySet().size());
        for (AbstractC0181Ug ug : r2.entrySet()) {
            objectOutputStream.writeObject(ug.A01());
            objectOutputStream.writeInt(ug.A00());
        }
    }
}
