package com.facebook.common.json;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.CH;
import X.CK;
import X.E4;
import X.E7;
import android.util.Base64;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TypeModelBase64Deserializer extends FbJsonDeserializer {
    @Override // com.facebook.common.json.FbJsonDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        int position;
        try {
            String A0n = qiVar.A0n();
            if (A0n != null) {
                if (A0n.startsWith("fltb:")) {
                    String replaceFirst = A0n.replaceFirst("fltb:", OacrConstants.AUTO_SPEECH_DOMAIN);
                    int A00 = CK.A00(replaceFirst);
                    if (replaceFirst != null && replaceFirst.startsWith("type_tag:")) {
                        replaceFirst = replaceFirst.substring(18);
                    }
                    Class cls = this.A00;
                    try {
                        cls.getDeclaredConstructor(Integer.TYPE, int[].class).newInstance(Integer.valueOf(A00), null);
                        try {
                            ByteBuffer byteBuffer = new E7(ByteBuffer.wrap(Base64.decode(replaceFirst, 2))).A01;
                            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                            synchronized (byteBuffer) {
                                position = byteBuffer.position();
                            }
                            if (byteBuffer.getInt(position) + position > 0) {
                                try {
                                    throw new NullPointerException("initFromMutableFlatBuffer");
                                } catch (IndexOutOfBoundsException e) {
                                    throw new E4(e);
                                }
                            }
                        } catch (Exception e2) {
                            throw new E4(e2);
                        }
                    } catch (Exception e3) {
                        throw new RuntimeException("Can't create model object", e3);
                    }
                } else {
                    Preconditions.checkState(A0n.startsWith("tree:"));
                    String replaceFirst2 = A0n.replaceFirst("tree:", OacrConstants.AUTO_SPEECH_DOMAIN);
                    CK.A00(replaceFirst2);
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
            CH.A00(this.A00, qiVar, e4);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
