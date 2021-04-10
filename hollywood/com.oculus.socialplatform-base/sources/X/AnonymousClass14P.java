package X;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.inject.Named;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.14P  reason: invalid class name */
public final class AnonymousClass14P<T> {
    public final AnonymousClass14O A00;
    public final AnonymousClass14R<T> A01;
    public final int A02;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnonymousClass14P)) {
            return false;
        }
        AnonymousClass14P r4 = (AnonymousClass14P) obj;
        return this.A00.equals(r4.A00) && this.A01.equals(r4.A01);
    }

    public static AnonymousClass14O A00(Annotation annotation) {
        Preconditions.checkNotNull(annotation, "annotation");
        Class<? extends Annotation> annotationType = annotation.annotationType();
        if (annotationType.getDeclaredMethods().length == 0) {
            return new AnonymousClass0cz(annotationType, annotation);
        }
        if (annotation instanceof Named) {
            annotation = new C01270ct(((Named) annotation).value());
        }
        return new AnonymousClass0d0(annotation);
    }

    public static <S> AnonymousClass14P<S> A01(Class<S> cls, Class<? extends Annotation> cls2) {
        Preconditions.checkNotNull(cls2, "annotation type");
        if (cls2 == Named.class) {
            cls2 = com.google.inject.name.Named.class;
        }
        return new AnonymousClass14P<>(cls, new AnonymousClass0cz(cls2, null));
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

    public AnonymousClass14P() {
        this.A00 = AnonymousClass0cy.INSTANCE;
        AnonymousClass14R<T> r0 = (AnonymousClass14R<T>) AnonymousClass14R.fromSuperclassTypeParameter(getClass());
        this.A01 = r0;
        this.A02 = (r0.hashCode() * 31) + this.A00.hashCode();
    }

    public AnonymousClass14P(AnonymousClass14R<T> r3, AnonymousClass14O r4) {
        this.A00 = r4;
        AnonymousClass14R<T> A002 = AnonymousClass14V.A00(r3);
        this.A01 = A002;
        this.A02 = (A002.hashCode() * 31) + this.A00.hashCode();
    }

    public AnonymousClass14P(Type type, AnonymousClass14O r4) {
        this.A00 = r4;
        AnonymousClass14R<T> A002 = AnonymousClass14V.A00(new AnonymousClass14R(type));
        this.A01 = A002;
        this.A02 = (A002.hashCode() * 31) + this.A00.hashCode();
    }
}
