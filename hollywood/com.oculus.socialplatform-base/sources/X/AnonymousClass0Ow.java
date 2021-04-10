package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;

/* renamed from: X.0Ow  reason: invalid class name */
public abstract class AnonymousClass0Ow extends AbstractC01990hm {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0hl[] _paramAnnotations;

    public abstract Object A0U() throws Exception;

    public abstract Object A0V(Object obj) throws Exception;

    public abstract Object A0W(Object[] objArr) throws Exception;

    public abstract Type A0X(int i);

    @Override // X.AnonymousClass0qA
    public final <A extends Annotation> A A0M(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap = this.A00.A00;
        if (hashMap == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    public final AbstractC02190iF A0S(AnonymousClass0r8 r7, TypeVariable<?>[] typeVariableArr) {
        int length;
        AbstractC02190iF A09;
        if (typeVariableArr == null || (length = typeVariableArr.length) <= 0) {
            return r7.A03.A09(A0O(), r7);
        }
        r7 = new AnonymousClass0r8(r7.A03, r7, r7.A04, r7.A02);
        int i = 0;
        do {
            TypeVariable<?> typeVariable = typeVariableArr[i];
            r7.A03(typeVariable.getName());
            Type type = typeVariable.getBounds()[0];
            if (type == null) {
                A09 = new AnonymousClass0C7(Object.class);
            } else {
                A09 = r7.A03.A09(type, r7);
            }
            r7.A04(typeVariable.getName(), A09);
            i++;
        } while (i < length);
        return r7.A03.A09(A0O(), r7);
    }

    public final void A0Y(int i, Annotation annotation) {
        AnonymousClass0hl[] r1 = this._paramAnnotations;
        AnonymousClass0hl r0 = r1[i];
        if (r0 == null) {
            r0 = new AnonymousClass0hl();
            r1[i] = r0;
        }
        HashMap<Class<? extends Annotation>, Annotation> hashMap = r0.A00;
        if (hashMap == null) {
            hashMap = new HashMap<>();
            r0.A00 = hashMap;
        }
        hashMap.put(annotation.annotationType(), annotation);
    }

    public AnonymousClass0Ow(AnonymousClass0hl r1, AnonymousClass0hl[] r2) {
        super(r1);
        this._paramAnnotations = r2;
    }

    public final AnonymousClass0Ox A0T(int i) {
        AnonymousClass0hl r1;
        Type A0X = A0X(i);
        AnonymousClass0hl[] r12 = this._paramAnnotations;
        if (r12 == null || i < 0 || i > r12.length) {
            r1 = null;
        } else {
            r1 = r12[i];
        }
        return new AnonymousClass0Ox(this, A0X, r1, i);
    }
}
