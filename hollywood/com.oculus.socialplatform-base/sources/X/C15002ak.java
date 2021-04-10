package X;

import java.util.ArrayList;

/* renamed from: X.2ak  reason: invalid class name and case insensitive filesystem */
public final class C15002ak extends AnonymousClass2ac {
    public float A00 = -1.0f;
    public int A01;
    public int A02 = -1;
    public int A03 = -1;
    public C14982ai A04;
    public boolean A05;

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
        if (r0.A0w[1] != r2) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001b, code lost:
        if (r0.A0w[0] != r2) goto L_0x001d;
     */
    @Override // X.AnonymousClass2ac
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0J(X.C15022am r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 224
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C15002ak.A0J(X.2am, boolean):void");
    }

    public final void A0V(int i) {
        C14982ai r3;
        if (this.A01 != i) {
            this.A01 = i;
            ArrayList<C14982ai> arrayList = this.A0k;
            arrayList.clear();
            if (i == 1) {
                r3 = this.A0a;
            } else {
                r3 = this.A0c;
            }
            this.A04 = r3;
            arrayList.add(r3);
            C14982ai[] r2 = this.A0t;
            int length = r2.length;
            for (int i2 = 0; i2 < length; i2++) {
                r2[i2] = r3;
            }
        }
    }

    public C15002ak() {
        C14982ai r3 = this.A0c;
        this.A04 = r3;
        this.A01 = 0;
        ArrayList<C14982ai> arrayList = this.A0k;
        arrayList.clear();
        arrayList.add(r3);
        C14982ai[] r1 = this.A0t;
        int length = r1.length;
        for (int i = 0; i < length; i++) {
            r1[i] = r3;
        }
    }
}
