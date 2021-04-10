package X;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: X.2aa  reason: invalid class name and case insensitive filesystem */
public final class C14922aa {
    public ArrayList<C15052ay> A00 = new ArrayList<>();
    public C14932ab A01;
    public C14932ab A02;
    public AnonymousClass2aK A03 = null;
    public ArrayList<AbstractC14992aj> A04 = new ArrayList<>();
    public boolean A05 = true;
    public boolean A06 = true;
    public AnonymousClass2aH A07 = new AnonymousClass2aH();
    public ArrayList<C15052ay> A08 = new ArrayList<>();

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f1, code lost:
        if ((r3 instanceof X.AnonymousClass2ae) == false) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f8, code lost:
        if ((r3 instanceof X.C14952af) == false) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00fb, code lost:
        r0 = r20.A0h;
        r1 = r0.A04;
        r2 = r0.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0029, code lost:
        if (r21 == 0) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int A00(X.C14922aa r19, X.C14932ab r20, int r21) {
        /*
        // Method dump skipped, instructions count: 261
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C14922aa.A00(X.2aa, X.2ab, int):int");
    }

    private void A01(AnonymousClass2ac r3, Integer num, int i, Integer num2, int i2) {
        AnonymousClass2aH r1 = this.A07;
        r1.A06 = num;
        r1.A07 = num2;
        r1.A00 = i;
        r1.A05 = i2;
        this.A03.A6N(r3, r1);
        AnonymousClass2aH r12 = this.A07;
        r3.A0E(r12.A04);
        r3.A0D(r12.A03);
        r3.A0l = r12.A08;
        int i3 = r12.A02;
        r3.A08 = i3;
        boolean z = false;
        if (i3 > 0) {
            z = true;
        }
        r3.A0l = z;
    }

    public static final void A02(C14922aa r6) {
        ArrayList<AbstractC14992aj> arrayList = r6.A04;
        arrayList.clear();
        r6.A02.A0g.A08();
        r6.A02.A0h.A08();
        C14932ab r1 = r6.A02;
        arrayList.add(r1.A0g);
        arrayList.add(r1.A0h);
        Iterator<AnonymousClass2ac> it = ((AnonymousClass2b2) r1).A00.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            AnonymousClass2ac next = it.next();
            if (next instanceof C15002ak) {
                arrayList.add(new AnonymousClass2au(next));
            } else {
                if (next.A0R()) {
                    C14942ad r12 = next.A0e;
                    if (r12 == null) {
                        r12 = new C14942ad(next, 0);
                        next.A0e = r12;
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(r12);
                } else {
                    arrayList.add(next.A0g);
                }
                if (next.A0S()) {
                    C14942ad r13 = next.A0f;
                    if (r13 == null) {
                        r13 = new C14942ad(next, 1);
                        next.A0f = r13;
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(r13);
                } else {
                    arrayList.add(next.A0h);
                }
                if (next instanceof C15062az) {
                    arrayList.add(new AnonymousClass2aq(next));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<AbstractC14992aj> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            it2.next().A08();
        }
        Iterator<AbstractC14992aj> it3 = arrayList.iterator();
        while (it3.hasNext()) {
            AbstractC14992aj next2 = it3.next();
            if (next2.A02 != r6.A02) {
                next2.A06();
            }
        }
        ArrayList<C15052ay> arrayList2 = r6.A00;
        arrayList2.clear();
        C15052ay.A03 = 0;
        r6.A06(r6.A01.A0g, 0, arrayList2);
        r6.A06(r6.A01.A0h, 1, r6.A00);
        r6.A05 = false;
    }

    public static final void A03(C14922aa r18) {
        boolean z;
        AnonymousClass2b1 r1;
        int i;
        AnonymousClass2b1 r12;
        int i2;
        AnonymousClass2b1 r13;
        Iterator<AnonymousClass2ac> it = ((AnonymousClass2b2) r18.A01).A00.iterator();
        while (it.hasNext()) {
            AnonymousClass2ac next = it.next();
            if (!next.A0o) {
                Integer[] numArr = next.A0w;
                boolean z2 = false;
                Integer num = numArr[0];
                Integer num2 = numArr[1];
                int i3 = next.A0G;
                int i4 = next.A0F;
                Integer num3 = AnonymousClass007.A01;
                if (num == num3 || (num == AnonymousClass007.A03 && i3 == 1)) {
                    z = true;
                } else {
                    z = false;
                }
                if (num2 == num3 || (num2 == AnonymousClass007.A03 && i4 == 1)) {
                    z2 = true;
                }
                AnonymousClass2b1 r4 = next.A0g.A05;
                boolean z3 = r4.A0B;
                AnonymousClass2b1 r3 = next.A0h.A05;
                boolean z4 = r3.A0B;
                if (!z3) {
                    if (z4 && z) {
                        r18.A01(next, num3, r4.A04, AnonymousClass007.A00, r3.A04);
                        if (num == AnonymousClass007.A03) {
                            r12 = next.A0g.A05;
                            i2 = next.A04();
                            r12.A00 = i2;
                        } else {
                            r1 = next.A0g.A05;
                            i = next.A04();
                            r1.A01(i);
                        }
                    }
                    r13.A01(next.A08);
                } else if (z4) {
                    Integer num4 = AnonymousClass007.A00;
                    r18.A01(next, num4, r4.A04, num4, r3.A04);
                } else {
                    if (z2) {
                        r18.A01(next, AnonymousClass007.A00, r4.A04, num3, r3.A04);
                        if (num2 == AnonymousClass007.A03) {
                            r12 = next.A0h.A05;
                            i2 = next.A03();
                            r12.A00 = i2;
                        } else {
                            r1 = next.A0h.A05;
                            i = next.A03();
                            r1.A01(i);
                        }
                    }
                    if (next.A0o && (r13 = next.A0h.A01) != null) {
                        r13.A01(next.A08);
                    }
                }
                next.A0o = true;
                r13.A01(next.A08);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01cd, code lost:
        if (r0[3].A04 != null) goto L_0x014c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0214, code lost:
        if (r0[1].A04 != null) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0238, code lost:
        if (r10.A0F == 0) goto L_0x0228;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A04(X.C14922aa r26, X.C14932ab r27) {
        /*
        // Method dump skipped, instructions count: 572
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C14922aa.A04(X.2aa, X.2ab):void");
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/2an;IILX/2an;Ljava/util/ArrayList<LX/2ay;>;LX/2ay;)V */
    private void A05(AnonymousClass2an r5, int i, AnonymousClass2an r7, ArrayList arrayList, C15052ay r9) {
        AbstractC14992aj r2 = r5.A02;
        if (r2.A06 == null) {
            C14932ab r1 = this.A01;
            if (!(r2 == r1.A0g || r2 == r1.A0h)) {
                if (r9 == null) {
                    r9 = new C15052ay(r2);
                    arrayList.add(r9);
                }
                r2.A06 = r9;
                r9.A02.add(r2);
                r9.A01 = r2;
                for (AnonymousClass2bJ r52 : r2.A04.A07) {
                    if (r52 instanceof AnonymousClass2an) {
                        A05((AnonymousClass2an) r52, i, r7, arrayList, r9);
                    }
                }
                for (AnonymousClass2bJ r53 : r2.A03.A07) {
                    if (r53 instanceof AnonymousClass2an) {
                        A05((AnonymousClass2an) r53, i, r7, arrayList, r9);
                    }
                }
                if (i == 1 && (r2 instanceof C14952af)) {
                    for (AnonymousClass2bJ r54 : ((C14952af) r2).A00.A07) {
                        if (r54 instanceof AnonymousClass2an) {
                            A05((AnonymousClass2an) r54, i, r7, arrayList, r9);
                        }
                    }
                }
                for (AnonymousClass2an r55 : r2.A04.A08) {
                    A05(r55, i, r7, arrayList, r9);
                }
                for (AnonymousClass2an r56 : r2.A03.A08) {
                    A05(r56, i, r7, arrayList, r9);
                }
                if (i == 1 && (r2 instanceof C14952af)) {
                    for (AnonymousClass2an r57 : ((C14952af) r2).A00.A08) {
                        A05(r57, i, r7, arrayList, r9);
                    }
                }
            }
        }
    }

    private void A06(AbstractC14992aj r9, int i, ArrayList<C15052ay> arrayList) {
        AnonymousClass2an r3;
        AnonymousClass2an r32;
        for (AnonymousClass2bJ r33 : r9.A04.A07) {
            if (r33 instanceof AnonymousClass2an) {
                r32 = (AnonymousClass2an) r33;
            } else if (r33 instanceof AbstractC14992aj) {
                r32 = ((AbstractC14992aj) r33).A04;
            }
            A05(r32, i, r9.A03, arrayList, null);
        }
        for (AnonymousClass2bJ r34 : r9.A03.A07) {
            if (r34 instanceof AnonymousClass2an) {
                r3 = (AnonymousClass2an) r34;
            } else if (r34 instanceof AbstractC14992aj) {
                r3 = ((AbstractC14992aj) r34).A03;
            }
            A05(r3, i, r9.A04, arrayList, null);
        }
        if (i == 1) {
            for (AnonymousClass2bJ r35 : ((C14952af) r9).A00.A07) {
                if (r35 instanceof AnonymousClass2an) {
                    A05((AnonymousClass2an) r35, 1, null, arrayList, null);
                }
            }
        }
    }

    public C14922aa(C14932ab r2) {
        this.A01 = r2;
        this.A02 = r2;
    }
}
