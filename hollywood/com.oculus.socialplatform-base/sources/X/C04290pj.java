package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.0pj  reason: invalid class name and case insensitive filesystem */
public final class C04290pj {
    public JsonDeserializer<Object> A00;
    public final AbstractC02220iI A01;
    public final AbstractC02190iF A02;
    public final Method A03;

    public final void A02(Object obj, String str, Object obj2) throws IOException {
        String name;
        try {
            this.A03.invoke(obj, str, obj2);
        } catch (Exception e) {
            e = e;
            if (e instanceof IllegalArgumentException) {
                if (obj2 == null) {
                    name = "[NULL]";
                } else {
                    name = obj2.getClass().getName();
                }
                StringBuilder sb = new StringBuilder("Problem deserializing \"any\" property '");
                sb.append(str);
                sb.append(AnonymousClass006.A09("' of class ", this.A03.getDeclaringClass().getName(), " (expected type: "));
                sb.append(this.A02);
                sb.append("; actual type: ");
                sb.append(name);
                sb.append(")");
                String message = e.getMessage();
                if (message != null) {
                    sb.append(", problem: ");
                } else {
                    message = " (no error message provided)";
                }
                sb.append(message);
                throw new C02180iD(sb.toString(), null, e);
            } else if (e instanceof IOException) {
                throw e;
            } else if (e instanceof RuntimeException) {
                throw e;
            } else {
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw new C02180iD(e.getMessage(), null, e);
            }
        }
    }

    public final String toString() {
        return AnonymousClass006.A09("[any property on class ", this.A03.getDeclaringClass().getName(), "]");
    }

    public C04290pj(AbstractC02220iI r1, Method method, AbstractC02190iF r3, JsonDeserializer<Object> jsonDeserializer) {
        this.A01 = r1;
        this.A02 = r3;
        this.A03 = method;
        this.A00 = jsonDeserializer;
    }

    public final Object A00(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        if (r3.A0i() == EnumC03640oE.VALUE_NULL) {
            return null;
        }
        return this.A00.A0A(r3, r4);
    }

    public final void A01(AbstractC02280iQ r2, AbstractC02210iH r3, Object obj, String str) throws IOException, C03620oC {
        A02(obj, str, A00(r2, r3));
    }
}
