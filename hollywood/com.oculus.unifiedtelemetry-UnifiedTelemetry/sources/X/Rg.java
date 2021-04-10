package X;

import com.squareup.okhttp.HttpUrl;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class Rg implements GenericArrayType, px, Serializable {
    public static final long serialVersionUID = 0;
    public final Type componentType;

    @Override // X.px
    public final boolean A3C() {
        return pt.A06(this.componentType);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType) || !pt.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.componentType.hashCode();
    }

    public final String toString() {
        return AnonymousClass06.A04(pt.A02(this.componentType), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
    }

    public Rg(Type type) {
        this.componentType = pt.A03(type);
    }

    public final Type getGenericComponentType() {
        return this.componentType;
    }
}
