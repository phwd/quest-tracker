package X;

import java.io.File;
import java.util.Iterator;
import javax.annotation.Nullable;

/* renamed from: X.0Gr  reason: invalid class name */
public final class AnonymousClass0Gr implements Iterator<AbstractC06950qN> {
    public int A00;
    public AnonymousClass0GE<Object> A01;
    @Nullable
    public File A02;
    @Nullable
    public AbstractC06950qN A03;
    public boolean A04;
    public final int A05;
    public final long A06;
    public final long A07;
    public final AnonymousClass0G8 A08;
    public final AnonymousClass0GB A09;
    public final AnonymousClass0q8 A0A;

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:103:0x0166 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v9, types: [long] */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x01d3, code lost:
        throw new java.lang.IllegalArgumentException(r1);
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean hasNext() {
        /*
        // Method dump skipped, instructions count: 517
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Gr.hasNext():boolean");
    }

    public final void remove() {
        throw new UnsupportedOperationException("File removal should be accomplished via markSuccessful");
    }

    public AnonymousClass0Gr(File file, AnonymousClass0GB r7, AnonymousClass0q8 r8, int i) {
        long currentTimeMillis;
        long currentTimeMillis2;
        this.A08 = new AnonymousClass0G8(new AnonymousClass0JW(file));
        this.A09 = r7;
        this.A0A = r8;
        this.A05 = i;
        synchronized (AnonymousClass0Fy.class) {
            currentTimeMillis = System.currentTimeMillis();
        }
        this.A06 = (currentTimeMillis / AnonymousClass0Fy.A00) - 7;
        synchronized (AnonymousClass0Fy.class) {
            currentTimeMillis2 = System.currentTimeMillis();
        }
        this.A07 = (currentTimeMillis2 / AnonymousClass0Fy.A01) - 168;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final AbstractC06950qN next() {
        if (hasNext()) {
            AbstractC06950qN r1 = this.A03;
            this.A04 = false;
            this.A03 = null;
            return r1;
        }
        throw new IllegalStateException();
    }
}
