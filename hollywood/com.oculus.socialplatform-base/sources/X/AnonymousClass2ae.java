package X;

/* renamed from: X.2ae  reason: invalid class name */
public final class AnonymousClass2ae extends AbstractC14992aj {
    public static int[] A00 = new int[2];

    public static void A00(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6;
        int i7;
        int i8 = i2 - i;
        int i9 = i4 - i3;
        if (i5 == -1) {
            i6 = (int) ((((float) i9) * f) + 0.5f);
            i7 = (int) ((((float) i8) / f) + 0.5f);
            if (i6 > i8 || i9 > i9) {
                if (i8 > i8 || i7 > i9) {
                    return;
                }
            }
            iArr[0] = i6;
            iArr[1] = i9;
            return;
        } else if (i5 == 0) {
            i6 = (int) ((((float) i9) * f) + 0.5f);
            iArr[0] = i6;
            iArr[1] = i9;
            return;
        } else if (i5 == 1) {
            i7 = (int) ((((float) i8) * f) + 0.5f);
        } else {
            return;
        }
        iArr[0] = i8;
        iArr[1] = i7;
    }

    public final void A0C() {
        this.A09 = false;
        this.A04.A00();
        this.A04.A0B = false;
        this.A03.A00();
        this.A03.A0B = false;
        this.A05.A0B = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:105:0x02bd, code lost:
        if (r7 != false) goto L_0x02bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0328, code lost:
        if (r10 != false) goto L_0x032a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0354, code lost:
        if (r15 != 1) goto L_0x0039;
     */
    @Override // X.AbstractC14992aj, X.AnonymousClass2bJ
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void AAs(X.AnonymousClass2bJ r24) {
        /*
        // Method dump skipped, instructions count: 941
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass2ae.AAs(X.2bJ):void");
    }

    public final String toString() {
        return AnonymousClass006.A07("HorizontalRun ", this.A02.A0j);
    }

    public AnonymousClass2ae(AnonymousClass2ac r3) {
        super(r3);
        this.A04.A06 = AnonymousClass007.A04;
        this.A03.A06 = AnonymousClass007.A05;
        this.A01 = 0;
    }
}
