package X;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* renamed from: X.7P  reason: invalid class name */
public final class AnonymousClass7P extends CB implements Serializable {
    public static final long serialVersionUID = 1;
    public final transient Method A00;
    public Class<?>[] _paramClasses;
    public VS _serialization;

    /* JADX WARN: Incorrect args count in method signature: (I)Ljava/lang/Class<*>; */
    public final Class A0X() {
        Class<?>[] clsArr = this._paramClasses;
        if (clsArr == null) {
            clsArr = this.A00.getParameterTypes();
            this._paramClasses = clsArr;
        }
        if (0 >= clsArr.length) {
            return null;
        }
        return clsArr[0];
    }

    @Override // X.VV
    public final Class<?> A0J() {
        return this.A00.getReturnType();
    }

    @Override // X.VV
    public final String A0K() {
        return this.A00.getName();
    }

    @Override // X.VV
    public final Type A0N() {
        return this.A00.getGenericReturnType();
    }

    @Override // X.WJ
    public final Class<?> A0O() {
        return this.A00.getDeclaringClass();
    }

    @Override // X.CB
    public final Object A0R() throws Exception {
        return this.A00.invoke(null, new Object[0]);
    }

    @Override // X.CB
    public final Object A0S(Object obj) throws Exception {
        return this.A00.invoke(null, obj);
    }

    @Override // X.CB
    public final Object A0T(Object[] objArr) throws Exception {
        return this.A00.invoke(null, objArr);
    }

    @Override // X.CB
    public final Type A0U(int i) {
        Type[] genericParameterTypes = this.A00.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    public final int A0W() {
        Class<?>[] clsArr = this._paramClasses;
        if (clsArr == null) {
            clsArr = this.A00.getParameterTypes();
            this._paramClasses = clsArr;
        }
        return clsArr.length;
    }

    public final String A0Y() {
        StringBuilder sb = new StringBuilder();
        sb.append(A0O().getName());
        sb.append("#");
        sb.append(A0K());
        sb.append("(");
        sb.append(A0W());
        sb.append(" params)");
        return sb.toString();
    }

    public Object readResolve() {
        VS vs = this._serialization;
        Class<?> cls = vs.clazz;
        try {
            Method declaredMethod = cls.getDeclaredMethod(vs.name, vs.args);
            if (!declaredMethod.isAccessible()) {
                Mv.A05(declaredMethod);
            }
            return new AnonymousClass7P(declaredMethod, null, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException(AnonymousClass06.A06("Could not find method '", this._serialization.name, "' from Class '", cls.getName()));
        }
    }

    public final String toString() {
        return AnonymousClass06.A05("[method ", A0Y(), "]");
    }

    public Object writeReplace() {
        return new AnonymousClass7P(new VS(this.A00));
    }

    @Override // X.VV
    public final /* bridge */ /* synthetic */ AnnotatedElement A0M() {
        return this.A00;
    }

    @Override // X.WJ
    public final /* bridge */ /* synthetic */ Member A0P() {
        return this.A00;
    }

    public AnonymousClass7P(VS vs) {
        super(null, null);
        this.A00 = null;
        this._serialization = vs;
    }

    public AnonymousClass7P(Method method, WI wi, WI[] wiArr) {
        super(wi, wiArr);
        if (method != null) {
            this.A00 = method;
            return;
        }
        throw new IllegalArgumentException("Can not construct AnnotatedMethod with null Method");
    }
}
