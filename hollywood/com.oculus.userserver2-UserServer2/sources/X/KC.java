package X;

import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;

public class KC implements h0 {
    public final Annotation A00;

    public final boolean equals(Object obj) {
        if (!(obj instanceof KC)) {
            return false;
        }
        return this.A00.equals(((KC) obj).A00);
    }

    @Override // X.h0
    public final Class<? extends Annotation> getAnnotationType() {
        return this.A00.annotationType();
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return this.A00.toString();
    }

    public KC(Annotation annotation) {
        Preconditions.checkNotNull(annotation, "annotation");
        this.A00 = annotation;
    }

    @Override // X.h0
    public final Annotation getAnnotation() {
        return this.A00;
    }
}
