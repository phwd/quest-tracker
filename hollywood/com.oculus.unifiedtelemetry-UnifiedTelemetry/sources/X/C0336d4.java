package X;

import com.squareup.okhttp.internal.framed.Hpack;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/* renamed from: X.d4  reason: case insensitive filesystem */
public final class C0336d4 implements Closeable {
    public static final Logger A04 = Logger.getLogger(C0340d8.class.getName());
    public final C0342dB A00;
    public final KC A01;
    public final boolean A02;
    public final C0113Kj A03;

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
    public final boolean A02(boolean r22, X.C0114Kk r23) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1356
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0336d4.A02(boolean, X.Kk):boolean");
    }

    public static int A00(int i, byte b, short s) throws IOException {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        C0340d8.A01("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    private List<C0343dC> A01(int i, short s, byte b, int i2) throws IOException {
        String str;
        List<C0343dC> list;
        C0343dC dCVar;
        ci A012;
        C0343dC dCVar2;
        C0343dC dCVar3;
        ci ciVar;
        int A002;
        C0113Kj kj = this.A03;
        kj.A02 = i;
        kj.A03 = i;
        kj.A00 = s;
        kj.A01 = b;
        kj.A04 = i2;
        C0342dB dBVar = this.A00;
        while (true) {
            KC kc = dBVar.A07;
            if (!kc.A1z()) {
                int readByte = kc.readByte() & 255;
                if (readByte == 128) {
                    str = "index == 0";
                    break;
                }
                if ((readByte & 128) == 128) {
                    A002 = C0342dB.A00(dBVar, readByte, Hpack.PREFIX_7_BITS) - 1;
                    if (A002 >= 0) {
                        C0343dC[] dCVarArr = C0341d9.A01;
                        if (A002 <= dCVarArr.length - 1) {
                            dBVar.A06.add(dCVarArr[A002]);
                        }
                    }
                    int length = dBVar.A03 + 1 + (A002 - C0341d9.A01.length);
                    if (length < 0) {
                        break;
                    }
                    C0343dC[] dCVarArr2 = dBVar.A04;
                    if (length > dCVarArr2.length - 1) {
                        break;
                    }
                    list = dBVar.A06;
                    dCVar = dCVarArr2[length];
                } else {
                    if (readByte == 64) {
                        ciVar = C0342dB.A01(dBVar);
                        C0341d9.A00(ciVar);
                    } else if ((readByte & 64) == 64) {
                        int A003 = C0342dB.A00(dBVar, readByte, 63) - 1;
                        if (A003 >= 0) {
                            C0343dC[] dCVarArr3 = C0341d9.A01;
                            if (A003 <= dCVarArr3.length - 1) {
                                dCVar3 = dCVarArr3[A003];
                                ciVar = dCVar3.A01;
                            }
                        }
                        dCVar3 = dBVar.A04[dBVar.A03 + 1 + (A003 - C0341d9.A01.length)];
                        ciVar = dCVar3.A01;
                    } else if ((readByte & 32) == 32) {
                        int A004 = C0342dB.A00(dBVar, readByte, 31);
                        dBVar.A02 = A004;
                        if (A004 < 0 || A004 > dBVar.A05) {
                            str = AnonymousClass06.A01("Invalid dynamic table size update ", A004);
                        } else {
                            int i3 = dBVar.A00;
                            if (A004 < i3) {
                                if (A004 == 0) {
                                    Arrays.fill(dBVar.A04, (Object) null);
                                    dBVar.A03 = dBVar.A04.length - 1;
                                    dBVar.A01 = 0;
                                    dBVar.A00 = 0;
                                } else {
                                    C0342dB.A02(dBVar, i3 - A004);
                                }
                            }
                        }
                    } else {
                        if (readByte == 16 || readByte == 0) {
                            A012 = C0342dB.A01(dBVar);
                            C0341d9.A00(A012);
                        } else {
                            int A005 = C0342dB.A00(dBVar, readByte, 15) - 1;
                            if (A005 >= 0) {
                                C0343dC[] dCVarArr4 = C0341d9.A01;
                                if (A005 <= dCVarArr4.length - 1) {
                                    dCVar2 = dCVarArr4[A005];
                                    A012 = dCVar2.A01;
                                }
                            }
                            dCVar2 = dBVar.A04[dBVar.A03 + 1 + (A005 - C0341d9.A01.length)];
                            A012 = dCVar2.A01;
                        }
                        ci A013 = C0342dB.A01(dBVar);
                        list = dBVar.A06;
                        dCVar = new C0343dC(A012, A013);
                    }
                    C0342dB.A03(dBVar, new C0343dC(ciVar, C0342dB.A01(dBVar)));
                }
                list.add(dCVar);
            } else {
                List<C0343dC> list2 = dBVar.A06;
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

    public C0336d4(KC kc, boolean z) {
        this.A01 = kc;
        this.A02 = z;
        C0113Kj kj = new C0113Kj(kc);
        this.A03 = kj;
        this.A00 = new C0342dB(kj);
    }
}
