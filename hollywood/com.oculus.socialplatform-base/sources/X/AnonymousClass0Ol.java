package X;

import java.util.HashMap;

/* renamed from: X.0Ol  reason: invalid class name */
public final class AnonymousClass0Ol extends AbstractC01870hE {
    public final AbstractC02110i2<?> A00;
    public final HashMap<String, AbstractC02190iF> A01;
    public final HashMap<String, String> A02;

    public AnonymousClass0Ol(AbstractC02110i2<?> r2, AbstractC02190iF r3, HashMap<String, String> hashMap, HashMap<String, AbstractC02190iF> hashMap2) {
        super(r3, r2._base._typeFactory);
        this.A00 = r2;
        this.A02 = hashMap;
        this.A01 = hashMap2;
    }

    @Override // X.AbstractC04530qb
    public final String A5Y(Object obj, Class<?> cls) {
        if (obj == null) {
            return null;
        }
        return A5X(obj);
    }

    @Override // X.AbstractC04530qb
    public final AbstractC02190iF AAn(String str) throws IllegalArgumentException {
        return this.A01.get(str);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(getClass().getName());
        sb.append("; id-to-type=");
        sb.append(this.A01);
        sb.append(']');
        return sb.toString();
    }

    @Override // X.AbstractC04530qb
    public final String A5X(Object obj) {
        String str;
        Class<?> cls = obj.getClass();
        String name = cls.getName();
        HashMap<String, String> hashMap = this.A02;
        synchronized (hashMap) {
            str = hashMap.get(name);
            if (str == null) {
                AbstractC02110i2<?> r1 = this.A00;
                if (r1.A05(EnumC02160i9.USE_ANNOTATIONS)) {
                    str = r1.A01().A0h(r1.A02(r1.A03(cls)).A07());
                }
                if (str == null) {
                    str = name;
                    int lastIndexOf = name.lastIndexOf(46);
                    if (lastIndexOf >= 0) {
                        str = name.substring(lastIndexOf + 1);
                    }
                }
                hashMap.put(name, str);
            }
        }
        return str;
    }
}
