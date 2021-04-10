package X;

import java.util.HashMap;

/* renamed from: X.0Jy  reason: invalid class name */
public final class AnonymousClass0Jy extends AnonymousClass0Ze {
    public final AnonymousClass0a7<?> A00;
    public final HashMap<String, AnonymousClass0aI> A01;
    public final HashMap<String, String> A02;

    public AnonymousClass0Jy(AnonymousClass0a7<?> r2, AnonymousClass0aI r3, HashMap<String, String> hashMap, HashMap<String, AnonymousClass0aI> hashMap2) {
        super(r3, r2._base._typeFactory);
        this.A00 = r2;
        this.A02 = hashMap;
        this.A01 = hashMap2;
    }

    @Override // X.AnonymousClass0o4
    public final String A57(Object obj, Class<?> cls) {
        if (obj == null) {
            return null;
        }
        return A56(obj);
    }

    @Override // X.AnonymousClass0o4
    public final AnonymousClass0aI A8c(String str) throws IllegalArgumentException {
        return this.A01.get(str);
    }

    public final String toString() {
        return "[" + getClass().getName() + "; id-to-type=" + this.A01 + ']';
    }

    @Override // X.AnonymousClass0o4
    public final String A56(Object obj) {
        String str;
        Class<?> cls = obj.getClass();
        String name = cls.getName();
        HashMap<String, String> hashMap = this.A02;
        synchronized (hashMap) {
            str = hashMap.get(name);
            if (str == null) {
                AnonymousClass0a7<?> r1 = this.A00;
                if (r1.A05(EnumC02540aC.USE_ANNOTATIONS)) {
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
