package X;

import com.google.common.base.Preconditions;
import com.google.inject.name.Named;
import java.io.Serializable;
import java.lang.annotation.Annotation;

/* renamed from: X.0UN  reason: invalid class name */
public final class AnonymousClass0UN implements Named, Serializable {
    public static final long serialVersionUID = 0;
    public final String value;

    public final boolean equals(Object obj) {
        if (!(obj instanceof Named)) {
            return false;
        }
        return this.value.equals(((Named) obj).value());
    }

    public String toString() {
        return AnonymousClass006.A09("@", Named.class.getName(), "(value=", this.value, ")");
    }

    public AnonymousClass0UN(String str) {
        Preconditions.checkNotNull(str, "name");
        this.value = str;
    }

    @Override // java.lang.annotation.Annotation
    public Class<? extends Annotation> annotationType() {
        return Named.class;
    }

    public final int hashCode() {
        return 1335633679 ^ this.value.hashCode();
    }

    @Override // com.google.inject.name.Named
    public String value() {
        return this.value;
    }
}
