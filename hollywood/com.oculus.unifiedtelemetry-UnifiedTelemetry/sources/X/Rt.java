package X;

import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;

public class Rt implements AbstractC0476qF {
    public final Annotation A00;

    public final boolean equals(Object obj) {
        if (!(obj instanceof Rt)) {
            return false;
        }
        return this.A00.equals(((Rt) obj).A00);
    }

    @Override // X.AbstractC0476qF
    public final Class<? extends Annotation> getAnnotationType() {
        return this.A00.annotationType();
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return this.A00.toString();
    }

    public Rt(Annotation annotation) {
        Preconditions.checkNotNull(annotation, "annotation");
        this.A00 = annotation;
    }

    @Override // X.AbstractC0476qF
    public final Annotation getAnnotation() {
        return this.A00;
    }
}
