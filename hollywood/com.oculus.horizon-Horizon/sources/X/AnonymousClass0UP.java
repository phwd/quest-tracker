package X;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/* renamed from: X.0UP  reason: invalid class name */
public class AnonymousClass0UP implements ParameterizedType, AbstractC09180zd, Serializable {
    public static final long serialVersionUID = 0;
    public final Type ownerType;
    public final Type rawType;
    public final Type[] typeArguments;

    @Override // X.AbstractC09180zd
    public final boolean A50() {
        Type type = this.ownerType;
        if ((type == null || C09190ze.A06(type)) && C09190ze.A06(this.rawType)) {
            for (Type type2 : this.typeArguments) {
                if (C09190ze.A06(type2)) {
                }
            }
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ParameterizedType) || !C09190ze.A07(this, (Type) obj)) {
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
        int i = 1;
        StringBuilder sb = new StringBuilder((this.typeArguments.length + 1) * 30);
        sb.append(C09190ze.A02(this.rawType));
        Type[] typeArr = this.typeArguments;
        if (typeArr.length != 0) {
            sb.append("<");
            sb.append(C09190ze.A02(typeArr[0]));
            while (true) {
                Type[] typeArr2 = this.typeArguments;
                if (i >= typeArr2.length) {
                    break;
                }
                sb.append(", ");
                sb.append(C09190ze.A02(typeArr2[i]));
                i++;
            }
            sb.append(">");
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        if (r4.getEnclosingClass() == null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass0UP(@javax.annotation.Nullable java.lang.reflect.Type r6, java.lang.reflect.Type r7, java.lang.reflect.Type... r8) {
        /*
            r5 = this;
            r5.<init>()
            boolean r0 = r7 instanceof java.lang.Class
            r2 = 0
            if (r0 == 0) goto L_0x0029
            r4 = r7
            java.lang.Class r4 = (java.lang.Class) r4
            r3 = 1
            if (r6 != 0) goto L_0x0015
            java.lang.Class r0 = r4.getEnclosingClass()
            r1 = 0
            if (r0 != 0) goto L_0x0016
        L_0x0015:
            r1 = 1
        L_0x0016:
            java.lang.String r0 = "No owner type for enclosed %s"
            com.google.common.base.Preconditions.checkArgument(r1, r0, r7)
            if (r6 == 0) goto L_0x0024
            java.lang.Class r0 = r4.getEnclosingClass()
            if (r0 != 0) goto L_0x0024
            r3 = 0
        L_0x0024:
            java.lang.String r0 = "Owner type for unenclosed %s"
            com.google.common.base.Preconditions.checkArgument(r3, r0, r7)
        L_0x0029:
            if (r6 != 0) goto L_0x005e
            r0 = 0
        L_0x002c:
            r5.ownerType = r0
            java.lang.reflect.Type r0 = X.C09190ze.A03(r7)
            r5.rawType = r0
            java.lang.Object r0 = r8.clone()
            java.lang.reflect.Type[] r0 = (java.lang.reflect.Type[]) r0
            r5.typeArguments = r0
        L_0x003c:
            java.lang.reflect.Type[] r1 = r5.typeArguments
            int r0 = r1.length
            if (r2 >= r0) goto L_0x0063
            r1 = r1[r2]
            java.lang.String r0 = "type parameter"
            com.google.common.base.Preconditions.checkNotNull(r1, r0)
            java.lang.reflect.Type[] r0 = r5.typeArguments
            r1 = r0[r2]
            java.lang.String r0 = "type parameters"
            X.C09190ze.A05(r1, r0)
            java.lang.reflect.Type[] r1 = r5.typeArguments
            r0 = r1[r2]
            java.lang.reflect.Type r0 = X.C09190ze.A03(r0)
            r1[r2] = r0
            int r2 = r2 + 1
            goto L_0x003c
        L_0x005e:
            java.lang.reflect.Type r0 = X.C09190ze.A03(r6)
            goto L_0x002c
        L_0x0063:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0UP.<init>(java.lang.reflect.Type, java.lang.reflect.Type, java.lang.reflect.Type[]):void");
    }

    public final Type getOwnerType() {
        return this.ownerType;
    }

    public final Type getRawType() {
        return this.rawType;
    }
}
