package X;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.HashMap;

public final class CD extends WJ implements Serializable {
    public static final long serialVersionUID = 7364428299211355871L;
    public final transient Field A00;
    public VT _serialization;

    @Override // X.VV
    public final Class<?> A0J() {
        return this.A00.getType();
    }

    @Override // X.VV
    public final String A0K() {
        return this.A00.getName();
    }

    @Override // X.VV
    public final <A extends Annotation> A A0L(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        WI wi = super.A00;
        if (wi == null || (hashMap = wi.A00) == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    @Override // X.VV
    public final Type A0N() {
        return this.A00.getGenericType();
    }

    @Override // X.WJ
    public final Class<?> A0O() {
        return this.A00.getDeclaringClass();
    }

    public Object readResolve() {
        VT vt = this._serialization;
        Class<?> cls = vt.clazz;
        try {
            Field declaredField = cls.getDeclaredField(vt.name);
            if (!declaredField.isAccessible()) {
                Mv.A05(declaredField);
            }
            return new CD(declaredField, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException(AnonymousClass06.A06("Could not find method '", this._serialization.name, "' from Class '", cls.getName()));
        }
    }

    public final String toString() {
        return AnonymousClass06.A05("[field ", AnonymousClass06.A05(A0O().getName(), "#", A0K()), "]");
    }

    public Object writeReplace() {
        return new CD(new VT(this.A00));
    }

    @Override // X.VV
    public final /* bridge */ /* synthetic */ AnnotatedElement A0M() {
        return this.A00;
    }

    @Override // X.WJ
    public final Member A0P() {
        return this.A00;
    }

    public CD(VT vt) {
        super(null);
        this.A00 = null;
        this._serialization = vt;
    }

    public CD(Field field, WI wi) {
        super(wi);
        this.A00 = field;
    }
}
