package X;

import com.google.common.base.Preconditions;
import com.google.inject.name.Named;
import java.io.Serializable;
import java.lang.annotation.Annotation;

public final class RM implements Named, Serializable {
    public static final long serialVersionUID = 0;
    public final String value;

    public final boolean equals(Object obj) {
        if (!(obj instanceof Named)) {
            return false;
        }
        return this.value.equals(((Named) obj).value());
    }

    public String toString() {
        return AnonymousClass06.A07("@", Named.class.getName(), "(value=", this.value, ")");
    }

    public RM(String str) {
        Preconditions.checkNotNull(str, "name");
        this.value = str;
    }

    public final int hashCode() {
        return 1335633679 ^ this.value.hashCode();
    }

    @Override // java.lang.annotation.Annotation
    public Class<? extends Annotation> annotationType() {
        return Named.class;
    }

    @Override // com.google.inject.name.Named
    public String value() {
        return this.value;
    }
}
