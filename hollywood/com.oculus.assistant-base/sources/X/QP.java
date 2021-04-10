package X;

import java.util.HashMap;

public final class QP extends AbstractC1056rW {
    public final AbstractC1032r3 A00;
    public final HashMap A01;
    public final HashMap A02;

    public QP(AbstractC1032r3 r3Var, AbstractC1024qt qtVar, HashMap hashMap, HashMap hashMap2) {
        super(qtVar, r3Var._base._typeFactory);
        this.A00 = r3Var;
        this.A02 = hashMap;
        this.A01 = hashMap2;
    }

    @Override // X.PS
    public final String A3G(Object obj, Class cls) {
        if (obj == null) {
            return null;
        }
        return A3F(obj);
    }

    @Override // X.PS
    public final AbstractC1024qt A5H(String str) {
        return (AbstractC1024qt) this.A01.get(str);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(getClass().getName());
        sb.append("; id-to-type=");
        sb.append(this.A01);
        sb.append(']');
        return sb.toString();
    }

    @Override // X.PS
    public final String A3F(Object obj) {
        String str;
        Class<?> cls = obj.getClass();
        String name = cls.getName();
        HashMap hashMap = this.A02;
        synchronized (hashMap) {
            str = (String) hashMap.get(name);
            if (str == null) {
                AbstractC1032r3 r3Var = this.A00;
                if (r3Var.A05(EnumC1027qy.USE_ANNOTATIONS)) {
                    str = r3Var.A01().A0H(((C1046rL) r3Var.A02(r3Var.A03(cls))).A09);
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
