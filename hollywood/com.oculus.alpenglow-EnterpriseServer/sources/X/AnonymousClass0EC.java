package X;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* renamed from: X.0EC  reason: invalid class name */
public final class AnonymousClass0EC extends AnonymousClass0KA implements Serializable {
    public static final long serialVersionUID = 1;
    public final transient Method A00;
    public Class<?>[] _paramClasses;
    public C06670ne _serialization;

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

    @Override // X.AbstractC06640nb
    public final AnonymousClass0aI A0I(C07030oc r2) {
        return A0S(r2, this.A00.getTypeParameters());
    }

    @Override // X.AbstractC06640nb
    public final Class<?> A0K() {
        return this.A00.getReturnType();
    }

    @Override // X.AbstractC06640nb
    public final String A0L() {
        return this.A00.getName();
    }

    @Override // X.AbstractC06640nb
    public final /* bridge */ /* synthetic */ AnnotatedElement A0N() {
        return this.A00;
    }

    @Override // X.AbstractC06640nb
    public final Type A0O() {
        return this.A00.getGenericReturnType();
    }

    @Override // X.AbstractC02450Zr
    public final Class<?> A0P() {
        return this.A00.getDeclaringClass();
    }

    @Override // X.AbstractC02450Zr
    public final Object A0Q(Object obj) throws IllegalArgumentException {
        try {
            return this.A00.invoke(obj, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Failed to getValue() with method ", A0b(), ": ", e.getMessage()), e);
        } catch (InvocationTargetException e2) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Failed to getValue() with method ", A0b(), ": ", e2.getMessage()), e2);
        }
    }

    @Override // X.AbstractC02450Zr
    public final /* bridge */ /* synthetic */ Member A0R() {
        return this.A00;
    }

    @Override // X.AnonymousClass0KA
    public final Object A0U() throws Exception {
        return this.A00.invoke(null, new Object[0]);
    }

    @Override // X.AnonymousClass0KA
    public final Object A0V(Object obj) throws Exception {
        return this.A00.invoke(null, obj);
    }

    @Override // X.AnonymousClass0KA
    public final Object A0W(Object[] objArr) throws Exception {
        return this.A00.invoke(null, objArr);
    }

    @Override // X.AnonymousClass0KA
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
        return A0P().getName() + "#" + A0L() + "(" + A0Z() + " params)";
    }

    public Object readResolve() {
        C06670ne r0 = this._serialization;
        Class<?> cls = r0.clazz;
        try {
            Method declaredMethod = cls.getDeclaredMethod(r0.name, r0.args);
            if (!declaredMethod.isAccessible()) {
                C07130om.A06(declaredMethod);
            }
            return new AnonymousClass0EC(declaredMethod, null, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException(AnonymousClass006.A08("Could not find method '", this._serialization.name, "' from Class '", cls.getName()));
        }
    }

    public final String toString() {
        return AnonymousClass006.A07("[method ", A0b(), "]");
    }

    public Object writeReplace() {
        return new AnonymousClass0EC(new C06670ne(this.A00));
    }

    public AnonymousClass0EC(C06670ne r2) {
        super(null, null);
        this.A00 = null;
        this._serialization = r2;
    }

    public AnonymousClass0EC(Method method, C02440Zq r4, C02440Zq[] r5) {
        super(r4, r5);
        if (method != null) {
            this.A00 = method;
            return;
        }
        throw new IllegalArgumentException("Can not construct AnnotatedMethod with null Method");
    }
}
