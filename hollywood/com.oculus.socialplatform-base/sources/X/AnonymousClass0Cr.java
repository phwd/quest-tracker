package X;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* renamed from: X.0Cr  reason: invalid class name */
public final class AnonymousClass0Cr extends AnonymousClass0Ow implements Serializable {
    public static final long serialVersionUID = 1;
    public final transient Method A00;
    public Class<?>[] _paramClasses;
    public AnonymousClass0qD _serialization;

    /* JADX WARN: Incorrect args count in method signature: (I)Ljava/lang/Class<*>; */
    public final Class A0a() {
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

    @Override // X.AnonymousClass0qA
    public final AbstractC02190iF A0I(AnonymousClass0r8 r2) {
        return A0S(r2, this.A00.getTypeParameters());
    }

    @Override // X.AnonymousClass0qA
    public final Class<?> A0K() {
        return this.A00.getReturnType();
    }

    @Override // X.AnonymousClass0qA
    public final String A0L() {
        return this.A00.getName();
    }

    @Override // X.AnonymousClass0qA
    public final Type A0O() {
        return this.A00.getGenericReturnType();
    }

    @Override // X.AbstractC01990hm
    public final Class<?> A0P() {
        return this.A00.getDeclaringClass();
    }

    @Override // X.AbstractC01990hm
    public final Object A0Q(Object obj) throws IllegalArgumentException {
        try {
            return this.A00.invoke(obj, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(AnonymousClass006.A0B("Failed to getValue() with method ", A0b(), ": ", e.getMessage()), e);
        } catch (InvocationTargetException e2) {
            throw new IllegalArgumentException(AnonymousClass006.A0B("Failed to getValue() with method ", A0b(), ": ", e2.getMessage()), e2);
        }
    }

    @Override // X.AnonymousClass0Ow
    public final Object A0U() throws Exception {
        return this.A00.invoke(null, new Object[0]);
    }

    @Override // X.AnonymousClass0Ow
    public final Object A0V(Object obj) throws Exception {
        return this.A00.invoke(null, obj);
    }

    @Override // X.AnonymousClass0Ow
    public final Object A0W(Object[] objArr) throws Exception {
        return this.A00.invoke(null, objArr);
    }

    @Override // X.AnonymousClass0Ow
    public final Type A0X(int i) {
        Type[] genericParameterTypes = this.A00.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    public final int A0Z() {
        Class<?>[] clsArr = this._paramClasses;
        if (clsArr == null) {
            clsArr = this.A00.getParameterTypes();
            this._paramClasses = clsArr;
        }
        return clsArr.length;
    }

    public final String A0b() {
        StringBuilder sb = new StringBuilder();
        sb.append(A0P().getName());
        sb.append("#");
        sb.append(A0L());
        sb.append("(");
        sb.append(A0Z());
        sb.append(" params)");
        return sb.toString();
    }

    public Object readResolve() {
        AnonymousClass0qD r0 = this._serialization;
        Class<?> cls = r0.clazz;
        try {
            Method declaredMethod = cls.getDeclaredMethod(r0.name, r0.args);
            if (!declaredMethod.isAccessible()) {
                C04810rI.A06(declaredMethod);
            }
            return new AnonymousClass0Cr(declaredMethod, null, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException(AnonymousClass006.A0B("Could not find method '", this._serialization.name, "' from Class '", cls.getName()));
        }
    }

    public final String toString() {
        return AnonymousClass006.A09("[method ", A0b(), "]");
    }

    public Object writeReplace() {
        return new AnonymousClass0Cr(new AnonymousClass0qD(this.A00));
    }

    @Override // X.AnonymousClass0qA
    public final /* bridge */ /* synthetic */ AnnotatedElement A0N() {
        return this.A00;
    }

    @Override // X.AbstractC01990hm
    public final /* bridge */ /* synthetic */ Member A0R() {
        return this.A00;
    }

    public AnonymousClass0Cr(AnonymousClass0qD r2) {
        super(null, null);
        this.A00 = null;
        this._serialization = r2;
    }

    public AnonymousClass0Cr(Method method, AnonymousClass0hl r4, AnonymousClass0hl[] r5) {
        super(r4, r5);
        if (method != null) {
            this.A00 = method;
            return;
        }
        throw new IllegalArgumentException("Can not construct AnnotatedMethod with null Method");
    }
}
