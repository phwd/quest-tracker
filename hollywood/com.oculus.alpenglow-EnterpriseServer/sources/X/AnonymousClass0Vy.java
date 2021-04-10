package X;

import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import javax.annotation.Nullable;

/* renamed from: X.0Vy  reason: invalid class name */
public class AnonymousClass0Vy implements AbstractC01290Ga {
    public final Class<? extends Annotation> A00;
    public final Annotation A01;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass0Vy)) {
            return false;
        }
        return this.A00.equals(((AnonymousClass0Vy) obj).A00);
    }

    @Override // X.AbstractC01290Ga
    public final Annotation getAnnotation() {
        return this.A01;
    }

    @Override // X.AbstractC01290Ga
    public final Class<? extends Annotation> getAnnotationType() {
        return this.A00;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return AnonymousClass006.A05("@", this.A00.getName());
    }

    public AnonymousClass0Vy(Class<? extends Annotation> cls, @Nullable Annotation annotation) {
        Preconditions.checkNotNull(cls, "annotation type");
        this.A00 = cls;
        this.A01 = annotation;
    }
}
