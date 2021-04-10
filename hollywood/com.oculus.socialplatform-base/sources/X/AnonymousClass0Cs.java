package X;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/* renamed from: X.0Cs  reason: invalid class name */
public final class AnonymousClass0Cs extends AnonymousClass0Ow {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _constructor;
    public AnonymousClass0qB _serialization;

    @Override // X.AnonymousClass0qA
    public final AbstractC02190iF A0I(AnonymousClass0r8 r2) {
        return A0S(r2, this._constructor.getTypeParameters());
    }

    @Override // X.AnonymousClass0qA
    public final Class<?> A0K() {
        return this._constructor.getDeclaringClass();
    }

    @Override // X.AnonymousClass0qA
    public final String A0L() {
        return this._constructor.getName();
    }

    @Override // X.AbstractC01990hm
    public final Class<?> A0P() {
        return this._constructor.getDeclaringClass();
    }

    @Override // X.AbstractC01990hm
    public final Object A0Q(Object obj) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(AnonymousClass006.A07("Cannot call getValue() on constructor of ", A0P().getName()));
    }

    @Override // X.AnonymousClass0Ow
    public final Object A0U() throws Exception {
        return this._constructor.newInstance(new Object[0]);
    }

    @Override // X.AnonymousClass0Ow
    public final Object A0V(Object obj) throws Exception {
        return this._constructor.newInstance(obj);
    }

    @Override // X.AnonymousClass0Ow
    public final Object A0W(Object[] objArr) throws Exception {
        return this._constructor.newInstance(objArr);
    }

    @Override // X.AnonymousClass0Ow
    public final Type A0X(int i) {
        Type[] genericParameterTypes = this._constructor.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    public Object readResolve() {
        AnonymousClass0qB r0 = this._serialization;
        Class<?> cls = r0.clazz;
        try {
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(r0.args);
            if (!declaredConstructor.isAccessible()) {
                C04810rI.A06(declaredConstructor);
            }
            return new AnonymousClass0Cs(declaredConstructor, null, null);
        } catch (Exception unused) {
            StringBuilder sb = new StringBuilder("Could not find constructor with ");
            sb.append(this._serialization.args.length);
            sb.append(" args from Class '");
            sb.append(cls.getName());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[constructor for ");
        sb.append(A0L());
        sb.append(", annotations: ");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }

    public Object writeReplace() {
        return new AnonymousClass0Cs(new AnonymousClass0qB(this._constructor));
    }

    @Override // X.AnonymousClass0qA
    public final /* bridge */ /* synthetic */ AnnotatedElement A0N() {
        return this._constructor;
    }

    @Override // X.AnonymousClass0qA
    public final Type A0O() {
        return A0K();
    }

    @Override // X.AbstractC01990hm
    public final Member A0R() {
        return this._constructor;
    }

    public AnonymousClass0Cs(AnonymousClass0qB r2) {
        super(null, null);
        this._constructor = null;
        this._serialization = r2;
    }

    public AnonymousClass0Cs(Constructor<?> constructor, AnonymousClass0hl r2, AnonymousClass0hl[] r3) {
        super(r2, r3);
        this._constructor = constructor;
    }
}
