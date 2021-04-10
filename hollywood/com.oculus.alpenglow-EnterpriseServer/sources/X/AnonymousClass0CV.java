package X;

import com.squareup.okhttp.HttpUrl;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* renamed from: X.0CV  reason: invalid class name */
public final class AnonymousClass0CV implements GenericArrayType, Serializable {
    public static final long serialVersionUID = 0;
    public final Type componentType;

    public final boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType) || !AnonymousClass0Ch.A07(this, (Type) obj)) {
            return false;
        }
        return true;
    }

    public final Type getGenericComponentType() {
        return this.componentType;
    }

    public final int hashCode() {
        return this.componentType.hashCode();
    }

    public final String toString() {
        return AnonymousClass006.A05(AnonymousClass0Ch.A01(this.componentType), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
    }

    public AnonymousClass0CV(Type type) {
        this.componentType = AnonymousClass0Ch.A02(type);
    }
}
