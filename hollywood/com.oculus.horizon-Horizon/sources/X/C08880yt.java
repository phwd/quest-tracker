package X;

import com.squareup.okhttp.HttpUrl;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* renamed from: X.0yt  reason: invalid class name and case insensitive filesystem */
public final class C08880yt implements GenericArrayType, Serializable {
    public static final long serialVersionUID = 0;
    public final Type componentType;

    public final boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType) || !C08910yw.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.componentType.hashCode();
    }

    public final String toString() {
        return AnonymousClass006.A05(C08910yw.A01(this.componentType), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
    }

    public C08880yt(Type type) {
        this.componentType = C08910yw.A02(type);
    }

    public final Type getGenericComponentType() {
        return this.componentType;
    }
}
