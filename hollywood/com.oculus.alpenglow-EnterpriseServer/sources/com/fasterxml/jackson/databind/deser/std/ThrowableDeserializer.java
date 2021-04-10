package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC01680Ku;
import X.AbstractC02570aK;
import X.AbstractC07200ov;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.AnonymousClass0nB;
import X.C05910ld;
import X.C06560nA;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import java.io.IOException;
import java.util.HashSet;

public final class ThrowableDeserializer extends BeanDeserializer {
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.deser.BeanDeserializer
    public final Object A0Z(AnonymousClass0aT r10, AbstractC02570aK r11) throws IOException, C05910ld {
        if (this._propertyBasedCreator != null) {
            return A0a(r10, r11);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.A09(r11, jsonDeserializer.A09(r10, r11));
        }
        if (!this._beanType.A0I()) {
            AnonymousClass0nB r0 = this._valueInstantiator;
            boolean A0J = r0.A0J();
            boolean A0K = r0.A0K();
            if (A0J || A0K) {
                Object obj = null;
                Object[] objArr = null;
                int i = 0;
                while (r10.A0G() != EnumC05930lf.END_OBJECT) {
                    String A0O = r10.A0O();
                    AbstractC01680Ku A00 = this._beanProperties.A00(A0O);
                    r10.A0a();
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
                    } else if (!"message".equals(A0O) || !A0J) {
                        HashSet<String> hashSet = this._ignorableProps;
                        if (hashSet == null || !hashSet.contains(A0O)) {
                            C06560nA r02 = this._anySetter;
                            if (r02 != null) {
                                r02.A01(r10, r11, obj, A0O);
                            } else {
                                A0N(r10, r11, obj, A0O);
                            }
                        } else {
                            r10.A0F();
                        }
                    } else {
                        obj = this._valueInstantiator.A0A(r11, r10.A0P());
                        if (objArr != null) {
                            for (int i4 = 0; i4 < i; i4 += 2) {
                                ((AbstractC01680Ku) objArr[i4]).A0A(obj, objArr[i4 + 1]);
                            }
                            objArr = null;
                        }
                    }
                    r10.A0a();
                }
                if (obj == null) {
                    AnonymousClass0nB r03 = this._valueInstantiator;
                    if (A0J) {
                        obj = r03.A0A(r11, null);
                    } else {
                        obj = r03.A05(r11);
                    }
                    if (objArr != null) {
                        for (int i5 = 0; i5 < i; i5 += 2) {
                            ((AbstractC01680Ku) objArr[i5]).A0A(obj, objArr[i5 + 1]);
                        }
                    }
                }
                return obj;
            }
            throw new AnonymousClass0aG("Can not deserialize Throwable of type " + this._beanType + " without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
        }
        throw AnonymousClass0aG.A00(r10, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.deser.BeanDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A0B(AbstractC07200ov r3) {
        if (getClass() != ThrowableDeserializer.class) {
            return this;
        }
        return new ThrowableDeserializer(this, r3);
    }

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
        this._vanillaProcessing = false;
    }

    public ThrowableDeserializer(BeanDeserializer beanDeserializer, AbstractC07200ov r2) {
        super(beanDeserializer, r2);
    }
}
