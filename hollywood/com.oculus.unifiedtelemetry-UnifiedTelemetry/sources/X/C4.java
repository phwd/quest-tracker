package X;

import java.lang.reflect.Field;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.EnumSet;

public class C4 extends W7 {
    @Override // X.V3
    public final String A32(Object obj, Class<?> cls) {
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
                    Field field = N2.A02.A01;
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
                return AnonymousClass2W.A00(EnumSet.class, NT.A02.A09(cls3, null)).A01();
            } else if (obj instanceof EnumMap) {
                AbstractMap abstractMap = (AbstractMap) obj;
                if (!abstractMap.isEmpty()) {
                    cls2 = abstractMap.keySet().iterator().next().getClass();
                    if (cls2.getSuperclass() != Enum.class) {
                        cls2 = cls2.getSuperclass();
                    }
                } else {
                    Field field2 = N2.A02.A00;
                    if (field2 != null) {
                        cls2 = (Class) field2.get(abstractMap);
                    } else {
                        str = "Can not figure out type for EnumMap (odd JDK platform?)";
                    }
                }
                NT nt = NT.A02;
                return AnonymousClass2V.A00(EnumMap.class, nt.A09(cls2, null), nt.A09(Object.class, null)).A01();
            } else {
                String substring = name.substring(9);
                if ((substring.startsWith(".Arrays$") || substring.startsWith(".Collections$")) && name.indexOf("List") >= 0) {
                    return "java.util.ArrayList";
                }
                return name;
            }
            throw new IllegalStateException(str);
        } else if (name.indexOf(36) < 0 || Mv.A00(cls) == null) {
            return name;
        } else {
            AbstractC0224Wl wl = this.A00;
            if (Mv.A00(wl._class) == null) {
                return wl._class.getName();
            }
            return name;
        }
    }

    @Override // X.V3
    public AbstractC0224Wl A5W(String str) {
        if (str.indexOf(60) > 0) {
            NC nc = this.A01._parser;
            NQ nq = new NQ(str.trim());
            AbstractC0224Wl A00 = NC.A00(nc, nq);
            if (!nq.hasMoreTokens()) {
                return A00;
            }
            throw NC.A01(nq, "Unexpected tokens after complete type");
        }
        try {
            return this.A01.A07(this.A00, Mv.A01(str));
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException(AnonymousClass06.A05("Invalid type id '", str, "' (for id type 'Id.class'): no such class found"));
        } catch (Exception e) {
            throw new IllegalArgumentException(AnonymousClass06.A06("Invalid type id '", str, "' (for id type 'Id.class'): ", e.getMessage()), e);
        }
    }

    public C4(AbstractC0224Wl wl, NT nt) {
        super(wl, nt);
    }
}
