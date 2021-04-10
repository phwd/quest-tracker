package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC01170Rz;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04300pk;
import X.AbstractC04870rR;
import X.C02180iD;
import X.C03620oC;
import X.C04290pj;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import java.io.IOException;
import java.util.HashSet;

public class ThrowableDeserializer extends BeanDeserializer {
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.deser.BeanDeserializer
    public final Object A0Z(AbstractC02280iQ r10, AbstractC02210iH r11) throws IOException, C03620oC {
        if (this._propertyBasedCreator != null) {
            return A0a(r10, r11);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.A09(r11, jsonDeserializer.A0A(r10, r11));
        }
        if (!this._beanType.A0I()) {
            AbstractC04300pk r0 = this._valueInstantiator;
            boolean A0J = r0.A0J();
            boolean A0K = r0.A0K();
            if (A0J || A0K) {
                Object obj = null;
                Object[] objArr = null;
                int i = 0;
                while (r10.A0i() != EnumC03640oE.END_OBJECT) {
                    String A0l = r10.A0l();
                    AbstractC01170Rz A00 = this._beanProperties.A00(A0l);
                    r10.A0j();
                    if (A00 != null) {
                        if (obj != null) {
                            A00.A08(r10, r11, obj);
                        } else {
                            if (objArr == null) {
                                int i2 = this._beanProperties._size;
                                objArr = new Object[(i2 + i2)];
                            }
                            int i3 = i + 1;
                            objArr[i] = A00;
                            i = i3 + 1;
                            objArr[i3] = A00.A05(r10, r11);
                        }
                    } else if (!"message".equals(A0l) || !A0J) {
                        HashSet<String> hashSet = this._ignorableProps;
                        if (hashSet == null || !hashSet.contains(A0l)) {
                            C04290pj r02 = this._anySetter;
                            if (r02 != null) {
                                r02.A01(r10, r11, obj, A0l);
                            } else {
                                A0N(r10, r11, obj, A0l);
                            }
                        } else {
                            r10.A0h();
                        }
                    } else {
                        obj = this._valueInstantiator.A0A(r11, r10.A0m());
                        if (objArr != null) {
                            for (int i4 = 0; i4 < i; i4 += 2) {
                                ((AbstractC01170Rz) objArr[i4]).A0A(obj, objArr[i4 + 1]);
                            }
                            objArr = null;
                        }
                    }
                    r10.A0j();
                }
                if (obj == null) {
                    AbstractC04300pk r03 = this._valueInstantiator;
                    if (A0J) {
                        obj = r03.A0A(r11, null);
                    } else {
                        obj = r03.A05(r11);
                    }
                    if (objArr != null) {
                        for (int i5 = 0; i5 < i; i5 += 2) {
                            ((AbstractC01170Rz) objArr[i5]).A0A(obj, objArr[i5 + 1]);
                        }
                    }
                }
                return obj;
            }
            StringBuilder sb = new StringBuilder("Can not deserialize Throwable of type ");
            sb.append(this._beanType);
            sb.append(" without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
            throw new C02180iD(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder("Can not instantiate abstract type ");
        sb2.append(this._beanType);
        sb2.append(" (need to add/enable type information?)");
        throw C02180iD.A00(r10, sb2.toString());
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.deser.BeanDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A09(AbstractC04870rR r3) {
        if (getClass() != ThrowableDeserializer.class) {
            return this;
        }
        return new ThrowableDeserializer(this, r3);
    }

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
        this._vanillaProcessing = false;
    }

    public ThrowableDeserializer(BeanDeserializer beanDeserializer, AbstractC04870rR r2) {
        super(beanDeserializer, r2);
    }
}
