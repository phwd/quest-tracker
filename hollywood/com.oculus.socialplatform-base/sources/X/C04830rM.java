package X;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0rM  reason: invalid class name and case insensitive filesystem */
public final class C04830rM {
    public final EnumMap<?, C02270iP> A00;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<Ljava/lang/Enum<*>;>;LX/0iJ;)LX/0rM; */
    public static C04830rM A00(Class cls) {
        Class cls2 = cls;
        if (cls.getSuperclass() != Enum.class) {
            cls2 = cls.getSuperclass();
        }
        Enum[] enumArr = (Enum[]) cls2.getEnumConstants();
        if (enumArr != null) {
            HashMap hashMap = new HashMap();
            for (Enum r2 : enumArr) {
                hashMap.put(r2, new C02270iP(r2.name()));
            }
            return new C04830rM(hashMap);
        }
        throw new IllegalArgumentException(AnonymousClass006.A07("Can not determine enum constants for Class ", cls.getName()));
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<Ljava/lang/Enum<*>;>;Ljava/util/Map<Ljava/lang/Enum<*>;LX/0iP;>;)V */
    public C04830rM(Map map) {
        this.A00 = new EnumMap<>(map);
    }
}
