package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.0nA  reason: invalid class name and case insensitive filesystem */
public final class C06560nA {
    public JsonDeserializer<Object> A00;
    public final AbstractC02580aL A01;
    public final AnonymousClass0aI A02;
    public final Method A03;

    public final void A02(Object obj, String str, Object obj2) throws IOException {
        String message;
        Exception exc;
        String name;
        try {
            this.A03.invoke(obj, str, obj2);
        } catch (Exception e) {
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
                exc = e;
            } else {
                if (!(e instanceof IOException)) {
                    boolean z = e instanceof RuntimeException;
                    Exception exc2 = e;
                    if (!z) {
                        while (exc2.getCause() != null) {
                            exc2 = exc2.getCause();
                        }
                        message = exc2.getMessage();
                        exc = exc2;
                    }
                }
                throw e;
            }
            throw new AnonymousClass0aG(message, null, exc);
        }
    }

    public final String toString() {
        return AnonymousClass006.A07("[any property on class ", this.A03.getDeclaringClass().getName(), "]");
    }

    public C06560nA(AbstractC02580aL r1, Method method, AnonymousClass0aI r3, JsonDeserializer<Object> jsonDeserializer) {
        this.A01 = r1;
        this.A02 = r3;
        this.A03 = method;
        this.A00 = jsonDeserializer;
    }

    public final Object A00(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        if (r3.A0G() == EnumC05930lf.VALUE_NULL) {
            return null;
        }
        return this.A00.A09(r3, r4);
    }

    public final void A01(AnonymousClass0aT r2, AbstractC02570aK r3, Object obj, String str) throws IOException, C05910ld {
        A02(obj, str, A00(r2, r3));
    }
}
