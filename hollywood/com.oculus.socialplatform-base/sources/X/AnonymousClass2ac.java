package X;

import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.commons.cli.HelpFormatter;

/* renamed from: X.2ac  reason: invalid class name */
public class AnonymousClass2ac {
    public float A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public float A01;
    public float A02;
    public float A03 = 1.0f;
    public float A04 = 1.0f;
    public float A05 = 1.0f;
    public float A06;
    public int A07;
    public int A08;
    public int A09;
    public int A0A;
    public int A0B;
    public int A0C = -1;
    public int A0D = 0;
    public int A0E = 0;
    public int A0F = 0;
    public int A0G = 0;
    public int A0H = 0;
    public int A0I = 0;
    public int A0J = 0;
    public int A0K = 0;
    public int A0L;
    public int A0M;
    public int A0N = -1;
    public int A0O;
    public int A0P = -1;
    public int A0Q;
    public int A0R;
    public int A0S;
    public int A0T;
    public int A0U;
    public C14982ai A0V;
    public C14982ai A0W;
    public C14982ai A0X;
    public C14982ai A0Y;
    public C14982ai A0Z;
    public C14982ai A0a;
    public C14982ai A0b;
    public C14982ai A0c;
    public AnonymousClass2ac A0d;
    public C14942ad A0e;
    public C14942ad A0f;
    public AnonymousClass2ae A0g = null;
    public C14952af A0h = null;
    public Object A0i;
    public String A0j;
    public ArrayList<C14982ai> A0k;
    public boolean A0l = false;
    public boolean A0m = false;
    public boolean A0n = true;
    public boolean A0o = false;
    public boolean A0p = false;
    public float[] A0q;
    public int[] A0r = {Integer.MAX_VALUE, Integer.MAX_VALUE};
    public int[] A0s = new int[2];
    public C14982ai[] A0t;
    public AnonymousClass2ac[] A0u;
    public AnonymousClass2ac[] A0v;
    public Integer[] A0w;
    public boolean[] A0x = {true, true};
    public boolean[] A0y;
    public boolean A0z = true;
    public boolean A10 = false;

    public final void A0C() {
        this.A10 = false;
        this.A0p = false;
        ArrayList<C14982ai> arrayList = this.A0k;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            C14982ai r1 = arrayList.get(i);
            r1.A06 = false;
            r1.A01 = 0;
        }
    }

    public final void A0L(C14932ab r9, C15022am r10, HashSet<AnonymousClass2ac> hashSet, int i, boolean z) {
        if (z) {
            if (hashSet.contains(this)) {
                C15042av.A00(r9, r10, this);
                hashSet.remove(this);
                boolean z2 = false;
                if ((r9.A01 & 64) == 64) {
                    z2 = true;
                }
                A0J(r10, z2);
            } else {
                return;
            }
        }
        if (i == 0) {
            HashSet<C14982ai> hashSet2 = this.A0a.A05;
            if (hashSet2 != null) {
                Iterator<C14982ai> it = hashSet2.iterator();
                while (it.hasNext()) {
                    it.next().A07.A0L(r9, r10, hashSet, 0, true);
                }
            }
            HashSet<C14982ai> hashSet3 = this.A0b.A05;
            if (hashSet3 != null) {
                Iterator<C14982ai> it2 = hashSet3.iterator();
                while (it2.hasNext()) {
                    it2.next().A07.A0L(r9, r10, hashSet, 0, true);
                }
                return;
            }
            return;
        }
        HashSet<C14982ai> hashSet4 = this.A0c.A05;
        if (hashSet4 != null) {
            Iterator<C14982ai> it3 = hashSet4.iterator();
            while (it3.hasNext()) {
                it3.next().A07.A0L(r9, r10, hashSet, i, true);
            }
        }
        HashSet<C14982ai> hashSet5 = this.A0W.A05;
        if (hashSet5 != null) {
            Iterator<C14982ai> it4 = hashSet5.iterator();
            while (it4.hasNext()) {
                it4.next().A07.A0L(r9, r10, hashSet, i, true);
            }
        }
        HashSet<C14982ai> hashSet6 = this.A0V.A05;
        if (hashSet6 != null) {
            Iterator<C14982ai> it5 = hashSet6.iterator();
            while (it5.hasNext()) {
                it5.next().A07.A0L(r9, r10, hashSet, i, true);
            }
        }
    }

    public final boolean A0U(int i) {
        int i2;
        C14982ai r0;
        if (i == 0) {
            i2 = 0;
            if (this.A0a.A04 != null) {
                i2 = 1;
            }
            r0 = this.A0b;
        } else {
            int i3 = 0;
            if (this.A0c.A04 != null) {
                i3 = 1;
            }
            int i4 = 0;
            if (this.A0W.A04 != null) {
                i4 = 1;
            }
            i2 = i3 + i4;
            r0 = this.A0V;
        }
        C14982ai r1 = r0.A04;
        int i5 = 0;
        if (r1 != null) {
            i5 = 1;
        }
        if (i2 + i5 >= 2) {
            return false;
        }
        return true;
    }

    public final int A03() {
        if (this.A0Q == 8) {
            return 0;
        }
        return this.A0A;
    }

    public final int A04() {
        if (this.A0Q == 8) {
            return 0;
        }
        return this.A0R;
    }

    public final int A05() {
        AnonymousClass2ac r1 = this.A0d;
        if (r1 == null || !(r1 instanceof C14932ab)) {
            return this.A0S;
        }
        return ((C14932ab) r1).A02 + this.A0S;
    }

    public final int A06() {
        AnonymousClass2ac r1 = this.A0d;
        if (r1 == null || !(r1 instanceof C14932ab)) {
            return this.A0T;
        }
        return ((C14932ab) r1).A03 + this.A0T;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0044, code lost:
        if (r2.A01 == 1) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0049, code lost:
        if (r2.A01 == 0) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004d, code lost:
        return r2.A04;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C14982ai A07(java.lang.Integer r4) {
        /*
        // Method dump skipped, instructions count: 124
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass2ac.A07(java.lang.Integer):X.2ai");
    }

    public final AnonymousClass2ac A08(int i) {
        C14982ai r2;
        if (i == 0) {
            r2 = this.A0b;
        } else if (i != 1) {
            return null;
        } else {
            r2 = this.A0W;
        }
        C14982ai r1 = r2.A04;
        if (r1 == null || r1.A04 != r2) {
            return null;
        }
        return r1.A07;
    }

    public final AnonymousClass2ac A09(int i) {
        C14982ai r2;
        if (i == 0) {
            r2 = this.A0a;
        } else if (i != 1) {
            return null;
        } else {
            r2 = this.A0c;
        }
        C14982ai r1 = r2.A04;
        if (r1 == null || r1.A04 != r2) {
            return null;
        }
        return r1.A07;
    }

    public void A0A() {
        this.A0a.A02();
        this.A0c.A02();
        this.A0b.A02();
        this.A0W.A02();
        this.A0V.A02();
        this.A0Y.A02();
        this.A0Z.A02();
        this.A0X.A02();
        this.A0d = null;
        this.A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A0R = 0;
        this.A0A = 0;
        this.A01 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A09 = -1;
        this.A0S = 0;
        this.A0T = 0;
        this.A08 = 0;
        this.A0M = 0;
        this.A0L = 0;
        this.A02 = 0.5f;
        this.A06 = 0.5f;
        Integer[] numArr = this.A0w;
        Integer num = AnonymousClass007.A00;
        numArr[0] = num;
        numArr[1] = num;
        this.A0i = null;
        this.A0Q = 0;
        this.A0B = 0;
        this.A0O = 0;
        float[] fArr = this.A0q;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.A0C = -1;
        this.A0P = -1;
        int[] iArr = this.A0r;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.A0G = 0;
        this.A0F = 0;
        this.A04 = 1.0f;
        this.A03 = 1.0f;
        this.A0I = Integer.MAX_VALUE;
        this.A0H = Integer.MAX_VALUE;
        this.A0K = 0;
        this.A0J = 0;
        this.A0N = -1;
        this.A05 = 1.0f;
        boolean[] zArr = this.A0x;
        zArr[0] = true;
        zArr[1] = true;
        this.A0m = false;
        boolean[] zArr2 = this.A0y;
        zArr2[0] = false;
        zArr2[1] = false;
        this.A0n = true;
    }

    public final void A0B() {
        if (this.A0g == null) {
            this.A0g = new AnonymousClass2ae(this);
        }
        if (this.A0h == null) {
            this.A0h = new C14952af(this);
        }
    }

    public final void A0D(int i) {
        this.A0A = i;
        int i2 = this.A0L;
        if (i < i2) {
            this.A0A = i2;
        }
    }

    public final void A0E(int i) {
        this.A0R = i;
        int i2 = this.A0M;
        if (i < i2) {
            this.A0R = i2;
        }
    }

    public final void A0F(int i, int i2) {
        this.A0a.A04(i);
        this.A0b.A04(i2);
        this.A0S = i;
        this.A0R = i2 - i;
        this.A10 = true;
    }

    public final void A0G(int i, int i2) {
        this.A0c.A04(i);
        this.A0W.A04(i2);
        this.A0T = i;
        this.A0A = i2 - i;
        if (this.A0l) {
            this.A0V.A04(i + this.A08);
        }
        this.A0p = true;
    }

    public void A0H(AnonymousClass2bA r2) {
        this.A0a.A03();
        this.A0c.A03();
        this.A0b.A03();
        this.A0W.A03();
        this.A0V.A03();
        this.A0X.A03();
        this.A0Y.A03();
        this.A0Z.A03();
    }

    public final void A0I(C15022am r2) {
        r2.A08(this.A0a);
        r2.A08(this.A0c);
        r2.A08(this.A0b);
        r2.A08(this.A0W);
        if (this.A08 > 0) {
            r2.A08(this.A0V);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02b7, code lost:
        if (r78.A0W.A04 != null) goto L_0x02b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x02cc, code lost:
        if (r7 == -1) goto L_0x02ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x02ce, code lost:
        r67 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x05a2, code lost:
        if (r7 != 1) goto L_0x02cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x05b0, code lost:
        if (r9 == -1) goto L_0x05b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x05d1, code lost:
        if (r78.A0b.A04 != null) goto L_0x02b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x05f6, code lost:
        if (r78.A0W.A04 != null) goto L_0x05ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x0603, code lost:
        if (r78.A0b.A04 != null) goto L_0x060c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x060a, code lost:
        if (r78.A0J > 0) goto L_0x060c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x060c, code lost:
        r78.A05 = 1.0f / r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x010e, code lost:
        if (r78.A0p != false) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003a, code lost:
        if (r6[1] != r5) goto L_0x003c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x0392  */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x039e  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x03a8  */
    /* JADX WARNING: Removed duplicated region for block: B:245:0x03bc  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0404  */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x044b  */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x0509  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x051b  */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05cd  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x05db A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x05ff  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x0608  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void A0J(X.C15022am r79, boolean r80) {
        /*
        // Method dump skipped, instructions count: 1631
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass2ac.A0J(X.2am, boolean):void");
    }

    public final void A0K(C15022am r8, boolean z) {
        int i;
        int i2;
        if (!(this instanceof C15002ak)) {
            int A002 = C15022am.A00(this.A0a);
            int A003 = C15022am.A00(this.A0c);
            int A004 = C15022am.A00(this.A0b);
            int A005 = C15022am.A00(this.A0W);
            if (z) {
                AnonymousClass2ae r1 = this.A0g;
                if (r1 != null) {
                    AnonymousClass2an r2 = r1.A04;
                    if (r2.A0B) {
                        AnonymousClass2an r12 = r1.A03;
                        if (r12.A0B) {
                            A002 = r2.A04;
                            A004 = r12.A04;
                        }
                    }
                }
                C14952af r13 = this.A0h;
                if (r13 != null) {
                    AnonymousClass2an r22 = r13.A04;
                    if (r22.A0B) {
                        AnonymousClass2an r14 = r13.A03;
                        if (r14.A0B) {
                            A003 = r22.A04;
                            A005 = r14.A04;
                        }
                    }
                }
            }
            int i3 = A005 - A003;
            if (A004 - A002 < 0 || i3 < 0 || A002 == Integer.MIN_VALUE || A002 == Integer.MAX_VALUE || A003 == Integer.MIN_VALUE || A003 == Integer.MAX_VALUE || A004 == Integer.MIN_VALUE || A004 == Integer.MAX_VALUE || A005 == Integer.MIN_VALUE || A005 == Integer.MAX_VALUE) {
                A005 = 0;
                A002 = 0;
                A003 = 0;
                A004 = 0;
            }
            int i4 = A004 - A002;
            int i5 = A005 - A003;
            this.A0S = A002;
            this.A0T = A003;
            if (this.A0Q == 8) {
                this.A0R = 0;
                this.A0A = 0;
                return;
            }
            Integer[] numArr = this.A0w;
            Integer num = numArr[0];
            Integer num2 = AnonymousClass007.A00;
            if (num != num2 || i4 >= (i = this.A0R)) {
                i = i4;
            }
            if (numArr[1] != num2 || i5 >= (i2 = this.A0A)) {
                i2 = i5;
            }
            this.A0R = i;
            this.A0A = i2;
            int i6 = this.A0L;
            if (i2 < i6) {
                this.A0A = i6;
            }
            int i7 = this.A0M;
            if (i < i7) {
                this.A0R = i7;
                return;
            }
            return;
        }
        C15002ak r5 = (C15002ak) this;
        AnonymousClass2ac r4 = r5.A0d;
        if (r4 != null) {
            int A006 = C15022am.A00(r5.A04);
            if (r5.A01 == 1) {
                r5.A0S = A006;
                r5.A0T = 0;
                r5.A0D(r4.A03());
                r5.A0E(0);
                return;
            }
            r5.A0S = 0;
            r5.A0T = A006;
            r5.A0E(r4.A04());
            r5.A0D(0);
        }
    }

    public void A0N(boolean z, boolean z2) {
        int i;
        int i2;
        AnonymousClass2ae r2 = this.A0g;
        boolean z3 = z & r2.A09;
        C14952af r1 = this.A0h;
        boolean z4 = z2 & r1.A09;
        int i3 = r2.A04.A04;
        int i4 = r1.A04.A04;
        int i5 = r2.A03.A04;
        int i6 = r1.A03.A04;
        int i7 = i6 - i4;
        if (i5 - i3 < 0 || i7 < 0 || i3 == Integer.MIN_VALUE || i3 == Integer.MAX_VALUE || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE) {
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        int i8 = i5 - i3;
        int i9 = i6 - i4;
        if (z3) {
            this.A0S = i3;
        }
        if (z4) {
            this.A0T = i4;
        }
        if (this.A0Q == 8) {
            this.A0R = 0;
            this.A0A = 0;
            return;
        }
        if (z3) {
            if (this.A0w[0] != AnonymousClass007.A00 || i8 >= (i2 = this.A0R)) {
                i2 = i8;
            }
            this.A0R = i2;
            int i10 = this.A0M;
            if (i2 < i10) {
                this.A0R = i10;
            }
        }
        if (z4) {
            if (this.A0w[1] != AnonymousClass007.A00 || i9 >= (i = this.A0A)) {
                i = i9;
            }
            this.A0A = i;
            int i11 = this.A0L;
            if (i < i11) {
                this.A0A = i11;
            }
        }
    }

    public final boolean A0O() {
        if ((this instanceof C15002ak) || (this instanceof C15012al) || this.A0Q != 8) {
            return true;
        }
        return false;
    }

    public final boolean A0P() {
        if (this instanceof C15002ak) {
            return ((C15002ak) this).A05;
        }
        if (this instanceof C15012al) {
            return ((C15012al) this).A03;
        }
        if (this.A10) {
            return true;
        }
        if (!this.A0a.A06 || !this.A0b.A06) {
            return false;
        }
        return true;
    }

    public final boolean A0Q() {
        if (this instanceof C15002ak) {
            return ((C15002ak) this).A05;
        }
        if (this instanceof C15012al) {
            return ((C15012al) this).A03;
        }
        if (this.A0p) {
            return true;
        }
        if (!this.A0c.A06 || !this.A0W.A06) {
            return false;
        }
        return true;
    }

    public final boolean A0R() {
        C14982ai r1 = this.A0a;
        C14982ai r0 = r1.A04;
        if (r0 != null && r0.A04 == r1) {
            return true;
        }
        C14982ai r12 = this.A0b;
        C14982ai r02 = r12.A04;
        if (r02 == null || r02.A04 != r12) {
            return false;
        }
        return true;
    }

    public final boolean A0S() {
        C14982ai r1 = this.A0c;
        C14982ai r0 = r1.A04;
        if (r0 != null && r0.A04 == r1) {
            return true;
        }
        C14982ai r12 = this.A0W;
        C14982ai r02 = r12.A04;
        if (r02 == null || r02.A04 != r12) {
            return false;
        }
        return true;
    }

    public final boolean A0T() {
        if (!this.A0n || this.A0Q == 8) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = "";
        sb.append(str);
        String str2 = this.A0j;
        if (str2 != null) {
            str = AnonymousClass006.A09("id: ", str2, HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
        }
        sb.append(str);
        sb.append("(");
        sb.append(this.A0S);
        sb.append(", ");
        sb.append(this.A0T);
        sb.append(") - (");
        sb.append(this.A0R);
        sb.append(" x ");
        sb.append(this.A0A);
        sb.append(")");
        return sb.toString();
    }

    public AnonymousClass2ac() {
        C14982ai r7 = new C14982ai(this, AnonymousClass007.A01);
        this.A0a = r7;
        C14982ai r9 = new C14982ai(this, AnonymousClass007.A03);
        this.A0c = r9;
        C14982ai r8 = new C14982ai(this, AnonymousClass007.A04);
        this.A0b = r8;
        C14982ai r10 = new C14982ai(this, AnonymousClass007.A05);
        this.A0W = r10;
        C14982ai r11 = new C14982ai(this, AnonymousClass007.A06);
        this.A0V = r11;
        this.A0Y = new C14982ai(this, AnonymousClass007.A08);
        this.A0Z = new C14982ai(this, AnonymousClass007.A09);
        C14982ai r12 = new C14982ai(this, AnonymousClass007.A07);
        this.A0X = r12;
        this.A0t = new C14982ai[]{r7, r8, r9, r10, r11, r12};
        this.A0k = new ArrayList<>();
        this.A0y = new boolean[2];
        Integer num = AnonymousClass007.A00;
        this.A0w = new Integer[]{num, num};
        this.A0d = null;
        this.A0R = 0;
        this.A0A = 0;
        this.A01 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A09 = -1;
        this.A0S = 0;
        this.A0T = 0;
        this.A08 = 0;
        this.A02 = 0.5f;
        this.A06 = 0.5f;
        this.A0Q = 0;
        this.A0j = null;
        this.A0B = 0;
        this.A0O = 0;
        this.A0q = new float[]{-1.0f, -1.0f};
        this.A0u = new AnonymousClass2ac[]{null, null};
        this.A0v = new AnonymousClass2ac[]{null, null};
        this.A07 = -1;
        this.A0U = -1;
        ArrayList<C14982ai> arrayList = this.A0k;
        arrayList.add(this.A0a);
        arrayList.add(this.A0c);
        arrayList.add(this.A0b);
        arrayList.add(this.A0W);
        arrayList.add(this.A0Y);
        arrayList.add(this.A0Z);
        arrayList.add(this.A0X);
        arrayList.add(this.A0V);
    }

    public final void A0M(Integer num, AnonymousClass2ac r4, Integer num2, int i, int i2) {
        A07(num).A06(r4.A07(num2), i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x016a, code lost:
        if (r7 == r9) goto L_0x016c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x01c0, code lost:
        if (r18 != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x02a0, code lost:
        if (r16 != false) goto L_0x023f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0060, code lost:
        if (r6 == 4) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x0334, code lost:
        if (r6 != 1) goto L_0x0336;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0140, code lost:
        if (r58 == 1) goto L_0x0142;
     */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x01cc A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x020f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0233 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x02a3  */
    /* JADX WARNING: Removed duplicated region for block: B:269:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A02(X.C15022am r36, boolean r37, boolean r38, boolean r39, boolean r40, X.C15032at r41, X.C15032at r42, java.lang.Integer r43, boolean r44, X.C14982ai r45, X.C14982ai r46, int r47, int r48, int r49, int r50, float r51, boolean r52, boolean r53, boolean r54, boolean r55, boolean r56, int r57, int r58, int r59, int r60, float r61, boolean r62) {
        /*
        // Method dump skipped, instructions count: 963
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass2ac.A02(X.2am, boolean, boolean, boolean, boolean, X.2at, X.2at, java.lang.Integer, boolean, X.2ai, X.2ai, int, int, int, int, float, boolean, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }
}
