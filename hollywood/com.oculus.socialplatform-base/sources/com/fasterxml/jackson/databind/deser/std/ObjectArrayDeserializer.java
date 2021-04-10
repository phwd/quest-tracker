package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02220iI;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04520qa;
import X.AnonymousClass0CA;
import X.AnonymousClass0rU;
import X.C02180iD;
import X.C03620oC;
import X.EnumC02200iG;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.lang.reflect.Array;

@JacksonStdImpl
public class ObjectArrayDeserializer extends ContainerDeserializerBase<Object[]> implements AbstractC04230pb {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0CA _arrayType;
    public final Class<?> _elementClass;
    public JsonDeserializer<Object> _elementDeserializer;
    public final AbstractC04520qa _elementTypeDeserializer;
    public final boolean _untyped;

    public ObjectArrayDeserializer(AnonymousClass0CA r4, JsonDeserializer<Object> jsonDeserializer, AbstractC04520qa r6) {
        super(Object[].class);
        this._arrayType = r4;
        Class<?> cls = r4.A04()._class;
        this._elementClass = cls;
        this._untyped = cls == Object.class;
        this._elementDeserializer = jsonDeserializer;
        this._elementTypeDeserializer = r6;
    }

    @Override // X.AbstractC04230pb
    public final JsonDeserializer<?> A2O(AbstractC02210iH r5, AbstractC02220iI r6) throws C02180iD {
        JsonDeserializer<?> A05 = StdDeserializer.A05(r5, r6, this._elementDeserializer);
        if (A05 == null) {
            A05 = r5.A09(this._arrayType.A04(), r6);
        } else if (A05 instanceof AbstractC04230pb) {
            A05 = ((AbstractC04230pb) A05).A2O(r5, r6);
        }
        AbstractC04520qa r2 = this._elementTypeDeserializer;
        if (r2 != null) {
            r2 = r2.A04(r6);
        }
        if (A05 == this._elementDeserializer && r2 == r2) {
            return this;
        }
        return new ObjectArrayDeserializer(this._arrayType, A05, r2);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Object[] A0A(AbstractC02280iQ r7, AbstractC02210iH r8) throws IOException, C03620oC {
        Object[] A03;
        Object A0B;
        Object[] objArr;
        if (!r7.A0K()) {
            EnumC03640oE A0i = r7.A0i();
            EnumC03640oE r1 = EnumC03640oE.VALUE_STRING;
            Object obj = null;
            Byte[] bArr = null;
            if (!(A0i == r1 && r8.A0P(EnumC02200iG.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0m().length() == 0)) {
                if (r8.A0P(EnumC02200iG.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    if (r7.A0i() != EnumC03640oE.VALUE_NULL) {
                        AbstractC04520qa r12 = this._elementTypeDeserializer;
                        if (r12 == null) {
                            obj = this._elementDeserializer.A0A(r7, r8);
                        } else {
                            obj = this._elementDeserializer.A0B(r7, r8, r12);
                        }
                    }
                    if (this._untyped) {
                        objArr = new Object[1];
                    } else {
                        objArr = (Object[]) Array.newInstance(this._elementClass, 1);
                    }
                    objArr[0] = obj;
                    return objArr;
                } else if (r7.A0i() == r1 && this._elementClass == Byte.class) {
                    byte[] A0r = r7.A0r(r8._config._base._defaultBase64);
                    int length = A0r.length;
                    bArr = new Byte[length];
                    for (int i = 0; i < length; i++) {
                        bArr[i] = Byte.valueOf(A0r[i]);
                    }
                } else {
                    throw r8.A0B(this._arrayType._class);
                }
            }
            return bArr;
        }
        AnonymousClass0rU A0L = r8.A0L();
        Object[] A01 = A0L.A01();
        AbstractC04520qa r5 = this._elementTypeDeserializer;
        int i2 = 0;
        while (true) {
            EnumC03640oE A0j = r7.A0j();
            if (A0j == EnumC03640oE.END_ARRAY) {
                break;
            }
            if (A0j == EnumC03640oE.VALUE_NULL) {
                A0B = null;
            } else {
                JsonDeserializer<Object> jsonDeserializer = this._elementDeserializer;
                if (r5 == null) {
                    A0B = jsonDeserializer.A0A(r7, r8);
                } else {
                    A0B = jsonDeserializer.A0B(r7, r8, r5);
                }
            }
            if (i2 >= A01.length) {
                A01 = A0L.A02(A01);
                i2 = 0;
            }
            A01[i2] = A0B;
            i2++;
        }
        if (this._untyped) {
            int i3 = A0L.A00 + i2;
            A03 = new Object[i3];
            AnonymousClass0rU.A00(A0L, A03, i3, A01, i2);
        } else {
            A03 = A0L.A03(A01, i2, this._elementClass);
        }
        r8.A0N(A0L);
        return A03;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return r4.A08(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._elementDeserializer;
    }
}
