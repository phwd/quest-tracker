package X;

import java.lang.annotation.Annotation;
import javax.annotation.Nullable;

public enum Rp implements AbstractC0476qF {
    INSTANCE;

    @Override // X.AbstractC0476qF
    @Nullable
    public Annotation getAnnotation() {
        return null;
    }

    @Override // X.AbstractC0476qF
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

    public AbstractC0476qF withoutAttributes() {
        throw new UnsupportedOperationException("Key already has no attributes.");
    }
}
