package X;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.HashMap;

/* renamed from: X.0KC  reason: invalid class name */
public final class AnonymousClass0KC extends AbstractC02450Zr implements Serializable {
    public static final long serialVersionUID = 7364428299211355871L;
    public final transient Field A00;
    public C06660nd _serialization;

    @Override // X.AbstractC06640nb
    public final Class<?> A0K() {
        return this.A00.getType();
    }

    @Override // X.AbstractC06640nb
    public final String A0L() {
        return this.A00.getName();
    }

    @Override // X.AbstractC06640nb
    public final <A extends Annotation> A A0M(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        C02440Zq r0 = super.A00;
        if (r0 == null || (hashMap = r0.A00) == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    @Override // X.AbstractC06640nb
    public final /* bridge */ /* synthetic */ AnnotatedElement A0N() {
        return this.A00;
    }

    @Override // X.AbstractC06640nb
    public final Type A0O() {
        return this.A00.getGenericType();
    }

    @Override // X.AbstractC02450Zr
    public final Class<?> A0P() {
        return this.A00.getDeclaringClass();
    }

    @Override // X.AbstractC02450Zr
    public final Object A0Q(Object obj) throws IllegalArgumentException {
        try {
            return this.A00.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Failed to getValue() for field ", A0S(), ": ", e.getMessage()), e);
        }
    }

    @Override // X.AbstractC02450Zr
    public final Member A0R() {
        return this.A00;
    }

    public Object readResolve() {
        C06660nd r0 = this._serialization;
        Class<?> cls = r0.clazz;
        try {
            Field declaredField = cls.getDeclaredField(r0.name);
            if (!declaredField.isAccessible()) {
                C07130om.A06(declaredField);
            }
            return new AnonymousClass0KC(declaredField, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Could not find method '", this._serialization.name, "' from Class '", cls.getName()));
        }
    }

    public final String toString() {
        return AnonymousClass006.A07("[field ", A0S(), "]");
    }

    public Object writeReplace() {
        return new AnonymousClass0KC(new C06660nd(this.A00));
    }

    public final String A0S() {
        return AnonymousClass006.A07(A0P().getName(), "#", A0L());
    }

    public AnonymousClass0KC(C06660nd r2) {
        super(null);
        this.A00 = null;
        this._serialization = r2;
    }

    public AnonymousClass0KC(Field field, C02440Zq r2) {
        super(r2);
        this.A00 = field;
    }
}
