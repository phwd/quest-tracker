package X;

import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import javax.annotation.Nullable;

/* renamed from: X.0UT  reason: invalid class name */
public class AnonymousClass0UT implements AbstractC09150zX {
    public final Class<? extends Annotation> A00;
    public final Annotation A01;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass0UT)) {
            return false;
        }
        return this.A00.equals(((AnonymousClass0UT) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return AnonymousClass006.A05("@", this.A00.getName());
    }

    public AnonymousClass0UT(Class<? extends Annotation> cls, @Nullable Annotation annotation) {
        Preconditions.checkNotNull(cls, "annotation type");
        this.A00 = cls;
        this.A01 = annotation;
    }

    @Override // X.AbstractC09150zX
    public final Annotation getAnnotation() {
        return this.A01;
    }

    @Override // X.AbstractC09150zX
    public final Class<? extends Annotation> getAnnotationType() {
        return this.A00;
    }
}
