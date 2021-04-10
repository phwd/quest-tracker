package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.NX;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class NumberDeserializers$CharacterDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer {
    public static final NumberDeserializers$CharacterDeserializer A00 = new NumberDeserializers$CharacterDeserializer(Character.class, 0);
    public static final NumberDeserializers$CharacterDeserializer A01 = new NumberDeserializers$CharacterDeserializer(Character.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Character A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        char charAt;
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT) {
            int A0J = qiVar.A0J();
            if (A0J >= 0 && A0J <= 65535) {
                charAt = (char) A0J;
            }
            throw qrVar.A0A(this._valueClass, A0U);
        }
        if (A0U == NX.VALUE_STRING) {
            String A0p = qiVar.A0p();
            int length = A0p.length();
            if (length == 1) {
                charAt = A0p.charAt(0);
            } else if (length == 0) {
                return (Character) A08();
            }
        }
        throw qrVar.A0A(this._valueClass, A0U);
        return Character.valueOf(charAt);
    }

    public NumberDeserializers$CharacterDeserializer(Class cls, Character ch) {
        super(cls, ch);
    }
}
