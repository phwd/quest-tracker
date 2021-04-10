package X;

import java.lang.reflect.Constructor;

/* renamed from: X.0s  reason: invalid class name and case insensitive filesystem */
public final class C00090s extends SV {
    public static final long serialVersionUID = 1;
    public final Constructor _constructor;
    public PA _serialization;

    public Object readResolve() {
        PA pa = this._serialization;
        Class cls = pa.clazz;
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(pa.args);
            if (!declaredConstructor.isAccessible()) {
                Q5.A06(declaredConstructor);
            }
            return new C00090s(declaredConstructor, null, null);
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
        return new C00090s(new PA(this._constructor));
    }

    public C00090s(PA pa) {
        super(null, null);
        this._constructor = null;
        this._serialization = pa;
    }

    public C00090s(Constructor constructor, C1045rK rKVar, C1045rK[] rKVarArr) {
        super(rKVar, rKVarArr);
        this._constructor = constructor;
    }
}
