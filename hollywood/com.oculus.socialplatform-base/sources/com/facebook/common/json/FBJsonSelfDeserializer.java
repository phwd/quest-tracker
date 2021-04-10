package com.facebook.common.json;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C00780Ix;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Throwables;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FBJsonSelfDeserializer extends FbJsonDeserializer {
    @Override // com.facebook.common.json.FbJsonDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException {
        try {
            A0F();
            throw new NullPointerException("deserialize");
        } catch (Exception e) {
            Throwables.propagateIfPossible(e, IOException.class);
            C00780Ix.A00(this.A00, r3, e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
