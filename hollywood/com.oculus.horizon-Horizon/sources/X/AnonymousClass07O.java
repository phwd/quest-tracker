package X;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* renamed from: X.07O  reason: invalid class name */
public final class AnonymousClass07O extends AnonymousClass0GV implements Serializable {
    public static final long serialVersionUID = 1;
    public final transient Method A00;
    public Class<?>[] _paramClasses;
    public C05710lj _serialization;

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

    @Override // X.AbstractC05680lg
    public final Class<?> A0J() {
        return this.A00.getReturnType();
    }

    @Override // X.AbstractC05680lg
    public final String A0K() {
        return this.A00.getName();
    }

    @Override // X.AbstractC05680lg
    public final Type A0N() {
        return this.A00.getGenericReturnType();
    }

    @Override // X.AnonymousClass0g9
    public final Class<?> A0O() {
        return this.A00.getDeclaringClass();
    }

    @Override // X.AnonymousClass0GV
    public final Object A0R() throws Exception {
        return this.A00.invoke(null, new Object[0]);
    }

    @Override // X.AnonymousClass0GV
    public final Object A0S(Object obj) throws Exception {
        return this.A00.invoke(null, obj);
    }

    @Override // X.AnonymousClass0GV
    public final Object A0T(Object[] objArr) throws Exception {
        return this.A00.invoke(null, objArr);
    }

    @Override // X.AnonymousClass0GV
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
        C05710lj r0 = this._serialization;
        Class<?> cls = r0.clazz;
        try {
            Method declaredMethod = cls.getDeclaredMethod(r0.name, r0.args);
            if (!declaredMethod.isAccessible()) {
                C06330mu.A05(declaredMethod);
            }
            return new AnonymousClass07O(declaredMethod, null, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Could not find method '", this._serialization.name, "' from Class '", cls.getName()));
        }
    }

    public final String toString() {
        return AnonymousClass006.A07("[method ", A0Y(), "]");
    }

    public Object writeReplace() {
        return new AnonymousClass07O(new C05710lj(this.A00));
    }

    @Override // X.AbstractC05680lg
    public final /* bridge */ /* synthetic */ AnnotatedElement A0M() {
        return this.A00;
    }

    @Override // X.AnonymousClass0g9
    public final /* bridge */ /* synthetic */ Member A0P() {
        return this.A00;
    }

    public AnonymousClass07O(C05710lj r2) {
        super(null, null);
        this.A00 = null;
        this._serialization = r2;
    }

    public AnonymousClass07O(Method method, C03800g8 r4, C03800g8[] r5) {
        super(r4, r5);
        if (method != null) {
            this.A00 = method;
            return;
        }
        throw new IllegalArgumentException("Can not construct AnnotatedMethod with null Method");
    }
}
