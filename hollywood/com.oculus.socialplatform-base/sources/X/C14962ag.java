package X;

import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: X.2ag  reason: invalid class name and case insensitive filesystem */
public final class C14962ag {
    public static AnonymousClass2aH A00 = new AnonymousClass2aH();

    public static void A00(AnonymousClass2ac r13, AnonymousClass2aK r14) {
        C14982ai r3;
        C14982ai r6;
        boolean z;
        C14982ai r0;
        C14982ai r02;
        C14982ai r03;
        boolean A0S;
        C14982ai r04;
        C14982ai r05;
        if (!(r13 instanceof C14932ab) && r13.A0T() && A06(r13)) {
            C14932ab.A00(r13, r14, new AnonymousClass2aH());
        }
        Integer num = AnonymousClass007.A03;
        C14982ai A07 = r13.A07(num);
        C14982ai A072 = r13.A07(AnonymousClass007.A05);
        int A002 = A07.A00();
        int A003 = A072.A00();
        HashSet<C14982ai> hashSet = A07.A05;
        if (hashSet != null && A07.A06) {
            Iterator<C14982ai> it = hashSet.iterator();
            while (it.hasNext()) {
                C14982ai next = it.next();
                AnonymousClass2ac r62 = next.A07;
                boolean A06 = A06(r62);
                if (r62.A0T() && A06) {
                    C14932ab.A00(r62, r14, new AnonymousClass2aH());
                }
                Integer num2 = r62.A0w[1];
                if (num2 != num || A06) {
                    if (!r62.A0T()) {
                        C14982ai r32 = r62.A0c;
                        if (next == r32 && r62.A0W.A04 == null) {
                            int A01 = r32.A01() + A002;
                            r62.A0G(A01, r62.A03() + A01);
                        } else {
                            C14982ai r1 = r62.A0W;
                            if (next == r1 && r1.A04 == null) {
                                int A012 = A002 - r1.A01();
                                r62.A0G(A012 - r62.A03(), A012);
                            } else if (next == r32 && (r03 = r1.A04) != null && r03.A06) {
                                A04(r14, r62);
                            }
                        }
                        A00(r62, r14);
                    }
                } else if (num2 == num && r62.A0H >= 0 && r62.A0J >= 0) {
                    if ((r62.A0Q == 8 || (r62.A0F == 0 && r62.A01 == AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z)) && !(A0S = r62.A0S()) && !r62.A0m) {
                        C14982ai r12 = r62.A0c;
                        if (((next == r12 && (r05 = r62.A0W.A04) != null && r05.A06) || (next == r62.A0W && (r04 = r12.A04) != null && r04.A06)) && !A0S) {
                            A01(r13, r14, r62);
                        }
                    }
                }
            }
        }
        if (!(r13 instanceof C15002ak)) {
            HashSet<C14982ai> hashSet2 = A072.A05;
            if (hashSet2 != null && A072.A06) {
                Iterator<C14982ai> it2 = hashSet2.iterator();
                while (it2.hasNext()) {
                    C14982ai next2 = it2.next();
                    AnonymousClass2ac r15 = next2.A07;
                    boolean A062 = A06(r15);
                    if (r15.A0T() && A062) {
                        C14932ab.A00(r15, r14, new AnonymousClass2aH());
                    }
                    C14982ai r7 = r15.A0c;
                    if ((next2 != r7 || (r02 = (r6 = r15.A0W).A04) == null || !r02.A06) && (next2 != (r6 = r15.A0W) || (r0 = r7.A04) == null || !r0.A06)) {
                        z = false;
                    } else {
                        z = true;
                    }
                    Integer num3 = r15.A0w[1];
                    if (num3 != num || A062) {
                        if (!r15.A0T()) {
                            if (next2 == r7 && r6.A04 == null) {
                                int A013 = r7.A01() + A003;
                                r15.A0G(A013, r15.A03() + A013);
                            } else if (next2 == r6 && r7.A04 == null) {
                                int A014 = A003 - r6.A01();
                                r15.A0G(A014 - r15.A03(), A014);
                            } else if (z && !r15.A0S()) {
                                A04(r14, r15);
                            }
                            A00(r15, r14);
                        }
                    } else if (num3 == num && r15.A0H >= 0 && r15.A0J >= 0) {
                        if ((r15.A0Q == 8 || (r15.A0F == 0 && r15.A01 == AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z)) && !r15.A0S() && !r15.A0m && z) {
                            A01(r13, r14, r15);
                        }
                    }
                }
            }
            C14982ai A073 = r13.A07(AnonymousClass007.A06);
            HashSet<C14982ai> hashSet3 = A073.A05;
            if (hashSet3 != null && A073.A06) {
                int A004 = A073.A00();
                Iterator<C14982ai> it3 = hashSet3.iterator();
                while (it3.hasNext()) {
                    C14982ai next3 = it3.next();
                    AnonymousClass2ac r4 = next3.A07;
                    boolean A063 = A06(r4);
                    if (r4.A0T() && A063) {
                        C14932ab.A00(r4, r14, new AnonymousClass2aH());
                    }
                    if ((r4.A0w[1] != num || A063) && !r4.A0T() && next3 == (r3 = r4.A0V)) {
                        if (r4.A0l) {
                            int i = A004 - r4.A08;
                            r4.A0T = i;
                            r4.A0c.A04(i);
                            r4.A0W.A04(r4.A0A + i);
                            r3.A04(A004);
                            r4.A0p = true;
                        }
                        A00(r4, r14);
                    }
                }
            }
        }
    }

    public static void A01(AnonymousClass2ac r7, AnonymousClass2aK r8, AnonymousClass2ac r9) {
        int A03;
        float f = r9.A06;
        C14982ai r1 = r9.A0c;
        int A002 = r1.A04.A00() + r1.A01();
        C14982ai r12 = r9.A0W;
        int A003 = r12.A04.A00() - r12.A01();
        if (A003 >= A002) {
            int A032 = r9.A03();
            if (r9.A0Q != 8) {
                int i = r9.A0F;
                if (i == 2) {
                    if (r7 instanceof C14932ab) {
                        A03 = r7.A03();
                    } else {
                        A03 = r7.A0d.A03();
                    }
                    A032 = (int) (f * 0.5f * ((float) A03));
                } else if (i == 0) {
                    A032 = A003 - A002;
                }
                A032 = Math.max(r9.A0J, A032);
                int i2 = r9.A0H;
                if (i2 > 0) {
                    A032 = Math.min(i2, A032);
                }
            }
            int i3 = A002 + ((int) ((f * ((float) ((A003 - A002) - A032))) + 0.5f));
            r9.A0G(i3, A032 + i3);
            A00(r9, r8);
        }
    }

    public static void A02(AnonymousClass2ac r7, AnonymousClass2aK r8, AnonymousClass2ac r9, boolean z) {
        int A04;
        float f = r9.A02;
        C14982ai r1 = r9.A0a;
        int A002 = r1.A04.A00() + r1.A01();
        C14982ai r12 = r9.A0b;
        int A003 = r12.A04.A00() - r12.A01();
        if (A003 >= A002) {
            int A042 = r9.A04();
            if (r9.A0Q != 8) {
                int i = r9.A0G;
                if (i == 2) {
                    if (r7 instanceof C14932ab) {
                        A04 = r7.A04();
                    } else {
                        A04 = r7.A0d.A04();
                    }
                    A042 = (int) (f * 0.5f * ((float) A04));
                } else if (i == 0) {
                    A042 = A003 - A002;
                }
                A042 = Math.max(r9.A0K, A042);
                int i2 = r9.A0I;
                if (i2 > 0) {
                    A042 = Math.min(i2, A042);
                }
            }
            int i3 = A002 + ((int) ((f * ((float) ((A003 - A002) - A042))) + 0.5f));
            r9.A0F(i3, A042 + i3);
            A03(r9, r8, z);
        }
    }

    public static void A03(AnonymousClass2ac r12, AnonymousClass2aK r13, boolean z) {
        HashSet<C14982ai> hashSet;
        C14982ai r5;
        boolean z2;
        C14982ai r0;
        C14982ai r02;
        C14982ai r03;
        boolean A0R;
        C14982ai r04;
        C14982ai r05;
        if (!(r12 instanceof C14932ab) && r12.A0T() && A06(r12)) {
            C14932ab.A00(r12, r13, new AnonymousClass2aH());
        }
        C14982ai A07 = r12.A07(AnonymousClass007.A01);
        C14982ai A072 = r12.A07(AnonymousClass007.A04);
        int A002 = A07.A00();
        int A003 = A072.A00();
        HashSet<C14982ai> hashSet2 = A07.A05;
        if (hashSet2 != null && A07.A06) {
            Iterator<C14982ai> it = hashSet2.iterator();
            while (it.hasNext()) {
                C14982ai next = it.next();
                AnonymousClass2ac r52 = next.A07;
                boolean A06 = A06(r52);
                if (r52.A0T() && A06) {
                    C14932ab.A00(r52, r13, new AnonymousClass2aH());
                }
                Integer num = r52.A0w[0];
                Integer num2 = AnonymousClass007.A03;
                if (num != num2 || A06) {
                    if (!r52.A0T()) {
                        C14982ai r3 = r52.A0a;
                        if (next == r3 && r52.A0b.A04 == null) {
                            int A01 = r3.A01() + A002;
                            r52.A0F(A01, r52.A04() + A01);
                        } else {
                            C14982ai r1 = r52.A0b;
                            if (next == r1 && r3.A04 == null) {
                                int A012 = A002 - r1.A01();
                                r52.A0F(A012 - r52.A04(), A012);
                            } else if (next == r3 && (r03 = r1.A04) != null && r03.A06 && !r52.A0R()) {
                                A05(r13, r52, z);
                            }
                        }
                        A03(r52, r13, z);
                    }
                } else if (num == num2 && r52.A0I >= 0 && r52.A0K >= 0) {
                    if ((r52.A0Q == 8 || (r52.A0G == 0 && r52.A01 == AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z)) && !(A0R = r52.A0R()) && !r52.A0m) {
                        C14982ai r14 = r52.A0a;
                        if (((next == r14 && (r05 = r52.A0b.A04) != null && r05.A06) || (next == r52.A0b && (r04 = r14.A04) != null && r04.A06)) && !A0R) {
                            A02(r12, r13, r52, z);
                        }
                    }
                }
            }
        }
        if (!(r12 instanceof C15002ak) && (hashSet = A072.A05) != null && A072.A06) {
            Iterator<C14982ai> it2 = hashSet.iterator();
            while (it2.hasNext()) {
                C14982ai next2 = it2.next();
                AnonymousClass2ac r15 = next2.A07;
                boolean A062 = A06(r15);
                if (r15.A0T() && A062) {
                    C14932ab.A00(r15, r13, new AnonymousClass2aH());
                }
                C14982ai r6 = r15.A0a;
                if ((next2 != r6 || (r02 = (r5 = r15.A0b).A04) == null || !r02.A06) && (next2 != (r5 = r15.A0b) || (r0 = r6.A04) == null || !r0.A06)) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                Integer num3 = r15.A0w[0];
                Integer num4 = AnonymousClass007.A03;
                if (num3 != num4 || A062) {
                    if (!r15.A0T()) {
                        if (next2 == r6 && r5.A04 == null) {
                            int A013 = r6.A01() + A003;
                            r15.A0F(A013, r15.A04() + A013);
                        } else if (next2 == r5 && r6.A04 == null) {
                            int A014 = A003 - r5.A01();
                            r15.A0F(A014 - r15.A04(), A014);
                        } else if (z2 && !r15.A0R()) {
                            A05(r13, r15, z);
                        }
                        A03(r15, r13, z);
                    }
                } else if (num3 == num4 && r15.A0I >= 0 && r15.A0K >= 0) {
                    if ((r15.A0Q == 8 || (r15.A0G == 0 && r15.A01 == AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z)) && !r15.A0R() && !r15.A0m && z2) {
                        A02(r12, r13, r15, z);
                    }
                }
            }
        }
    }

    public static void A04(AnonymousClass2aK r7, AnonymousClass2ac r8) {
        float f = r8.A06;
        C14982ai r2 = r8.A0c;
        int A002 = r2.A04.A00();
        C14982ai r1 = r8.A0W;
        int A003 = r1.A04.A00();
        int A01 = r2.A01() + A002;
        int A012 = A003 - r1.A01();
        if (A002 == A003) {
            f = 0.5f;
        } else {
            A002 = A01;
            A003 = A012;
        }
        int A03 = r8.A03();
        int i = (A003 - A002) - A03;
        if (A002 > A003) {
            i = (A002 - A003) - A03;
        }
        int i2 = (int) ((f * ((float) i)) + 0.5f);
        int i3 = A002 + i2;
        int i4 = i3 + A03;
        if (A002 > A003) {
            i3 = A002 - i2;
            i4 = i3 - A03;
        }
        r8.A0G(i3, i4);
        A00(r8, r7);
    }

    public static void A05(AnonymousClass2aK r6, AnonymousClass2ac r7, boolean z) {
        float f = r7.A02;
        C14982ai r2 = r7.A0a;
        int A002 = r2.A04.A00();
        C14982ai r1 = r7.A0b;
        int A003 = r1.A04.A00();
        int A01 = r2.A01() + A002;
        int A012 = A003 - r1.A01();
        if (A002 == A003) {
            f = 0.5f;
        } else {
            A002 = A01;
            A003 = A012;
        }
        int A04 = r7.A04();
        int i = (A003 - A002) - A04;
        if (A002 > A003) {
            i = (A002 - A003) - A04;
        }
        int i2 = ((int) ((f * ((float) i)) + 0.5f)) + A002;
        int i3 = i2 + A04;
        if (A002 > A003) {
            i3 = i2 - A04;
        }
        r7.A0F(i2, i3);
        A03(r7, r6, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        if (r7.A0P() != false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004d, code lost:
        if (r7.A0Q() != false) goto L_0x004f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A06(X.AnonymousClass2ac r7) {
        /*
            java.lang.Integer[] r1 = r7.A0w
            r0 = 0
            r2 = r1[r0]
            r4 = 1
            r6 = r1[r4]
            java.lang.Integer r1 = X.AnonymousClass007.A00
            r5 = 0
            r3 = 0
            if (r2 == r1) goto L_0x002d
            java.lang.Integer r0 = X.AnonymousClass007.A01
            if (r2 == r0) goto L_0x002d
            java.lang.Integer r0 = X.AnonymousClass007.A03
            if (r2 != r0) goto L_0x0026
            int r0 = r7.A0G
            if (r0 != 0) goto L_0x0026
            float r0 = r7.A01
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 != 0) goto L_0x0026
            boolean r0 = r7.A0U(r3)
            if (r0 != 0) goto L_0x002d
        L_0x0026:
            boolean r0 = r7.A0P()
            r2 = 0
            if (r0 == 0) goto L_0x002e
        L_0x002d:
            r2 = 1
        L_0x002e:
            if (r6 == r1) goto L_0x004f
            java.lang.Integer r0 = X.AnonymousClass007.A01
            if (r6 == r0) goto L_0x004f
            java.lang.Integer r0 = X.AnonymousClass007.A03
            if (r6 != r0) goto L_0x0048
            int r0 = r7.A0F
            if (r0 != 0) goto L_0x0048
            float r0 = r7.A01
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 != 0) goto L_0x0048
            boolean r0 = r7.A0U(r4)
            if (r0 != 0) goto L_0x004f
        L_0x0048:
            boolean r0 = r7.A0Q()
            r1 = 0
            if (r0 == 0) goto L_0x0050
        L_0x004f:
            r1 = 1
        L_0x0050:
            float r0 = r7.A01
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x005b
            if (r2 != 0) goto L_0x005a
            if (r1 == 0) goto L_0x0060
        L_0x005a:
            return r4
        L_0x005b:
            if (r2 == 0) goto L_0x0060
            if (r1 == 0) goto L_0x0060
            r3 = 1
        L_0x0060:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C14962ag.A06(X.2ac):boolean");
    }
}
