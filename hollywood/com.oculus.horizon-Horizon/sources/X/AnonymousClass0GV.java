package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;

/* renamed from: X.0GV  reason: invalid class name */
public abstract class AnonymousClass0GV extends AnonymousClass0g9 {
    public static final long serialVersionUID = 1;
    public final C03800g8[] _paramAnnotations;

    public abstract Object A0R() throws Exception;

    public abstract Object A0S(Object obj) throws Exception;

    public abstract Object A0T(Object[] objArr) throws Exception;

    public abstract Type A0U(int i);

    @Override // X.AbstractC05680lg
    public final <A extends Annotation> A A0L(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap = this.A00.A00;
        if (hashMap == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    public final void A0V(int i, Annotation annotation) {
        C03800g8[] r1 = this._paramAnnotations;
        C03800g8 r0 = r1[i];
        if (r0 == null) {
            r0 = new C03800g8();
            r1[i] = r0;
        }
        HashMap<Class<? extends Annotation>, Annotation> hashMap = r0.A00;
        if (hashMap == null) {
            hashMap = new HashMap<>();
            r0.A00 = hashMap;
        }
        hashMap.put(annotation.annotationType(), annotation);
    }

    public AnonymousClass0GV(C03800g8 r1, C03800g8[] r2) {
        super(r1);
        this._paramAnnotations = r2;
    }

    public final AnonymousClass0GW A0Q(int i) {
        C03800g8 r1;
        Type A0U = A0U(i);
        C03800g8[] r12 = this._paramAnnotations;
        if (r12 == null || i < 0 || i > r12.length) {
            r1 = null;
        } else {
            r1 = r12[i];
        }
        return new AnonymousClass0GW(this, A0U, r1, i);
    }
}
