package X;

/* renamed from: X.0lp  reason: invalid class name and case insensitive filesystem */
public final class C05770lp {
    public final Class<? extends AbstractC04750jT<?>> A00;
    public final Class<?> A01;
    public final String A02;
    public final boolean A03;

    public final String toString() {
        String name;
        StringBuilder sb = new StringBuilder("ObjectIdInfo: propName=");
        sb.append(this.A02);
        sb.append(", scope=");
        Class<?> cls = this.A01;
        String str = "null";
        if (cls == null) {
            name = str;
        } else {
            name = cls.getName();
        }
        sb.append(name);
        sb.append(", generatorType=");
        Class<? extends AbstractC04750jT<?>> cls2 = this.A00;
        if (cls2 != null) {
            str = cls2.getName();
        }
        sb.append(str);
        sb.append(", alwaysAsId=");
        sb.append(this.A03);
        return sb.toString();
    }

    public C05770lp(String str, Class<?> cls, Class<? extends AbstractC04750jT<?>> cls2, boolean z) {
        this.A02 = str;
        this.A01 = cls;
        this.A00 = cls2;
        this.A03 = z;
    }
}
