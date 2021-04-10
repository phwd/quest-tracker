package X;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/* renamed from: X.13h  reason: invalid class name */
public final class AnonymousClass13h implements ParameterizedType, Serializable {
    public static final long serialVersionUID = 0;
    public final Type ownerType;
    public final Type rawType;
    public final Type[] typeArguments;

    public final boolean equals(Object obj) {
        if (!(obj instanceof ParameterizedType) || !AnonymousClass13j.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final Type[] getActualTypeArguments() {
        return (Type[]) this.typeArguments.clone();
    }

    public final int hashCode() {
        int i;
        int hashCode = Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode();
        Type type = this.ownerType;
        if (type != null) {
            i = type.hashCode();
        } else {
            i = 0;
        }
        return hashCode ^ i;
    }

    public final String toString() {
        int length = this.typeArguments.length;
        if (length == 0) {
            return AnonymousClass13j.A01(this.rawType);
        }
        StringBuilder sb = new StringBuilder((length + 1) * 30);
        sb.append(AnonymousClass13j.A01(this.rawType));
        sb.append("<");
        sb.append(AnonymousClass13j.A01(this.typeArguments[0]));
        for (int i = 1; i < length; i++) {
            sb.append(", ");
            sb.append(AnonymousClass13j.A01(this.typeArguments[i]));
        }
        sb.append(">");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001b, code lost:
        if (r1.getEnclosingClass() == null) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass13h(java.lang.reflect.Type r5, java.lang.reflect.Type r6, java.lang.reflect.Type... r7) {
        /*
            r4 = this;
            r4.<init>()
            boolean r0 = r6 instanceof java.lang.Class
            r3 = 0
            if (r0 == 0) goto L_0x0026
            r1 = r6
            java.lang.Class r1 = (java.lang.Class) r1
            int r0 = r1.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            r2 = 1
            if (r0 != 0) goto L_0x001d
            java.lang.Class r1 = r1.getEnclosingClass()
            r0 = 0
            if (r1 != 0) goto L_0x001e
        L_0x001d:
            r0 = 1
        L_0x001e:
            if (r5 != 0) goto L_0x0023
            if (r0 != 0) goto L_0x0023
            r2 = 0
        L_0x0023:
            X.AnonymousClass13f.A00(r2)
        L_0x0026:
            if (r5 != 0) goto L_0x0052
            r0 = 0
        L_0x0029:
            r4.ownerType = r0
            java.lang.reflect.Type r0 = X.AnonymousClass13j.A02(r6)
            r4.rawType = r0
            java.lang.Object r0 = r7.clone()
            java.lang.reflect.Type[] r0 = (java.lang.reflect.Type[]) r0
            r4.typeArguments = r0
            int r2 = r0.length
        L_0x003a:
            if (r3 >= r2) goto L_0x0059
            java.lang.reflect.Type[] r0 = r4.typeArguments
            r0 = r0[r3]
            if (r0 == 0) goto L_0x0057
            X.AnonymousClass13j.A06(r0)
            java.lang.reflect.Type[] r1 = r4.typeArguments
            r0 = r1[r3]
            java.lang.reflect.Type r0 = X.AnonymousClass13j.A02(r0)
            r1[r3] = r0
            int r3 = r3 + 1
            goto L_0x003a
        L_0x0052:
            java.lang.reflect.Type r0 = X.AnonymousClass13j.A02(r5)
            goto L_0x0029
        L_0x0057:
            r0 = 0
            throw r0
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass13h.<init>(java.lang.reflect.Type, java.lang.reflect.Type, java.lang.reflect.Type[]):void");
    }

    public final Type getOwnerType() {
        return this.ownerType;
    }

    public final Type getRawType() {
        return this.rawType;
    }
}
