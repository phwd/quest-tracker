package X;

import com.google.gson.annotations.SerializedName;
import java.lang.Enum;
import java.util.HashMap;
import java.util.Map;

public final class KG<T extends Enum<T>> extends hh<T> {
    public final Map<T, String> A00 = new HashMap();
    public final Map<String, T> A01 = new HashMap();

    public KG(Class<T> cls) {
        try {
            T[] enumConstants = cls.getEnumConstants();
            for (T t : enumConstants) {
                String name = t.name();
                SerializedName serializedName = (SerializedName) cls.getField(name).getAnnotation(SerializedName.class);
                if (serializedName != null) {
                    name = serializedName.value();
                    for (String str : serializedName.alternate()) {
                        this.A01.put(str, t);
                    }
                }
                this.A01.put(name, t);
                this.A00.put(t, name);
            }
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }
}
