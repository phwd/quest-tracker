package com.facebook.common.json;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AnonymousClass0J4;
import X.AnonymousClass0NO;
import X.AnonymousClass0NX;
import X.C00780Ix;
import android.util.Base64;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nullable;

public class TypeModelBase64Deserializer extends FbJsonDeserializer {
    @Override // com.facebook.common.json.FbJsonDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    @Nullable
    public final Object A0A(AbstractC02280iQ r8, AbstractC02210iH r9) throws IOException {
        int position;
        try {
            String A0P = r8.A0P();
            if (A0P != null) {
                if (A0P.startsWith("fltb:")) {
                    String replaceFirst = A0P.replaceFirst("fltb:", "");
                    int A00 = AnonymousClass0J4.A00(replaceFirst);
                    if (replaceFirst != null && replaceFirst.startsWith("type_tag:")) {
                        replaceFirst = replaceFirst.substring(18);
                    }
                    try {
                        this.A00.getDeclaredConstructor(Integer.TYPE, int[].class).newInstance(Integer.valueOf(A00), null);
                        try {
                            ByteBuffer byteBuffer = new AnonymousClass0NX(ByteBuffer.wrap(Base64.decode(replaceFirst, 2))).A01;
                            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                            synchronized (byteBuffer) {
                                position = byteBuffer.position();
                            }
                            if (byteBuffer.getInt(position) + position > 0) {
                                try {
                                    throw new NullPointerException("initFromMutableFlatBuffer");
                                } catch (IndexOutOfBoundsException e) {
                                    throw new AnonymousClass0NO(e);
                                }
                            }
                        } catch (Exception e2) {
                            throw new AnonymousClass0NO(e2);
                        }
                    } catch (Exception e3) {
                        throw new RuntimeException("Can't create model object", e3);
                    }
                } else {
                    Preconditions.checkState(A0P.startsWith("tree:"));
                    String replaceFirst2 = A0P.replaceFirst("tree:", "");
                    AnonymousClass0J4.A00(replaceFirst2);
                    if (replaceFirst2 != null && replaceFirst2.startsWith("type_tag:")) {
                        replaceFirst2 = replaceFirst2.substring(18);
                    }
                    ByteBuffer.wrap(Base64.decode(replaceFirst2, 2));
                    StaticGraphServiceFactory.getTreeSerializer();
                    throw new NullPointerException("deserializeTreeFromByteBuffer");
                }
            }
            return null;
        } catch (Exception e4) {
            Throwables.propagateIfPossible(e4, IOException.class);
            C00780Ix.A00(this.A00, r8, e4);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
