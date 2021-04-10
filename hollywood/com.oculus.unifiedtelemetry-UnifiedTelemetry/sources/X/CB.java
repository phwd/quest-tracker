package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;

public abstract class CB extends WJ {
    public static final long serialVersionUID = 1;
    public final WI[] _paramAnnotations;

    public abstract Object A0R() throws Exception;

    public abstract Object A0S(Object obj) throws Exception;

    public abstract Object A0T(Object[] objArr) throws Exception;

    public abstract Type A0U(int i);

    @Override // X.VV
    public final <A extends Annotation> A A0L(Class<A> cls) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap = this.A00.A00;
        if (hashMap == null) {
            return null;
        }
        return (A) hashMap.get(cls);
    }

    public final void A0V(int i, Annotation annotation) {
        WI[] wiArr = this._paramAnnotations;
        WI wi = wiArr[i];
        if (wi == null) {
            wi = new WI();
            wiArr[i] = wi;
        }
        HashMap<Class<? extends Annotation>, Annotation> hashMap = wi.A00;
        if (hashMap == null) {
            hashMap = new HashMap<>();
            wi.A00 = hashMap;
        }
        hashMap.put(annotation.annotationType(), annotation);
    }

    public CB(WI wi, WI[] wiArr) {
        super(wi);
        this._paramAnnotations = wiArr;
    }

    public final CC A0Q(int i) {
        WI wi;
        Type A0U = A0U(i);
        WI[] wiArr = this._paramAnnotations;
        if (wiArr == null || i < 0 || i > wiArr.length) {
            wi = null;
        } else {
            wi = wiArr[i];
        }
        return new CC(this, A0U, wi, i);
    }
}
