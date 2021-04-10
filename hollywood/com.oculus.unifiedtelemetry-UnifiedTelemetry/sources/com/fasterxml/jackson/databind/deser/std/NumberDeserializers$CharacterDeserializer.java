package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.EnumC0470q2;
import X.q0;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$CharacterDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Character> {
    public static final NumberDeserializers$CharacterDeserializer A00 = new NumberDeserializers$CharacterDeserializer(Character.class, 0);
    public static final NumberDeserializers$CharacterDeserializer A01 = new NumberDeserializers$CharacterDeserializer(Character.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Character A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        char charAt;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT) {
            int A0L = ww.A0L();
            if (A0L >= 0 && A0L <= 65535) {
                charAt = (char) A0L;
            }
            throw wn.A09(this._valueClass, A0Z);
        }
        if (A0Z == EnumC0470q2.VALUE_STRING) {
            String A0d = ww.A0d();
            int length = A0d.length();
            if (length == 1) {
                charAt = A0d.charAt(0);
            } else if (length == 0) {
                return (Character) A08();
            }
        }
        throw wn.A09(this._valueClass, A0Z);
        return Character.valueOf(charAt);
    }

    public NumberDeserializers$CharacterDeserializer(Class<Character> cls, Character ch) {
        super(cls, ch);
    }
}
