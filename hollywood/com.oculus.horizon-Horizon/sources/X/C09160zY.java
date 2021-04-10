package X;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.inject.Named;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0zY  reason: invalid class name and case insensitive filesystem */
public final class C09160zY<T> {
    public final AbstractC09150zX A00;
    public final C09170za<T> A01;
    public final int A02;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C09160zY)) {
            return false;
        }
        C09160zY r4 = (C09160zY) obj;
        return this.A00.equals(r4.A00) && this.A01.equals(r4.A01);
    }

    public static AbstractC09150zX A00(Annotation annotation) {
        Preconditions.checkNotNull(annotation, "annotation");
        Class<? extends Annotation> annotationType = annotation.annotationType();
        if (annotationType.getDeclaredMethods().length == 0) {
            return new AnonymousClass0UT(annotationType, annotation);
        }
        if (annotation instanceof Named) {
            annotation = new AnonymousClass0UN(((Named) annotation).value());
        }
        return new AnonymousClass0UU(annotation);
    }

    public static <S> C09160zY<S> A01(Class<S> cls, Class<? extends Annotation> cls2) {
        Preconditions.checkNotNull(cls2, "annotation type");
        if (cls2 == Named.class) {
            cls2 = com.google.inject.name.Named.class;
        }
        return new C09160zY<>(cls, new AnonymousClass0UT(cls2, null));
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

    public C09160zY() {
        this.A00 = AnonymousClass0US.INSTANCE;
        C09170za<T> r0 = (C09170za<T>) C09170za.fromSuperclassTypeParameter(getClass());
        this.A01 = r0;
        this.A02 = (r0.hashCode() * 31) + this.A00.hashCode();
    }

    public C09160zY(C09170za<T> r3, AbstractC09150zX r4) {
        this.A00 = r4;
        C09170za<T> A002 = C09190ze.A00(r3);
        this.A01 = A002;
        this.A02 = (A002.hashCode() * 31) + this.A00.hashCode();
    }

    public C09160zY(Type type, AbstractC09150zX r4) {
        this.A00 = r4;
        C09170za<T> A002 = C09190ze.A00(new C09170za(type));
        this.A01 = A002;
        this.A02 = (A002.hashCode() * 31) + this.A00.hashCode();
    }
}
