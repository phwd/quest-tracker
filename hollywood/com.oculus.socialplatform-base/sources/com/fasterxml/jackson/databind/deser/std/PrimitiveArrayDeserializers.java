package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.AnonymousClass006;
import X.AnonymousClass0gz;
import X.AnonymousClass0h0;
import X.AnonymousClass0h2;
import X.AnonymousClass0o3;
import X.C01770h1;
import X.C01780h3;
import X.C01790h4;
import X.C01800h5;
import X.C02180iD;
import X.C03620oC;
import X.C04790rG;
import X.EnumC02200iG;
import X.EnumC03640oE;
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
        public final boolean[] A0A(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
            if (r6.A0K()) {
                C04790rG A0K = r7.A0K();
                C01800h5 r4 = A0K.A00;
                if (r4 == null) {
                    r4 = new C01800h5();
                    A0K.A00 = r4;
                }
                boolean[] zArr = (boolean[]) r4.A00();
                int i = 0;
                while (r6.A0j() != EnumC03640oE.END_ARRAY) {
                    boolean A0O = A0O(r6, r7);
                    if (i >= zArr.length) {
                        zArr = (boolean[]) r4.A02(zArr, i);
                        i = 0;
                    }
                    zArr[i] = A0O;
                    i++;
                }
                return (boolean[]) r4.A03(zArr, i);
            } else if (r6.A0i() == EnumC03640oE.VALUE_STRING && r7.A0P(EnumC02200iG.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0m().length() == 0) {
                return null;
            } else {
                if (r7.A0P(EnumC02200iG.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new boolean[]{A0O(r6, r7)};
                }
                throw r7.A0B(this._valueClass);
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
        public final byte[] A0A(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
            byte A0E;
            byte A0E2;
            EnumC03640oE A0i = r6.A0i();
            EnumC03640oE r2 = EnumC03640oE.VALUE_STRING;
            if (A0i == r2) {
                return r6.A0r(r7._config._base._defaultBase64);
            }
            if (A0i == EnumC03640oE.VALUE_EMBEDDED_OBJECT) {
                Object A0Z = r6.A0Z();
                if (A0Z == null) {
                    return null;
                }
                if (A0Z instanceof byte[]) {
                    return (byte[]) A0Z;
                }
            }
            if (r6.A0K()) {
                C04790rG A0K = r7.A0K();
                C01790h4 r4 = A0K.A01;
                if (r4 == null) {
                    r4 = new C01790h4();
                    A0K.A01 = r4;
                }
                byte[] bArr = (byte[]) r4.A00();
                int i = 0;
                while (true) {
                    EnumC03640oE A0j = r6.A0j();
                    if (A0j == EnumC03640oE.END_ARRAY) {
                        return (byte[]) r4.A03(bArr, i);
                    }
                    if (A0j == EnumC03640oE.VALUE_NUMBER_INT || A0j == EnumC03640oE.VALUE_NUMBER_FLOAT) {
                        A0E = r6.A0E();
                    } else if (A0j == EnumC03640oE.VALUE_NULL) {
                        A0E = 0;
                    } else {
                        throw r7.A0B(this._valueClass.getComponentType());
                    }
                    if (i >= bArr.length) {
                        bArr = (byte[]) r4.A02(bArr, i);
                        i = 0;
                    }
                    bArr[i] = A0E;
                    i++;
                }
            } else if (A0i == r2 && r7.A0P(EnumC02200iG.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0m().length() == 0) {
                return null;
            } else {
                if (r7.A0P(EnumC02200iG.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    EnumC03640oE A0i2 = r6.A0i();
                    if (A0i2 == EnumC03640oE.VALUE_NUMBER_INT || A0i2 == EnumC03640oE.VALUE_NUMBER_FLOAT) {
                        A0E2 = r6.A0E();
                    } else if (A0i2 == EnumC03640oE.VALUE_NULL) {
                        A0E2 = 0;
                    } else {
                        throw r7.A0B(this._valueClass.getComponentType());
                    }
                    return new byte[]{A0E2};
                }
                throw r7.A0B(this._valueClass);
            }
        }
    }

    @JacksonStdImpl
    public static final class CharDeser extends PrimitiveArrayDeserializers<char[]> {
        public static final long serialVersionUID = 1;

        public CharDeser() {
            super(char[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final char[] A0A(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
            String str;
            EnumC03640oE A0i = r6.A0i();
            if (A0i == EnumC03640oE.VALUE_STRING) {
                char[] A0s = r6.A0s();
                int A0e = r6.A0e();
                int A0d = r6.A0d();
                char[] cArr = new char[A0d];
                System.arraycopy(A0s, A0e, cArr, 0, A0d);
                return cArr;
            }
            if (r6.A0K()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    EnumC03640oE A0j = r6.A0j();
                    if (A0j == EnumC03640oE.END_ARRAY) {
                        str = sb.toString();
                        break;
                    } else if (A0j == EnumC03640oE.VALUE_STRING) {
                        String A0m = r6.A0m();
                        int length = A0m.length();
                        if (length == 1) {
                            sb.append(A0m.charAt(0));
                        } else {
                            throw C02180iD.A00(r6, AnonymousClass006.A04("Can not convert a JSON String of length ", length, " into a char element of char array"));
                        }
                    } else {
                        throw r7.A0B(Character.TYPE);
                    }
                }
            } else {
                if (A0i == EnumC03640oE.VALUE_EMBEDDED_OBJECT) {
                    Object A0Z = r6.A0Z();
                    if (A0Z == null) {
                        return null;
                    }
                    if (A0Z instanceof char[]) {
                        return (char[]) A0Z;
                    }
                    if (A0Z instanceof String) {
                        str = (String) A0Z;
                    } else if (A0Z instanceof byte[]) {
                        str = AnonymousClass0o3.A01.A02((byte[]) A0Z, false);
                    }
                }
                throw r7.A0B(this._valueClass);
            }
            return str.toCharArray();
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
        public final double[] A0A(AbstractC02280iQ r7, AbstractC02210iH r8) throws IOException, C03620oC {
            if (r7.A0K()) {
                C04790rG A0K = r8.A0K();
                C01780h3 r5 = A0K.A02;
                if (r5 == null) {
                    r5 = new C01780h3();
                    A0K.A02 = r5;
                }
                double[] dArr = (double[]) r5.A00();
                int i = 0;
                while (r7.A0j() != EnumC03640oE.END_ARRAY) {
                    double A0F = A0F(r7, r8);
                    if (i >= dArr.length) {
                        dArr = (double[]) r5.A02(dArr, i);
                        i = 0;
                    }
                    dArr[i] = A0F;
                    i++;
                }
                return (double[]) r5.A03(dArr, i);
            } else if (r7.A0i() == EnumC03640oE.VALUE_STRING && r8.A0P(EnumC02200iG.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0m().length() == 0) {
                return null;
            } else {
                if (r8.A0P(EnumC02200iG.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new double[]{A0F(r7, r8)};
                }
                throw r8.A0B(this._valueClass);
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
        public final float[] A0A(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
            if (r6.A0K()) {
                C04790rG A0K = r7.A0K();
                AnonymousClass0h2 r4 = A0K.A03;
                if (r4 == null) {
                    r4 = new AnonymousClass0h2();
                    A0K.A03 = r4;
                }
                float[] fArr = (float[]) r4.A00();
                int i = 0;
                while (r6.A0j() != EnumC03640oE.END_ARRAY) {
                    float A0G = A0G(r6, r7);
                    if (i >= fArr.length) {
                        fArr = (float[]) r4.A02(fArr, i);
                        i = 0;
                    }
                    fArr[i] = A0G;
                    i++;
                }
                return (float[]) r4.A03(fArr, i);
            } else if (r6.A0i() == EnumC03640oE.VALUE_STRING && r7.A0P(EnumC02200iG.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0m().length() == 0) {
                return null;
            } else {
                if (r7.A0P(EnumC02200iG.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new float[]{A0G(r6, r7)};
                }
                throw r7.A0B(this._valueClass);
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
        public final int[] A0A(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
            if (r6.A0K()) {
                C04790rG A0K = r7.A0K();
                C01770h1 r4 = A0K.A04;
                if (r4 == null) {
                    r4 = new C01770h1();
                    A0K.A04 = r4;
                }
                int[] iArr = (int[]) r4.A00();
                int i = 0;
                while (r6.A0j() != EnumC03640oE.END_ARRAY) {
                    int A0H = A0H(r6, r7);
                    if (i >= iArr.length) {
                        iArr = (int[]) r4.A02(iArr, i);
                        i = 0;
                    }
                    iArr[i] = A0H;
                    i++;
                }
                return (int[]) r4.A03(iArr, i);
            } else if (r6.A0i() == EnumC03640oE.VALUE_STRING && r7.A0P(EnumC02200iG.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0m().length() == 0) {
                return null;
            } else {
                if (r7.A0P(EnumC02200iG.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new int[]{A0H(r6, r7)};
                }
                throw r7.A0B(this._valueClass);
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
        public final long[] A0A(AbstractC02280iQ r7, AbstractC02210iH r8) throws IOException, C03620oC {
            if (r7.A0K()) {
                C04790rG A0K = r8.A0K();
                AnonymousClass0h0 r5 = A0K.A05;
                if (r5 == null) {
                    r5 = new AnonymousClass0h0();
                    A0K.A05 = r5;
                }
                long[] jArr = (long[]) r5.A00();
                int i = 0;
                while (r7.A0j() != EnumC03640oE.END_ARRAY) {
                    long A0I = A0I(r7, r8);
                    if (i >= jArr.length) {
                        jArr = (long[]) r5.A02(jArr, i);
                        i = 0;
                    }
                    jArr[i] = A0I;
                    i++;
                }
                return (long[]) r5.A03(jArr, i);
            } else if (r7.A0i() == EnumC03640oE.VALUE_STRING && r8.A0P(EnumC02200iG.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0m().length() == 0) {
                return null;
            } else {
                if (r8.A0P(EnumC02200iG.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new long[]{A0I(r7, r8)};
                }
                throw r8.A0B(this._valueClass);
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
        public final short[] A0A(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
            if (r6.A0K()) {
                C04790rG A0K = r7.A0K();
                AnonymousClass0gz r4 = A0K.A06;
                if (r4 == null) {
                    r4 = new AnonymousClass0gz();
                    A0K.A06 = r4;
                }
                short[] sArr = (short[]) r4.A00();
                int i = 0;
                while (r6.A0j() != EnumC03640oE.END_ARRAY) {
                    int A0H = A0H(r6, r7);
                    if (A0H < -32768 || A0H > 32767) {
                        throw r7.A0G(String.valueOf(A0H), this._valueClass, "overflow, value can not be represented as 16-bit value");
                    }
                    short s = (short) A0H;
                    if (i >= sArr.length) {
                        sArr = (short[]) r4.A02(sArr, i);
                        i = 0;
                    }
                    sArr[i] = s;
                    i++;
                }
                return (short[]) r4.A03(sArr, i);
            } else if (r6.A0i() == EnumC03640oE.VALUE_STRING && r7.A0P(EnumC02200iG.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0m().length() == 0) {
                return null;
            } else {
                if (r7.A0P(EnumC02200iG.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    short[] sArr2 = new short[1];
                    int A0H2 = A0H(r6, r7);
                    if (A0H2 < -32768 || A0H2 > 32767) {
                        throw r7.A0G(String.valueOf(A0H2), this._valueClass, "overflow, value can not be represented as 16-bit value");
                    }
                    sArr2[0] = (short) A0H2;
                    return sArr2;
                }
                throw r7.A0B(this._valueClass);
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return r4.A08(r2, r3);
    }

    public PrimitiveArrayDeserializers(Class<T> cls) {
        super((Class<?>) cls);
    }
}
