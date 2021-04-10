package X;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* renamed from: X.0cu  reason: invalid class name */
public class AnonymousClass0cu implements WildcardType, AnonymousClass14U, Serializable {
    public static final long serialVersionUID = 0;
    public final Type lowerBound;
    public final Type upperBound;

    @Override // X.AnonymousClass14U
    public final boolean A5z() {
        if (!AnonymousClass14V.A06(this.upperBound)) {
            return false;
        }
        Type type = this.lowerBound;
        if (type == null || AnonymousClass14V.A06(type)) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof WildcardType) || !AnonymousClass14V.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final Type[] getLowerBounds() {
        Type type = this.lowerBound;
        if (type != null) {
            return new Type[]{type};
        }
        return AnonymousClass14V.A00;
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
        sb.append(AnonymousClass14V.A02(type));
        return sb.toString();
    }

    public AnonymousClass0cu(Type[] typeArr, Type[] typeArr2) {
        int length = typeArr2.length;
        boolean z = true;
        Preconditions.checkArgument(length <= 1, "Must have at most one lower bound.");
        Preconditions.checkArgument(typeArr.length == 1, "Must have exactly one upper bound.");
        if (length == 1) {
            Preconditions.checkNotNull(typeArr2[0], "lowerBound");
            AnonymousClass14V.A05(typeArr2[0], "wildcard bounds");
            Preconditions.checkArgument(typeArr[0] != Object.class ? false : z, "bounded both ways");
            this.lowerBound = AnonymousClass14V.A03(typeArr2[0]);
            this.upperBound = Object.class;
            return;
        }
        Preconditions.checkNotNull(typeArr[0], "upperBound");
        AnonymousClass14V.A05(typeArr[0], "wildcard bounds");
        this.lowerBound = null;
        this.upperBound = AnonymousClass14V.A03(typeArr[0]);
    }
}
