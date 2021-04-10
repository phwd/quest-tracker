package X;

import java.lang.reflect.Field;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.EnumSet;

/* renamed from: X.0GO  reason: invalid class name */
public class AnonymousClass0GO extends AbstractC03710fw {
    @Override // X.AbstractC05940mA
    public final String A4l(Object obj, Class<?> cls) {
        String str;
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
                    Field field = C06320mt.A02.A01;
                    if (field != null) {
                        try {
                            cls3 = (Class) field.get(abstractCollection);
                        } catch (Exception e) {
                            throw new IllegalArgumentException(e);
                        }
                    } else {
                        str = "Can not figure out type for EnumSet (odd JDK platform?)";
                    }
                }
                return AnonymousClass02Z.A00(EnumSet.class, C06240ml.A02.A09(cls3, null)).A01();
            } else if (obj instanceof EnumMap) {
                AbstractMap abstractMap = (AbstractMap) obj;
                if (!abstractMap.isEmpty()) {
                    cls2 = abstractMap.keySet().iterator().next().getClass();
                    if (cls2.getSuperclass() != Enum.class) {
                        cls2 = cls2.getSuperclass();
                    }
                } else {
                    Field field2 = C06320mt.A02.A00;
                    if (field2 != null) {
                        cls2 = (Class) field2.get(abstractMap);
                    } else {
                        str = "Can not figure out type for EnumMap (odd JDK platform?)";
                    }
                }
                C06240ml r4 = C06240ml.A02;
                return AnonymousClass02Y.A00(EnumMap.class, r4.A09(cls2, null), r4.A09(Object.class, null)).A01();
            } else {
                String substring = name.substring(9);
                if ((substring.startsWith(".Arrays$") || substring.startsWith(".Collections$")) && name.indexOf("List") >= 0) {
                    return "java.util.ArrayList";
                }
                return name;
            }
            throw new IllegalStateException(str);
        } else if (name.indexOf(36) < 0 || C06330mu.A00(cls) == null) {
            return name;
        } else {
            AbstractC04000gb r1 = this.A00;
            if (C06330mu.A00(r1._class) == null) {
                return r1._class.getName();
            }
            return name;
        }
    }

    @Override // X.AbstractC05940mA
    public AbstractC04000gb A9e(String str) {
        if (str.indexOf(60) > 0) {
            C06270mo r1 = this.A01._parser;
            C06260mn r2 = new C06260mn(str.trim());
            AbstractC04000gb A00 = C06270mo.A00(r1, r2);
            if (!r2.hasMoreTokens()) {
                return A00;
            }
            throw C06270mo.A01(r2, "Unexpected tokens after complete type");
        }
        try {
            return this.A01.A07(this.A00, C06330mu.A01(str));
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException(AnonymousClass006.A07("Invalid type id '", str, "' (for id type 'Id.class'): no such class found"));
        } catch (Exception e) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Invalid type id '", str, "' (for id type 'Id.class'): ", e.getMessage()), e);
        }
    }

    public AnonymousClass0GO(AbstractC04000gb r1, C06240ml r2) {
        super(r1, r2);
    }
}
