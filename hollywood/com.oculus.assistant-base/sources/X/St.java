package X;

import java.io.Serializable;
import java.lang.reflect.Field;

public final class St extends AbstractC1044rJ implements Serializable {
    public static final long serialVersionUID = 7364428299211355871L;
    public final transient Field A00;
    public PB _serialization;

    public Object readResolve() {
        PB pb = this._serialization;
        Class cls = pb.clazz;
        try {
            Field declaredField = cls.getDeclaredField(pb.name);
            if (!declaredField.isAccessible()) {
                Q5.A06(declaredField);
            }
            return new St(declaredField, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException(AnonymousClass08.A06("Could not find method '", this._serialization.name, "' from Class '", cls.getName()));
        }
    }

    public final String toString() {
        return AnonymousClass08.A05("[field ", A0S(), "]");
    }

    public Object writeReplace() {
        return new St(new PB(this.A00));
    }

    public final String A0S() {
        return AnonymousClass08.A05(A0P().getName(), "#", A0K());
    }

    public St(PB pb) {
        super(null);
        this.A00 = null;
        this._serialization = pb;
    }

    public St(Field field, C1045rK rKVar) {
        super(rKVar);
        this.A00 = field;
    }
}
