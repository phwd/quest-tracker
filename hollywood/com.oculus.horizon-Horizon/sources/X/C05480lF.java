package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.0lF  reason: invalid class name and case insensitive filesystem */
public final class C05480lF {
    public JsonDeserializer<Object> A00;
    public final AbstractC04030gh A01;
    public final AbstractC04000gb A02;
    public final Method A03;

    public final void A02(Object obj, String str, Object obj2) throws IOException {
        String message;
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
                sb.append(AnonymousClass006.A07("' of class ", this.A03.getDeclaringClass().getName(), " (expected type: "));
                sb.append(this.A02);
                sb.append("; actual type: ");
                sb.append(name);
                sb.append(")");
                String message2 = e.getMessage();
                if (message2 != null) {
                    sb.append(", problem: ");
                } else {
                    message2 = " (no error message provided)";
                }
                sb.append(message2);
                message = sb.toString();
            } else if ((e instanceof IOException) || (e instanceof RuntimeException)) {
                throw e;
            } else {
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                message = e.getMessage();
            }
            throw new C03990gZ(message, null, e);
        }
    }

    public final String toString() {
        return AnonymousClass006.A07("[any property on class ", this.A03.getDeclaringClass().getName(), "]");
    }

    public C05480lF(AbstractC04030gh r1, Method method, AbstractC04000gb r3, JsonDeserializer<Object> jsonDeserializer) {
        this.A01 = r1;
        this.A02 = r3;
        this.A03 = method;
        this.A00 = jsonDeserializer;
    }

    public final Object A00(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        if (r3.A0a() == EnumC04820ji.VALUE_NULL) {
            return null;
        }
        return this.A00.A09(r3, r4);
    }

    public final void A01(AbstractC04100gp r2, AbstractC04020gg r3, Object obj, String str) throws IOException, AnonymousClass0jg {
        A02(obj, str, A00(r2, r3));
    }
}
