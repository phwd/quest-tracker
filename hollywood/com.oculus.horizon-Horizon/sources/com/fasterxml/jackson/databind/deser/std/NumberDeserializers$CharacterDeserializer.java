package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$CharacterDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Character> {
    public static final NumberDeserializers$CharacterDeserializer A00 = new NumberDeserializers$CharacterDeserializer(Character.class, 0);
    public static final NumberDeserializers$CharacterDeserializer A01 = new NumberDeserializers$CharacterDeserializer(Character.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Character A09(AbstractC04100gp r5, AbstractC04020gg r6) throws IOException, AnonymousClass0jg {
        char charAt;
        EnumC04820ji A0a = r5.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT) {
            int A0M = r5.A0M();
            if (A0M >= 0 && A0M <= 65535) {
                charAt = (char) A0M;
            }
            throw r6.A07(this._valueClass, A0a);
        }
        if (A0a == EnumC04820ji.VALUE_STRING) {
            String A0e = r5.A0e();
            int length = A0e.length();
            if (length == 1) {
                charAt = A0e.charAt(0);
            } else if (length == 0) {
                return (Character) A08();
            }
        }
        throw r6.A07(this._valueClass, A0a);
        return Character.valueOf(charAt);
    }

    public NumberDeserializers$CharacterDeserializer(Class<Character> cls, Character ch) {
        super(cls, ch);
    }
}
