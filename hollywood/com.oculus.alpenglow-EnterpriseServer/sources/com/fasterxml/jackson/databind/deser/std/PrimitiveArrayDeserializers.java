package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass006;
import X.AnonymousClass0ZP;
import X.AnonymousClass0ZQ;
import X.AnonymousClass0ZR;
import X.AnonymousClass0ZS;
import X.AnonymousClass0ZT;
import X.AnonymousClass0ZU;
import X.AnonymousClass0ZV;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05840lV;
import X.C05910ld;
import X.C07110ok;
import X.EnumC02560aJ;
import X.EnumC05930lf;
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
        public final boolean[] A09(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
            if (r6.A0V()) {
                C07110ok A0J = r7.A0J();
                AnonymousClass0ZV r4 = A0J.A00;
                if (r4 == null) {
                    r4 = new AnonymousClass0ZV();
                    A0J.A00 = r4;
                }
                boolean[] zArr = (boolean[]) r4.A00();
                int i = 0;
                while (r6.A0a() != EnumC05930lf.END_ARRAY) {
                    boolean A0O = A0O(r6, r7);
                    if (i >= zArr.length) {
                        zArr = (boolean[]) r4.A02(zArr, i);
                        i = 0;
                    }
                    zArr[i] = A0O;
                    i++;
                }
                return (boolean[]) r4.A03(zArr, i);
            } else if (r6.A0G() == EnumC05930lf.VALUE_STRING && r7.A0O(EnumC02560aJ.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0P().length() == 0) {
                return null;
            } else {
                if (r7.A0O(EnumC02560aJ.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
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
        public final byte[] A09(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
            Class<?> cls;
            byte A02;
            byte A022;
            EnumC05930lf A0G = r6.A0G();
            EnumC05930lf r2 = EnumC05930lf.VALUE_STRING;
            if (A0G == r2) {
                return r6.A0Y(r7._config._base._defaultBase64);
            }
            if (A0G == EnumC05930lf.VALUE_EMBEDDED_OBJECT) {
                Object A0M = r6.A0M();
                if (A0M == null) {
                    return null;
                }
                if (A0M instanceof byte[]) {
                    return (byte[]) A0M;
                }
            }
            if (r6.A0V()) {
                C07110ok A0J = r7.A0J();
                AnonymousClass0ZU r4 = A0J.A01;
                if (r4 == null) {
                    r4 = new AnonymousClass0ZU();
                    A0J.A01 = r4;
                }
                byte[] bArr = (byte[]) r4.A00();
                int i = 0;
                while (true) {
                    EnumC05930lf A0a = r6.A0a();
                    if (A0a == EnumC05930lf.END_ARRAY) {
                        return (byte[]) r4.A03(bArr, i);
                    }
                    if (A0a != EnumC05930lf.VALUE_NUMBER_INT && A0a != EnumC05930lf.VALUE_NUMBER_FLOAT) {
                        if (A0a != EnumC05930lf.VALUE_NULL) {
                            break;
                        }
                        A02 = 0;
                    } else {
                        A02 = r6.A02();
                    }
                    if (i >= bArr.length) {
                        bArr = (byte[]) r4.A02(bArr, i);
                        i = 0;
                    }
                    bArr[i] = A02;
                    i++;
                }
            } else if (A0G == r2 && r7.A0O(EnumC02560aJ.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0P().length() == 0) {
                return null;
            } else {
                if (r7.A0O(EnumC02560aJ.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    EnumC05930lf A0G2 = r6.A0G();
                    if (A0G2 == EnumC05930lf.VALUE_NUMBER_INT || A0G2 == EnumC05930lf.VALUE_NUMBER_FLOAT) {
                        A022 = r6.A02();
                    } else if (A0G2 == EnumC05930lf.VALUE_NULL) {
                        A022 = 0;
                    }
                    return new byte[]{A022};
                }
                cls = this._valueClass;
                throw r7.A0B(cls);
            }
            cls = this._valueClass.getComponentType();
            throw r7.A0B(cls);
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
        public final char[] A09(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
            Class<?> cls;
            String str;
            EnumC05930lf A0G = r6.A0G();
            if (A0G == EnumC05930lf.VALUE_STRING) {
                char[] A0Z = r6.A0Z();
                int A08 = r6.A08();
                int A07 = r6.A07();
                char[] cArr = new char[A07];
                System.arraycopy(A0Z, A08, cArr, 0, A07);
                return cArr;
            }
            if (r6.A0V()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    EnumC05930lf A0a = r6.A0a();
                    if (A0a != EnumC05930lf.END_ARRAY) {
                        if (A0a != EnumC05930lf.VALUE_STRING) {
                            cls = Character.TYPE;
                            break;
                        }
                        String A0P = r6.A0P();
                        int length = A0P.length();
                        if (length == 1) {
                            sb.append(A0P.charAt(0));
                        } else {
                            throw AnonymousClass0aG.A00(r6, AnonymousClass006.A02("Can not convert a JSON String of length ", length, " into a char element of char array"));
                        }
                    } else {
                        str = sb.toString();
                        break;
                    }
                }
            } else {
                if (A0G == EnumC05930lf.VALUE_EMBEDDED_OBJECT) {
                    Object A0M = r6.A0M();
                    if (A0M == null) {
                        return null;
                    }
                    if (A0M instanceof char[]) {
                        return (char[]) A0M;
                    }
                    if (A0M instanceof String) {
                        str = (String) A0M;
                    } else if (A0M instanceof byte[]) {
                        str = C05840lV.A01.A01((byte[]) A0M, false);
                    }
                }
                cls = this._valueClass;
                throw r7.A0B(cls);
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
        public final double[] A09(AnonymousClass0aT r7, AbstractC02570aK r8) throws IOException, C05910ld {
            if (r7.A0V()) {
                C07110ok A0J = r8.A0J();
                AnonymousClass0ZT r5 = A0J.A02;
                if (r5 == null) {
                    r5 = new AnonymousClass0ZT();
                    A0J.A02 = r5;
                }
                double[] dArr = (double[]) r5.A00();
                int i = 0;
                while (r7.A0a() != EnumC05930lf.END_ARRAY) {
                    double A0F = A0F(r7, r8);
                    if (i >= dArr.length) {
                        dArr = (double[]) r5.A02(dArr, i);
                        i = 0;
                    }
                    dArr[i] = A0F;
                    i++;
                }
                return (double[]) r5.A03(dArr, i);
            } else if (r7.A0G() == EnumC05930lf.VALUE_STRING && r8.A0O(EnumC02560aJ.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0P().length() == 0) {
                return null;
            } else {
                if (r8.A0O(EnumC02560aJ.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
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
        public final float[] A09(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
            if (r6.A0V()) {
                C07110ok A0J = r7.A0J();
                AnonymousClass0ZS r4 = A0J.A03;
                if (r4 == null) {
                    r4 = new AnonymousClass0ZS();
                    A0J.A03 = r4;
                }
                float[] fArr = (float[]) r4.A00();
                int i = 0;
                while (r6.A0a() != EnumC05930lf.END_ARRAY) {
                    float A0G = A0G(r6, r7);
                    if (i >= fArr.length) {
                        fArr = (float[]) r4.A02(fArr, i);
                        i = 0;
                    }
                    fArr[i] = A0G;
                    i++;
                }
                return (float[]) r4.A03(fArr, i);
            } else if (r6.A0G() == EnumC05930lf.VALUE_STRING && r7.A0O(EnumC02560aJ.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0P().length() == 0) {
                return null;
            } else {
                if (r7.A0O(EnumC02560aJ.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
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
        public final int[] A09(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
            if (r6.A0V()) {
                C07110ok A0J = r7.A0J();
                AnonymousClass0ZR r4 = A0J.A04;
                if (r4 == null) {
                    r4 = new AnonymousClass0ZR();
                    A0J.A04 = r4;
                }
                int[] iArr = (int[]) r4.A00();
                int i = 0;
                while (r6.A0a() != EnumC05930lf.END_ARRAY) {
                    int A0H = A0H(r6, r7);
                    if (i >= iArr.length) {
                        iArr = (int[]) r4.A02(iArr, i);
                        i = 0;
                    }
                    iArr[i] = A0H;
                    i++;
                }
                return (int[]) r4.A03(iArr, i);
            } else if (r6.A0G() == EnumC05930lf.VALUE_STRING && r7.A0O(EnumC02560aJ.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0P().length() == 0) {
                return null;
            } else {
                if (r7.A0O(EnumC02560aJ.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
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
        public final long[] A09(AnonymousClass0aT r7, AbstractC02570aK r8) throws IOException, C05910ld {
            if (r7.A0V()) {
                C07110ok A0J = r8.A0J();
                AnonymousClass0ZQ r5 = A0J.A05;
                if (r5 == null) {
                    r5 = new AnonymousClass0ZQ();
                    A0J.A05 = r5;
                }
                long[] jArr = (long[]) r5.A00();
                int i = 0;
                while (r7.A0a() != EnumC05930lf.END_ARRAY) {
                    long A0I = A0I(r7, r8);
                    if (i >= jArr.length) {
                        jArr = (long[]) r5.A02(jArr, i);
                        i = 0;
                    }
                    jArr[i] = A0I;
                    i++;
                }
                return (long[]) r5.A03(jArr, i);
            } else if (r7.A0G() == EnumC05930lf.VALUE_STRING && r8.A0O(EnumC02560aJ.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0P().length() == 0) {
                return null;
            } else {
                if (r8.A0O(EnumC02560aJ.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
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
        public final short[] A09(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
            int i;
            if (r6.A0V()) {
                C07110ok A0J = r7.A0J();
                AnonymousClass0ZP r4 = A0J.A06;
                if (r4 == null) {
                    r4 = new AnonymousClass0ZP();
                    A0J.A06 = r4;
                }
                short[] sArr = (short[]) r4.A00();
                int i2 = 0;
                while (r6.A0a() != EnumC05930lf.END_ARRAY) {
                    i = A0H(r6, r7);
                    if (i >= -32768 && i <= 32767) {
                        short s = (short) i;
                        if (i2 >= sArr.length) {
                            sArr = (short[]) r4.A02(sArr, i2);
                            i2 = 0;
                        }
                        sArr[i2] = s;
                        i2++;
                    }
                }
                return (short[]) r4.A03(sArr, i2);
            } else if (r6.A0G() == EnumC05930lf.VALUE_STRING && r7.A0O(EnumC02560aJ.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r6.A0P().length() == 0) {
                return null;
            } else {
                if (r7.A0O(EnumC02560aJ.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    short[] sArr2 = new short[1];
                    i = A0H(r6, r7);
                    if (i >= -32768 && i <= 32767) {
                        sArr2[0] = (short) i;
                        return sArr2;
                    }
                } else {
                    throw r7.A0B(this._valueClass);
                }
            }
            throw r7.A0G(String.valueOf(i), this._valueClass, "overflow, value can not be represented as 16-bit value");
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return r4.A08(r2, r3);
    }

    public PrimitiveArrayDeserializers(Class<T> cls) {
        super((Class<?>) cls);
    }
}
