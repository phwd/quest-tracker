package X;

import com.squareup.okhttp.HttpUrl;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* renamed from: X.0Vv  reason: invalid class name */
public class AnonymousClass0Vv implements GenericArrayType, AnonymousClass0H3, Serializable {
    public static final long serialVersionUID = 0;
    public final Type componentType;

    @Override // X.AnonymousClass0H3
    public final boolean A5Q() {
        return AnonymousClass0Hy.A06(this.componentType);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType) || !AnonymousClass0Hy.A07(this, (Type) obj)) {
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
        return AnonymousClass006.A05(AnonymousClass0Hy.A02(this.componentType), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
    }

    public AnonymousClass0Vv(Type type) {
        this.componentType = AnonymousClass0Hy.A03(type);
    }
}
