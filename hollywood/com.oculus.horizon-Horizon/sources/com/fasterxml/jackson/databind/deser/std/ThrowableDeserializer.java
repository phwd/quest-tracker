package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AbstractC06410n2;
import X.AnonymousClass0HD;
import X.AnonymousClass0jg;
import X.AnonymousClass0lG;
import X.C03990gZ;
import X.C05480lF;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import java.io.IOException;
import java.util.HashSet;

public final class ThrowableDeserializer extends BeanDeserializer {
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.deser.BeanDeserializer
    public final Object A0Z(AbstractC04100gp r10, AbstractC04020gg r11) throws IOException, AnonymousClass0jg {
        if (this._propertyBasedCreator != null) {
            return A0a(r10, r11);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.A09(r11, jsonDeserializer.A09(r10, r11));
        }
        if (!this._beanType.A0E()) {
            AnonymousClass0lG r0 = this._valueInstantiator;
            boolean A0J = r0.A0J();
            boolean A0K = r0.A0K();
            if (A0J || A0K) {
                Object obj = null;
                Object[] objArr = null;
                int i = 0;
                while (r10.A0a() != EnumC04820ji.END_OBJECT) {
                    String A0d = r10.A0d();
                    AnonymousClass0HD A00 = this._beanProperties.A00(A0d);
                    r10.A0b();
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
                    } else if (!"message".equals(A0d) || !A0J) {
                        HashSet<String> hashSet = this._ignorableProps;
                        if (hashSet == null || !hashSet.contains(A0d)) {
                            C05480lF r02 = this._anySetter;
                            if (r02 != null) {
                                r02.A01(r10, r11, obj, A0d);
                            } else {
                                A0N(r10, r11, obj, A0d);
                            }
                        } else {
                            r10.A0Z();
                        }
                    } else {
                        obj = this._valueInstantiator.A0A(r11, r10.A0e());
                        if (objArr != null) {
                            for (int i4 = 0; i4 < i; i4 += 2) {
                                ((AnonymousClass0HD) objArr[i4]).A0A(obj, objArr[i4 + 1]);
                            }
                            objArr = null;
                        }
                    }
                    r10.A0b();
                }
                if (obj == null) {
                    AnonymousClass0lG r03 = this._valueInstantiator;
                    if (A0J) {
                        obj = r03.A0A(r11, null);
                    } else {
                        obj = r03.A05(r11);
                    }
                    if (objArr != null) {
                        for (int i5 = 0; i5 < i; i5 += 2) {
                            ((AnonymousClass0HD) objArr[i5]).A0A(obj, objArr[i5 + 1]);
                        }
                    }
                }
                return obj;
            }
            StringBuilder sb = new StringBuilder("Can not deserialize Throwable of type ");
            sb.append(this._beanType);
            sb.append(" without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
            throw new C03990gZ(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder("Can not instantiate abstract type ");
        sb2.append(this._beanType);
        sb2.append(" (need to add/enable type information?)");
        throw C03990gZ.A00(r10, sb2.toString());
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.deser.BeanDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public final JsonDeserializer<Object> A0B(AbstractC06410n2 r3) {
        if (getClass() != ThrowableDeserializer.class) {
            return this;
        }
        return new ThrowableDeserializer(this, r3);
    }

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
        this._vanillaProcessing = false;
    }

    public ThrowableDeserializer(BeanDeserializer beanDeserializer, AbstractC06410n2 r2) {
        super(beanDeserializer, r2);
    }
}
