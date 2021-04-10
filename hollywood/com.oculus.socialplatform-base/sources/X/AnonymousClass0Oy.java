package X;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.HashMap;

/* renamed from: X.0Oy  reason: invalid class name */
public final class AnonymousClass0Oy extends AbstractC01990hm implements Serializable {
    public static final long serialVersionUID = 7364428299211355871L;
    public final transient Field A00;
    public AnonymousClass0qC _serialization;

    @Override // X.AnonymousClass0qA
    public final Class<?> A0K() {
        return this.A00.getType();
    }

    @Override // X.AnonymousClass0qA
    public final String A0L() {
        return this.A00.getName();
    }

    @Override // X.AnonymousClass0qA
    public final <A extends Annotation> A A0M(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        AnonymousClass0hl r0 = super.A00;
        if (r0 == null || (hashMap = r0.A00) == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    @Override // X.AnonymousClass0qA
    public final Type A0O() {
        return this.A00.getGenericType();
    }

    @Override // X.AbstractC01990hm
    public final Class<?> A0P() {
        return this.A00.getDeclaringClass();
    }

    @Override // X.AbstractC01990hm
    public final Object A0Q(Object obj) throws IllegalArgumentException {
        try {
            return this.A00.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(AnonymousClass006.A0B("Failed to getValue() for field ", A0S(), ": ", e.getMessage()), e);
        }
    }

    public Object readResolve() {
        AnonymousClass0qC r0 = this._serialization;
        Class<?> cls = r0.clazz;
        try {
            Field declaredField = cls.getDeclaredField(r0.name);
            if (!declaredField.isAccessible()) {
                C04810rI.A06(declaredField);
            }
            return new AnonymousClass0Oy(declaredField, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException(AnonymousClass006.A0B("Could not find method '", this._serialization.name, "' from Class '", cls.getName()));
        }
    }

    public final String toString() {
        return AnonymousClass006.A09("[field ", A0S(), "]");
    }

    public Object writeReplace() {
        return new AnonymousClass0Oy(new AnonymousClass0qC(this.A00));
    }

    @Override // X.AnonymousClass0qA
    public final /* bridge */ /* synthetic */ AnnotatedElement A0N() {
        return this.A00;
    }

    @Override // X.AbstractC01990hm
    public final Member A0R() {
        return this.A00;
    }

    public final String A0S() {
        return AnonymousClass006.A09(A0P().getName(), "#", A0L());
    }

    public AnonymousClass0Oy(AnonymousClass0qC r2) {
        super(null);
        this.A00 = null;
        this._serialization = r2;
    }

    public AnonymousClass0Oy(Field field, AnonymousClass0hl r2) {
        super(r2);
        this.A00 = field;
    }
}
