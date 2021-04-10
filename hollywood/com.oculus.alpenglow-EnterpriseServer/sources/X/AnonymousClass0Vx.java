package X;

import java.lang.annotation.Annotation;
import javax.annotation.Nullable;

/* renamed from: X.0Vx  reason: invalid class name */
public enum AnonymousClass0Vx implements AbstractC01290Ga {
    INSTANCE;

    @Override // X.AbstractC01290Ga
    @Nullable
    public Annotation getAnnotation() {
        return null;
    }

    @Override // X.AbstractC01290Ga
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

    public AbstractC01290Ga withoutAttributes() {
        throw new UnsupportedOperationException("Key already has no attributes.");
    }
}
