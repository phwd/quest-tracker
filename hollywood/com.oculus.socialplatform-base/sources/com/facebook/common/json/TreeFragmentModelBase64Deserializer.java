package com.facebook.common.json;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AnonymousClass0J4;
import X.C00780Ix;
import android.util.Base64;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public class TreeFragmentModelBase64Deserializer extends FbJsonDeserializer {
    public Class<Tree> A00;

    @Override // com.facebook.common.json.FbJsonDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    @Nullable
    public final Object A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException {
        try {
            String A0P = r3.A0P();
            if (A0P == null) {
                return null;
            }
            AnonymousClass0J4.A00(A0P);
            if (A0P.startsWith("type_tag:")) {
                A0P = A0P.substring(18);
            }
            ByteBuffer.wrap(Base64.decode(A0P, 2));
            StaticGraphServiceFactory.getTreeSerializer();
            throw new NullPointerException("deserializeTreeFromByteBuffer");
        } catch (Exception e) {
            Throwables.propagateIfPossible(e, IOException.class);
            C00780Ix.A00(this.A00, r3, e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Class<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public TreeFragmentModelBase64Deserializer(Class<?> cls) {
        this.A00 = cls;
    }
}
