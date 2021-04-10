package X;

import java.lang.reflect.Method;

/* renamed from: X.0lo  reason: invalid class name and case insensitive filesystem */
public final class C05760lo {
    public static final Class<?>[] A02 = new Class[0];
    public final String A00;
    public final Class<?>[] A01;

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj != null && obj.getClass() == getClass()) {
                C05760lo r10 = (C05760lo) obj;
                if (this.A00.equals(r10.A00)) {
                    Class<?>[] clsArr = r10.A01;
                    Class<?>[] clsArr2 = this.A01;
                    int length = clsArr2.length;
                    if (clsArr.length == length) {
                        for (int i = 0; i < length; i++) {
                            Class<?> cls = clsArr[i];
                            Class<?> cls2 = clsArr2[i];
                            if (cls == cls2 || cls.isAssignableFrom(cls2) || cls2.isAssignableFrom(cls)) {
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode() + this.A01.length;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A00);
        sb.append("(");
        sb.append(this.A01.length);
        sb.append("-args)");
        return sb.toString();
    }

    public C05760lo(String str, Class<?>[] clsArr) {
        this.A00 = str;
        this.A01 = clsArr == null ? A02 : clsArr;
    }

    public C05760lo(Method method) {
        this(method.getName(), method.getParameterTypes());
    }
}
