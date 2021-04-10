package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass006;
import X.AnonymousClass0fn;
import X.AnonymousClass0fo;
import X.AnonymousClass0fp;
import X.AnonymousClass0fq;
import X.AnonymousClass0fr;
import X.AnonymousClass0fs;
import X.AnonymousClass0ft;
import X.AnonymousClass0jX;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.C03990gZ;
import X.C06310ms;
import X.EnumC04010gf;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

public abstract class PrimitiveArrayDeserializers<T> extends StdDeserializer<T> {

    @JacksonStdImpl
    public static final class BooleanDeser extends PrimitiveArrayDeserializers<boolean[]> {
        public static final long serialVersionUID = 1;

        public BooleanDeser() {
            super(boolean[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final boolean[] A09(AbstractC04100gp r6, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
            if (r6.A0G()) {
                C06310ms A0C = r7.A0C();
                AnonymousClass0ft r4 = A0C.A00;
                if (r4 == null) {
                    r4 = new AnonymousClass0ft();
                    A0C.A00 = r4;
                }
                boolean[] zArr = (boolean[]) r4.A00();
                int i = 0;
                while (r6.A0b() != EnumC04820ji.END_ARRAY) {
                    boolean A0O = A0O(r6, r7);
                    if (i >= zArr.length) {
                        zArr = (boolean[]) r4.A02(zArr, i);
                        i = 0;
                    }
                    zArr[i] = A0O;
                    i++;
                }
                return (boolean[]) r4.A03(zArr, i);
            } else if (r6.A0a() == EnumC04820ji.VALUE_STRING && r7.A0I(EnumC04010gf.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0e().length() == 0) {
                return null;
            } else {
                if (r7.A0I(EnumC04010gf.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new boolean[]{A0O(r6, r7)};
                }
                throw null;
            }
        }
    }

    @JacksonStdImpl
    public static final class ByteDeser extends PrimitiveArrayDeserializers<byte[]> {
        public static final long serialVersionUID = 1;

        public ByteDeser() {
            super(byte[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final byte[] A09(AbstractC04100gp r6, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
            byte A0B;
            byte A0B2;
            EnumC04820ji A0a = r6.A0a();
            EnumC04820ji r2 = EnumC04820ji.VALUE_STRING;
            if (A0a == r2) {
                return r6.A0j(r7._config._base._defaultBase64);
            }
            if (A0a == EnumC04820ji.VALUE_EMBEDDED_OBJECT) {
                Object A0S = r6.A0S();
                if (A0S == null) {
                    return null;
                }
                if (A0S instanceof byte[]) {
                    return (byte[]) A0S;
                }
            }
            if (r6.A0G()) {
                C06310ms A0C = r7.A0C();
                AnonymousClass0fs r4 = A0C.A01;
                if (r4 == null) {
                    r4 = new AnonymousClass0fs();
                    A0C.A01 = r4;
                }
                byte[] bArr = (byte[]) r4.A00();
                int i = 0;
                while (true) {
                    EnumC04820ji A0b = r6.A0b();
                    if (A0b == EnumC04820ji.END_ARRAY) {
                        return (byte[]) r4.A03(bArr, i);
                    }
                    if (A0b != EnumC04820ji.VALUE_NUMBER_INT && A0b != EnumC04820ji.VALUE_NUMBER_FLOAT) {
                        if (A0b != EnumC04820ji.VALUE_NULL) {
                            break;
                        }
                        A0B = 0;
                    } else {
                        A0B = r6.A0B();
                    }
                    if (i >= bArr.length) {
                        bArr = (byte[]) r4.A02(bArr, i);
                        i = 0;
                    }
                    bArr[i] = A0B;
                    i++;
                }
            } else if (A0a == r2 && r7.A0I(EnumC04010gf.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0e().length() == 0) {
                return null;
            } else {
                if (r7.A0I(EnumC04010gf.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    EnumC04820ji A0a2 = r6.A0a();
                    if (A0a2 == EnumC04820ji.VALUE_NUMBER_INT || A0a2 == EnumC04820ji.VALUE_NUMBER_FLOAT) {
                        A0B2 = r6.A0B();
                    } else if (A0a2 == EnumC04820ji.VALUE_NULL) {
                        A0B2 = 0;
                    }
                    return new byte[]{A0B2};
                }
                throw null;
            }
            this._valueClass.getComponentType();
            throw null;
        }
    }

    @JacksonStdImpl
    public static final class CharDeser extends PrimitiveArrayDeserializers<char[]> {
        public static final long serialVersionUID = 1;

        public CharDeser() {
            super(char[].class);
        }

        private final char[] A00(AbstractC04100gp r6) throws IOException, AnonymousClass0jg {
            String str;
            EnumC04820ji A0a = r6.A0a();
            if (A0a == EnumC04820ji.VALUE_STRING) {
                char[] A0k = r6.A0k();
                int A0W = r6.A0W();
                int A0V = r6.A0V();
                char[] cArr = new char[A0V];
                System.arraycopy(A0k, A0W, cArr, 0, A0V);
                return cArr;
            }
            if (r6.A0G()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    EnumC04820ji A0b = r6.A0b();
                    if (A0b == EnumC04820ji.END_ARRAY) {
                        str = sb.toString();
                        break;
                    } else if (A0b == EnumC04820ji.VALUE_STRING) {
                        String A0e = r6.A0e();
                        int length = A0e.length();
                        if (length == 1) {
                            sb.append(A0e.charAt(0));
                        } else {
                            throw C03990gZ.A00(r6, AnonymousClass006.A02("Can not convert a JSON String of length ", length, " into a char element of char array"));
                        }
                    } else {
                        throw null;
                    }
                }
            } else if (A0a == EnumC04820ji.VALUE_EMBEDDED_OBJECT) {
                Object A0S = r6.A0S();
                if (A0S == null) {
                    return null;
                }
                if (A0S instanceof char[]) {
                    return (char[]) A0S;
                }
                if (A0S instanceof String) {
                    str = (String) A0S;
                } else if (A0S instanceof byte[]) {
                    str = AnonymousClass0jX.A01.A02((byte[]) A0S, false);
                } else {
                    throw null;
                }
            } else {
                throw null;
            }
            return str.toCharArray();
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final /* bridge */ /* synthetic */ Object A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
            return A00(r2);
        }
    }

    @JacksonStdImpl
    public static final class DoubleDeser extends PrimitiveArrayDeserializers<double[]> {
        public static final long serialVersionUID = 1;

        public DoubleDeser() {
            super(double[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final double[] A09(AbstractC04100gp r7, AbstractC04020gg r8) throws IOException, AnonymousClass0jg {
            if (r7.A0G()) {
                C06310ms A0C = r8.A0C();
                AnonymousClass0fr r5 = A0C.A02;
                if (r5 == null) {
                    r5 = new AnonymousClass0fr();
                    A0C.A02 = r5;
                }
                double[] dArr = (double[]) r5.A00();
                int i = 0;
                while (r7.A0b() != EnumC04820ji.END_ARRAY) {
                    double A0F = A0F(r7, r8);
                    if (i >= dArr.length) {
                        dArr = (double[]) r5.A02(dArr, i);
                        i = 0;
                    }
                    dArr[i] = A0F;
                    i++;
                }
                return (double[]) r5.A03(dArr, i);
            } else if (r7.A0a() == EnumC04820ji.VALUE_STRING && r8.A0I(EnumC04010gf.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0e().length() == 0) {
                return null;
            } else {
                if (r8.A0I(EnumC04010gf.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new double[]{A0F(r7, r8)};
                }
                throw null;
            }
        }
    }

    @JacksonStdImpl
    public static final class FloatDeser extends PrimitiveArrayDeserializers<float[]> {
        public static final long serialVersionUID = 1;

        public FloatDeser() {
            super(float[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final float[] A09(AbstractC04100gp r6, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
            if (r6.A0G()) {
                C06310ms A0C = r7.A0C();
                AnonymousClass0fq r4 = A0C.A03;
                if (r4 == null) {
                    r4 = new AnonymousClass0fq();
                    A0C.A03 = r4;
                }
                float[] fArr = (float[]) r4.A00();
                int i = 0;
                while (r6.A0b() != EnumC04820ji.END_ARRAY) {
                    float A0G = A0G(r6, r7);
                    if (i >= fArr.length) {
                        fArr = (float[]) r4.A02(fArr, i);
                        i = 0;
                    }
                    fArr[i] = A0G;
                    i++;
                }
                return (float[]) r4.A03(fArr, i);
            } else if (r6.A0a() == EnumC04820ji.VALUE_STRING && r7.A0I(EnumC04010gf.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0e().length() == 0) {
                return null;
            } else {
                if (r7.A0I(EnumC04010gf.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new float[]{A0G(r6, r7)};
                }
                throw null;
            }
        }
    }

    @JacksonStdImpl
    public static final class IntDeser extends PrimitiveArrayDeserializers<int[]> {
        public static final IntDeser A00 = new IntDeser();
        public static final long serialVersionUID = 1;

        public IntDeser() {
            super(int[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final int[] A09(AbstractC04100gp r6, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
            if (r6.A0G()) {
                C06310ms A0C = r7.A0C();
                AnonymousClass0fp r4 = A0C.A04;
                if (r4 == null) {
                    r4 = new AnonymousClass0fp();
                    A0C.A04 = r4;
                }
                int[] iArr = (int[]) r4.A00();
                int i = 0;
                while (r6.A0b() != EnumC04820ji.END_ARRAY) {
                    int A0H = A0H(r6, r7);
                    if (i >= iArr.length) {
                        iArr = (int[]) r4.A02(iArr, i);
                        i = 0;
                    }
                    iArr[i] = A0H;
                    i++;
                }
                return (int[]) r4.A03(iArr, i);
            } else if (r6.A0a() == EnumC04820ji.VALUE_STRING && r7.A0I(EnumC04010gf.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0e().length() == 0) {
                return null;
            } else {
                if (r7.A0I(EnumC04010gf.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new int[]{A0H(r6, r7)};
                }
                throw null;
            }
        }
    }

    @JacksonStdImpl
    public static final class LongDeser extends PrimitiveArrayDeserializers<long[]> {
        public static final LongDeser A00 = new LongDeser();
        public static final long serialVersionUID = 1;

        public LongDeser() {
            super(long[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final long[] A09(AbstractC04100gp r7, AbstractC04020gg r8) throws IOException, AnonymousClass0jg {
            if (r7.A0G()) {
                C06310ms A0C = r8.A0C();
                AnonymousClass0fo r5 = A0C.A05;
                if (r5 == null) {
                    r5 = new AnonymousClass0fo();
                    A0C.A05 = r5;
                }
                long[] jArr = (long[]) r5.A00();
                int i = 0;
                while (r7.A0b() != EnumC04820ji.END_ARRAY) {
                    long A0I = A0I(r7, r8);
                    if (i >= jArr.length) {
                        jArr = (long[]) r5.A02(jArr, i);
                        i = 0;
                    }
                    jArr[i] = A0I;
                    i++;
                }
                return (long[]) r5.A03(jArr, i);
            } else if (r7.A0a() == EnumC04820ji.VALUE_STRING && r8.A0I(EnumC04010gf.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0e().length() == 0) {
                return null;
            } else {
                if (r8.A0I(EnumC04010gf.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new long[]{A0I(r7, r8)};
                }
                throw null;
            }
        }
    }

    @JacksonStdImpl
    public static final class ShortDeser extends PrimitiveArrayDeserializers<short[]> {
        public static final long serialVersionUID = 1;

        public ShortDeser() {
            super(short[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final short[] A09(AbstractC04100gp r6, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
            if (r6.A0G()) {
                C06310ms A0C = r7.A0C();
                AnonymousClass0fn r4 = A0C.A06;
                if (r4 == null) {
                    r4 = new AnonymousClass0fn();
                    A0C.A06 = r4;
                }
                short[] sArr = (short[]) r4.A00();
                int i = 0;
                while (r6.A0b() != EnumC04820ji.END_ARRAY) {
                    int A0H = A0H(r6, r7);
                    if (A0H >= -32768 && A0H <= 32767) {
                        short s = (short) A0H;
                        if (i >= sArr.length) {
                            sArr = (short[]) r4.A02(sArr, i);
                            i = 0;
                        }
                        sArr[i] = s;
                        i++;
                    }
                }
                return (short[]) r4.A03(sArr, i);
            } else if (r6.A0a() == EnumC04820ji.VALUE_STRING && r7.A0I(EnumC04010gf.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0e().length() == 0) {
                return null;
            } else {
                if (r7.A0I(EnumC04010gf.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    short[] sArr2 = new short[1];
                    int A0H2 = A0H(r6, r7);
                    if (A0H2 >= -32768 && A0H2 <= 32767) {
                        sArr2[0] = (short) A0H2;
                        return sArr2;
                    }
                } else {
                    throw null;
                }
            }
            try {
                throw null;
            } catch (Exception unused) {
                throw null;
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return r4.A08(r2, r3);
    }

    public PrimitiveArrayDeserializers(Class<T> cls) {
        super((Class<?>) cls);
    }
}
