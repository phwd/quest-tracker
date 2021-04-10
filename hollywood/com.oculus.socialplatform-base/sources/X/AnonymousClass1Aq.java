package X;

import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1Aq  reason: invalid class name */
public final class AnonymousClass1Aq implements AnonymousClass1BO {
    public int A00 = 0;
    public AnonymousClass06o<AnonymousClass1B0> A01 = new C05520vs(30);
    public final AnonymousClass1BA A02;
    public final AnonymousClass1BU A03;
    public final ArrayList<AnonymousClass1B0> A04 = new ArrayList<>();
    public final ArrayList<AnonymousClass1B0> A05 = new ArrayList<>();

    public AnonymousClass1Aq(AnonymousClass1BA r4) {
        this.A02 = r4;
        this.A03 = new AnonymousClass1BU(this);
    }

    @Override // X.AnonymousClass1BO
    public final void A8v(AnonymousClass1B0 r2) {
        r2.A03 = null;
        this.A01.A8z(r2);
    }

    private int A00(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        ArrayList<AnonymousClass1B0> arrayList = this.A05;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            AnonymousClass1B0 r3 = arrayList.get(size);
            if (r3.A00 == 8) {
                if (r3.A02 < r3.A01) {
                    i4 = r3.A02;
                    i5 = r3.A01;
                } else {
                    i4 = r3.A01;
                    i5 = r3.A02;
                }
                if (i < i4 || i > i5) {
                    if (i < r3.A02) {
                        if (i2 == 1) {
                            r3.A02++;
                            i6 = r3.A01 + 1;
                        } else if (i2 == 2) {
                            r3.A02--;
                            i6 = r3.A01 - 1;
                        }
                        r3.A01 = i6;
                    }
                } else if (i4 == r3.A02) {
                    if (i2 == 1) {
                        i8 = r3.A01 + 1;
                    } else {
                        if (i2 == 2) {
                            i8 = r3.A01 - 1;
                        }
                        i++;
                    }
                    r3.A01 = i8;
                    i++;
                } else {
                    if (i2 == 1) {
                        i7 = r3.A02 + 1;
                    } else {
                        if (i2 == 2) {
                            i7 = r3.A02 - 1;
                        }
                        i--;
                    }
                    r3.A02 = i7;
                    i--;
                }
            } else if (r3.A02 > i) {
                if (i2 == 1) {
                    i3 = r3.A02 + 1;
                } else if (i2 == 2) {
                    i3 = r3.A02 - 1;
                }
                r3.A02 = i3;
            } else if (r3.A00 == 1) {
                i -= r3.A01;
            } else if (r3.A00 == 2) {
                i += r3.A01;
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            AnonymousClass1B0 r2 = arrayList.get(size2);
            if (r2.A00 == 8) {
                if (r2.A01 != r2.A02 && r2.A01 >= 0) {
                }
            } else if (r2.A01 > 0) {
            }
            arrayList.remove(size2);
            A8v(r2);
        }
        return i;
    }

    public static final int A01(AnonymousClass1Aq r4, int i, int i2) {
        ArrayList<AnonymousClass1B0> arrayList = r4.A05;
        int size = arrayList.size();
        while (i2 < size) {
            AnonymousClass1B0 r42 = arrayList.get(i2);
            if (r42.A00 == 8) {
                if (r42.A02 == i) {
                    i = r42.A01;
                } else {
                    if (r42.A02 < i) {
                        i--;
                    }
                    if (r42.A01 <= i) {
                        i++;
                    }
                }
            } else if (r42.A02 > i) {
                continue;
            } else if (r42.A00 == 2) {
                if (i < r42.A02 + r42.A01) {
                    return -1;
                }
                i -= r42.A01;
            } else if (r42.A00 == 1) {
                i += r42.A01;
            }
            i2++;
        }
        return i;
    }

    private void A02(AnonymousClass1B0 r11) {
        int i;
        int i2 = r11.A00;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int A002 = A00(r11.A02, i2);
        int i3 = r11.A02;
        int i4 = r11.A00;
        if (i4 == 2) {
            i = 0;
        } else if (i4 == 4) {
            i = 1;
        } else {
            StringBuilder sb = new StringBuilder("op should be remove or update.");
            sb.append(r11);
            throw new IllegalArgumentException(sb.toString());
        }
        int i5 = 1;
        for (int i6 = 1; i6 < r11.A01; i6++) {
            int A003 = A00(r11.A02 + (i * i6), i4);
            i4 = r11.A00;
            if (i4 == 2 ? A003 != A002 : !(i4 == 4 && A003 == A002 + 1)) {
                AnonymousClass1B0 A6a = A6a(i4, A002, i5, r11.A03);
                A04(A6a, i3);
                A8v(A6a);
                i4 = r11.A00;
                if (i4 == 4) {
                    i3 += i5;
                }
                A002 = A003;
                i5 = 1;
            } else {
                i5++;
            }
        }
        Object obj = r11.A03;
        A8v(r11);
        if (i5 > 0) {
            AnonymousClass1B0 A6a2 = A6a(r11.A00, A002, i5, obj);
            A04(A6a2, i3);
            A8v(A6a2);
        }
    }

    private void A03(AnonymousClass1B0 r5) {
        this.A05.add(r5);
        int i = r5.A00;
        if (i == 1) {
            this.A02.A6c(r5.A02, r5.A01);
        } else if (i == 2) {
            this.A02.A6f(r5.A02, r5.A01);
        } else if (i == 4) {
            this.A02.A6M(r5.A02, r5.A01, r5.A03);
        } else if (i == 8) {
            this.A02.A6d(r5.A02, r5.A01);
        } else {
            StringBuilder sb = new StringBuilder("Unknown update op type for ");
            sb.append(r5);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private final void A04(AnonymousClass1B0 r4, int i) {
        AnonymousClass1BA r2 = this.A02;
        r2.A71(r4);
        int i2 = r4.A00;
        if (i2 == 2) {
            r2.A6e(i, r4.A01);
        } else if (i2 == 4) {
            r2.A6M(i, r4.A01, r4.A03);
        } else {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    private boolean A06(int i) {
        ArrayList<AnonymousClass1B0> arrayList = this.A05;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            AnonymousClass1B0 r7 = arrayList.get(i2);
            if (r7.A00 != 8) {
                if (r7.A00 == 1) {
                    int i3 = r7.A02;
                    int i4 = i3 + r7.A01;
                    while (i3 < i4) {
                        if (A01(this, i3, i2 + 1) != i) {
                            i3++;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            } else if (A01(this, r7.A01, i2 + 1) != i) {
            }
            return true;
        }
        return false;
    }

    public final void A07() {
        ArrayList<AnonymousClass1B0> arrayList = this.A05;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.A02.A72(arrayList.get(i));
        }
        A05(this, arrayList);
        this.A00 = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0247, code lost:
        if (r1 == 0) goto L_0x0259;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0257, code lost:
        if (r12 == 0) goto L_0x0259;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a2, code lost:
        if (r4.A01 != (r12 - r14)) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00fc, code lost:
        if (r4.A01 != (r14 - r12)) goto L_0x00fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0100, code lost:
        r16 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x012c, code lost:
        if (r1 > r4.A02) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0171, code lost:
        if (r1 >= r4.A02) goto L_0x012e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0006 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A09() {
        /*
        // Method dump skipped, instructions count: 615
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1Aq.A09():void");
    }

    @Override // X.AnonymousClass1BO
    public final AnonymousClass1B0 A6a(int i, int i2, int i3, Object obj) {
        AnonymousClass1B0 A19 = this.A01.A19();
        if (A19 == null) {
            return new AnonymousClass1B0(i, i2, i3, obj);
        }
        A19.A00 = i;
        A19.A02 = i2;
        A19.A01 = i3;
        A19.A03 = obj;
        return A19;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/List<LX/1B0;>;)V */
    public static final void A05(AnonymousClass1Aq r3, List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            r3.A8v((AnonymousClass1B0) list.get(i));
        }
        list.clear();
    }

    public final void A08() {
        A07();
        ArrayList<AnonymousClass1B0> arrayList = this.A04;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass1B0 r8 = arrayList.get(i);
            int i2 = r8.A00;
            if (i2 == 1) {
                AnonymousClass1BA r2 = this.A02;
                r2.A72(r8);
                r2.A6c(r8.A02, r8.A01);
            } else if (i2 == 2) {
                AnonymousClass1BA r22 = this.A02;
                r22.A72(r8);
                r22.A6e(r8.A02, r8.A01);
            } else if (i2 == 4) {
                AnonymousClass1BA r3 = this.A02;
                r3.A72(r8);
                r3.A6M(r8.A02, r8.A01, r8.A03);
            } else if (i2 == 8) {
                AnonymousClass1BA r23 = this.A02;
                r23.A72(r8);
                r23.A6d(r8.A02, r8.A01);
            }
        }
        A05(this, arrayList);
        this.A00 = 0;
    }
}
