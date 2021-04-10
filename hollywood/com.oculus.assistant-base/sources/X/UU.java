package X;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;

public final class UU {
    public static void A01(UK uk, ObjectInputStream objectInputStream, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            Collection A2E = uk.A2E(objectInputStream.readObject());
            int readInt = objectInputStream.readInt();
            for (int i3 = 0; i3 < readInt; i3++) {
                A2E.add(objectInputStream.readObject());
            }
        }
    }

    public static UT A00(Class cls, String str) {
        try {
            return new UT(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    public static void A02(UK uk, ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(uk.A1G().size());
        for (Map.Entry entry : uk.A1G().entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(((Collection) entry.getValue()).size());
            for (Object obj : (Collection) entry.getValue()) {
                objectOutputStream.writeObject(obj);
            }
        }
    }

    public static void A03(UM um, ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(um.entrySet().size());
        for (AbstractC1179ua uaVar : um.entrySet()) {
            objectOutputStream.writeObject(uaVar.A01());
            objectOutputStream.writeInt(uaVar.A00());
        }
    }
}
