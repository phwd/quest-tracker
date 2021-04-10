package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$CharacterDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Character> {
    public static final NumberDeserializers$CharacterDeserializer A00 = new NumberDeserializers$CharacterDeserializer(Character.class, 0);
    public static final NumberDeserializers$CharacterDeserializer A01 = new NumberDeserializers$CharacterDeserializer(Character.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Character A09(AnonymousClass0aT r5, AbstractC02570aK r6) throws IOException, C05910ld {
        char charAt;
        EnumC05930lf A0G = r5.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT) {
            int A06 = r5.A06();
            if (A06 >= 0 && A06 <= 65535) {
                charAt = (char) A06;
            }
            throw r6.A0C(this._valueClass, A0G);
        }
        if (A0G == EnumC05930lf.VALUE_STRING) {
            String A0P = r5.A0P();
            int length = A0P.length();
            if (length == 1) {
                charAt = A0P.charAt(0);
            } else if (length == 0) {
                return (Character) A08();
            }
        }
        throw r6.A0C(this._valueClass, A0G);
        return Character.valueOf(charAt);
    }

    public NumberDeserializers$CharacterDeserializer(Class<Character> cls, Character ch) {
        super(cls, ch);
    }
}
