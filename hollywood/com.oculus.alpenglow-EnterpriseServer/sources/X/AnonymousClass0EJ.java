package X;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/* renamed from: X.0EJ  reason: invalid class name */
public final class AnonymousClass0EJ extends AnonymousClass0KA {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _constructor;
    public C06650nc _serialization;

    @Override // X.AbstractC06640nb
    public final AnonymousClass0aI A0I(C07030oc r2) {
        return A0S(r2, this._constructor.getTypeParameters());
    }

    @Override // X.AbstractC06640nb
    public final Class<?> A0K() {
        return this._constructor.getDeclaringClass();
    }

    @Override // X.AbstractC06640nb
    public final String A0L() {
        return this._constructor.getName();
    }

    @Override // X.AbstractC06640nb
    public final /* bridge */ /* synthetic */ AnnotatedElement A0N() {
        return this._constructor;
    }

    @Override // X.AbstractC02450Zr
    public final Class<?> A0P() {
        return this._constructor.getDeclaringClass();
    }

    @Override // X.AbstractC02450Zr
    public final Object A0Q(Object obj) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(AnonymousClass006.A05("Cannot call getValue() on constructor of ", A0P().getName()));
    }

    @Override // X.AbstractC02450Zr
    public final Member A0R() {
        return this._constructor;
    }

    @Override // X.AnonymousClass0KA
    public final Object A0U() throws Exception {
        return this._constructor.newInstance(new Object[0]);
    }

    @Override // X.AnonymousClass0KA
    public final Object A0V(Object obj) throws Exception {
        return this._constructor.newInstance(obj);
    }

    @Override // X.AnonymousClass0KA
    public final Object A0W(Object[] objArr) throws Exception {
        return this._constructor.newInstance(objArr);
    }

    @Override // X.AnonymousClass0KA
    public final Type A0X(int i) {
        Type[] genericParameterTypes = this._constructor.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    public Object readResolve() {
        C06650nc r0 = this._serialization;
        Class<?> cls = r0.clazz;
        try {
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(r0.args);
            if (!declaredConstructor.isAccessible()) {
                C07130om.A06(declaredConstructor);
            }
            return new AnonymousClass0EJ(declaredConstructor, null, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException("Could not find constructor with " + this._serialization.args.length + " args from Class '" + cls.getName());
        }
    }

    public final String toString() {
        return "[constructor for " + A0L() + ", annotations: " + this.A00 + "]";
    }

    public Object writeReplace() {
        return new AnonymousClass0EJ(new C06650nc(this._constructor));
    }

    @Override // X.AbstractC06640nb
    public final Type A0O() {
        return A0K();
    }

    public AnonymousClass0EJ(C06650nc r2) {
        super(null, null);
        this._constructor = null;
        this._serialization = r2;
    }

    public AnonymousClass0EJ(Constructor<?> constructor, C02440Zq r2, C02440Zq[] r3) {
        super(r2, r3);
        this._constructor = constructor;
    }
}
