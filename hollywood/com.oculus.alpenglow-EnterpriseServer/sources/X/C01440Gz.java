package X;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.inject.Named;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0Gz  reason: invalid class name and case insensitive filesystem */
public final class C01440Gz<T> {
    public final AbstractC01290Ga A00;
    public final AnonymousClass0H2<T> A01;
    public final int A02;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C01440Gz)) {
            return false;
        }
        C01440Gz r4 = (C01440Gz) obj;
        return this.A00.equals(r4.A00) && this.A01.equals(r4.A01);
    }

    public static AbstractC01290Ga A00(Annotation annotation) {
        Preconditions.checkNotNull(annotation, "annotation");
        Class<? extends Annotation> annotationType = annotation.annotationType();
        if (annotationType.getDeclaredMethods().length == 0) {
            return new AnonymousClass0Vy(annotationType, annotation);
        }
        if (annotation instanceof Named) {
            annotation = new AnonymousClass0Vs(((Named) annotation).value());
        }
        return new AnonymousClass0Vz(annotation);
    }

    public static <S> C01440Gz<S> A01(Class<S> cls, Class<? extends Annotation> cls2) {
        Preconditions.checkNotNull(cls2, "annotation type");
        if (cls2 == Named.class) {
            cls2 = com.google.inject.name.Named.class;
        }
        return new C01440Gz<>(cls, new AnonymousClass0Vy(cls2, null));
    }

    public final int hashCode() {
        return this.A02;
    }

    public final String toString() {
        return "Key[type=" + this.A01 + ", annotation=" + this.A00 + "]";
    }

    public C01440Gz() {
        this.A00 = AnonymousClass0Vx.INSTANCE;
        AnonymousClass0H2<T> r0 = (AnonymousClass0H2<T>) AnonymousClass0H2.fromSuperclassTypeParameter(getClass());
        this.A01 = r0;
        this.A02 = (r0.hashCode() * 31) + this.A00.hashCode();
    }

    public C01440Gz(AnonymousClass0H2<T> r3, AbstractC01290Ga r4) {
        this.A00 = r4;
        AnonymousClass0H2<T> A002 = AnonymousClass0Hy.A00(r3);
        this.A01 = A002;
        this.A02 = (A002.hashCode() * 31) + this.A00.hashCode();
    }

    public C01440Gz(Type type, AbstractC01290Ga r4) {
        this.A00 = r4;
        AnonymousClass0H2<T> A002 = AnonymousClass0Hy.A00(new AnonymousClass0H2(type));
        this.A01 = A002;
        this.A02 = (A002.hashCode() * 31) + this.A00.hashCode();
    }
}
