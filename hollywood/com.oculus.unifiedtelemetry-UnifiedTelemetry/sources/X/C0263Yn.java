package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.Yn  reason: case insensitive filesystem */
public final class C0263Yn {
    public JsonDeserializer<Object> A00;
    public final AbstractC0227Wo A01;
    public final AbstractC0224Wl A02;
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
                sb.append(AnonymousClass06.A05("' of class ", this.A03.getDeclaringClass().getName(), " (expected type: "));
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
            throw new C0223Wj(message, null, e);
        }
    }

    public final String toString() {
        return AnonymousClass06.A05("[any property on class ", this.A03.getDeclaringClass().getName(), "]");
    }

    public C0263Yn(AbstractC0227Wo wo, Method method, AbstractC0224Wl wl, JsonDeserializer<Object> jsonDeserializer) {
        this.A01 = wo;
        this.A02 = wl;
        this.A03 = method;
        this.A00 = jsonDeserializer;
    }

    public final Object A00(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        if (ww.A0Z() == EnumC0470q2.VALUE_NULL) {
            return null;
        }
        return this.A00.A09(ww, wn);
    }

    public final void A01(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj, String str) throws IOException, q0 {
        A02(obj, str, A00(ww, wn));
    }
}
