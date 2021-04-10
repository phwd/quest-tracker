package X;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.inject.Named;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.qE  reason: case insensitive filesystem */
public final class C0475qE<T> {
    public final AbstractC0476qF A00;
    public final C0474qC<T> A01;
    public final int A02;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0475qE)) {
            return false;
        }
        C0475qE qEVar = (C0475qE) obj;
        return this.A00.equals(qEVar.A00) && this.A01.equals(qEVar.A01);
    }

    public static AbstractC0476qF A00(Annotation annotation) {
        Preconditions.checkNotNull(annotation, "annotation");
        Class<? extends Annotation> annotationType = annotation.annotationType();
        if (annotationType.getDeclaredMethods().length == 0) {
            return new Rr(annotationType, annotation);
        }
        if (annotation instanceof Named) {
            annotation = new RM(((Named) annotation).value());
        }
        return new Rt(annotation);
    }

    public static <S> C0475qE<S> A01(Class<S> cls, Class<? extends Annotation> cls2) {
        Preconditions.checkNotNull(cls2, "annotation type");
        if (cls2 == Named.class) {
            cls2 = com.google.inject.name.Named.class;
        }
        return new C0475qE<>(cls, new Rr(cls2, null));
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

    public C0475qE() {
        this.A00 = Rp.INSTANCE;
        C0474qC<T> qCVar = (C0474qC<T>) C0474qC.fromSuperclassTypeParameter(getClass());
        this.A01 = qCVar;
        this.A02 = (qCVar.hashCode() * 31) + this.A00.hashCode();
    }

    public C0475qE(C0474qC<T> qCVar, AbstractC0476qF qFVar) {
        this.A00 = qFVar;
        C0474qC<T> A002 = pt.A00(qCVar);
        this.A01 = A002;
        this.A02 = (A002.hashCode() * 31) + this.A00.hashCode();
    }

    public C0475qE(Type type, AbstractC0476qF qFVar) {
        this.A00 = qFVar;
        C0474qC<T> A002 = pt.A00(new C0474qC(type));
        this.A01 = A002;
        this.A02 = (A002.hashCode() * 31) + this.A00.hashCode();
    }
}
