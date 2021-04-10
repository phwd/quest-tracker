package X;

import java.io.File;
import java.util.Iterator;

/* renamed from: X.77  reason: invalid class name */
public final class AnonymousClass77 implements Iterator {
    public int A00;
    public AbstractC00356c A01;
    public File A02;
    public eq A03;
    public boolean A04;
    public final int A05;
    public final long A06;
    public final long A07;
    public final AnonymousClass6W A08;
    public final AnonymousClass6Z A09;
    public final C0692fT A0A;

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00aa, code lost:
        if (r6 != false) goto L_0x00ae;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean hasNext() {
        /*
        // Method dump skipped, instructions count: 694
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass77.hasNext():boolean");
    }

    public final void remove() {
        throw new UnsupportedOperationException("File removal should be accomplished via markSuccessful");
    }

    public AnonymousClass77(File file, AnonymousClass6Z r7, C0692fT fTVar, int i) {
        long currentTimeMillis;
        long currentTimeMillis2;
        this.A08 = new AnonymousClass6W(new C0662e8(file));
        this.A09 = r7;
        this.A0A = fTVar;
        this.A05 = i;
        synchronized (AnonymousClass6N.class) {
            currentTimeMillis = System.currentTimeMillis();
        }
        this.A06 = (currentTimeMillis / AnonymousClass6N.A00) - 7;
        synchronized (AnonymousClass6N.class) {
            currentTimeMillis2 = System.currentTimeMillis();
        }
        this.A07 = (currentTimeMillis2 / AnonymousClass6N.A01) - 168;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            eq eqVar = this.A03;
            this.A04 = false;
            this.A03 = null;
            return eqVar;
        }
        throw new IllegalStateException();
    }
}
