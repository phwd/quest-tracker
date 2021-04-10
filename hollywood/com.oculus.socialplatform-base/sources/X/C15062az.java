package X;

import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: X.2az  reason: invalid class name and case insensitive filesystem */
public class C15062az extends AnonymousClass2ac implements AnonymousClass2aL {
    public int A00 = 0;
    public AnonymousClass2ac[] A01 = new AnonymousClass2ac[4];

    public final void A0V(ArrayList<AnonymousClass2ao> arrayList, int i, AnonymousClass2ao r7) {
        for (int i2 = 0; i2 < this.A00; i2++) {
            AnonymousClass2ac r1 = this.A01[i2];
            if (!r7.A03.contains(r1)) {
                r7.A03.add(r1);
            }
        }
        for (int i3 = 0; i3 < this.A00; i3++) {
            AnonymousClass2ar.A00(this.A01[i3], i, arrayList, r7);
        }
    }

    @Override // X.AnonymousClass2aL
    public final void A97() {
        this.A00 = 0;
        Arrays.fill(this.A01, (Object) null);
    }

    @Override // X.AnonymousClass2aL
    public final void A1C(AnonymousClass2ac r4) {
        if (r4 != this && r4 != null) {
            int i = this.A00 + 1;
            AnonymousClass2ac[] r1 = this.A01;
            int length = r1.length;
            if (i > length) {
                r1 = (AnonymousClass2ac[]) Arrays.copyOf(r1, length << 1);
                this.A01 = r1;
            }
            int i2 = this.A00;
            r1[i2] = r4;
            this.A00 = i2 + 1;
        }
    }

    @Override // X.AnonymousClass2aL
    public final void AAu(C14932ab r4) {
        if (this instanceof AnonymousClass2b6) {
            for (int i = 0; i < this.A00; i++) {
                AnonymousClass2ac r1 = this.A01[i];
                if (r1 != null) {
                    r1.A0m = true;
                }
            }
        }
    }
}
