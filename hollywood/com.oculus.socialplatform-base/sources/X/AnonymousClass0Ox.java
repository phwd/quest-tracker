package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.HashMap;

/* renamed from: X.0Ox  reason: invalid class name */
public final class AnonymousClass0Ox extends AbstractC01990hm {
    public static final long serialVersionUID = 1;
    public final int _index;
    public final AnonymousClass0Ow _owner;
    public final Type _type;

    @Override // X.AnonymousClass0qA
    public final String A0L() {
        return "";
    }

    @Override // X.AnonymousClass0qA
    public final AnnotatedElement A0N() {
        return null;
    }

    @Override // X.AnonymousClass0qA
    public final Class<?> A0K() {
        Type type = this._type;
        if (type instanceof Class) {
            return (Class) type;
        }
        return AnonymousClass0r9.A02.A09(type, null)._class;
    }

    @Override // X.AnonymousClass0qA
    public final <A extends Annotation> A A0M(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        AnonymousClass0hl r0 = this.A00;
        if (r0 == null || (hashMap = r0.A00) == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    @Override // X.AbstractC01990hm
    public final Class<?> A0P() {
        return this._owner.A0P();
    }

    @Override // X.AbstractC01990hm
    public final Object A0Q(Object obj) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(AnonymousClass006.A07("Cannot call getValue() on constructor parameter of ", A0P().getName()));
    }

    @Override // X.AbstractC01990hm
    public final Member A0R() {
        return this._owner.A0R();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[parameter #");
        sb.append(this._index);
        sb.append(", annotations: ");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass0Ox(AnonymousClass0Ow r1, Type type, AnonymousClass0hl r3, int i) {
        super(r3);
        this._owner = r1;
        this._type = type;
        this._index = i;
    }

    @Override // X.AnonymousClass0qA
    public final Type A0O() {
        return this._type;
    }
}
