package X;

/* renamed from: X.2al  reason: invalid class name and case insensitive filesystem */
public final class C15012al extends C15062az {
    public int A00 = 0;
    public int A01 = 0;
    public boolean A02 = true;
    public boolean A03 = false;

    public final boolean A0X() {
        int i;
        Integer num;
        Integer num2;
        Integer num3;
        int i2;
        int i3 = 0;
        boolean z = true;
        while (true) {
            i = ((C15062az) this).A00;
            if (i3 >= i) {
                break;
            }
            AnonymousClass2ac r6 = ((C15062az) this).A01[i3];
            if ((this.A02 || r6.A0O()) && ((((i2 = this.A00) == 0 || i2 == 1) && !r6.A0P()) || ((i2 == 2 || i2 == 3) && !r6.A0Q()))) {
                z = false;
            }
            i3++;
        }
        if (!z || i <= 0) {
            return false;
        }
        int i4 = 0;
        boolean z2 = false;
        for (int i5 = 0; i5 < ((C15062az) this).A00; i5++) {
            AnonymousClass2ac r62 = ((C15062az) this).A01[i5];
            if (this.A02 || r62.A0O()) {
                if (!z2) {
                    int i6 = this.A00;
                    if (i6 == 0) {
                        num3 = AnonymousClass007.A01;
                    } else if (i6 == 1) {
                        num3 = AnonymousClass007.A04;
                    } else if (i6 == 2) {
                        num3 = AnonymousClass007.A03;
                    } else {
                        if (i6 == 3) {
                            num3 = AnonymousClass007.A05;
                        }
                        z2 = true;
                    }
                    i4 = r62.A07(num3).A00();
                    z2 = true;
                }
                int i7 = this.A00;
                if (i7 == 0) {
                    num2 = AnonymousClass007.A01;
                } else {
                    if (i7 == 1) {
                        num = AnonymousClass007.A04;
                    } else if (i7 == 2) {
                        num2 = AnonymousClass007.A03;
                    } else if (i7 == 3) {
                        num = AnonymousClass007.A05;
                    }
                    i4 = Math.max(i4, r62.A07(num).A00());
                }
                i4 = Math.min(i4, r62.A07(num2).A00());
            }
        }
        int i8 = i4 + this.A01;
        int i9 = this.A00;
        if (i9 == 0 || i9 == 1) {
            A0F(i8, i8);
        } else {
            A0G(i8, i8);
        }
        this.A03 = true;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c5, code lost:
        if (r14.A0b.A07() != false) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d7, code lost:
        if (r14.A0W.A07() != false) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00e2, code lost:
        if (r1 == false) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x014f, code lost:
        if (r4 == false) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0156, code lost:
        if (r1 != false) goto L_0x0158;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0158, code lost:
        r11 = 5;
     */
    @Override // X.AnonymousClass2ac
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0J(X.C15022am r15, boolean r16) {
        /*
        // Method dump skipped, instructions count: 495
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C15012al.A0J(X.2am, boolean):void");
    }

    public final int A0W() {
        int i = this.A00;
        if (i == 0 || i == 1) {
            return 0;
        }
        if (i == 2 || i == 3) {
            return 1;
        }
        return -1;
    }

    @Override // X.AnonymousClass2ac
    public final String toString() {
        String A09 = AnonymousClass006.A09("[Barrier] ", this.A0j, " {");
        for (int i = 0; i < ((C15062az) this).A00; i++) {
            AnonymousClass2ac r1 = ((C15062az) this).A01[i];
            if (i > 0) {
                A09 = AnonymousClass006.A07(A09, ", ");
            }
            A09 = AnonymousClass006.A07(A09, r1.A0j);
        }
        return AnonymousClass006.A07(A09, "}");
    }
}
