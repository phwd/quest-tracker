package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.NX;
import X.O5;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.util.EnumSet;

public class EnumSetDeserializer extends StdDeserializer implements AbstractC0264Od {
    public static final long serialVersionUID = 3479455075597887177L;
    public final Class _enumClass;
    public JsonDeserializer _enumDeserializer;
    public final AbstractC1024qt _enumType;

    public EnumSetDeserializer(AbstractC1024qt qtVar, JsonDeserializer jsonDeserializer) {
        super(EnumSet.class);
        this._enumType = qtVar;
        this._enumClass = qtVar._class;
        this._enumDeserializer = jsonDeserializer;
    }

    @Override // X.AbstractC0264Od
    public final JsonDeserializer A1X(AbstractC1022qr qrVar, O5 o5) {
        JsonDeserializer jsonDeserializer = this._enumDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = qrVar.A08(this._enumType, o5);
        } else if (jsonDeserializer instanceof AbstractC0264Od) {
            jsonDeserializer = ((AbstractC0264Od) jsonDeserializer).A1X(qrVar, o5);
        }
        if (this._enumDeserializer == jsonDeserializer) {
            return this;
        }
        return new EnumSetDeserializer(this._enumType, jsonDeserializer);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final EnumSet A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        if (qiVar.A0i()) {
            EnumSet noneOf = EnumSet.noneOf(this._enumClass);
            while (true) {
                NX A0o = qiVar.A0o();
                if (A0o == NX.END_ARRAY) {
                    return noneOf;
                }
                if (A0o != NX.VALUE_NULL) {
                    Object A0C = this._enumDeserializer.A0C(qiVar, qrVar);
                    if (A0C != null) {
                        noneOf.add(A0C);
                    }
                } else {
                    qrVar.A0J();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else {
            qrVar.A0J();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
