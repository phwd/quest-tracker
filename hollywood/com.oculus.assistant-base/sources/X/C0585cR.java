package X;

import java.io.Closeable;
import java.util.logging.Logger;

/* renamed from: X.cR  reason: case insensitive filesystem */
public final class C0585cR implements Closeable {
    public static final Logger A04 = Logger.getLogger(C0582cN.class.getName());
    public final C0579cK A00;
    public final t4 A01;
    public final boolean A02;
    public final C1122tK A03;

    public static int A00(int i, byte b, short s) {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        C0582cN.A01("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0115, code lost:
        throw new java.io.IOException(X.AnonymousClass08.A00("Invalid dynamic table size update ", r1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List A01(int r7, short r8, byte r9, int r10) {
        /*
        // Method dump skipped, instructions count: 311
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0585cR.A01(int, short, byte, int):java.util.List");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.A01.close();
    }

    public C0585cR(t4 t4Var, boolean z) {
        this.A01 = t4Var;
        this.A02 = z;
        C1122tK tKVar = new C1122tK(t4Var);
        this.A03 = tKVar;
        this.A00 = new C0579cK(tKVar);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:210:0x038f */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [int] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [short] */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x02b0, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:?, code lost:
        r2.A04 = true;
        r0 = r2.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x02b5, code lost:
        if (r0 != null) goto L_0x02c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x02b7, code lost:
        r2.A02 = r11;
        r3 = r2.A07();
        r2.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x02c0, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x02c2, code lost:
        r1 = new java.util.ArrayList();
        r1.addAll(r0);
        r1.add(null);
        r1.addAll(r11);
        r2.A02 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02d4, code lost:
        if (r3 != false) goto L_0x02dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x02d6, code lost:
        r2.A07.A02(r2.A06);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x02dd, code lost:
        if (r12 == false) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x02df, code lost:
        r2.A04();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x02e2, code lost:
        return true;
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A02(boolean r22, X.C1123tL r23) {
        /*
        // Method dump skipped, instructions count: 1596
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0585cR.A02(boolean, X.tL):boolean");
    }
}
