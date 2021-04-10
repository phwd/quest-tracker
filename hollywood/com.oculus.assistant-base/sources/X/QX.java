package X;

import java.lang.reflect.Field;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.EnumSet;

public class QX extends AbstractC1056rW {
    private final String A00(Object obj, Class cls) {
        Class<?> cls2;
        Class<?> cls3;
        if (Enum.class.isAssignableFrom(cls) && !cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        String name = cls.getName();
        if (name.startsWith("java.util")) {
            if (obj instanceof EnumSet) {
                AbstractCollection abstractCollection = (AbstractCollection) obj;
                if (!abstractCollection.isEmpty()) {
                    cls3 = abstractCollection.iterator().next().getClass();
                    if (cls3.getSuperclass() != Enum.class) {
                        cls3 = cls3.getSuperclass();
                    }
                } else {
                    Field field = Q4.A02.A01;
                    if (field != null) {
                        try {
                            cls3 = (Class) field.get(abstractCollection);
                        } catch (Exception e) {
                            throw new IllegalArgumentException(e);
                        }
                    } else {
                        throw new IllegalStateException("Can not figure out type for EnumSet (odd JDK platform?)");
                    }
                }
                return C00323q.A00(EnumSet.class, C0300Pw.A02.A09(cls3, null)).A0I();
            } else if (obj instanceof EnumMap) {
                AbstractMap abstractMap = (AbstractMap) obj;
                if (!abstractMap.isEmpty()) {
                    cls2 = abstractMap.keySet().iterator().next().getClass();
                    if (cls2.getSuperclass() != Enum.class) {
                        cls2 = cls2.getSuperclass();
                    }
                } else {
                    Field field2 = Q4.A02.A00;
                    if (field2 != null) {
                        try {
                            cls2 = (Class) field2.get(abstractMap);
                        } catch (Exception e2) {
                            throw new IllegalArgumentException(e2);
                        }
                    } else {
                        throw new IllegalStateException("Can not figure out type for EnumMap (odd JDK platform?)");
                    }
                }
                C0300Pw pw = C0300Pw.A02;
                return C00313p.A00(EnumMap.class, pw.A09(cls2, null), pw.A09(Object.class, null)).A0I();
            } else {
                String substring = name.substring(9);
                if ((substring.startsWith(".Arrays$") || substring.startsWith(".Collections$")) && name.indexOf("List") >= 0) {
                    return "java.util.ArrayList";
                }
                return name;
            }
        } else if (name.indexOf(36) < 0 || Q5.A00(cls) == null) {
            return name;
        } else {
            AbstractC1024qt qtVar = this.A00;
            if (Q5.A00(qtVar._class) == null) {
                return qtVar._class.getName();
            }
            return name;
        }
    }

    @Override // X.PS
    public final String A3F(Object obj) {
        if (!(this instanceof AnonymousClass0a)) {
            return A00(obj, obj.getClass());
        }
        String name = obj.getClass().getName();
        String str = ((AnonymousClass0a) this).A00;
        if (name.startsWith(str)) {
            return name.substring(str.length() - 1);
        }
        return name;
    }

    @Override // X.PS
    public AbstractC1024qt A5H(String str) {
        if (str.indexOf(60) > 0) {
            C0303Pz pz = this.A01._parser;
            C0302Py py = new C0302Py(str.trim());
            AbstractC1024qt A00 = C0303Pz.A00(pz, py);
            if (!py.hasMoreTokens()) {
                return A00;
            }
            throw C0303Pz.A01(py, "Unexpected tokens after complete type");
        }
        try {
            return this.A01.A07(this.A00, Q5.A01(str));
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException(AnonymousClass08.A05("Invalid type id '", str, "' (for id type 'Id.class'): no such class found"));
        } catch (Exception e) {
            throw new IllegalArgumentException(AnonymousClass08.A06("Invalid type id '", str, "' (for id type 'Id.class'): ", e.getMessage()), e);
        }
    }

    @Override // X.PS
    public final String A3G(Object obj, Class cls) {
        return A00(obj, cls);
    }

    public QX(AbstractC1024qt qtVar, C0300Pw pw) {
        super(qtVar, pw);
    }
}
