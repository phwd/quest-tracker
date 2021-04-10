package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0073Cr;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AbstractC0262Ym;
import X.C0223Wj;
import X.C0263Yn;
import X.EnumC0470q2;
import X.KI;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import java.io.IOException;
import java.util.HashSet;

public final class ThrowableDeserializer extends BeanDeserializer {
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.deser.BeanDeserializer
    public final Object A0Z(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        if (this._propertyBasedCreator != null) {
            return A0a(ww, wn);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.A09(wn, jsonDeserializer.A09(ww, wn));
        }
        if (!this._beanType.A0E()) {
            AbstractC0262Ym ym = this._valueInstantiator;
            boolean A0J = ym.A0J();
            boolean A0K = ym.A0K();
            if (A0J || A0K) {
                Object obj = null;
                Object[] objArr = null;
                int i = 0;
                while (ww.A0Z() != EnumC0470q2.END_OBJECT) {
                    String A0c = ww.A0c();
                    AbstractC0073Cr A00 = this._beanProperties.A00(A0c);
                    ww.A0a();
                    if (A00 != null) {
                        if (obj != null) {
                            A00.A08(ww, wn, obj);
                        } else {
                            if (objArr == null) {
                                int i2 = this._beanProperties._size;
                                objArr = new Object[(i2 + i2)];
                            }
                            int i3 = i + 1;
                            objArr[i] = A00;
                            i = i3 + 1;
                            objArr[i3] = A00.A05(ww, wn);
                        }
                    } else if (!"message".equals(A0c) || !A0J) {
                        HashSet<String> hashSet = this._ignorableProps;
                        if (hashSet == null || !hashSet.contains(A0c)) {
                            C0263Yn yn = this._anySetter;
                            if (yn != null) {
                                yn.A01(ww, wn, obj, A0c);
                            } else {
                                A0N(ww, wn, obj, A0c);
                            }
                        } else {
                            ww.A0Y();
                        }
                    } else {
                        obj = this._valueInstantiator.A0A(wn, ww.A0d());
                        if (objArr != null) {
                            for (int i4 = 0; i4 < i; i4 += 2) {
                                ((AbstractC0073Cr) objArr[i4]).A0A(obj, objArr[i4 + 1]);
                            }
                            objArr = null;
                        }
                    }
                    ww.A0a();
                }
                if (obj == null) {
                    AbstractC0262Ym ym2 = this._valueInstantiator;
                    if (A0J) {
                        obj = ym2.A0A(wn, null);
                    } else {
                        obj = ym2.A05(wn);
                    }
                    if (objArr != null) {
                        for (int i5 = 0; i5 < i; i5 += 2) {
                            ((AbstractC0073Cr) objArr[i5]).A0A(obj, objArr[i5 + 1]);
                        }
                    }
                }
                return obj;
            }
            StringBuilder sb = new StringBuilder("Can not deserialize Throwable of type ");
            sb.append(this._beanType);
            sb.append(" without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
            throw new C0223Wj(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder("Can not instantiate abstract type ");
        sb2.append(this._beanType);
        sb2.append(" (need to add/enable type information?)");
        throw C0223Wj.A00(ww, sb2.toString());
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.deser.BeanDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A0B(KI ki) {
        if (getClass() != ThrowableDeserializer.class) {
            return this;
        }
        return new ThrowableDeserializer(this, ki);
    }

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
        this._vanillaProcessing = false;
    }

    public ThrowableDeserializer(BeanDeserializer beanDeserializer, KI ki) {
        super(beanDeserializer, ki);
    }
}
