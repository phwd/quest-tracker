package X;

import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import javax.annotation.Nullable;

public class KB implements h0 {
    public final Class<? extends Annotation> A00;
    public final Annotation A01;

    public final boolean equals(Object obj) {
        if (!(obj instanceof KB)) {
            return false;
        }
        return this.A00.equals(((KB) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return AnonymousClass06.A03("@", this.A00.getName());
    }

    public KB(Class<? extends Annotation> cls, @Nullable Annotation annotation) {
        Preconditions.checkNotNull(cls, "annotation type");
        this.A00 = cls;
        this.A01 = annotation;
    }

    @Override // X.h0
    public final Annotation getAnnotation() {
        return this.A01;
    }

    @Override // X.h0
    public final Class<? extends Annotation> getAnnotationType() {
        return this.A00;
    }
}
