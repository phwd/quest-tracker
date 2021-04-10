package X;

import java.lang.annotation.Annotation;
import javax.annotation.Nullable;

/* renamed from: X.0US  reason: invalid class name */
public enum AnonymousClass0US implements AbstractC09150zX {
    INSTANCE;

    @Override // X.AbstractC09150zX
    @Nullable
    public Annotation getAnnotation() {
        return null;
    }

    @Override // X.AbstractC09150zX
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

    public AbstractC09150zX withoutAttributes() {
        throw new UnsupportedOperationException("Key already has no attributes.");
    }
}
