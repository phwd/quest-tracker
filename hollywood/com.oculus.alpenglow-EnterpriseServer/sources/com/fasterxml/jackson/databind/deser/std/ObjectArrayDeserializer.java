package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AbstractC02580aL;
import X.AbstractC06520n2;
import X.AnonymousClass0CC;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import X.C07230oy;
import X.EnumC02560aJ;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.lang.reflect.Array;

@JacksonStdImpl
public final class ObjectArrayDeserializer extends ContainerDeserializerBase<Object[]> implements AbstractC06520n2 {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0CC _arrayType;
    public final Class<?> _elementClass;
    public JsonDeserializer<Object> _elementDeserializer;
    public final AnonymousClass0o3 _elementTypeDeserializer;
    public final boolean _untyped;

    public ObjectArrayDeserializer(AnonymousClass0CC r4, JsonDeserializer<Object> jsonDeserializer, AnonymousClass0o3 r6) {
        super(Object[].class);
        this._arrayType = r4;
        Class<?> cls = r4.A04()._class;
        this._elementClass = cls;
        this._untyped = cls == Object.class;
        this._elementDeserializer = jsonDeserializer;
        this._elementTypeDeserializer = r6;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._elementDeserializer;
    }

    @Override // X.AbstractC06520n2
    public final JsonDeserializer<?> A1w(AbstractC02570aK r5, AbstractC02580aL r6) throws AnonymousClass0aG {
        JsonDeserializer<?> A05 = StdDeserializer.A05(r5, r6, this._elementDeserializer);
        if (A05 == null) {
            A05 = r5.A09(this._arrayType.A04(), r6);
        } else if (A05 instanceof AbstractC06520n2) {
            A05 = ((AbstractC06520n2) A05).A1w(r5, r6);
        }
        AnonymousClass0o3 r2 = this._elementTypeDeserializer;
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
    public final Object[] A09(AnonymousClass0aT r7, AbstractC02570aK r8) throws IOException, C05910ld {
        Object[] A03;
        Object A0C;
        Object[] objArr;
        if (!r7.A0V()) {
            EnumC05930lf A0G = r7.A0G();
            EnumC05930lf r1 = EnumC05930lf.VALUE_STRING;
            Object obj = null;
            Byte[] bArr = null;
            if (!(A0G == r1 && r8.A0O(EnumC02560aJ.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0P().length() == 0)) {
                if (r8.A0O(EnumC02560aJ.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    if (r7.A0G() != EnumC05930lf.VALUE_NULL) {
                        AnonymousClass0o3 r12 = this._elementTypeDeserializer;
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
                } else if (r7.A0G() == r1 && this._elementClass == Byte.class) {
                    byte[] A0Y = r7.A0Y(r8._config._base._defaultBase64);
                    int length = A0Y.length;
                    bArr = new Byte[length];
                    for (int i = 0; i < length; i++) {
                        bArr[i] = Byte.valueOf(A0Y[i]);
                    }
                } else {
                    throw r8.A0B(this._arrayType._class);
                }
            }
            return bArr;
        }
        C07230oy A0K = r8.A0K();
        Object[] A01 = A0K.A01();
        AnonymousClass0o3 r5 = this._elementTypeDeserializer;
        int i2 = 0;
        while (true) {
            EnumC05930lf A0a = r7.A0a();
            if (A0a == EnumC05930lf.END_ARRAY) {
                break;
            }
            if (A0a == EnumC05930lf.VALUE_NULL) {
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
                A01 = A0K.A02(A01);
                i2 = 0;
            }
            A01[i2] = A0C;
            i2++;
        }
        if (this._untyped) {
            int i3 = A0K.A00 + i2;
            A03 = new Object[i3];
            C07230oy.A00(A0K, A03, i3, A01, i2);
        } else {
            A03 = A0K.A03(A01, i2, this._elementClass);
        }
        r8.A0M(A0K);
        return A03;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return r4.A08(r2, r3);
    }
}
