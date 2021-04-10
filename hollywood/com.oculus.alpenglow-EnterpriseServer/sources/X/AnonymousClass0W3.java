package X;

import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.lang.Enum;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0W3  reason: invalid class name */
public final class AnonymousClass0W3<T extends Enum<T>> extends AnonymousClass0Bd<T> {
    public final Map<T, String> A00 = new HashMap();
    public final Map<String, T> A01 = new HashMap();

    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, Object obj) throws IOException {
        String str;
        if (obj == null) {
            str = null;
        } else {
            str = this.A00.get(obj);
        }
        r2.A0E(str);
    }

    public AnonymousClass0W3(Class<T> cls) {
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

    @Override // X.AnonymousClass0Bd
    public final Object A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() != AnonymousClass007.A0I) {
            return this.A01.get(r3.A0F());
        }
        r3.A0L();
        return null;
    }
}
