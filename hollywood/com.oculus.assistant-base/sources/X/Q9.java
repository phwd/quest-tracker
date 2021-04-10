package X;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class Q9 {
    public final EnumMap A00;

    public static Q9 A00(Class cls) {
        Class cls2 = cls;
        if (cls.getSuperclass() != Enum.class) {
            cls2 = cls.getSuperclass();
        }
        Enum[] enumArr = (Enum[]) cls2.getEnumConstants();
        if (enumArr != null) {
            HashMap hashMap = new HashMap();
            for (Enum r2 : enumArr) {
                hashMap.put(r2, new C1015qj(r2.name()));
            }
            return new Q9(hashMap);
        }
        throw new IllegalArgumentException(AnonymousClass08.A04("Can not determine enum constants for Class ", cls.getName()));
    }

    public Q9(Map map) {
        this.A00 = new EnumMap(map);
    }
}
