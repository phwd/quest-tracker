package com.facebook.common.json;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.CH;
import X.CK;
import android.util.Base64;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.nio.ByteBuffer;

public class TreeFragmentModelBase64Deserializer extends FbJsonDeserializer {
    public final Class A00;

    @Override // com.facebook.common.json.FbJsonDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        try {
            String A0n = qiVar.A0n();
            if (A0n == null) {
                return null;
            }
            CK.A00(A0n);
            if (A0n.startsWith("type_tag:")) {
                A0n = A0n.substring(18);
            }
            ByteBuffer.wrap(Base64.decode(A0n, 2));
            StaticGraphServiceFactory.getTreeSerializer();
            throw new NullPointerException("deserializeTreeFromByteBuffer");
        } catch (Exception e) {
            Throwables.propagateIfPossible(e, IOException.class);
            CH.A00(this.A00, qiVar, e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
