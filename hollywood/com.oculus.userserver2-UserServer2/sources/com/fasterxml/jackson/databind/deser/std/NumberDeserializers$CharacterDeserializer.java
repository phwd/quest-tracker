package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$CharacterDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Character> {
    public static final NumberDeserializers$CharacterDeserializer A00 = new NumberDeserializers$CharacterDeserializer(Character.class, 0);
    public static final NumberDeserializers$CharacterDeserializer A01 = new NumberDeserializers$CharacterDeserializer(Character.TYPE, null);
    public static final long serialVersionUID = 1;

    private final Character A00(Rn rn) throws IOException, AnonymousClass9r {
        char charAt;
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.VALUE_NUMBER_INT) {
            int A002 = rn.A00();
            if (A002 < 0) {
                throw null;
            } else if (A002 <= 65535) {
                charAt = (char) A002;
            } else {
                throw null;
            }
        } else if (r1 == AnonymousClass9p.VALUE_STRING) {
            String A09 = rn.A09();
            int length = A09.length();
            if (length == 1) {
                charAt = A09.charAt(0);
            } else if (length == 0) {
                return (Character) A02();
            } else {
                throw null;
            }
        } else {
            throw null;
        }
        return Character.valueOf(charAt);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A00(rn);
    }

    public NumberDeserializers$CharacterDeserializer(Class<Character> cls, Character ch) {
        super(cls, ch);
    }
}
