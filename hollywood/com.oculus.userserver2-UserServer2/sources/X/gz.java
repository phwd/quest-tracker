package X;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.inject.Named;

@Nullsafe(Nullsafe.Mode.STRICT)
public final class gz<T> {
    public final h0 A00;
    public final gx<T> A01;
    public final int A02;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof gz)) {
            return false;
        }
        gz gzVar = (gz) obj;
        return this.A00.equals(gzVar.A00) && this.A01.equals(gzVar.A01);
    }

    public static h0 A00(Annotation annotation) {
        Preconditions.checkNotNull(annotation, "annotation");
        Class<? extends Annotation> annotationType = annotation.annotationType();
        if (annotationType.getDeclaredMethods().length == 0) {
            return new KB(annotationType, annotation);
        }
        if (annotation instanceof Named) {
            annotation = new K5(((Named) annotation).value());
        }
        return new KC(annotation);
    }

    public static <S> gz<S> A01(Class<S> cls, Class<? extends Annotation> cls2) {
        Preconditions.checkNotNull(cls2, "annotation type");
        if (cls2 == Named.class) {
            cls2 = com.google.inject.name.Named.class;
        }
        return new gz<>(cls, new KB(cls2, null));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Key[type=");
        sb.append(this.A01);
        sb.append(", annotation=");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }

    public final int hashCode() {
        return this.A02;
    }

    public gz() {
        this.A00 = KA.INSTANCE;
        gx<T> gxVar = (gx<T>) gx.fromSuperclassTypeParameter(getClass());
        this.A01 = gxVar;
        this.A02 = (gxVar.hashCode() * 31) + this.A00.hashCode();
    }

    public gz(gx<T> gxVar, h0 h0Var) {
        this.A00 = h0Var;
        gx<T> A002 = gt.A00(gxVar);
        this.A01 = A002;
        this.A02 = (A002.hashCode() * 31) + this.A00.hashCode();
    }

    public gz(Type type, h0 h0Var) {
        this.A00 = h0Var;
        gx<T> A002 = gt.A00(new gx(type));
        this.A01 = A002;
        this.A02 = (A002.hashCode() * 31) + this.A00.hashCode();
    }
}
