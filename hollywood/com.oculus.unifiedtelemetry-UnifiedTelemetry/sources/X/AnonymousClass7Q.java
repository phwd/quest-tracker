package X;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/* renamed from: X.7Q  reason: invalid class name */
public final class AnonymousClass7Q extends CB {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _constructor;
    public VU _serialization;

    @Override // X.VV
    public final Class<?> A0J() {
        return this._constructor.getDeclaringClass();
    }

    @Override // X.VV
    public final String A0K() {
        return this._constructor.getName();
    }

    @Override // X.WJ
    public final Class<?> A0O() {
        return this._constructor.getDeclaringClass();
    }

    @Override // X.CB
    public final Object A0R() throws Exception {
        return this._constructor.newInstance(new Object[0]);
    }

    @Override // X.CB
    public final Object A0S(Object obj) throws Exception {
        return this._constructor.newInstance(obj);
    }

    @Override // X.CB
    public final Object A0T(Object[] objArr) throws Exception {
        return this._constructor.newInstance(objArr);
    }

    @Override // X.CB
    public final Type A0U(int i) {
        Type[] genericParameterTypes = this._constructor.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    public Object readResolve() {
        VU vu = this._serialization;
        Class<?> cls = vu.clazz;
        try {
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(vu.args);
            if (!declaredConstructor.isAccessible()) {
                Mv.A05(declaredConstructor);
            }
            return new AnonymousClass7Q(declaredConstructor, null, null);
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
        sb.append(A0K());
        sb.append(", annotations: ");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }

    public Object writeReplace() {
        return new AnonymousClass7Q(new VU(this._constructor));
    }

    @Override // X.VV
    public final Type A0N() {
        return A0J();
    }

    @Override // X.VV
    public final /* bridge */ /* synthetic */ AnnotatedElement A0M() {
        return this._constructor;
    }

    @Override // X.WJ
    public final Member A0P() {
        return this._constructor;
    }

    public AnonymousClass7Q(VU vu) {
        super(null, null);
        this._constructor = null;
        this._serialization = vu;
    }

    public AnonymousClass7Q(Constructor<?> constructor, WI wi, WI[] wiArr) {
        super(wi, wiArr);
        this._constructor = constructor;
    }
}
