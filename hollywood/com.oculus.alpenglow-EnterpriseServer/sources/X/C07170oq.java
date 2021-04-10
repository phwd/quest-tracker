package X;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0oq  reason: invalid class name and case insensitive filesystem */
public final class C07170oq {
    public final EnumMap<?, C02620aS> A00;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<Ljava/lang/Enum<*>;>;LX/0aM;)LX/0oq; */
    public static C07170oq A00(Class cls) {
        Class cls2 = cls;
        if (cls.getSuperclass() != Enum.class) {
            cls2 = cls.getSuperclass();
        }
        Enum[] enumArr = (Enum[]) cls2.getEnumConstants();
        if (enumArr != null) {
            HashMap hashMap = new HashMap();
            for (Enum r2 : enumArr) {
                hashMap.put(r2, new C02620aS(r2.name()));
            }
            return new C07170oq(hashMap);
        }
        throw new IllegalArgumentException(AnonymousClass006.A05("Can not determine enum constants for Class ", cls.getName()));
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<Ljava/lang/Enum<*>;>;Ljava/util/Map<Ljava/lang/Enum<*>;LX/0aS;>;)V */
    public C07170oq(Map map) {
        this.A00 = new EnumMap<>(map);
    }
}
