package X;

import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;

/* renamed from: X.0d0  reason: invalid class name */
public class AnonymousClass0d0 implements AnonymousClass14O {
    public final Annotation A00;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass0d0)) {
            return false;
        }
        return this.A00.equals(((AnonymousClass0d0) obj).A00);
    }

    @Override // X.AnonymousClass14O
    public final Class<? extends Annotation> getAnnotationType() {
        return this.A00.annotationType();
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return this.A00.toString();
    }

    public AnonymousClass0d0(Annotation annotation) {
        Preconditions.checkNotNull(annotation, "annotation");
        this.A00 = annotation;
    }

    @Override // X.AnonymousClass14O
    public final Annotation getAnnotation() {
        return this.A00;
    }
}
