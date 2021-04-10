package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;

public abstract class SV extends AbstractC1044rJ {
    public static final long serialVersionUID = 1;
    public final C1045rK[] _paramAnnotations;

    public final AbstractC1024qt A0S(C0299Pv pv, TypeVariable[] typeVariableArr) {
        int length;
        AbstractC1024qt A09;
        if (typeVariableArr == null || (length = typeVariableArr.length) <= 0) {
            return pv.A03.A09(A0N(), pv);
        }
        pv = new C0299Pv(pv.A03, pv, pv.A04, pv.A02);
        int i = 0;
        do {
            TypeVariable typeVariable = typeVariableArr[i];
            pv.A03(typeVariable.getName());
            Type type = typeVariable.getBounds()[0];
            if (type == null) {
                A09 = new fF(Object.class);
            } else {
                A09 = pv.A03.A09(type, pv);
            }
            pv.A04(typeVariable.getName(), A09);
            i++;
        } while (i < length);
        return pv.A03.A09(A0N(), pv);
    }

    public final Object A0U(Object obj) {
        if (!(this instanceof AnonymousClass0q)) {
            return ((C00090s) this)._constructor.newInstance(obj);
        }
        return ((AnonymousClass0q) this).A00.invoke(null, obj);
    }

    public final Type A0V(int i) {
        Type[] genericParameterTypes;
        if (!(this instanceof AnonymousClass0q)) {
            genericParameterTypes = ((C00090s) this)._constructor.getGenericParameterTypes();
        } else {
            genericParameterTypes = ((AnonymousClass0q) this).A00.getGenericParameterTypes();
        }
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    public final void A0W(int i, Annotation annotation) {
        C1045rK[] rKVarArr = this._paramAnnotations;
        C1045rK rKVar = rKVarArr[i];
        if (rKVar == null) {
            rKVar = new C1045rK();
            rKVarArr[i] = rKVar;
        }
        HashMap hashMap = rKVar.A00;
        if (hashMap == null) {
            hashMap = new HashMap();
            rKVar.A00 = hashMap;
        }
        hashMap.put(annotation.annotationType(), annotation);
    }

    public SV(C1045rK rKVar, C1045rK[] rKVarArr) {
        super(rKVar);
        this._paramAnnotations = rKVarArr;
    }

    public final Ss A0T(int i) {
        C1045rK rKVar;
        Type A0V = A0V(i);
        C1045rK[] rKVarArr = this._paramAnnotations;
        if (rKVarArr == null || i < 0 || i > rKVarArr.length) {
            rKVar = null;
        } else {
            rKVar = rKVarArr[i];
        }
        return new Ss(this, A0V, rKVar, i);
    }
}
