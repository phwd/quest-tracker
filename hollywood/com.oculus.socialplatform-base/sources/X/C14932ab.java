package X;

import java.lang.ref.WeakReference;
import java.util.Arrays;

/* renamed from: X.2ab  reason: invalid class name and case insensitive filesystem */
public final class C14932ab extends AnonymousClass2b2 {
    public int A00 = 0;
    public int A01 = 257;
    public int A02;
    public int A03;
    public int A04 = 0;
    public C15022am A05 = new C15022am();
    public AnonymousClass2aY A06;
    public AnonymousClass2aH A07 = new AnonymousClass2aH();
    public AnonymousClass2aK A08 = null;
    public AnonymousClass2aB A09 = new AnonymousClass2aB(this);
    public C14922aa A0A = new C14922aa(this);
    public WeakReference<C14982ai> A0B = null;
    public WeakReference<C14982ai> A0C = null;
    public boolean A0D = false;
    public boolean A0E = false;
    public boolean A0F = false;
    public AnonymousClass2b0[] A0G = new AnonymousClass2b0[4];
    public AnonymousClass2b0[] A0H = new AnonymousClass2b0[4];
    public WeakReference<C14982ai> A0I = null;

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        if (r13.A01 <= com.oculus.vrshell.panels.AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        if (r13.A01 <= com.oculus.vrshell.panels.AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) goto L_0x003d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(X.AnonymousClass2ac r13, X.AnonymousClass2aK r14, X.AnonymousClass2aH r15) {
        /*
        // Method dump skipped, instructions count: 269
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C14932ab.A00(X.2ac, X.2aK, X.2aH):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:351:0x05d6, code lost:
        if (0 != 0) goto L_0x05d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:0x05f3, code lost:
        if (r1[1] == r13) goto L_0x05f5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x056c  */
    /* JADX WARNING: Removed duplicated region for block: B:335:0x05a3  */
    /* JADX WARNING: Removed duplicated region for block: B:341:0x05b6  */
    @Override // X.AnonymousClass2b2
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0V() {
        /*
        // Method dump skipped, instructions count: 2313
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C14932ab.A0V():void");
    }

    public static boolean A01(Integer num, Integer num2, Integer num3, Integer num4) {
        boolean z;
        boolean z2;
        Integer num5;
        Integer num6;
        Integer num7 = AnonymousClass007.A00;
        if (num3 == num7 || num3 == (num6 = AnonymousClass007.A01) || (num3 == AnonymousClass007.A04 && num != num6)) {
            z = true;
        } else {
            z = false;
        }
        if (num4 == num7 || num4 == (num5 = AnonymousClass007.A01) || (num4 == AnonymousClass007.A04 && num2 != num5)) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || z2) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass2b2, X.AnonymousClass2ac
    public final void A0A() {
        this.A05.A0A();
        this.A02 = 0;
        this.A03 = 0;
        super.A0A();
    }

    public final void A0W(C14982ai r4) {
        WeakReference<C14982ai> weakReference = this.A0I;
        if (weakReference == null || weakReference.get() == null || r4.A00() > weakReference.get().A00()) {
            this.A0I = new WeakReference<>(r4);
        }
    }

    public final void A0X(AnonymousClass2ac r6, int i) {
        if (i == 0) {
            int i2 = this.A00 + 1;
            AnonymousClass2b0[] r4 = this.A0G;
            int length = r4.length;
            if (i2 >= length) {
                r4 = (AnonymousClass2b0[]) Arrays.copyOf(r4, length << 1);
                this.A0G = r4;
            }
            int i3 = this.A00;
            r4[i3] = new AnonymousClass2b0(r6, 0, this.A0E);
            this.A00 = i3 + 1;
        } else if (i == 1) {
            int i4 = this.A04 + 1;
            AnonymousClass2b0[] r3 = this.A0H;
            int length2 = r3.length;
            if (i4 >= length2) {
                r3 = (AnonymousClass2b0[]) Arrays.copyOf(r3, length2 << 1);
                this.A0H = r3;
            }
            int i5 = this.A04;
            r3[i5] = new AnonymousClass2b0(r6, 1, this.A0E);
            this.A04 = i5 + 1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00aa, code lost:
        if (r14 == 0) goto L_0x005d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0110 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0Y(boolean r13, int r14) {
        /*
        // Method dump skipped, instructions count: 305
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C14932ab.A0Y(boolean, int):boolean");
    }

    @Override // X.AnonymousClass2ac
    public final void A0N(boolean z, boolean z2) {
        super.A0N(z, z2);
        int size = ((AnonymousClass2b2) this).A00.size();
        for (int i = 0; i < size; i++) {
            ((AnonymousClass2b2) this).A00.get(i).A0N(z, z2);
        }
    }
}
