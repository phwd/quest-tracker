package X;

import java.util.Arrays;

public final class DH extends C0483aS {
    public static final boolean A02(Object[] objArr, Object[] objArr2) {
        int length;
        boolean equals;
        if (objArr != objArr2) {
            if (!(objArr == null || objArr2 == null || (length = objArr.length) != objArr2.length)) {
                for (int i = 0; i < length; i++) {
                    Object obj = objArr[i];
                    Object obj2 = objArr2[i];
                    if (obj != obj2) {
                        if (!(obj == null || obj2 == null)) {
                            if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
                                equals = A02((Object[]) obj, (Object[]) obj2);
                            } else if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
                                equals = Arrays.equals((byte[]) obj, (byte[]) obj2);
                            } else if ((obj instanceof short[]) && (obj2 instanceof short[])) {
                                equals = Arrays.equals((short[]) obj, (short[]) obj2);
                            } else if ((obj instanceof int[]) && (obj2 instanceof int[])) {
                                equals = Arrays.equals((int[]) obj, (int[]) obj2);
                            } else if ((obj instanceof long[]) && (obj2 instanceof long[])) {
                                equals = Arrays.equals((long[]) obj, (long[]) obj2);
                            } else if ((obj instanceof float[]) && (obj2 instanceof float[])) {
                                equals = Arrays.equals((float[]) obj, (float[]) obj2);
                            } else if ((obj instanceof double[]) && (obj2 instanceof double[])) {
                                equals = Arrays.equals((double[]) obj, (double[]) obj2);
                            } else if ((obj instanceof char[]) && (obj2 instanceof char[])) {
                                equals = Arrays.equals((char[]) obj, (char[]) obj2);
                            } else if ((obj instanceof boolean[]) && (obj2 instanceof boolean[])) {
                                equals = Arrays.equals((boolean[]) obj, (boolean[]) obj2);
                            } else if ((obj instanceof C0470aF) && (obj2 instanceof C0470aF)) {
                                byte[] bArr = ((C0470aF) obj).A00;
                                byte[] bArr2 = ((C0470aF) obj2).A00;
                                byte[] bArr3 = bArr2;
                                if (bArr == null) {
                                    bArr = null;
                                }
                                if (bArr2 == null) {
                                    bArr3 = null;
                                }
                                equals = Arrays.equals(bArr, bArr3);
                            } else if ((obj instanceof C0480aP) && (obj2 instanceof C0480aP)) {
                                short[] sArr = ((C0480aP) obj).A00;
                                short[] sArr2 = ((C0480aP) obj2).A00;
                                short[] sArr3 = sArr2;
                                if (sArr == null) {
                                    sArr = null;
                                }
                                if (sArr2 == null) {
                                    sArr3 = null;
                                }
                                equals = Arrays.equals(sArr, sArr3);
                            } else if ((obj instanceof C0473aI) && (obj2 instanceof C0473aI)) {
                                int[] iArr = ((C0473aI) obj).A00;
                                int[] iArr2 = ((C0473aI) obj2).A00;
                                int[] iArr3 = iArr2;
                                if (iArr == null) {
                                    iArr = null;
                                }
                                if (iArr2 == null) {
                                    iArr3 = null;
                                }
                                equals = Arrays.equals(iArr, iArr3);
                            } else if ((obj instanceof C0476aL) && (obj2 instanceof C0476aL)) {
                                long[] jArr = ((C0476aL) obj).A00;
                                long[] jArr2 = ((C0476aL) obj2).A00;
                                long[] jArr3 = jArr2;
                                if (jArr == null) {
                                    jArr = null;
                                }
                                if (jArr2 == null) {
                                    jArr3 = null;
                                }
                                equals = Arrays.equals(jArr, jArr3);
                            } else if (!C0514bB.A05(obj, obj2)) {
                                return false;
                            }
                            if (!equals) {
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public static /* synthetic */ String A00(Iterable iterable) {
        CharSequence charSequence;
        C0514bB.A02(iterable, "$this$joinToString");
        StringBuilder sb = new StringBuilder();
        C0514bB.A02(iterable, "$this$joinTo");
        sb.append((CharSequence) "[");
        int i = 0;
        for (Object obj : iterable) {
            i++;
            if (i > 1) {
                sb.append((CharSequence) ", ");
            }
            if (obj == null || (obj instanceof CharSequence)) {
                charSequence = (CharSequence) obj;
            } else if (obj instanceof Character) {
                sb.append(((Character) obj).charValue());
            } else {
                charSequence = String.valueOf(obj);
            }
            sb.append(charSequence);
        }
        sb.append((CharSequence) "]");
        String obj2 = sb.toString();
        C0514bB.A01(obj2, "joinTo(StringBuilder(), …ed, transform).toString()");
        return obj2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a5, code lost:
        if (r0 != null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00bb, code lost:
        if (r0 != null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00d1, code lost:
        if (r0 != null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00e8, code lost:
        if (r0 != null) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A01(java.lang.Object[] r5, java.lang.StringBuilder r6, java.util.List r7) {
        /*
        // Method dump skipped, instructions count: 257
        */
        throw new UnsupportedOperationException("Method not decompiled: X.DH.A01(java.lang.Object[], java.lang.StringBuilder, java.util.List):void");
    }
}
