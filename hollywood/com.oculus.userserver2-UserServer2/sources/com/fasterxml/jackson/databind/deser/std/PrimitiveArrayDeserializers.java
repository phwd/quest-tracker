package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.AnonymousClass9z;
import X.B3;
import X.RW;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

public abstract class PrimitiveArrayDeserializers<T> extends StdDeserializer<T> {

    @JacksonStdImpl
    public static final class BooleanDeser extends PrimitiveArrayDeserializers<boolean[]> {
        public static final long serialVersionUID = 1;

        public BooleanDeser() {
            super(boolean[].class);
        }

        private final void A00(Rn rn) throws IOException, AnonymousClass9r {
            throw null;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
            throw null;
        }
    }

    @JacksonStdImpl
    public static final class ByteDeser extends PrimitiveArrayDeserializers<byte[]> {
        public static final long serialVersionUID = 1;

        private final byte[] A00(Rn rn) throws IOException, AnonymousClass9r {
            AnonymousClass9p r1 = ((B3) rn).A00;
            if (r1 == AnonymousClass9p.VALUE_STRING) {
                throw null;
            } else if (r1 == AnonymousClass9p.VALUE_EMBEDDED_OBJECT) {
                Object A07 = rn.A07();
                if (A07 == null) {
                    return null;
                }
                if (A07 instanceof byte[]) {
                    return (byte[]) A07;
                }
                throw null;
            } else {
                throw null;
            }
        }

        public ByteDeser() {
            super(byte[].class);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
            return A00(rn);
        }
    }

    @JacksonStdImpl
    public static final class CharDeser extends PrimitiveArrayDeserializers<char[]> {
        public static final long serialVersionUID = 1;

        private final char[] A00(Rn rn) throws IOException, AnonymousClass9r {
            String str;
            AnonymousClass9p r1 = ((B3) rn).A00;
            if (r1 == AnonymousClass9p.VALUE_STRING) {
                char[] A0C = rn.A0C();
                int A02 = rn.A02();
                int A01 = rn.A01();
                char[] cArr = new char[A01];
                System.arraycopy(A0C, A02, cArr, 0, A01);
                return cArr;
            }
            if (r1 == AnonymousClass9p.START_ARRAY) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    AnonymousClass9p A04 = rn.A04();
                    if (A04 == AnonymousClass9p.END_ARRAY) {
                        str = sb.toString();
                        break;
                    } else if (A04 == AnonymousClass9p.VALUE_STRING) {
                        String A09 = rn.A09();
                        int length = A09.length();
                        if (length == 1) {
                            sb.append(A09.charAt(0));
                        } else {
                            StringBuilder sb2 = new StringBuilder("Can not convert a JSON String of length ");
                            sb2.append(length);
                            sb2.append(" into a char element of char array");
                            throw RW.A00(rn, sb2.toString());
                        }
                    } else {
                        throw null;
                    }
                }
            } else if (r1 == AnonymousClass9p.VALUE_EMBEDDED_OBJECT) {
                Object A07 = rn.A07();
                if (A07 == null) {
                    return null;
                }
                if (A07 instanceof char[]) {
                    return (char[]) A07;
                }
                if (A07 instanceof String) {
                    str = (String) A07;
                } else if (A07 instanceof byte[]) {
                    str = AnonymousClass9z.A01.A00((byte[]) A07, false);
                } else {
                    throw null;
                }
            } else {
                throw null;
            }
            return str.toCharArray();
        }

        public CharDeser() {
            super(char[].class);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
            return A00(rn);
        }
    }

    @JacksonStdImpl
    public static final class DoubleDeser extends PrimitiveArrayDeserializers<double[]> {
        public static final long serialVersionUID = 1;

        public DoubleDeser() {
            super(double[].class);
        }

        private final void A00(Rn rn) throws IOException, AnonymousClass9r {
            throw null;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
            throw null;
        }
    }

    @JacksonStdImpl
    public static final class FloatDeser extends PrimitiveArrayDeserializers<float[]> {
        public static final long serialVersionUID = 1;

        public FloatDeser() {
            super(float[].class);
        }

        private final void A00(Rn rn) throws IOException, AnonymousClass9r {
            throw null;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
            throw null;
        }
    }

    @JacksonStdImpl
    public static final class IntDeser extends PrimitiveArrayDeserializers<int[]> {
        public static final IntDeser A00 = new IntDeser();
        public static final long serialVersionUID = 1;

        public IntDeser() {
            super(int[].class);
        }

        private final void A00(Rn rn) throws IOException, AnonymousClass9r {
            throw null;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
            throw null;
        }
    }

    @JacksonStdImpl
    public static final class LongDeser extends PrimitiveArrayDeserializers<long[]> {
        public static final LongDeser A00 = new LongDeser();
        public static final long serialVersionUID = 1;

        public LongDeser() {
            super(long[].class);
        }

        private final void A00(Rn rn) throws IOException, AnonymousClass9r {
            throw null;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
            throw null;
        }
    }

    @JacksonStdImpl
    public static final class ShortDeser extends PrimitiveArrayDeserializers<short[]> {
        public static final long serialVersionUID = 1;

        public ShortDeser() {
            super(short[].class);
        }

        private final void A00(Rn rn) throws IOException, AnonymousClass9r {
            throw null;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
            throw null;
        }
    }

    public PrimitiveArrayDeserializers(Class<T> cls) {
        super(cls);
    }
}
