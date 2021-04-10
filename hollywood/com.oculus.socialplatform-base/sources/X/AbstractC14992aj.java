package X;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: X.2aj  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC14992aj implements AnonymousClass2bJ {
    public int A00;
    public int A01 = 0;
    public AnonymousClass2ac A02;
    public AnonymousClass2an A03 = new AnonymousClass2an(this);
    public AnonymousClass2an A04 = new AnonymousClass2an(this);
    public AnonymousClass2b1 A05 = new AnonymousClass2b1(this);
    public C15052ay A06;
    public Integer A07;
    public Integer A08 = AnonymousClass007.A00;
    public boolean A09 = false;

    public static final AnonymousClass2an A01(C14982ai r2) {
        C14982ai r0 = r2.A04;
        if (r0 != null) {
            AnonymousClass2ac r1 = r0.A07;
            switch (r0.A08.intValue()) {
                case 1:
                    return r1.A0g.A04;
                case 2:
                    return r1.A0h.A04;
                case 3:
                    return r1.A0g.A03;
                case 4:
                    return r1.A0h.A03;
                case 5:
                    return r1.A0h.A00;
            }
        }
        return null;
    }

    public static final AnonymousClass2an A02(C14982ai r3, int i) {
        AbstractC14992aj r1;
        C14982ai r32 = r3.A04;
        if (r32 != null) {
            AnonymousClass2ac r0 = r32.A07;
            if (i == 0) {
                r1 = r0.A0g;
            } else {
                r1 = r0.A0h;
            }
            switch (r32.A08.intValue()) {
                case 1:
                case 2:
                    return r1.A04;
                case 3:
                case 4:
                    return r1.A03;
            }
        }
        return null;
    }

    public static final void A03(AnonymousClass2an r1, AnonymousClass2an r2, int i) {
        r1.A08.add(r2);
        r1.A03 = i;
        r2.A07.add(r1);
    }

    private final void A0A(AnonymousClass2an r3, AnonymousClass2an r4, int i, AnonymousClass2b1 r6) {
        r3.A08.add(r4);
        r3.A08.add(this.A05);
        r3.A00 = i;
        r3.A01 = r6;
        r4.A07.add(r3);
        r6.A07.add(r3);
    }

    public final int A04(int i, int i2) {
        int i3;
        int i4;
        if (i2 == 0) {
            AnonymousClass2ac r0 = this.A02;
            i3 = r0.A0I;
            i4 = r0.A0K;
        } else {
            AnonymousClass2ac r02 = this.A02;
            i3 = r02.A0H;
            i4 = r02.A0J;
        }
        int max = Math.max(i4, i);
        if (i3 > 0) {
            max = Math.min(i3, i);
        }
        if (max != i) {
            return max;
        }
        return i;
    }

    public final long A05() {
        if (!(this instanceof C14942ad)) {
            AnonymousClass2b1 r1 = this.A05;
            if (r1.A0B) {
                return (long) r1.A04;
            }
            return 0;
        }
        ArrayList<AbstractC14992aj> arrayList = ((C14942ad) this).A01;
        int size = arrayList.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            AbstractC14992aj r2 = arrayList.get(i);
            j = j + ((long) r2.A04.A03) + r2.A05() + ((long) r2.A03.A03);
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:175:0x0399  */
    /* JADX WARNING: Removed duplicated region for block: B:314:0x06fb  */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x070e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:399:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:413:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A06() {
        /*
        // Method dump skipped, instructions count: 2298
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC14992aj.A06():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0055, code lost:
        if (r1 != 0) goto L_0x0057;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A07() {
        /*
        // Method dump skipped, instructions count: 104
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC14992aj.A07():void");
    }

    public final void A08() {
        if (this instanceof C14952af) {
            C14952af r1 = (C14952af) this;
            r1.A06 = null;
            r1.A04.A00();
            r1.A03.A00();
            r1.A00.A00();
            r1.A05.A00();
            r1.A09 = false;
        } else if (!(this instanceof AnonymousClass2ae)) {
            if (this instanceof AnonymousClass2aq) {
                this.A06 = null;
            } else if (!(this instanceof AnonymousClass2au)) {
                C14942ad r12 = (C14942ad) this;
                r12.A06 = null;
                Iterator<AbstractC14992aj> it = r12.A01.iterator();
                while (it.hasNext()) {
                    it.next().A08();
                }
                return;
            }
            this.A04.A00();
        } else {
            this.A06 = null;
            this.A04.A00();
            this.A03.A00();
            this.A05.A00();
            this.A09 = false;
        }
    }

    public final boolean A0B() {
        int i;
        if (!(this instanceof C14952af)) {
            if (!(this instanceof AnonymousClass2ae)) {
                if ((this instanceof AnonymousClass2aq) || (this instanceof AnonymousClass2au)) {
                    return false;
                }
                ArrayList<AbstractC14992aj> arrayList = ((C14942ad) this).A01;
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (!arrayList.get(i2).A0B()) {
                        return false;
                    }
                }
                return true;
            } else if (this.A07 != AnonymousClass007.A03) {
                return true;
            } else {
                i = this.A02.A0G;
            }
        } else if (this.A07 != AnonymousClass007.A03) {
            return true;
        } else {
            i = this.A02.A0F;
        }
        if (i == 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass2bJ
    public void AAs(AnonymousClass2bJ r11) {
        int i;
        AnonymousClass2an r0;
        float f;
        int i2;
        float f2;
        float f3;
        if (!(this instanceof C14952af)) {
            if (this instanceof AnonymousClass2aq) {
                C15012al r6 = (C15012al) this.A02;
                int i3 = r6.A00;
                int i4 = -1;
                int i5 = 0;
                for (AnonymousClass2an r02 : this.A04.A08) {
                    int i6 = r02.A04;
                    if (i4 == -1 || i6 < i4) {
                        i4 = i6;
                    }
                    if (i5 < i6) {
                        i5 = i6;
                    }
                }
                if (i3 == 0 || i3 == 2) {
                    this.A04.A01(i4 + r6.A01);
                    return;
                } else {
                    this.A04.A01(i5 + r6.A01);
                    return;
                }
            } else if (this instanceof AnonymousClass2au) {
                AnonymousClass2an r1 = this.A04;
                if (r1.A0A && !r1.A0B) {
                    i = (int) ((((float) r1.A08.get(0).A04) * ((C15002ak) this.A02).A00) + 0.5f);
                    r0 = this.A04;
                } else {
                    return;
                }
            } else {
                return;
            }
        } else if (3 - this.A08.intValue() != 0) {
            AnonymousClass2b1 r5 = this.A05;
            if (r5.A0A && !r5.A0B && this.A07 == AnonymousClass007.A03) {
                AnonymousClass2ac r62 = this.A02;
                int i7 = r62.A0F;
                if (i7 == 2) {
                    AnonymousClass2ac r03 = r62.A0d;
                    if (r03 != null) {
                        AnonymousClass2b1 r3 = r03.A0h.A05;
                        if (r3.A0B) {
                            f2 = r62.A03;
                            f3 = (float) r3.A04;
                            f = f3 * f2;
                            i2 = (int) (f + 0.5f);
                        }
                    }
                } else if (i7 == 3) {
                    AnonymousClass2b1 r32 = r62.A0g.A05;
                    if (r32.A0B) {
                        int i8 = r62.A09;
                        if (i8 != -1) {
                            if (i8 == 0) {
                                f3 = (float) r32.A04;
                                f2 = r62.A01;
                                f = f3 * f2;
                                i2 = (int) (f + 0.5f);
                            } else if (i8 != 1) {
                                i2 = 0;
                            }
                        }
                        f = ((float) r32.A04) / r62.A01;
                        i2 = (int) (f + 0.5f);
                    }
                }
                r5.A01(i2);
            }
            AnonymousClass2an r52 = this.A04;
            if (r52.A0A) {
                AnonymousClass2an r2 = this.A03;
                if (!r2.A0A) {
                    return;
                }
                if (!r52.A0B || !r2.A0B || !this.A05.A0B) {
                    boolean z = this.A05.A0B;
                    if (!z && this.A07 == AnonymousClass007.A03) {
                        AnonymousClass2ac r22 = this.A02;
                        if (r22.A0G == 0 && !r22.A0S()) {
                            int i9 = r52.A08.get(0).A04;
                            AnonymousClass2an r33 = this.A04;
                            int i10 = i9 + r33.A03;
                            int i11 = this.A03.A08.get(0).A04 + this.A03.A03;
                            i = i11 - i10;
                            r33.A01(i10);
                            this.A03.A01(i11);
                            r0 = this.A05;
                        }
                    }
                    if (!z && this.A07 == AnonymousClass007.A03 && this.A00 == 1 && r52.A08.size() > 0 && this.A03.A08.size() > 0) {
                        int i12 = (this.A03.A08.get(0).A04 + this.A03.A03) - (this.A04.A08.get(0).A04 + this.A04.A03);
                        AnonymousClass2b1 r23 = this.A05;
                        int i13 = r23.A00;
                        if (i12 < i13) {
                            r23.A01(i12);
                        } else {
                            r23.A01(i13);
                        }
                    }
                    if (this.A05.A0B && this.A04.A08.size() > 0 && this.A03.A08.size() > 0) {
                        AnonymousClass2an r8 = this.A04.A08.get(0);
                        AnonymousClass2an r7 = this.A03.A08.get(0);
                        int i14 = r8.A04;
                        AnonymousClass2an r53 = this.A04;
                        int i15 = i14 + r53.A03;
                        int i16 = r7.A04;
                        int i17 = i16 + this.A03.A03;
                        float f4 = this.A02.A06;
                        if (r8 == r7) {
                            i15 = i14;
                            i17 = i16;
                            f4 = 0.5f;
                        }
                        r53.A01((int) (((float) i15) + 0.5f + (((float) ((i17 - i15) - this.A05.A04)) * f4)));
                        this.A03.A01(this.A04.A04 + this.A05.A04);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        } else {
            AnonymousClass2ac r04 = this.A02;
            A09(r04.A0c, r04.A0W, 1);
            return;
        }
        r0.A01(i);
    }

    public AbstractC14992aj(AnonymousClass2ac r2) {
        this.A02 = r2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
        if (((X.AbstractC14992aj) r1).A00 == 3) goto L_0x0053;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A09(X.C14982ai r16, X.C14982ai r17, int r18) {
        /*
        // Method dump skipped, instructions count: 230
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC14992aj.A09(X.2ai, X.2ai, int):void");
    }
}
