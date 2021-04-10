package X;

import com.facebook.FacebookSdk;
import com.squareup.okhttp.internal.framed.Hpack;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/* renamed from: X.0va  reason: invalid class name and case insensitive filesystem */
public final class C07910va implements Closeable {
    public static final Logger A04 = Logger.getLogger(C07950ve.class.getName());
    public final C07980vh A00;
    public final AnonymousClass0Lw A01;
    public final boolean A02;
    public final AnonymousClass0MD A03;

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:207:0x036a */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [int] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [short] */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0294, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:?, code lost:
        r3.A04 = true;
        r0 = r3.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0299, code lost:
        if (r0 != null) goto L_0x02a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x029b, code lost:
        r3.A02 = r11;
        r4 = r3.A07();
        r3.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x02a4, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x02a6, code lost:
        r1 = new java.util.ArrayList();
        r1.addAll(r0);
        r1.add(null);
        r1.addAll(r11);
        r3.A02 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x02b8, code lost:
        if (r4 != false) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x02ba, code lost:
        r3.A07.A02(r3.A06);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02c1, code lost:
        if (r12 == false) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x02c3, code lost:
        r3.A04();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x02c6, code lost:
        return true;
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A02(boolean r22, X.AnonymousClass0ME r23) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1380
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C07910va.A02(boolean, X.0ME):boolean");
    }

    public static int A00(int i, byte b, short s) throws IOException {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        C07950ve.A01("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    private List<C07990vi> A01(int i, short s, byte b, int i2) throws IOException {
        String str;
        List<C07990vi> list;
        C07990vi r0;
        C07700vD A012;
        C07990vi r02;
        C07990vi r03;
        C07700vD r2;
        int A002;
        AnonymousClass0MD r04 = this.A03;
        r04.A02 = i;
        r04.A03 = i;
        r04.A00 = s;
        r04.A01 = b;
        r04.A04 = i2;
        C07980vh r4 = this.A00;
        while (true) {
            AnonymousClass0Lw r1 = r4.A07;
            if (!r1.A2a()) {
                int readByte = r1.readByte() & 255;
                if (readByte == 128) {
                    str = "index == 0";
                    break;
                }
                if ((readByte & FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE) == 128) {
                    A002 = C07980vh.A00(r4, readByte, Hpack.PREFIX_7_BITS) - 1;
                    if (A002 >= 0) {
                        C07990vi[] r12 = C07960vf.A01;
                        if (A002 <= r12.length - 1) {
                            r4.A06.add(r12[A002]);
                        }
                    }
                    int length = r4.A03 + 1 + (A002 - C07960vf.A01.length);
                    if (length < 0) {
                        break;
                    }
                    C07990vi[] r22 = r4.A04;
                    if (length > r22.length - 1) {
                        break;
                    }
                    list = r4.A06;
                    r0 = r22[length];
                } else {
                    if (readByte == 64) {
                        r2 = C07980vh.A01(r4);
                        C07960vf.A00(r2);
                    } else if ((readByte & 64) == 64) {
                        int A003 = C07980vh.A00(r4, readByte, 63) - 1;
                        if (A003 >= 0) {
                            C07990vi[] r13 = C07960vf.A01;
                            if (A003 <= r13.length - 1) {
                                r03 = r13[A003];
                                r2 = r03.A01;
                            }
                        }
                        r03 = r4.A04[r4.A03 + 1 + (A003 - C07960vf.A01.length)];
                        r2 = r03.A01;
                    } else if ((readByte & 32) == 32) {
                        int A004 = C07980vh.A00(r4, readByte, 31);
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
                                    C07980vh.A02(r4, i3 - A004);
                                }
                            }
                        }
                    } else {
                        if (readByte == 16 || readByte == 0) {
                            A012 = C07980vh.A01(r4);
                            C07960vf.A00(A012);
                        } else {
                            int A005 = C07980vh.A00(r4, readByte, 15) - 1;
                            if (A005 >= 0) {
                                C07990vi[] r14 = C07960vf.A01;
                                if (A005 <= r14.length - 1) {
                                    r02 = r14[A005];
                                    A012 = r02.A01;
                                }
                            }
                            r02 = r4.A04[r4.A03 + 1 + (A005 - C07960vf.A01.length)];
                            A012 = r02.A01;
                        }
                        C07700vD A013 = C07980vh.A01(r4);
                        list = r4.A06;
                        r0 = new C07990vi(A012, A013);
                    }
                    C07980vh.A03(r4, new C07990vi(r2, C07980vh.A01(r4)));
                }
                list.add(r0);
            } else {
                List<C07990vi> list2 = r4.A06;
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

    public C07910va(AnonymousClass0Lw r3, boolean z) {
        this.A01 = r3;
        this.A02 = z;
        AnonymousClass0MD r1 = new AnonymousClass0MD(r3);
        this.A03 = r1;
        this.A00 = new C07980vh(r1);
    }
}
