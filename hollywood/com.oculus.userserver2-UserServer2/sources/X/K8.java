package X;

import com.squareup.okhttp.HttpUrl;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class K8 implements GenericArrayType, gu, Serializable {
    public static final long serialVersionUID = 0;
    public final Type componentType;

    @Override // X.gu
    public final boolean A2B() {
        return gt.A06(this.componentType);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType) || !gt.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.componentType.hashCode();
    }

    public final String toString() {
        return AnonymousClass06.A03(gt.A02(this.componentType), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
    }

    public K8(Type type) {
        this.componentType = gt.A03(type);
    }

    public final Type getGenericComponentType() {
        return this.componentType;
    }
}
