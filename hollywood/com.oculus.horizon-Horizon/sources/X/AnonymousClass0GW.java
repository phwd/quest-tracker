package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.HashMap;

/* renamed from: X.0GW  reason: invalid class name */
public final class AnonymousClass0GW extends AnonymousClass0g9 {
    public static final long serialVersionUID = 1;
    public final int _index;
    public final AnonymousClass0GV _owner;
    public final Type _type;

    @Override // X.AbstractC05680lg
    public final String A0K() {
        return "";
    }

    @Override // X.AbstractC05680lg
    public final AnnotatedElement A0M() {
        return null;
    }

    @Override // X.AbstractC05680lg
    public final Class<?> A0J() {
        Type type = this._type;
        if (type instanceof Class) {
            return (Class) type;
        }
        return C06240ml.A02.A09(type, null)._class;
    }

    @Override // X.AbstractC05680lg
    public final <A extends Annotation> A A0L(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        C03800g8 r0 = this.A00;
        if (r0 == null || (hashMap = r0.A00) == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    @Override // X.AnonymousClass0g9
    public final Class<?> A0O() {
        return this._owner.A0O();
    }

    @Override // X.AnonymousClass0g9
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

    public AnonymousClass0GW(AnonymousClass0GV r1, Type type, C03800g8 r3, int i) {
        super(r3);
        this._owner = r1;
        this._type = type;
        this._index = i;
    }

    @Override // X.AbstractC05680lg
    public final Type A0N() {
        return this._type;
    }
}
