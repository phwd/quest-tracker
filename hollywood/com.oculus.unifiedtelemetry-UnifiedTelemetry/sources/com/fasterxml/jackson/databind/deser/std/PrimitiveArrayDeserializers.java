package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AnonymousClass06;
import X.C0214Vy;
import X.C0215Vz;
import X.C0223Wj;
import X.C0466pr;
import X.EnumC0225Wm;
import X.EnumC0470q2;
import X.N3;
import X.V4;
import X.W0;
import X.W1;
import X.W2;
import X.W3;
import X.W4;
import X.q0;
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
        public final boolean[] A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            if (ww.A0F()) {
                N3 A0G = wn.A0G();
                W4 w4 = A0G.A00;
                if (w4 == null) {
                    w4 = new W4();
                    A0G.A00 = w4;
                }
                boolean[] zArr = (boolean[]) w4.A00();
                int i = 0;
                while (ww.A0a() != EnumC0470q2.END_ARRAY) {
                    boolean A0O = A0O(ww, wn);
                    if (i >= zArr.length) {
                        zArr = (boolean[]) w4.A02(zArr, i);
                        i = 0;
                    }
                    zArr[i] = A0O;
                    i++;
                }
                return (boolean[]) w4.A03(zArr, i);
            } else if (ww.A0Z() == EnumC0470q2.VALUE_STRING && wn.A0L(EnumC0225Wm.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && ww.A0d().length() == 0) {
                return null;
            } else {
                if (wn.A0L(EnumC0225Wm.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new boolean[]{A0O(ww, wn)};
                }
                throw wn.A08(this._valueClass);
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
        public final byte[] A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            Class<?> cls;
            byte A0B;
            byte A0B2;
            EnumC0470q2 A0Z = ww.A0Z();
            EnumC0470q2 q2Var = EnumC0470q2.VALUE_STRING;
            if (A0Z == q2Var) {
                return ww.A0i(wn._config._base._defaultBase64);
            }
            if (A0Z == EnumC0470q2.VALUE_EMBEDDED_OBJECT) {
                Object A0R = ww.A0R();
                if (A0R == null) {
                    return null;
                }
                if (A0R instanceof byte[]) {
                    return (byte[]) A0R;
                }
            }
            if (ww.A0F()) {
                N3 A0G = wn.A0G();
                W3 w3 = A0G.A01;
                if (w3 == null) {
                    w3 = new W3();
                    A0G.A01 = w3;
                }
                byte[] bArr = (byte[]) w3.A00();
                int i = 0;
                while (true) {
                    EnumC0470q2 A0a = ww.A0a();
                    if (A0a == EnumC0470q2.END_ARRAY) {
                        return (byte[]) w3.A03(bArr, i);
                    }
                    if (A0a != EnumC0470q2.VALUE_NUMBER_INT && A0a != EnumC0470q2.VALUE_NUMBER_FLOAT) {
                        if (A0a != EnumC0470q2.VALUE_NULL) {
                            break;
                        }
                        A0B = 0;
                    } else {
                        A0B = ww.A0B();
                    }
                    if (i >= bArr.length) {
                        bArr = (byte[]) w3.A02(bArr, i);
                        i = 0;
                    }
                    bArr[i] = A0B;
                    i++;
                }
            } else if (A0Z == q2Var && wn.A0L(EnumC0225Wm.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && ww.A0d().length() == 0) {
                return null;
            } else {
                if (wn.A0L(EnumC0225Wm.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    EnumC0470q2 A0Z2 = ww.A0Z();
                    if (A0Z2 == EnumC0470q2.VALUE_NUMBER_INT || A0Z2 == EnumC0470q2.VALUE_NUMBER_FLOAT) {
                        A0B2 = ww.A0B();
                    } else if (A0Z2 == EnumC0470q2.VALUE_NULL) {
                        A0B2 = 0;
                    }
                    return new byte[]{A0B2};
                }
                cls = this._valueClass;
                throw wn.A08(cls);
            }
            cls = this._valueClass.getComponentType();
            throw wn.A08(cls);
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
        public final char[] A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            Class<?> cls;
            String str;
            EnumC0470q2 A0Z = ww.A0Z();
            if (A0Z == EnumC0470q2.VALUE_STRING) {
                char[] A0j = ww.A0j();
                int A0V = ww.A0V();
                int A0U = ww.A0U();
                char[] cArr = new char[A0U];
                System.arraycopy(A0j, A0V, cArr, 0, A0U);
                return cArr;
            }
            if (ww.A0F()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    EnumC0470q2 A0a = ww.A0a();
                    if (A0a != EnumC0470q2.END_ARRAY) {
                        if (A0a != EnumC0470q2.VALUE_STRING) {
                            cls = Character.TYPE;
                            break;
                        }
                        String A0d = ww.A0d();
                        int length = A0d.length();
                        if (length == 1) {
                            sb.append(A0d.charAt(0));
                        } else {
                            throw C0223Wj.A00(ww, AnonymousClass06.A02("Can not convert a JSON String of length ", length, " into a char element of char array"));
                        }
                    } else {
                        str = sb.toString();
                        break;
                    }
                }
            } else {
                if (A0Z == EnumC0470q2.VALUE_EMBEDDED_OBJECT) {
                    Object A0R = ww.A0R();
                    if (A0R == null) {
                        return null;
                    }
                    if (A0R instanceof char[]) {
                        return (char[]) A0R;
                    }
                    if (A0R instanceof String) {
                        str = (String) A0R;
                    } else if (A0R instanceof byte[]) {
                        str = C0466pr.A01.A02((byte[]) A0R, false);
                    }
                }
                cls = this._valueClass;
                throw wn.A08(cls);
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
        public final double[] A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            if (ww.A0F()) {
                N3 A0G = wn.A0G();
                W2 w2 = A0G.A02;
                if (w2 == null) {
                    w2 = new W2();
                    A0G.A02 = w2;
                }
                double[] dArr = (double[]) w2.A00();
                int i = 0;
                while (ww.A0a() != EnumC0470q2.END_ARRAY) {
                    double A0F = A0F(ww, wn);
                    if (i >= dArr.length) {
                        dArr = (double[]) w2.A02(dArr, i);
                        i = 0;
                    }
                    dArr[i] = A0F;
                    i++;
                }
                return (double[]) w2.A03(dArr, i);
            } else if (ww.A0Z() == EnumC0470q2.VALUE_STRING && wn.A0L(EnumC0225Wm.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && ww.A0d().length() == 0) {
                return null;
            } else {
                if (wn.A0L(EnumC0225Wm.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new double[]{A0F(ww, wn)};
                }
                throw wn.A08(this._valueClass);
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
        public final float[] A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            if (ww.A0F()) {
                N3 A0G = wn.A0G();
                W1 w1 = A0G.A03;
                if (w1 == null) {
                    w1 = new W1();
                    A0G.A03 = w1;
                }
                float[] fArr = (float[]) w1.A00();
                int i = 0;
                while (ww.A0a() != EnumC0470q2.END_ARRAY) {
                    float A0G2 = A0G(ww, wn);
                    if (i >= fArr.length) {
                        fArr = (float[]) w1.A02(fArr, i);
                        i = 0;
                    }
                    fArr[i] = A0G2;
                    i++;
                }
                return (float[]) w1.A03(fArr, i);
            } else if (ww.A0Z() == EnumC0470q2.VALUE_STRING && wn.A0L(EnumC0225Wm.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && ww.A0d().length() == 0) {
                return null;
            } else {
                if (wn.A0L(EnumC0225Wm.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new float[]{A0G(ww, wn)};
                }
                throw wn.A08(this._valueClass);
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
        public final int[] A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            if (ww.A0F()) {
                N3 A0G = wn.A0G();
                W0 w0 = A0G.A04;
                if (w0 == null) {
                    w0 = new W0();
                    A0G.A04 = w0;
                }
                int[] iArr = (int[]) w0.A00();
                int i = 0;
                while (ww.A0a() != EnumC0470q2.END_ARRAY) {
                    int A0H = A0H(ww, wn);
                    if (i >= iArr.length) {
                        iArr = (int[]) w0.A02(iArr, i);
                        i = 0;
                    }
                    iArr[i] = A0H;
                    i++;
                }
                return (int[]) w0.A03(iArr, i);
            } else if (ww.A0Z() == EnumC0470q2.VALUE_STRING && wn.A0L(EnumC0225Wm.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && ww.A0d().length() == 0) {
                return null;
            } else {
                if (wn.A0L(EnumC0225Wm.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new int[]{A0H(ww, wn)};
                }
                throw wn.A08(this._valueClass);
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
        public final long[] A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            if (ww.A0F()) {
                N3 A0G = wn.A0G();
                C0215Vz vz = A0G.A05;
                if (vz == null) {
                    vz = new C0215Vz();
                    A0G.A05 = vz;
                }
                long[] jArr = (long[]) vz.A00();
                int i = 0;
                while (ww.A0a() != EnumC0470q2.END_ARRAY) {
                    long A0I = A0I(ww, wn);
                    if (i >= jArr.length) {
                        jArr = (long[]) vz.A02(jArr, i);
                        i = 0;
                    }
                    jArr[i] = A0I;
                    i++;
                }
                return (long[]) vz.A03(jArr, i);
            } else if (ww.A0Z() == EnumC0470q2.VALUE_STRING && wn.A0L(EnumC0225Wm.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && ww.A0d().length() == 0) {
                return null;
            } else {
                if (wn.A0L(EnumC0225Wm.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new long[]{A0I(ww, wn)};
                }
                throw wn.A08(this._valueClass);
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
        public final short[] A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            int i;
            if (ww.A0F()) {
                N3 A0G = wn.A0G();
                C0214Vy vy = A0G.A06;
                if (vy == null) {
                    vy = new C0214Vy();
                    A0G.A06 = vy;
                }
                short[] sArr = (short[]) vy.A00();
                int i2 = 0;
                while (ww.A0a() != EnumC0470q2.END_ARRAY) {
                    i = A0H(ww, wn);
                    if (i >= -32768 && i <= 32767) {
                        short s = (short) i;
                        if (i2 >= sArr.length) {
                            sArr = (short[]) vy.A02(sArr, i2);
                            i2 = 0;
                        }
                        sArr[i2] = s;
                        i2++;
                    }
                }
                return (short[]) vy.A03(sArr, i2);
            } else if (ww.A0Z() == EnumC0470q2.VALUE_STRING && wn.A0L(EnumC0225Wm.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && ww.A0d().length() == 0) {
                return null;
            } else {
                if (wn.A0L(EnumC0225Wm.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    short[] sArr2 = new short[1];
                    i = A0H(ww, wn);
                    if (i >= -32768 && i <= 32767) {
                        sArr2[0] = (short) i;
                        return sArr2;
                    }
                } else {
                    throw wn.A08(this._valueClass);
                }
            }
            throw wn.A0D(String.valueOf(i), this._valueClass, "overflow, value can not be represented as 16-bit value");
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return v4.A08(ww, wn);
    }

    public PrimitiveArrayDeserializers(Class<T> cls) {
        super((Class<?>) cls);
    }
}
