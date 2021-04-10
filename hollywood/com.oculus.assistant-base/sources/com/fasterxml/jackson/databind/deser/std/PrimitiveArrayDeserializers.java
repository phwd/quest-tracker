package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AnonymousClass08;
import X.C1025qv;
import X.C1065rh;
import X.C1066ri;
import X.C1067rj;
import X.C1068rk;
import X.C1069rl;
import X.C1070rm;
import X.C1071rn;
import X.EnumC1023qs;
import X.NQ;
import X.NX;
import X.Q3;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

public abstract class PrimitiveArrayDeserializers extends StdDeserializer {

    @JacksonStdImpl
    public final class BooleanDeser extends PrimitiveArrayDeserializers {
        public static final long serialVersionUID = 1;

        public BooleanDeser() {
            super(boolean[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final boolean[] A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            if (qiVar.A0i()) {
                Q3 A0G = qrVar.A0G();
                C1065rh rhVar = A0G.A00;
                if (rhVar == null) {
                    rhVar = new C1065rh();
                    A0G.A00 = rhVar;
                }
                boolean[] zArr = (boolean[]) rhVar.A00();
                int i = 0;
                while (qiVar.A0o() != NX.END_ARRAY) {
                    boolean A0M = A0M(qiVar, qrVar);
                    if (i >= zArr.length) {
                        zArr = (boolean[]) rhVar.A02(zArr, i);
                        i = 0;
                    }
                    zArr[i] = A0M;
                    i++;
                }
                return (boolean[]) rhVar.A03(zArr, i);
            } else if (qiVar.A0U() == NX.VALUE_STRING && qrVar.A0O(EnumC1023qs.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && qiVar.A0p().length() == 0) {
                return null;
            } else {
                if (qrVar.A0O(EnumC1023qs.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new boolean[]{A0M(qiVar, qrVar)};
                }
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @JacksonStdImpl
    public final class ByteDeser extends PrimitiveArrayDeserializers {
        public static final long serialVersionUID = 1;

        public ByteDeser() {
            super(byte[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final byte[] A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            byte A0E;
            byte A0E2;
            NX A0U = qiVar.A0U();
            NX nx = NX.VALUE_STRING;
            if (A0U == nx) {
                return qiVar.A0l(qrVar._config._base._defaultBase64);
            }
            if (A0U == NX.VALUE_EMBEDDED_OBJECT) {
                Object A0Z = qiVar.A0Z();
                if (A0Z == null) {
                    return null;
                }
                if (A0Z instanceof byte[]) {
                    return (byte[]) A0Z;
                }
            }
            if (qiVar.A0i()) {
                Q3 A0G = qrVar.A0G();
                C1066ri riVar = A0G.A01;
                if (riVar == null) {
                    riVar = new C1066ri();
                    A0G.A01 = riVar;
                }
                byte[] bArr = (byte[]) riVar.A00();
                int i = 0;
                while (true) {
                    NX A0o = qiVar.A0o();
                    if (A0o == NX.END_ARRAY) {
                        return (byte[]) riVar.A03(bArr, i);
                    }
                    if (A0o == NX.VALUE_NUMBER_INT || A0o == NX.VALUE_NUMBER_FLOAT) {
                        A0E = qiVar.A0E();
                    } else if (A0o == NX.VALUE_NULL) {
                        A0E = 0;
                    } else {
                        this._valueClass.getComponentType();
                        qrVar.A0J();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    if (i >= bArr.length) {
                        bArr = (byte[]) riVar.A02(bArr, i);
                        i = 0;
                    }
                    bArr[i] = A0E;
                    i++;
                }
            } else if (A0U == nx && qrVar.A0O(EnumC1023qs.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && qiVar.A0p().length() == 0) {
                return null;
            } else {
                if (qrVar.A0O(EnumC1023qs.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    NX A0U2 = qiVar.A0U();
                    if (A0U2 == NX.VALUE_NUMBER_INT || A0U2 == NX.VALUE_NUMBER_FLOAT) {
                        A0E2 = qiVar.A0E();
                    } else if (A0U2 == NX.VALUE_NULL) {
                        A0E2 = 0;
                    } else {
                        this._valueClass.getComponentType();
                        qrVar.A0J();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    return new byte[]{A0E2};
                }
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @JacksonStdImpl
    public final class CharDeser extends PrimitiveArrayDeserializers {
        public static final long serialVersionUID = 1;

        public CharDeser() {
            super(char[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final char[] A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            String str;
            NX A0U = qiVar.A0U();
            if (A0U == NX.VALUE_STRING) {
                char[] A0m = qiVar.A0m();
                int A0L = qiVar.A0L();
                int A0K = qiVar.A0K();
                char[] cArr = new char[A0K];
                System.arraycopy(A0m, A0L, cArr, 0, A0K);
                return cArr;
            }
            if (qiVar.A0i()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    NX A0o = qiVar.A0o();
                    if (A0o == NX.END_ARRAY) {
                        str = sb.toString();
                        break;
                    } else if (A0o == NX.VALUE_STRING) {
                        String A0p = qiVar.A0p();
                        int length = A0p.length();
                        if (length == 1) {
                            sb.append(A0p.charAt(0));
                        } else {
                            throw C1025qv.A00(qiVar, AnonymousClass08.A01("Can not convert a JSON String of length ", length, " into a char element of char array"));
                        }
                    } else {
                        qrVar.A0J();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else {
                if (A0U == NX.VALUE_EMBEDDED_OBJECT) {
                    Object A0Z = qiVar.A0Z();
                    if (A0Z == null) {
                        return null;
                    }
                    if (A0Z instanceof char[]) {
                        return (char[]) A0Z;
                    }
                    if (A0Z instanceof String) {
                        str = (String) A0Z;
                    } else if (A0Z instanceof byte[]) {
                        str = NQ.A01.A02((byte[]) A0Z, false);
                    }
                }
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
            return str.toCharArray();
        }
    }

    @JacksonStdImpl
    public final class DoubleDeser extends PrimitiveArrayDeserializers {
        public static final long serialVersionUID = 1;

        public DoubleDeser() {
            super(double[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final double[] A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            if (qiVar.A0i()) {
                Q3 A0G = qrVar.A0G();
                C1067rj rjVar = A0G.A02;
                if (rjVar == null) {
                    rjVar = new C1067rj();
                    A0G.A02 = rjVar;
                }
                double[] dArr = (double[]) rjVar.A00();
                int i = 0;
                while (qiVar.A0o() != NX.END_ARRAY) {
                    double A0D = A0D(qiVar, qrVar);
                    if (i >= dArr.length) {
                        dArr = (double[]) rjVar.A02(dArr, i);
                        i = 0;
                    }
                    dArr[i] = A0D;
                    i++;
                }
                return (double[]) rjVar.A03(dArr, i);
            } else if (qiVar.A0U() == NX.VALUE_STRING && qrVar.A0O(EnumC1023qs.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && qiVar.A0p().length() == 0) {
                return null;
            } else {
                if (qrVar.A0O(EnumC1023qs.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new double[]{A0D(qiVar, qrVar)};
                }
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @JacksonStdImpl
    public final class FloatDeser extends PrimitiveArrayDeserializers {
        public static final long serialVersionUID = 1;

        public FloatDeser() {
            super(float[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final float[] A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            if (qiVar.A0i()) {
                Q3 A0G = qrVar.A0G();
                C1068rk rkVar = A0G.A03;
                if (rkVar == null) {
                    rkVar = new C1068rk();
                    A0G.A03 = rkVar;
                }
                float[] fArr = (float[]) rkVar.A00();
                int i = 0;
                while (qiVar.A0o() != NX.END_ARRAY) {
                    float A0E = A0E(qiVar, qrVar);
                    if (i >= fArr.length) {
                        fArr = (float[]) rkVar.A02(fArr, i);
                        i = 0;
                    }
                    fArr[i] = A0E;
                    i++;
                }
                return (float[]) rkVar.A03(fArr, i);
            } else if (qiVar.A0U() == NX.VALUE_STRING && qrVar.A0O(EnumC1023qs.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && qiVar.A0p().length() == 0) {
                return null;
            } else {
                if (qrVar.A0O(EnumC1023qs.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new float[]{A0E(qiVar, qrVar)};
                }
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @JacksonStdImpl
    public final class IntDeser extends PrimitiveArrayDeserializers {
        public static final IntDeser A00 = new IntDeser();
        public static final long serialVersionUID = 1;

        public IntDeser() {
            super(int[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final int[] A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            if (qiVar.A0i()) {
                Q3 A0G = qrVar.A0G();
                C1069rl rlVar = A0G.A04;
                if (rlVar == null) {
                    rlVar = new C1069rl();
                    A0G.A04 = rlVar;
                }
                int[] iArr = (int[]) rlVar.A00();
                int i = 0;
                while (qiVar.A0o() != NX.END_ARRAY) {
                    int A0F = A0F(qiVar, qrVar);
                    if (i >= iArr.length) {
                        iArr = (int[]) rlVar.A02(iArr, i);
                        i = 0;
                    }
                    iArr[i] = A0F;
                    i++;
                }
                return (int[]) rlVar.A03(iArr, i);
            } else if (qiVar.A0U() == NX.VALUE_STRING && qrVar.A0O(EnumC1023qs.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && qiVar.A0p().length() == 0) {
                return null;
            } else {
                if (qrVar.A0O(EnumC1023qs.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new int[]{A0F(qiVar, qrVar)};
                }
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @JacksonStdImpl
    public final class LongDeser extends PrimitiveArrayDeserializers {
        public static final LongDeser A00 = new LongDeser();
        public static final long serialVersionUID = 1;

        public LongDeser() {
            super(long[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final long[] A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            if (qiVar.A0i()) {
                Q3 A0G = qrVar.A0G();
                C1070rm rmVar = A0G.A05;
                if (rmVar == null) {
                    rmVar = new C1070rm();
                    A0G.A05 = rmVar;
                }
                long[] jArr = (long[]) rmVar.A00();
                int i = 0;
                while (qiVar.A0o() != NX.END_ARRAY) {
                    long A0G2 = A0G(qiVar, qrVar);
                    if (i >= jArr.length) {
                        jArr = (long[]) rmVar.A02(jArr, i);
                        i = 0;
                    }
                    jArr[i] = A0G2;
                    i++;
                }
                return (long[]) rmVar.A03(jArr, i);
            } else if (qiVar.A0U() == NX.VALUE_STRING && qrVar.A0O(EnumC1023qs.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && qiVar.A0p().length() == 0) {
                return null;
            } else {
                if (qrVar.A0O(EnumC1023qs.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    return new long[]{A0G(qiVar, qrVar)};
                }
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @JacksonStdImpl
    public final class ShortDeser extends PrimitiveArrayDeserializers {
        public static final long serialVersionUID = 1;

        public ShortDeser() {
            super(short[].class);
        }

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final short[] A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            if (qiVar.A0i()) {
                Q3 A0G = qrVar.A0G();
                C1071rn rnVar = A0G.A06;
                if (rnVar == null) {
                    rnVar = new C1071rn();
                    A0G.A06 = rnVar;
                }
                short[] sArr = (short[]) rnVar.A00();
                int i = 0;
                while (qiVar.A0o() != NX.END_ARRAY) {
                    int A0F = A0F(qiVar, qrVar);
                    if (A0F < -32768 || A0F > 32767) {
                        qrVar.A0L(this._valueClass);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    short s = (short) A0F;
                    if (i >= sArr.length) {
                        sArr = (short[]) rnVar.A02(sArr, i);
                        i = 0;
                    }
                    sArr[i] = s;
                    i++;
                }
                return (short[]) rnVar.A03(sArr, i);
            } else if (qiVar.A0U() == NX.VALUE_STRING && qrVar.A0O(EnumC1023qs.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && qiVar.A0p().length() == 0) {
                return null;
            } else {
                if (qrVar.A0O(EnumC1023qs.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                    short[] sArr2 = new short[1];
                    int A0F2 = A0F(qiVar, qrVar);
                    if (A0F2 < -32768 || A0F2 > 32767) {
                        qrVar.A0L(this._valueClass);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    sArr2[0] = (short) A0F2;
                    return sArr2;
                }
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    public PrimitiveArrayDeserializers(Class cls) {
        super(cls);
    }
}
