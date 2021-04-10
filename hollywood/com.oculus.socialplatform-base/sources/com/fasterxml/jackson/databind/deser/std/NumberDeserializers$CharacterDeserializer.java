package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$CharacterDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Character> {
    public static final NumberDeserializers$CharacterDeserializer A00 = new NumberDeserializers$CharacterDeserializer(Character.class, 0);
    public static final NumberDeserializers$CharacterDeserializer A01 = new NumberDeserializers$CharacterDeserializer(Character.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Character A0A(AbstractC02280iQ r5, AbstractC02210iH r6) throws IOException, C03620oC {
        char charAt;
        EnumC03640oE A0i = r5.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT) {
            int A0T = r5.A0T();
            if (A0T >= 0 && A0T <= 65535) {
                charAt = (char) A0T;
            }
            throw r6.A0C(this._valueClass, A0i);
        }
        if (A0i == EnumC03640oE.VALUE_STRING) {
            String A0m = r5.A0m();
            int length = A0m.length();
            if (length == 1) {
                charAt = A0m.charAt(0);
            } else if (length == 0) {
                return (Character) A08();
            }
        }
        throw r6.A0C(this._valueClass, A0i);
        return Character.valueOf(charAt);
    }

    public NumberDeserializers$CharacterDeserializer(Class<Character> cls, Character ch) {
        super(cls, ch);
    }
}
