package X;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* renamed from: X.13i  reason: invalid class name */
public final class AnonymousClass13i implements WildcardType, Serializable {
    public static final long serialVersionUID = 0;
    public final Type lowerBound;
    public final Type upperBound;

    public final boolean equals(Object obj) {
        if (!(obj instanceof WildcardType) || !AnonymousClass13j.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final Type[] getLowerBounds() {
        Type type = this.lowerBound;
        if (type != null) {
            return new Type[]{type};
        }
        return AnonymousClass13j.A00;
    }

    public final Type[] getUpperBounds() {
        return new Type[]{this.upperBound};
    }

    public final int hashCode() {
        int i;
        Type type = this.lowerBound;
        if (type != null) {
            i = type.hashCode() + 31;
        } else {
            i = 1;
        }
        return i ^ (this.upperBound.hashCode() + 31);
    }

    public final String toString() {
        String str;
        Type type = this.lowerBound;
        if (type != null) {
            str = "? super ";
        } else {
            type = this.upperBound;
            if (type == Object.class) {
                return "?";
            }
            str = "? extends ";
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append(AnonymousClass13j.A01(type));
        return sb.toString();
    }

    public AnonymousClass13i(Type[] typeArr, Type[] typeArr2) {
        int length = typeArr2.length;
        boolean z = true;
        AnonymousClass13f.A00(length <= 1);
        AnonymousClass13f.A00(typeArr.length == 1);
        if (length == 1) {
            Type type = typeArr2[0];
            if (type != null) {
                AnonymousClass13j.A06(type);
                AnonymousClass13f.A00(typeArr[0] != Object.class ? false : z);
                this.lowerBound = AnonymousClass13j.A02(typeArr2[0]);
                this.upperBound = Object.class;
                return;
            }
            throw null;
        }
        Type type2 = typeArr[0];
        if (type2 != null) {
            AnonymousClass13j.A06(type2);
            this.lowerBound = null;
            this.upperBound = AnonymousClass13j.A02(typeArr[0]);
            return;
        }
        throw null;
    }
}
