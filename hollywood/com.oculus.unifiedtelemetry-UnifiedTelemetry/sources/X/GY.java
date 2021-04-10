package X;

import java.io.File;
import java.util.Iterator;
import javax.annotation.Nullable;

public final class GY implements Iterator<Z9> {
    public int A00;
    public Fz<Object> A01;
    @Nullable
    public File A02;
    @Nullable
    public Z9 A03;
    public boolean A04;
    public final int A05;
    public final long A06 = (Fj.A00() - 7);
    public final long A07 = (Fj.A01() - 168);
    public final Ft A08;
    public final Fw A09;
    public final Ya A0A;

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:44:0x00d2 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v9, types: [long] */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0132, code lost:
        r2 = r6.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0136, code lost:
        if (r2 <= 0) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0138, code lost:
        r15.A00 += r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x013d, code lost:
        if (r2 <= 1) goto L_0x0156;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x013f, code lost:
        r6.size();
        r0 = new X.IB(r6, r15.A09);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0149, code lost:
        r1 = new X.IE(r0, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x014e, code lost:
        r15.A03 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0156, code lost:
        r0 = (X.Z9) r6.get(0);
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean hasNext() {
        /*
        // Method dump skipped, instructions count: 349
        */
        throw new UnsupportedOperationException("Method not decompiled: X.GY.hasNext():boolean");
    }

    public final void remove() {
        throw new UnsupportedOperationException("File removal should be accomplished via markSuccessful");
    }

    public GY(File file, Fw fw, Ya ya, int i) {
        this.A08 = new Ft(new IR(file));
        this.A09 = fw;
        this.A0A = ya;
        this.A05 = i;
    }

    public static void A00(File file) {
        if (!file.isDirectory()) {
            Mu.A06("FileBatchPayloadIterator", "%s: not a directory, deleting anyway...", file);
        }
        file.delete();
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final Z9 next() {
        if (hasNext()) {
            Z9 z9 = this.A03;
            this.A04 = false;
            this.A03 = null;
            return z9;
        }
        throw new IllegalStateException();
    }
}
