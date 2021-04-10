package X;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/* renamed from: X.07P  reason: invalid class name */
public final class AnonymousClass07P extends AnonymousClass0GV {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _constructor;
    public C05690lh _serialization;

    @Override // X.AbstractC05680lg
    public final Class<?> A0J() {
        return this._constructor.getDeclaringClass();
    }

    @Override // X.AbstractC05680lg
    public final String A0K() {
        return this._constructor.getName();
    }

    @Override // X.AnonymousClass0g9
    public final Class<?> A0O() {
        return this._constructor.getDeclaringClass();
    }

    @Override // X.AnonymousClass0GV
    public final Object A0R() throws Exception {
        return this._constructor.newInstance(new Object[0]);
    }

    @Override // X.AnonymousClass0GV
    public final Object A0S(Object obj) throws Exception {
        return this._constructor.newInstance(obj);
    }

    @Override // X.AnonymousClass0GV
    public final Object A0T(Object[] objArr) throws Exception {
        return this._constructor.newInstance(objArr);
    }

    @Override // X.AnonymousClass0GV
    public final Type A0U(int i) {
        Type[] genericParameterTypes = this._constructor.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    public Object readResolve() {
        C05690lh r0 = this._serialization;
        Class<?> cls = r0.clazz;
        try {
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(r0.args);
            if (!declaredConstructor.isAccessible()) {
                C06330mu.A05(declaredConstructor);
            }
            return new AnonymousClass07P(declaredConstructor, null, null);
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
        return new AnonymousClass07P(new C05690lh(this._constructor));
    }

    @Override // X.AbstractC05680lg
    public final /* bridge */ /* synthetic */ AnnotatedElement A0M() {
        return this._constructor;
    }

    @Override // X.AbstractC05680lg
    public final Type A0N() {
        return A0J();
    }

    @Override // X.AnonymousClass0g9
    public final Member A0P() {
        return this._constructor;
    }

    public AnonymousClass07P(C05690lh r2) {
        super(null, null);
        this._constructor = null;
        this._serialization = r2;
    }

    public AnonymousClass07P(Constructor<?> constructor, C03800g8 r2, C03800g8[] r3) {
        super(r2, r3);
        this._constructor = constructor;
    }
}
