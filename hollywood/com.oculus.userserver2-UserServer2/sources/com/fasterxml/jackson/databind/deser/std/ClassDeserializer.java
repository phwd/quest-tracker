package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class ClassDeserializer extends StdScalarDeserializer<Class<?>> {
    public static final ClassDeserializer A00 = new ClassDeserializer();
    public static final long serialVersionUID = 1;

    /* JADX WARN: Incorrect args count in method signature: (LX/Rn;LX/Rd;)Ljava/lang/Class<*>; */
    private final Class A00(Rn rn) throws IOException, AnonymousClass9r {
        if (((B3) rn).A00 == AnonymousClass9p.VALUE_STRING) {
            String trim = rn.A09().trim();
            try {
                if (trim.indexOf(46) < 0) {
                    if ("int".equals(trim)) {
                        return Integer.TYPE;
                    }
                    if ("long".equals(trim)) {
                        return Long.TYPE;
                    }
                    if ("float".equals(trim)) {
                        return Float.TYPE;
                    }
                    if ("double".equals(trim)) {
                        return Double.TYPE;
                    }
                    if ("boolean".equals(trim)) {
                        return Boolean.TYPE;
                    }
                    if ("byte".equals(trim)) {
                        return Byte.TYPE;
                    }
                    if ("char".equals(trim)) {
                        return Character.TYPE;
                    }
                    if ("short".equals(trim)) {
                        return Short.TYPE;
                    }
                    if ("void".equals(trim)) {
                        return Void.TYPE;
                    }
                }
                Throwable e = null;
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                if (contextClassLoader != null) {
                    try {
                        return Class.forName(trim, true, contextClassLoader);
                    } catch (Exception e2) {
                        e = e2;
                        while (e.getCause() != null) {
                            e = e.getCause();
                        }
                    }
                }
                try {
                    return Class.forName(trim);
                } catch (Exception e3) {
                    e = e3;
                    if (e == null) {
                        while (e.getCause() != null) {
                            e = e.getCause();
                        }
                        e = e;
                    }
                    if (e instanceof RuntimeException) {
                        throw e;
                    }
                    throw new ClassNotFoundException(e.getMessage(), e);
                }
            } catch (Exception e4) {
                for (e = e4; e.getCause() != null; e = e.getCause()) {
                }
                throw null;
            }
        } else {
            throw null;
        }
    }

    public ClassDeserializer() {
        super(Class.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A00(rn);
    }
}
