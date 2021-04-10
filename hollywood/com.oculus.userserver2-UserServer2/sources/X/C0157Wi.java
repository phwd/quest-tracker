package X;

import com.squareup.okhttp.internal.framed.Hpack;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/* renamed from: X.Wi  reason: case insensitive filesystem */
public final class C0157Wi implements Closeable {
    public static final Logger A04 = Logger.getLogger(C0161Wm.class.getName());
    public final C0164Wp A00;
    public final Dp A01;
    public final boolean A02;
    public final E9 A03;

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:207:0x035c */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [int] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [short] */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x028c, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:?, code lost:
        r3.A04 = true;
        r0 = r3.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0291, code lost:
        if (r0 != null) goto L_0x029e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0293, code lost:
        r3.A02 = r12;
        r4 = r3.A07();
        r3.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x029c, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x029e, code lost:
        r1 = new java.util.ArrayList();
        r1.addAll(r0);
        r1.add(null);
        r1.addAll(r12);
        r3.A02 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x02b0, code lost:
        if (r4 != false) goto L_0x02b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x02b2, code lost:
        r3.A07.A02(r3.A06);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02b9, code lost:
        if (r13 == false) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x02bb, code lost:
        r3.A04();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x02be, code lost:
        return true;
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A02(boolean r22, X.EA r23) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1356
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0157Wi.A02(boolean, X.EA):boolean");
    }

    public static int A00(int i, byte b, short s) throws IOException {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        C0161Wm.A01("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    private List<C0165Wq> A01(int i, short s, byte b, int i2) throws IOException {
        String str;
        List<C0165Wq> list;
        C0165Wq wq;
        WM A012;
        C0165Wq wq2;
        C0165Wq wq3;
        WM wm;
        int A002;
        E9 e9 = this.A03;
        e9.A02 = i;
        e9.A03 = i;
        e9.A00 = s;
        e9.A01 = b;
        e9.A04 = i2;
        C0164Wp wp = this.A00;
        while (true) {
            Dp dp = wp.A07;
            if (!dp.A1V()) {
                int readByte = dp.readByte() & 255;
                if (readByte == 128) {
                    str = "index == 0";
                    break;
                }
                if ((readByte & 128) == 128) {
                    A002 = C0164Wp.A00(wp, readByte, Hpack.PREFIX_7_BITS) - 1;
                    if (A002 >= 0) {
                        C0165Wq[] wqArr = C0162Wn.A01;
                        if (A002 <= wqArr.length - 1) {
                            wp.A06.add(wqArr[A002]);
                        }
                    }
                    int length = wp.A03 + 1 + (A002 - C0162Wn.A01.length);
                    if (length < 0) {
                        break;
                    }
                    C0165Wq[] wqArr2 = wp.A04;
                    if (length > wqArr2.length - 1) {
                        break;
                    }
                    list = wp.A06;
                    wq = wqArr2[length];
                } else {
                    if (readByte == 64) {
                        wm = C0164Wp.A01(wp);
                        C0162Wn.A00(wm);
                    } else if ((readByte & 64) == 64) {
                        int A003 = C0164Wp.A00(wp, readByte, 63) - 1;
                        if (A003 >= 0) {
                            C0165Wq[] wqArr3 = C0162Wn.A01;
                            if (A003 <= wqArr3.length - 1) {
                                wq3 = wqArr3[A003];
                                wm = wq3.A01;
                            }
                        }
                        wq3 = wp.A04[wp.A03 + 1 + (A003 - C0162Wn.A01.length)];
                        wm = wq3.A01;
                    } else if ((readByte & 32) == 32) {
                        int A004 = C0164Wp.A00(wp, readByte, 31);
                        wp.A02 = A004;
                        if (A004 < 0 || A004 > wp.A05) {
                            str = AnonymousClass06.A01("Invalid dynamic table size update ", A004);
                        } else {
                            int i3 = wp.A00;
                            if (A004 < i3) {
                                if (A004 == 0) {
                                    Arrays.fill(wp.A04, (Object) null);
                                    wp.A03 = wp.A04.length - 1;
                                    wp.A01 = 0;
                                    wp.A00 = 0;
                                } else {
                                    C0164Wp.A02(wp, i3 - A004);
                                }
                            }
                        }
                    } else {
                        if (readByte == 16 || readByte == 0) {
                            A012 = C0164Wp.A01(wp);
                            C0162Wn.A00(A012);
                        } else {
                            int A005 = C0164Wp.A00(wp, readByte, 15) - 1;
                            if (A005 >= 0) {
                                C0165Wq[] wqArr4 = C0162Wn.A01;
                                if (A005 <= wqArr4.length - 1) {
                                    wq2 = wqArr4[A005];
                                    A012 = wq2.A01;
                                }
                            }
                            wq2 = wp.A04[wp.A03 + 1 + (A005 - C0162Wn.A01.length)];
                            A012 = wq2.A01;
                        }
                        WM A013 = C0164Wp.A01(wp);
                        list = wp.A06;
                        wq = new C0165Wq(A012, A013);
                    }
                    C0164Wp.A03(wp, new C0165Wq(wm, C0164Wp.A01(wp)));
                }
                list.add(wq);
            } else {
                List<C0165Wq> list2 = wp.A06;
                ArrayList arrayList = new ArrayList(list2);
                list2.clear();
                return arrayList;
            }
        }
        str = AnonymousClass06.A01("Header index too large ", A002 + 1);
        throw new IOException(str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01.close();
    }

    public C0157Wi(Dp dp, boolean z) {
        this.A01 = dp;
        this.A02 = z;
        E9 e9 = new E9(dp);
        this.A03 = e9;
        this.A00 = new C0164Wp(e9);
    }
}
