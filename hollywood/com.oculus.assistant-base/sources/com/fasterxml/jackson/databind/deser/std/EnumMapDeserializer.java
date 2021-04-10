package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.EnumC1023qs;
import X.NX;
import X.O5;
import X.PR;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.util.EnumMap;

public class EnumMapDeserializer extends StdDeserializer implements AbstractC0264Od {
    public static final long serialVersionUID = 1518773374647478964L;
    public final Class _enumClass;
    public JsonDeserializer _keyDeserializer;
    public final AbstractC1024qt _mapType;
    public JsonDeserializer _valueDeserializer;
    public final PR _valueTypeDeserializer;

    public EnumMapDeserializer(AbstractC1024qt qtVar, JsonDeserializer jsonDeserializer, JsonDeserializer jsonDeserializer2, PR pr) {
        super(EnumMap.class);
        this._mapType = qtVar;
        this._enumClass = qtVar.A05()._class;
        this._keyDeserializer = jsonDeserializer;
        this._valueDeserializer = jsonDeserializer2;
        this._valueTypeDeserializer = pr;
    }

    @Override // X.AbstractC0264Od
    public final JsonDeserializer A1X(AbstractC1022qr qrVar, O5 o5) {
        JsonDeserializer jsonDeserializer = this._keyDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = qrVar.A08(this._mapType.A05(), o5);
        }
        JsonDeserializer jsonDeserializer2 = this._valueDeserializer;
        if (jsonDeserializer2 == null) {
            jsonDeserializer2 = qrVar.A08(this._mapType.A04(), o5);
        } else if (jsonDeserializer2 instanceof AbstractC0264Od) {
            jsonDeserializer2 = ((AbstractC0264Od) jsonDeserializer2).A1X(qrVar, o5);
        }
        PR pr = this._valueTypeDeserializer;
        if (pr != null) {
            pr = pr.A03(o5);
        }
        if (jsonDeserializer == this._keyDeserializer && jsonDeserializer2 == this._valueDeserializer && pr == pr) {
            return this;
        }
        return new EnumMapDeserializer(this._mapType, jsonDeserializer, jsonDeserializer2, pr);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final EnumMap A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        if (qiVar.A0U() == NX.START_OBJECT) {
            EnumMap enumMap = new EnumMap(this._enumClass);
            JsonDeserializer jsonDeserializer = this._valueDeserializer;
            PR pr = this._valueTypeDeserializer;
            while (qiVar.A0o() != NX.END_OBJECT) {
                Enum r3 = (Enum) this._keyDeserializer.A0C(qiVar, qrVar);
                Object obj = null;
                if (r3 != null) {
                    if (qiVar.A0o() != NX.VALUE_NULL) {
                        if (pr == null) {
                            obj = jsonDeserializer.A0C(qiVar, qrVar);
                        } else {
                            obj = jsonDeserializer.A09(qiVar, qrVar, pr);
                        }
                    }
                    enumMap.put(r3, obj);
                } else if (!qrVar.A0O(EnumC1023qs.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    try {
                        if (qiVar.A0g()) {
                            qiVar.A0p();
                        }
                    } catch (Exception unused) {
                    }
                    qrVar.A0L(this._enumClass);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } else {
                    qiVar.A0o();
                    qiVar.A0T();
                }
            }
            return enumMap;
        }
        qrVar.A0J();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
