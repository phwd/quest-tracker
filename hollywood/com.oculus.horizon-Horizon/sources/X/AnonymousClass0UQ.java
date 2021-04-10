package X;

import com.squareup.okhttp.HttpUrl;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* renamed from: X.0UQ  reason: invalid class name */
public class AnonymousClass0UQ implements GenericArrayType, AbstractC09180zd, Serializable {
    public static final long serialVersionUID = 0;
    public final Type componentType;

    @Override // X.AbstractC09180zd
    public final boolean A50() {
        return C09190ze.A06(this.componentType);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType) || !C09190ze.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.componentType.hashCode();
    }

    public final String toString() {
        return AnonymousClass006.A05(C09190ze.A02(this.componentType), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
    }

    public AnonymousClass0UQ(Type type) {
        this.componentType = C09190ze.A03(type);
    }

    public final Type getGenericComponentType() {
        return this.componentType;
    }
}
