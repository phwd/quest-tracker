package X;

import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.lang.Enum;
import java.util.HashMap;
import java.util.Map;

public final class S5<T extends Enum<T>> extends AbstractC0131Ob<T> {
    public final Map<T, String> A00 = new HashMap();
    public final Map<String, T> A01 = new HashMap();

    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Object obj) throws IOException {
        String str;
        if (obj == null) {
            str = null;
        } else {
            str = this.A00.get(obj);
        }
        mmVar.A0G(str);
    }

    public S5(Class<T> cls) {
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

    @Override // X.AbstractC0131Ob
    public final Object A02(lk lkVar) throws IOException {
        if (lkVar.A0G() != AnonymousClass07.A08) {
            return this.A01.get(lkVar.A0J());
        }
        lkVar.A0P();
        return null;
    }
}
