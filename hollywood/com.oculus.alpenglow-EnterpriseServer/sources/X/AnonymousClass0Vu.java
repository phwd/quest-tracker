package X;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/* renamed from: X.0Vu  reason: invalid class name */
public class AnonymousClass0Vu implements ParameterizedType, AnonymousClass0H3, Serializable {
    public static final long serialVersionUID = 0;
    public final Type ownerType;
    public final Type rawType;
    public final Type[] typeArguments;

    @Override // X.AnonymousClass0H3
    public final boolean A5Q() {
        Type type = this.ownerType;
        if ((type == null || AnonymousClass0Hy.A06(type)) && AnonymousClass0Hy.A06(this.rawType)) {
            for (Type type2 : this.typeArguments) {
                if (AnonymousClass0Hy.A06(type2)) {
                }
            }
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ParameterizedType) || !AnonymousClass0Hy.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final Type[] getActualTypeArguments() {
        return (Type[]) this.typeArguments.clone();
    }

    public final Type getOwnerType() {
        return this.ownerType;
    }

    public final Type getRawType() {
        return this.rawType;
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
        sb.append(AnonymousClass0Hy.A02(this.rawType));
        Type[] typeArr = this.typeArguments;
        if (typeArr.length != 0) {
            sb.append("<");
            sb.append(AnonymousClass0Hy.A02(typeArr[0]));
            while (true) {
                Type[] typeArr2 = this.typeArguments;
                if (i >= typeArr2.length) {
                    break;
                }
                sb.append(", ");
                sb.append(AnonymousClass0Hy.A02(typeArr2[i]));
                i++;
            }
            sb.append(">");
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r3 != false) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        if (r4.getEnclosingClass() == null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass0Vu(@javax.annotation.Nullable java.lang.reflect.Type r6, java.lang.reflect.Type r7, java.lang.reflect.Type... r8) {
        /*
        // Method dump skipped, instructions count: 112
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Vu.<init>(java.lang.reflect.Type, java.lang.reflect.Type, java.lang.reflect.Type[]):void");
    }
}
