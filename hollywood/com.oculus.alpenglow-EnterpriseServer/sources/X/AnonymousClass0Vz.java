package X;

import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;

/* renamed from: X.0Vz  reason: invalid class name */
public class AnonymousClass0Vz implements AbstractC01290Ga {
    public final Annotation A00;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass0Vz)) {
            return false;
        }
        return this.A00.equals(((AnonymousClass0Vz) obj).A00);
    }

    @Override // X.AbstractC01290Ga
    public final Annotation getAnnotation() {
        return this.A00;
    }

    @Override // X.AbstractC01290Ga
    public final Class<? extends Annotation> getAnnotationType() {
        return this.A00.annotationType();
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return this.A00.toString();
    }

    public AnonymousClass0Vz(Annotation annotation) {
        Preconditions.checkNotNull(annotation, "annotation");
        this.A00 = annotation;
    }
}
