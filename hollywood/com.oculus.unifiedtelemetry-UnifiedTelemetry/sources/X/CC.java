package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.HashMap;

public final class CC extends WJ {
    public static final long serialVersionUID = 1;
    public final int _index;
    public final CB _owner;
    public final Type _type;

    @Override // X.VV
    public final String A0K() {
        return "";
    }

    @Override // X.VV
    public final AnnotatedElement A0M() {
        return null;
    }

    @Override // X.VV
    public final Class<?> A0J() {
        Type type = this._type;
        if (type instanceof Class) {
            return (Class) type;
        }
        return NT.A02.A09(type, null)._class;
    }

    @Override // X.VV
    public final <A extends Annotation> A A0L(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        WI wi = this.A00;
        if (wi == null || (hashMap = wi.A00) == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    @Override // X.WJ
    public final Class<?> A0O() {
        return this._owner.A0O();
    }

    @Override // X.WJ
    public final Member A0P() {
        return this._owner.A0P();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[parameter #");
        sb.append(this._index);
        sb.append(", annotations: ");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }

    public CC(CB cb, Type type, WI wi, int i) {
        super(wi);
        this._owner = cb;
        this._type = type;
        this._index = i;
    }

    @Override // X.VV
    public final Type A0N() {
        return this._type;
    }
}
