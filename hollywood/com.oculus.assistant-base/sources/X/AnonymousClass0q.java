package X;

import java.io.Serializable;
import java.lang.reflect.Method;

/* renamed from: X.0q  reason: invalid class name */
public final class AnonymousClass0q extends SV implements Serializable {
    public static final long serialVersionUID = 1;
    public final transient Method A00;
    public Class[] _paramClasses;
    public PC _serialization;

    public final Class A0Y() {
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

    public final int A0X() {
        Class<?>[] clsArr = this._paramClasses;
        if (clsArr == null) {
            clsArr = this.A00.getParameterTypes();
            this._paramClasses = clsArr;
        }
        return clsArr.length;
    }

    public final String A0Z() {
        StringBuilder sb = new StringBuilder();
        sb.append(A0P().getName());
        sb.append("#");
        sb.append(A0K());
        sb.append("(");
        sb.append(A0X());
        sb.append(" params)");
        return sb.toString();
    }

    public Object readResolve() {
        PC pc = this._serialization;
        Class cls = pc.clazz;
        try {
            Method declaredMethod = cls.getDeclaredMethod(pc.name, pc.args);
            if (!declaredMethod.isAccessible()) {
                Q5.A06(declaredMethod);
            }
            return new AnonymousClass0q(declaredMethod, null, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException(AnonymousClass08.A06("Could not find method '", this._serialization.name, "' from Class '", cls.getName()));
        }
    }

    public final String toString() {
        return AnonymousClass08.A05("[method ", A0Z(), "]");
    }

    public Object writeReplace() {
        return new AnonymousClass0q(new PC(this.A00));
    }

    public AnonymousClass0q(PC pc) {
        super(null, null);
        this.A00 = null;
        this._serialization = pc;
    }

    public AnonymousClass0q(Method method, C1045rK rKVar, C1045rK[] rKVarArr) {
        super(rKVar, rKVarArr);
        if (method != null) {
            this.A00 = method;
            return;
        }
        throw new IllegalArgumentException("Can not construct AnnotatedMethod with null Method");
    }
}
