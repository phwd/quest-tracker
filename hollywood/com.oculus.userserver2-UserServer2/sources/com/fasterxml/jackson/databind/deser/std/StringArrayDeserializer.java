package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass1V;
import X.AnonymousClass9r;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StringArrayDeserializer extends StdDeserializer<String[]> implements AnonymousClass1V {
    public static final StringArrayDeserializer A00 = new StringArrayDeserializer();
    public static final long serialVersionUID = -7589512013334920693L;
    public JsonDeserializer<String> _elementDeserializer = null;

    public StringArrayDeserializer() {
        super(String[].class);
    }

    private final void A00(Rn rn) throws IOException, AnonymousClass9r {
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        throw null;
    }
}
