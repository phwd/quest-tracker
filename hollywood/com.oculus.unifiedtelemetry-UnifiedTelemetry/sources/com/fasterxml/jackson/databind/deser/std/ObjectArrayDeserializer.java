package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0227Wo;
import X.AbstractC0232Ww;
import X.AnonymousClass78;
import X.C0223Wj;
import X.EnumC0225Wm;
import X.EnumC0470q2;
import X.J9;
import X.V4;
import X.Zy;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.lang.reflect.Array;

@JacksonStdImpl
public final class ObjectArrayDeserializer extends ContainerDeserializerBase<Object[]> implements Zy {
    public static final long serialVersionUID = 1;
    public final AnonymousClass78 _arrayType;
    public final Class<?> _elementClass;
    public JsonDeserializer<Object> _elementDeserializer;
    public final V4 _elementTypeDeserializer;
    public final boolean _untyped;

    public ObjectArrayDeserializer(AnonymousClass78 r4, JsonDeserializer<Object> jsonDeserializer, V4 v4) {
        super(Object[].class);
        this._arrayType = r4;
        Class<?> cls = r4.A03()._class;
        this._elementClass = cls;
        this._untyped = cls == Object.class;
        this._elementDeserializer = jsonDeserializer;
        this._elementTypeDeserializer = v4;
    }

    @Override // X.Zy
    public final JsonDeserializer<?> A1g(AbstractC0226Wn wn, AbstractC0227Wo wo) throws C0223Wj {
        JsonDeserializer<?> A05 = StdDeserializer.A05(wn, wo, this._elementDeserializer);
        if (A05 == null) {
            A05 = wn.A06(this._arrayType.A03(), wo);
        } else if (A05 instanceof Zy) {
            A05 = ((Zy) A05).A1g(wn, wo);
        }
        V4 v4 = this._elementTypeDeserializer;
        if (v4 != null) {
            v4 = v4.A04(wo);
        }
        if (A05 == this._elementDeserializer && v4 == v4) {
            return this;
        }
        return new ObjectArrayDeserializer(this._arrayType, A05, v4);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Object[] A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Object[] A03;
        Object A0C;
        Object[] objArr;
        if (!ww.A0F()) {
            EnumC0470q2 A0Z = ww.A0Z();
            EnumC0470q2 q2Var = EnumC0470q2.VALUE_STRING;
            Object obj = null;
            Byte[] bArr = null;
            if (!(A0Z == q2Var && wn.A0L(EnumC0225Wm.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && ww.A0d().length() == 0)) {
                if (wn.A0L(EnumC0225Wm.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    if (ww.A0Z() != EnumC0470q2.VALUE_NULL) {
                        V4 v4 = this._elementTypeDeserializer;
                        if (v4 == null) {
                            obj = this._elementDeserializer.A09(ww, wn);
                        } else {
                            obj = this._elementDeserializer.A0C(ww, wn, v4);
                        }
                    }
                    if (this._untyped) {
                        objArr = new Object[1];
                    } else {
                        objArr = (Object[]) Array.newInstance(this._elementClass, 1);
                    }
                    objArr[0] = obj;
                    return objArr;
                } else if (ww.A0Z() == q2Var && this._elementClass == Byte.class) {
                    byte[] A0i = ww.A0i(wn._config._base._defaultBase64);
                    int length = A0i.length;
                    bArr = new Byte[length];
                    for (int i = 0; i < length; i++) {
                        bArr[i] = Byte.valueOf(A0i[i]);
                    }
                } else {
                    throw wn.A08(this._arrayType._class);
                }
            }
            return bArr;
        }
        J9 A0H = wn.A0H();
        Object[] A01 = A0H.A01();
        V4 v42 = this._elementTypeDeserializer;
        int i2 = 0;
        while (true) {
            EnumC0470q2 A0a = ww.A0a();
            if (A0a == EnumC0470q2.END_ARRAY) {
                break;
            }
            if (A0a == EnumC0470q2.VALUE_NULL) {
                A0C = null;
            } else {
                JsonDeserializer<Object> jsonDeserializer = this._elementDeserializer;
                if (v42 == null) {
                    A0C = jsonDeserializer.A09(ww, wn);
                } else {
                    A0C = jsonDeserializer.A0C(ww, wn, v42);
                }
            }
            if (i2 >= A01.length) {
                A01 = A0H.A02(A01);
                i2 = 0;
            }
            A01[i2] = A0C;
            i2++;
        }
        if (this._untyped) {
            int i3 = A0H.A00 + i2;
            A03 = new Object[i3];
            J9.A00(A0H, A03, i3, A01, i2);
        } else {
            A03 = A0H.A03(A01, i2, this._elementClass);
        }
        wn.A0J(A0H);
        return A03;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return v4.A08(ww, wn);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._elementDeserializer;
    }
}
