package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.Oj  reason: case insensitive filesystem */
public final class C0267Oj {
    public JsonDeserializer A00;
    public final O5 A01;
    public final AbstractC1024qt A02;
    public final Method A03;

    public final void A02(Object obj, String str, Object obj2) {
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
                sb.append(AnonymousClass08.A05("' of class ", this.A03.getDeclaringClass().getName(), " (expected type: "));
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
                throw new C1025qv(sb.toString(), null, e);
            } else if (e instanceof IOException) {
                throw e;
            } else if (e instanceof RuntimeException) {
                throw e;
            } else {
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw new C1025qv(e.getMessage(), null, e);
            }
        }
    }

    public final String toString() {
        return AnonymousClass08.A05("[any property on class ", this.A03.getDeclaringClass().getName(), "]");
    }

    public C0267Oj(O5 o5, Method method, AbstractC1024qt qtVar, JsonDeserializer jsonDeserializer) {
        this.A01 = o5;
        this.A02 = qtVar;
        this.A03 = method;
        this.A00 = jsonDeserializer;
    }

    public final Object A00(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        if (qiVar.A0U() == NX.VALUE_NULL) {
            return null;
        }
        return this.A00.A0C(qiVar, qrVar);
    }

    public final void A01(AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj, String str) {
        A02(obj, str, A00(qiVar, qrVar));
    }
}
