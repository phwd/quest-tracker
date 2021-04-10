package X;

import com.squareup.okhttp.HttpUrl;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public final class hZ implements GenericArrayType, Serializable {
    public static final long serialVersionUID = 0;
    public final Type componentType;

    public final boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType) || !C0233hW.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.componentType.hashCode();
    }

    public final String toString() {
        return AnonymousClass06.A03(C0233hW.A01(this.componentType), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
    }

    public hZ(Type type) {
        this.componentType = C0233hW.A02(type);
    }

    public final Type getGenericComponentType() {
        return this.componentType;
    }
}
