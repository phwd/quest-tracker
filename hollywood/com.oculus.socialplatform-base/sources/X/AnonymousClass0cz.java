package X;

import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import javax.annotation.Nullable;

/* renamed from: X.0cz  reason: invalid class name */
public class AnonymousClass0cz implements AnonymousClass14O {
    public final Class<? extends Annotation> A00;
    public final Annotation A01;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass0cz)) {
            return false;
        }
        return this.A00.equals(((AnonymousClass0cz) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return AnonymousClass006.A07("@", this.A00.getName());
    }

    public AnonymousClass0cz(Class<? extends Annotation> cls, @Nullable Annotation annotation) {
        Preconditions.checkNotNull(cls, "annotation type");
        this.A00 = cls;
        this.A01 = annotation;
    }

    @Override // X.AnonymousClass14O
    public final Annotation getAnnotation() {
        return this.A01;
    }

    @Override // X.AnonymousClass14O
    public final Class<? extends Annotation> getAnnotationType() {
        return this.A00;
    }
}
