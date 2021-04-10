package X;

import java.io.Serializable;
import java.lang.reflect.Method;

/* renamed from: X.7B  reason: invalid class name */
public final class AnonymousClass7B extends A8 implements Serializable {
    public static final long serialVersionUID = 1;
    public final transient Method A00;
    public Class<?>[] _paramClasses;
    public AnonymousClass6M _serialization;

    public Object readResolve() {
        AnonymousClass6M r0 = this._serialization;
        Class<?> cls = r0.clazz;
        try {
            Method declaredMethod = cls.getDeclaredMethod(r0.name, r0.args);
            if (!declaredMethod.isAccessible()) {
                try {
                    declaredMethod.setAccessible(true);
                } catch (SecurityException e) {
                    if (!declaredMethod.isAccessible()) {
                        Class<?> declaringClass = declaredMethod.getDeclaringClass();
                        StringBuilder sb = new StringBuilder("Can not access ");
                        sb.append(declaredMethod);
                        sb.append(" (from class ");
                        sb.append(declaringClass.getName());
                        sb.append("; failed to set access: ");
                        sb.append(e.getMessage());
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
            }
            return new AnonymousClass7B(declaredMethod);
        } catch (Exception unused) {
            throw new IllegalArgumentException(AnonymousClass06.A05("Could not find method '", this._serialization.name, "' from Class '", cls.getName()));
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        Method method = this.A00;
        sb.append(method.getDeclaringClass().getName());
        sb.append("#");
        sb.append(this.A00.getName());
        sb.append("(");
        Class<?>[] clsArr = this._paramClasses;
        if (clsArr == null) {
            clsArr = method.getParameterTypes();
            this._paramClasses = clsArr;
        }
        sb.append(clsArr.length);
        sb.append(" params)");
        return AnonymousClass06.A04("[method ", sb.toString(), "]");
    }

    public Object writeReplace() {
        return new AnonymousClass7B(new AnonymousClass6M(this.A00));
    }

    public AnonymousClass7B(AnonymousClass6M r2) {
        this.A00 = null;
        this._serialization = r2;
    }

    public AnonymousClass7B(Method method) {
        this.A00 = method;
    }
}
