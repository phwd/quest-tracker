package com.facebook.common.json;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.CH;
import com.google.common.base.Throwables;
import java.io.IOException;

public class FBJsonSelfDeserializer extends FbJsonDeserializer {
    @Override // com.facebook.common.json.FbJsonDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        try {
            A0E();
            throw new NullPointerException("deserialize");
        } catch (Exception e) {
            Throwables.propagateIfPossible(e, IOException.class);
            CH.A00(this.A00, qiVar, e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
