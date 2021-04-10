package X;

import java.lang.annotation.Annotation;
import javax.annotation.Nullable;

public enum KA implements h0 {
    INSTANCE;

    @Override // X.h0
    @Nullable
    public Annotation getAnnotation() {
        return null;
    }

    @Override // X.h0
    @Nullable
    public Class<? extends Annotation> getAnnotationType() {
        return null;
    }

    public boolean hasAttributes() {
        return false;
    }

    public String toString() {
        return "[none]";
    }

    public h0 withoutAttributes() {
        throw new UnsupportedOperationException("Key already has no attributes.");
    }
}
