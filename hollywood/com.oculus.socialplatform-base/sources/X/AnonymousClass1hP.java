package X;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: X.1hP  reason: invalid class name */
public class AnonymousClass1hP {
    public final int A00;
    public final int A01;
    public final byte[] A02;

    public static AnonymousClass1hP A00(int i, ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[(AnonymousClass1hO.A0Z[3] * 1)]);
        wrap.order(byteOrder);
        wrap.putShort((short) new int[]{i}[0]);
        return new AnonymousClass1hP(3, 1, wrap.array());
    }

    public static AnonymousClass1hP A01(long j, ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[(AnonymousClass1hO.A0Z[4] * 1)]);
        wrap.order(byteOrder);
        wrap.putInt((int) new long[]{j}[0]);
        return new AnonymousClass1hP(4, 1, wrap.array());
    }

    public static AnonymousClass1hP A02(AnonymousClass1hS r7, ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[(AnonymousClass1hO.A0Z[5] * 1)]);
        wrap.order(byteOrder);
        AnonymousClass1hS r3 = new AnonymousClass1hS[]{r7}[0];
        wrap.putInt((int) r3.A01);
        wrap.putInt((int) r3.A00);
        return new AnonymousClass1hP(5, 1, wrap.array());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0179, code lost:
        if (r6 != null) goto L_0x017b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0187 A[SYNTHETIC, Splitter:B:134:0x0187] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object A04(java.nio.ByteOrder r14) {
        /*
        // Method dump skipped, instructions count: 428
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1hP.A04(java.nio.ByteOrder):java.lang.Object");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(AnonymousClass1hO.A0F[this.A00]);
        sb.append(", data length:");
        sb.append(this.A02.length);
        sb.append(")");
        return sb.toString();
    }

    public AnonymousClass1hP(int i, int i2, byte[] bArr) {
        this.A00 = i;
        this.A01 = i2;
        this.A02 = bArr;
    }

    public final int A03(ByteOrder byteOrder) {
        Object A04 = A04(byteOrder);
        if (A04 == null) {
            throw new NumberFormatException("NULL can't be converted to a integer value");
        } else if (A04 instanceof String) {
            return Integer.parseInt((String) A04);
        } else {
            if (A04 instanceof long[]) {
                long[] jArr = (long[]) A04;
                if (jArr.length == 1) {
                    return (int) jArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else if (A04 instanceof int[]) {
                int[] iArr = (int[]) A04;
                if (iArr.length == 1) {
                    return iArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else {
                throw new NumberFormatException("Couldn't find a integer value");
            }
        }
    }

    public final String A05(ByteOrder byteOrder) {
        Object A04 = A04(byteOrder);
        if (A04 != null) {
            if (A04 instanceof String) {
                return (String) A04;
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            if (!(A04 instanceof long[])) {
                if (!(A04 instanceof int[])) {
                    if (!(A04 instanceof double[])) {
                        if (A04 instanceof AnonymousClass1hS[]) {
                            AnonymousClass1hS[] r7 = (AnonymousClass1hS[]) A04;
                            while (true) {
                                int length = r7.length;
                                if (i >= length) {
                                    break;
                                }
                                AnonymousClass1hS r2 = r7[i];
                                sb.append(r2.A01);
                                sb.append('/');
                                sb.append(r2.A00);
                                i++;
                                if (i != length) {
                                    sb.append(",");
                                }
                            }
                        }
                    } else {
                        double[] dArr = (double[]) A04;
                        while (true) {
                            int length2 = dArr.length;
                            if (i >= length2) {
                                break;
                            }
                            sb.append(dArr[i]);
                            i++;
                            if (i != length2) {
                                sb.append(",");
                            }
                        }
                    }
                } else {
                    int[] iArr = (int[]) A04;
                    while (true) {
                        int length3 = iArr.length;
                        if (i >= length3) {
                            break;
                        }
                        sb.append(iArr[i]);
                        i++;
                        if (i != length3) {
                            sb.append(",");
                        }
                    }
                }
            } else {
                long[] jArr = (long[]) A04;
                while (true) {
                    int length4 = jArr.length;
                    if (i >= length4) {
                        break;
                    }
                    sb.append(jArr[i]);
                    i++;
                    if (i != length4) {
                        sb.append(",");
                    }
                }
            }
            return sb.toString();
        }
        return null;
    }
}
