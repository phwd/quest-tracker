package X;

import com.squareup.okhttp.internal.framed.Hpack;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/* renamed from: X.0hT  reason: invalid class name and case insensitive filesystem */
public final class C04770hT implements Closeable {
    public static final Logger A04 = Logger.getLogger(C04810hX.class.getName());
    public final C04850hp A00;
    public final AnonymousClass0Od A01;
    public final boolean A02;
    public final C01960Ow A03;

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
    public final boolean A02(boolean r22, X.AnonymousClass0Ox r23) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1356
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04770hT.A02(boolean, X.0Ox):boolean");
    }

    public static int A00(int i, byte b, short s) throws IOException {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        C04810hX.A01("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    private List<C04870hr> A01(int i, short s, byte b, int i2) throws IOException {
        String str;
        List<C04870hr> list;
        C04870hr r0;
        C04610h7 A012;
        C04870hr r02;
        C04870hr r03;
        C04610h7 r2;
        int A002;
        C01960Ow r04 = this.A03;
        r04.A02 = i;
        r04.A03 = i;
        r04.A00 = s;
        r04.A01 = b;
        r04.A04 = i2;
        C04850hp r4 = this.A00;
        while (true) {
            AnonymousClass0Od r1 = r4.A07;
            if (!r1.A2T()) {
                int readByte = r1.readByte() & 255;
                if (readByte == 128) {
                    str = "index == 0";
                    break;
                }
                if ((readByte & 128) == 128) {
                    A002 = C04850hp.A00(r4, readByte, Hpack.PREFIX_7_BITS) - 1;
                    if (A002 >= 0) {
                        C04870hr[] r12 = C04820hY.A01;
                        if (A002 <= r12.length - 1) {
                            r4.A06.add(r12[A002]);
                        }
                    }
                    int length = r4.A03 + 1 + (A002 - C04820hY.A01.length);
                    if (length < 0) {
                        break;
                    }
                    C04870hr[] r22 = r4.A04;
                    if (length > r22.length - 1) {
                        break;
                    }
                    list = r4.A06;
                    r0 = r22[length];
                } else {
                    if (readByte == 64) {
                        r2 = C04850hp.A01(r4);
                        C04820hY.A00(r2);
                    } else if ((readByte & 64) == 64) {
                        int A003 = C04850hp.A00(r4, readByte, 63) - 1;
                        if (A003 >= 0) {
                            C04870hr[] r13 = C04820hY.A01;
                            if (A003 <= r13.length - 1) {
                                r03 = r13[A003];
                                r2 = r03.A01;
                            }
                        }
                        r03 = r4.A04[r4.A03 + 1 + (A003 - C04820hY.A01.length)];
                        r2 = r03.A01;
                    } else if ((readByte & 32) == 32) {
                        int A004 = C04850hp.A00(r4, readByte, 31);
                        r4.A02 = A004;
                        if (A004 < 0 || A004 > r4.A05) {
                            str = AnonymousClass006.A01("Invalid dynamic table size update ", A004);
                        } else {
                            int i3 = r4.A00;
                            if (A004 < i3) {
                                if (A004 == 0) {
                                    Arrays.fill(r4.A04, (Object) null);
                                    r4.A03 = r4.A04.length - 1;
                                    r4.A01 = 0;
                                    r4.A00 = 0;
                                } else {
                                    C04850hp.A02(r4, i3 - A004);
                                }
                            }
                        }
                    } else {
                        if (readByte == 16 || readByte == 0) {
                            A012 = C04850hp.A01(r4);
                            C04820hY.A00(A012);
                        } else {
                            int A005 = C04850hp.A00(r4, readByte, 15) - 1;
                            if (A005 >= 0) {
                                C04870hr[] r14 = C04820hY.A01;
                                if (A005 <= r14.length - 1) {
                                    r02 = r14[A005];
                                    A012 = r02.A01;
                                }
                            }
                            r02 = r4.A04[r4.A03 + 1 + (A005 - C04820hY.A01.length)];
                            A012 = r02.A01;
                        }
                        C04610h7 A013 = C04850hp.A01(r4);
                        list = r4.A06;
                        r0 = new C04870hr(A012, A013);
                    }
                    C04850hp.A03(r4, new C04870hr(r2, C04850hp.A01(r4)));
                }
                list.add(r0);
            } else {
                List<C04870hr> list2 = r4.A06;
                ArrayList arrayList = new ArrayList(list2);
                list2.clear();
                return arrayList;
            }
        }
        str = AnonymousClass006.A01("Header index too large ", A002 + 1);
        throw new IOException(str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01.close();
    }

    public C04770hT(AnonymousClass0Od r3, boolean z) {
        this.A01 = r3;
        this.A02 = z;
        C01960Ow r1 = new C01960Ow(r3);
        this.A03 = r1;
        this.A00 = new C04850hp(r1);
    }
}
