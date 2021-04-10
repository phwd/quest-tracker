package X;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.EnumSet;

/* renamed from: X.0K0  reason: invalid class name */
public class AnonymousClass0K0 extends AnonymousClass0Ze {
    private final String A00(Object obj, Class<?> cls) {
        String str;
        Class<?> cls2;
        Class<?> cls3;
        if (Enum.class.isAssignableFrom(cls) && !cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        String name = cls.getName();
        if (name.startsWith("java.util")) {
            if (obj instanceof EnumSet) {
                EnumSet enumSet = (EnumSet) obj;
                if (!enumSet.isEmpty()) {
                    cls3 = enumSet.iterator().next().getClass();
                    if (cls3.getSuperclass() != Enum.class) {
                        cls3 = cls3.getSuperclass();
                    }
                } else {
                    Field field = C07120ol.A02.A01;
                    if (field != null) {
                        try {
                            cls3 = (Class) field.get(enumSet);
                        } catch (Exception e) {
                            throw new IllegalArgumentException(e);
                        }
                    } else {
                        str = "Can not figure out type for EnumSet (odd JDK platform?)";
                    }
                }
                return C006606d.A00(EnumSet.class, C07040od.A02.A09(cls3, null)).A02();
            } else if (obj instanceof EnumMap) {
                EnumMap enumMap = (EnumMap) obj;
                if (!enumMap.isEmpty()) {
                    cls2 = enumMap.keySet().iterator().next().getClass();
                    if (cls2.getSuperclass() != Enum.class) {
                        cls2 = cls2.getSuperclass();
                    }
                } else {
                    Field field2 = C07120ol.A02.A00;
                    if (field2 != null) {
                        cls2 = (Class) field2.get(enumMap);
                    } else {
                        str = "Can not figure out type for EnumMap (odd JDK platform?)";
                    }
                }
                C07040od r4 = C07040od.A02;
                return C006506c.A00(EnumMap.class, r4.A09(cls2, null), r4.A09(Object.class, null)).A02();
            } else {
                String substring = name.substring(9);
                if ((substring.startsWith(".Arrays$") || substring.startsWith(".Collections$")) && name.indexOf("List") >= 0) {
                    return "java.util.ArrayList";
                }
                return name;
            }
            throw new IllegalStateException(str);
        } else if (name.indexOf(36) < 0 || C07130om.A00(cls) == null) {
            return name;
        } else {
            AnonymousClass0aI r1 = this.A00;
            if (C07130om.A00(r1._class) == null) {
                return r1._class.getName();
            }
            return name;
        }
    }

    @Override // X.AnonymousClass0o4
    public AnonymousClass0aI A8c(String str) {
        if (str.indexOf(60) > 0) {
            C07070og r1 = this.A01._parser;
            C07060of r2 = new C07060of(str.trim());
            AnonymousClass0aI A00 = C07070og.A00(r1, r2);
            if (!r2.hasMoreTokens()) {
                return A00;
            }
            throw C07070og.A01(r2, "Unexpected tokens after complete type");
        }
        try {
            return this.A01.A07(this.A00, C07130om.A01(str));
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException(AnonymousClass006.A07("Invalid type id '", str, "' (for id type 'Id.class'): no such class found"));
        } catch (Exception e) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Invalid type id '", str, "' (for id type 'Id.class'): ", e.getMessage()), e);
        }
    }

    @Override // X.AnonymousClass0o4
    public String A56(Object obj) {
        return A00(obj, obj.getClass());
    }

    @Override // X.AnonymousClass0o4
    public final String A57(Object obj, Class<?> cls) {
        return A00(obj, cls);
    }

    public AnonymousClass0K0(AnonymousClass0aI r1, C07040od r2) {
        super(r1, r2);
    }
}
