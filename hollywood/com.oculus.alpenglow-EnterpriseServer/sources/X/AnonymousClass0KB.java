package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.HashMap;

/* renamed from: X.0KB  reason: invalid class name */
public final class AnonymousClass0KB extends AbstractC02450Zr {
    public static final long serialVersionUID = 1;
    public final int _index;
    public final AnonymousClass0KA _owner;
    public final Type _type;

    @Override // X.AbstractC06640nb
    public final String A0L() {
        return "";
    }

    @Override // X.AbstractC06640nb
    public final AnnotatedElement A0N() {
        return null;
    }

    @Override // X.AbstractC06640nb
    public final Class<?> A0K() {
        Type type = this._type;
        if (type instanceof Class) {
            return (Class) type;
        }
        return C07040od.A02.A09(type, null)._class;
    }

    @Override // X.AbstractC06640nb
    public final <A extends Annotation> A A0M(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        C02440Zq r0 = this.A00;
        if (r0 == null || (hashMap = r0.A00) == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    @Override // X.AbstractC06640nb
    public final Type A0O() {
        return this._type;
    }

    @Override // X.AbstractC02450Zr
    public final Class<?> A0P() {
        return this._owner.A0P();
    }

    @Override // X.AbstractC02450Zr
    public final Object A0Q(Object obj) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(AnonymousClass006.A05("Cannot call getValue() on constructor parameter of ", A0P().getName()));
    }

    @Override // X.AbstractC02450Zr
    public final Member A0R() {
        return this._owner.A0R();
    }

    public final String toString() {
        return "[parameter #" + this._index + ", annotations: " + this.A00 + "]";
    }

    public AnonymousClass0KB(AnonymousClass0KA r1, Type type, C02440Zq r3, int i) {
        super(r3);
        this._owner = r1;
        this._type = type;
        this._index = i;
    }
}
