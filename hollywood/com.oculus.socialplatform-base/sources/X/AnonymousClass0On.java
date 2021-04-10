package X;

import java.lang.reflect.Field;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.EnumSet;

/* renamed from: X.0On  reason: invalid class name */
public class AnonymousClass0On extends AbstractC01870hE {
    private final String A00(Object obj, Class<?> cls) {
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
                    Field field = C04800rH.A02.A01;
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
                return AnonymousClass03E.A00(EnumSet.class, AnonymousClass0r9.A02.A09(cls3, null)).A02();
            } else if (obj instanceof EnumMap) {
                AbstractMap abstractMap = (AbstractMap) obj;
                if (!abstractMap.isEmpty()) {
                    cls2 = abstractMap.keySet().iterator().next().getClass();
                    if (cls2.getSuperclass() != Enum.class) {
                        cls2 = cls2.getSuperclass();
                    }
                } else {
                    Field field2 = C04800rH.A02.A00;
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
                AnonymousClass0r9 r4 = AnonymousClass0r9.A02;
                return AnonymousClass03D.A00(EnumMap.class, r4.A09(cls2, null), r4.A09(Object.class, null)).A02();
            } else {
                String substring = name.substring(9);
                if ((substring.startsWith(".Arrays$") || substring.startsWith(".Collections$")) && name.indexOf("List") >= 0) {
                    return "java.util.ArrayList";
                }
                return name;
            }
        } else if (name.indexOf(36) < 0 || C04810rI.A00(cls) == null) {
            return name;
        } else {
            AbstractC02190iF r1 = this.A00;
            if (C04810rI.A00(r1._class) == null) {
                return r1._class.getName();
            }
            return name;
        }
    }

    @Override // X.AbstractC04530qb
    public AbstractC02190iF AAn(String str) {
        if (str.indexOf(60) > 0) {
            C04750rC r1 = this.A01._parser;
            C04740rB r2 = new C04740rB(str.trim());
            AbstractC02190iF A00 = C04750rC.A00(r1, r2);
            if (!r2.hasMoreTokens()) {
                return A00;
            }
            throw C04750rC.A01(r2, "Unexpected tokens after complete type");
        }
        try {
            return this.A01.A07(this.A00, C04810rI.A01(str));
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException(AnonymousClass006.A09("Invalid type id '", str, "' (for id type 'Id.class'): no such class found"));
        } catch (Exception e) {
            throw new IllegalArgumentException(AnonymousClass006.A0B("Invalid type id '", str, "' (for id type 'Id.class'): ", e.getMessage()), e);
        }
    }

    @Override // X.AbstractC04530qb
    public String A5X(Object obj) {
        return A00(obj, obj.getClass());
    }

    @Override // X.AbstractC04530qb
    public final String A5Y(Object obj, Class<?> cls) {
        return A00(obj, cls);
    }

    public AnonymousClass0On(AbstractC02190iF r1, AnonymousClass0r9 r2) {
        super(r1, r2);
    }
}
