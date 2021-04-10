package com.facebook.graphql.query;

import X.AbstractC02570aK;
import X.AbstractC05940lg;
import X.AnonymousClass006;
import X.AnonymousClass0aT;
import X.C02630aU;
import X.C03110bb;
import X.C03120bc;
import X.C03130be;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Map;
import javax.annotation.Nullable;

public final class GraphQlQueryParamSetDeserializer extends JsonDeserializer<Object> {
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @Nullable
    public final Object A09(AnonymousClass0aT r12, AbstractC02570aK r13) throws IOException {
        String str;
        GraphQlQueryParamSet graphQlQueryParamSet = null;
        while (true) {
            try {
                EnumC05930lf A0a = r12.A0a();
                if (A0a == null) {
                    throw new IOException("Unexpected end of json input");
                } else if (A0a == EnumC05930lf.END_OBJECT) {
                    return graphQlQueryParamSet;
                } else {
                    if (r12.A0G() == EnumC05930lf.FIELD_NAME) {
                        String A0O = r12.A0O();
                        r12.A0a();
                        if (A0O.equals("params")) {
                            C03120bc r1 = new C03120bc(this);
                            AbstractC05940lg A0I = r12.A0I();
                            if (A0I != null) {
                                graphQlQueryParamSet = new GraphQlQueryParamSet();
                                C03130be r14 = graphQlQueryParamSet.A00;
                                r14.A04(r14.A03(), (Map) A0I.A01(r12, r1));
                            } else {
                                throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
                            }
                        } else if (A0O.equals("input_name")) {
                            C03110bb r15 = new C03110bb(this);
                            AbstractC05940lg A0I2 = r12.A0I();
                            if (A0I2 != null) {
                                A0I2.A01(r12, r15);
                            } else {
                                throw new IllegalStateException("No ObjectCodec defined for the parser, can not deserialize JSON into Java objects");
                            }
                        }
                        r12.A0F();
                    }
                }
            } catch (Exception e) {
                if (IOException.class.isInstance(e)) {
                    throw ((Throwable) IOException.class.cast(e));
                } else if ((e instanceof RuntimeException) || (e instanceof Error)) {
                    throw e;
                } else {
                    try {
                        Object A0L = r12.A0L();
                        StringBuilder sb = new StringBuilder("current token: ");
                        sb.append(r12.A0P());
                        sb.append("\n");
                        if (A0L instanceof InputStream) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byteArrayOutputStream.flush();
                            char[] charArray = byteArrayOutputStream.toString().toCharArray();
                            sb.append(charArray, 0, Math.min(charArray.length, 100 - sb.length()));
                            byteArrayOutputStream.close();
                            InputStream inputStream = (InputStream) A0L;
                            while (true) {
                                int read = inputStream.read();
                                if (read == -1 || sb.length() >= 100) {
                                    break;
                                }
                                sb.append((char) read);
                            }
                        } else if (A0L instanceof Reader) {
                            StringWriter stringWriter = new StringWriter();
                            stringWriter.flush();
                            char[] charArray2 = stringWriter.toString().toCharArray();
                            sb.append(charArray2, 0, Math.min(charArray2.length, 100 - sb.length()));
                            stringWriter.close();
                            Reader reader = (Reader) A0L;
                            while (true) {
                                int read2 = reader.read();
                                if (read2 == -1 || sb.length() >= 100) {
                                    reader.close();
                                } else {
                                    sb.append((char) read2);
                                }
                            }
                            reader.close();
                        }
                        if (sb.length() == 100) {
                            sb.append("...");
                        }
                        str = sb.toString();
                    } catch (Exception unused) {
                        str = "failed to get parser text";
                    }
                    throw new C02630aU(AnonymousClass006.A08("Failed to deserialize to instance ", GraphQlQueryParamSet.class.getSimpleName(), "\n", str), r12.A0D(), e);
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }
}
