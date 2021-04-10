package X;

import java.util.Arrays;
import org.apache.commons.cli.HelpFormatter;

/* renamed from: X.2as  reason: invalid class name */
public final class AnonymousClass2as extends AnonymousClass2ap {
    public AnonymousClass2bA A00;
    public int A01 = 0;
    public AnonymousClass2b3 A02 = new AnonymousClass2b3(this, this);
    public C15032at[] A03 = new C15032at[128];
    public C15032at[] A04 = new C15032at[128];

    public static final void A02(AnonymousClass2as r6, C15032at r7) {
        int i = 0;
        while (true) {
            int i2 = r6.A01;
            if (i < i2) {
                C15032at[] r2 = r6.A03;
                if (r2[i] != r7) {
                    i++;
                } else {
                    while (true) {
                        int i3 = i2 - 1;
                        if (i < i3) {
                            int i4 = i + 1;
                            r2[i] = r2[i4];
                            i = i4;
                        } else {
                            r6.A01 = i3;
                            r7.A08 = false;
                            return;
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    public static final void A01(AnonymousClass2as r7, C15032at r8) {
        int i = r7.A01 + 1;
        C15032at[] r1 = r7.A03;
        int length = r1.length;
        if (i > length) {
            C15032at[] r12 = (C15032at[]) Arrays.copyOf(r1, length << 1);
            r7.A03 = r12;
            r7.A04 = (C15032at[]) Arrays.copyOf(r12, r12.length << 1);
        }
        C15032at[] r6 = r7.A03;
        int i2 = r7.A01;
        r6[i2] = r8;
        int i3 = i2 + 1;
        r7.A01 = i3;
        if (i3 > 1 && r6[i3 - 1].A04 > r8.A04) {
            for (int i4 = 0; i4 < i3; i4++) {
                r7.A04[i4] = r6[i4];
            }
            Arrays.sort(r7.A04, 0, i3, new AnonymousClass2b8(r7));
            for (int i5 = 0; i5 < r7.A01; i5++) {
                r7.A03[i5] = r7.A04[i5];
            }
        }
        r8.A08 = true;
        r8.A01(r7);
    }

    @Override // X.AnonymousClass2ap
    public final String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(" goal -> (");
        sb.append(super.A00);
        sb.append(") : ");
        String obj = sb.toString();
        for (int i = 0; i < this.A01; i++) {
            C15032at r1 = this.A03[i];
            AnonymousClass2b3 r0 = this.A02;
            r0.A01 = r1;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(obj);
            sb2.append(r0);
            sb2.append(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
            obj = sb2.toString();
        }
        return obj;
    }

    public AnonymousClass2as(AnonymousClass2bA r3) {
        super(r3);
        this.A00 = r3;
    }
}
