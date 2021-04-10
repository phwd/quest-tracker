package X;

import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Arrays;

/* renamed from: X.2at  reason: invalid class name and case insensitive filesystem */
public final class C15032at {
    public static int A0C = 1;
    public int A00 = 0;
    public AnonymousClass2ap[] A01 = new AnonymousClass2ap[16];
    public float A02;
    public int A03 = -1;
    public int A04 = -1;
    public int A05 = 0;
    public int A06 = 0;
    public Integer A07;
    public boolean A08;
    public boolean A09 = false;
    public float[] A0A = new float[9];
    public float[] A0B = new float[9];

    public final void A00() {
        this.A07 = AnonymousClass007.A05;
        this.A05 = 0;
        this.A04 = -1;
        this.A03 = -1;
        this.A02 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        this.A09 = false;
        int i = this.A00;
        for (int i2 = 0; i2 < i; i2++) {
            this.A01[i2] = null;
        }
        this.A00 = 0;
        this.A06 = 0;
        this.A08 = false;
        Arrays.fill(this.A0A, (float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
    }

    public final void A01(AnonymousClass2ap r4) {
        int i = 0;
        while (true) {
            int i2 = this.A00;
            if (i >= i2) {
                AnonymousClass2ap[] r1 = this.A01;
                int length = r1.length;
                if (i2 >= length) {
                    r1 = (AnonymousClass2ap[]) Arrays.copyOf(r1, length << 1);
                    this.A01 = r1;
                }
                int i3 = this.A00;
                r1[i3] = r4;
                this.A00 = i3 + 1;
                return;
            } else if (this.A01[i] != r4) {
                i++;
            } else {
                return;
            }
        }
    }

    public final void A02(AnonymousClass2ap r6) {
        int i = this.A00;
        int i2 = 0;
        while (i2 < i) {
            AnonymousClass2ap[] r2 = this.A01;
            if (r2[i2] != r6) {
                i2++;
            } else {
                while (true) {
                    int i3 = i - 1;
                    if (i2 < i3) {
                        int i4 = i2 + 1;
                        r2[i2] = r2[i4];
                        i2 = i4;
                    } else {
                        this.A00 = i3;
                        return;
                    }
                }
            }
        }
    }

    public final void A03(C15022am r5, float f) {
        this.A02 = f;
        this.A09 = true;
        int i = this.A00;
        this.A03 = -1;
        for (int i2 = 0; i2 < i; i2++) {
            this.A01[i2].A04(r5, this, false);
        }
        this.A00 = 0;
    }

    public final void A04(C15022am r5, AnonymousClass2ap r6) {
        int i = this.A00;
        for (int i2 = 0; i2 < i; i2++) {
            this.A01[i2].A03(r5, r6, false);
        }
        this.A00 = 0;
    }

    public final String toString() {
        return AnonymousClass006.A03("", this.A04);
    }

    public C15032at(Integer num) {
        this.A07 = num;
    }
}
