package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04030gh;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AnonymousClass076;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.C03990gZ;
import X.C06440n5;
import X.EnumC04010gf;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.lang.reflect.Array;

@JacksonStdImpl
public final class ObjectArrayDeserializer extends ContainerDeserializerBase<Object[]> implements AbstractC05430l6 {
    public static final long serialVersionUID = 1;
    public final AnonymousClass076 _arrayType;
    public final Class<?> _elementClass;
    public JsonDeserializer<Object> _elementDeserializer;
    public final AnonymousClass0m9 _elementTypeDeserializer;
    public final boolean _untyped;

    public ObjectArrayDeserializer(AnonymousClass076 r4, JsonDeserializer<Object> jsonDeserializer, AnonymousClass0m9 r6) {
        super(Object[].class);
        this._arrayType = r4;
        Class<?> cls = r4.A03()._class;
        this._elementClass = cls;
        this._untyped = cls == Object.class;
        this._elementDeserializer = jsonDeserializer;
        this._elementTypeDeserializer = r6;
    }

    @Override // X.AbstractC05430l6
    public final JsonDeserializer<?> A21(AbstractC04020gg r5, AbstractC04030gh r6) throws C03990gZ {
        JsonDeserializer<?> A05 = StdDeserializer.A05(r5, r6, this._elementDeserializer);
        if (A05 == null) {
            A05 = r5.A05(this._arrayType.A03(), r6);
        } else if (A05 instanceof AbstractC05430l6) {
            A05 = ((AbstractC05430l6) A05).A21(r5, r6);
        }
        AnonymousClass0m9 r2 = this._elementTypeDeserializer;
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
    public final Object[] A09(AbstractC04100gp r7, AbstractC04020gg r8) throws IOException, AnonymousClass0jg {
        Object[] A03;
        Object A0C;
        Object[] objArr;
        if (!r7.A0G()) {
            EnumC04820ji A0a = r7.A0a();
            EnumC04820ji r1 = EnumC04820ji.VALUE_STRING;
            Object obj = null;
            Byte[] bArr = null;
            if (!(A0a == r1 && r8.A0I(EnumC04010gf.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0e().length() == 0)) {
                if (r8.A0I(EnumC04010gf.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    if (r7.A0a() != EnumC04820ji.VALUE_NULL) {
                        AnonymousClass0m9 r12 = this._elementTypeDeserializer;
                        if (r12 == null) {
                            obj = this._elementDeserializer.A09(r7, r8);
                        } else {
                            obj = this._elementDeserializer.A0C(r7, r8, r12);
                        }
                    }
                    if (this._untyped) {
                        objArr = new Object[1];
                    } else {
                        objArr = (Object[]) Array.newInstance(this._elementClass, 1);
                    }
                    objArr[0] = obj;
                    return objArr;
                } else if (r7.A0a() == r1 && this._elementClass == Byte.class) {
                    byte[] A0j = r7.A0j(r8._config._base._defaultBase64);
                    int length = A0j.length;
                    bArr = new Byte[length];
                    for (int i = 0; i < length; i++) {
                        bArr[i] = Byte.valueOf(A0j[i]);
                    }
                } else {
                    throw null;
                }
            }
            return bArr;
        }
        C06440n5 A0D = r8.A0D();
        Object[] A01 = A0D.A01();
        AnonymousClass0m9 r5 = this._elementTypeDeserializer;
        int i2 = 0;
        while (true) {
            EnumC04820ji A0b = r7.A0b();
            if (A0b == EnumC04820ji.END_ARRAY) {
                break;
            }
            if (A0b == EnumC04820ji.VALUE_NULL) {
                A0C = null;
            } else {
                JsonDeserializer<Object> jsonDeserializer = this._elementDeserializer;
                if (r5 == null) {
                    A0C = jsonDeserializer.A09(r7, r8);
                } else {
                    A0C = jsonDeserializer.A0C(r7, r8, r5);
                }
            }
            if (i2 >= A01.length) {
                A01 = A0D.A02(A01);
                i2 = 0;
            }
            A01[i2] = A0C;
            i2++;
        }
        if (this._untyped) {
            int i3 = A0D.A00 + i2;
            A03 = new Object[i3];
            C06440n5.A00(A0D, A03, i3, A01, i2);
        } else {
            A03 = A0D.A03(A01, i2, this._elementClass);
        }
        r8.A0F(A0D);
        return A03;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return r4.A08(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._elementDeserializer;
    }
}
