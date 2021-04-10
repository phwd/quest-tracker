package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.C0683fI;
import X.EnumC1023qs;
import X.NX;
import X.O5;
import X.PR;
import X.QE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.lang.reflect.Array;

@JacksonStdImpl
public class ObjectArrayDeserializer extends ContainerDeserializerBase implements AbstractC0264Od {
    public static final long serialVersionUID = 1;
    public final C0683fI _arrayType;
    public final Class _elementClass;
    public JsonDeserializer _elementDeserializer;
    public final PR _elementTypeDeserializer;
    public final boolean _untyped;

    public ObjectArrayDeserializer(C0683fI fIVar, JsonDeserializer jsonDeserializer, PR pr) {
        super(Object[].class);
        this._arrayType = fIVar;
        Class cls = fIVar.A04()._class;
        this._elementClass = cls;
        this._untyped = cls == Object.class;
        this._elementDeserializer = jsonDeserializer;
        this._elementTypeDeserializer = pr;
    }

    @Override // X.AbstractC0264Od
    public final JsonDeserializer A1X(AbstractC1022qr qrVar, O5 o5) {
        JsonDeserializer A05 = StdDeserializer.A05(qrVar, o5, this._elementDeserializer);
        if (A05 == null) {
            A05 = qrVar.A08(this._arrayType.A04(), o5);
        } else if (A05 instanceof AbstractC0264Od) {
            A05 = ((AbstractC0264Od) A05).A1X(qrVar, o5);
        }
        PR pr = this._elementTypeDeserializer;
        if (pr != null) {
            pr = pr.A03(o5);
        }
        if (A05 == this._elementDeserializer && pr == pr) {
            return this;
        }
        return new ObjectArrayDeserializer(this._arrayType, A05, pr);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Object[] A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object[] A03;
        Object A09;
        Object[] objArr;
        if (!qiVar.A0i()) {
            NX A0U = qiVar.A0U();
            NX nx = NX.VALUE_STRING;
            Object obj = null;
            Byte[] bArr = null;
            if (!(A0U == nx && qrVar.A0O(EnumC1023qs.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && qiVar.A0p().length() == 0)) {
                if (qrVar.A0O(EnumC1023qs.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    if (qiVar.A0U() != NX.VALUE_NULL) {
                        PR pr = this._elementTypeDeserializer;
                        if (pr == null) {
                            obj = this._elementDeserializer.A0C(qiVar, qrVar);
                        } else {
                            obj = this._elementDeserializer.A09(qiVar, qrVar, pr);
                        }
                    }
                    if (this._untyped) {
                        objArr = new Object[1];
                    } else {
                        objArr = (Object[]) Array.newInstance(this._elementClass, 1);
                    }
                    objArr[0] = obj;
                    return objArr;
                } else if (qiVar.A0U() == nx && this._elementClass == Byte.class) {
                    byte[] A0l = qiVar.A0l(qrVar._config._base._defaultBase64);
                    int length = A0l.length;
                    bArr = new Byte[length];
                    for (int i = 0; i < length; i++) {
                        bArr[i] = Byte.valueOf(A0l[i]);
                    }
                } else {
                    qrVar.A0J();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            return bArr;
        }
        QE A0H = qrVar.A0H();
        Object[] A01 = A0H.A01();
        PR pr2 = this._elementTypeDeserializer;
        int i2 = 0;
        while (true) {
            NX A0o = qiVar.A0o();
            if (A0o == NX.END_ARRAY) {
                break;
            }
            if (A0o == NX.VALUE_NULL) {
                A09 = null;
            } else {
                JsonDeserializer jsonDeserializer = this._elementDeserializer;
                if (pr2 == null) {
                    A09 = jsonDeserializer.A0C(qiVar, qrVar);
                } else {
                    A09 = jsonDeserializer.A09(qiVar, qrVar, pr2);
                }
            }
            if (i2 >= A01.length) {
                A01 = A0H.A02(A01);
                i2 = 0;
            }
            A01[i2] = A09;
            i2++;
        }
        if (this._untyped) {
            int i3 = A0H.A00 + i2;
            A03 = new Object[i3];
            QE.A00(A0H, A03, i3, A01, i2);
        } else {
            A03 = A0H.A03(A01, i2, this._elementClass);
        }
        qrVar.A0K(A0H);
        return A03;
    }
}
