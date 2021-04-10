package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0Fv  reason: invalid class name */
public final class AnonymousClass0Fv extends AnonymousClass0LL {
    public int A00 = 1;
    public final AnonymousClass0aT[] A01;

    public AnonymousClass0Fv(AnonymousClass0aT[] r2) {
        super(r2[0]);
        this.A01 = r2;
    }

    public static AnonymousClass0Fv A00(AnonymousClass0aT r3, AnonymousClass0aT r4) {
        AnonymousClass0aT[] r1;
        boolean z = r3 instanceof AnonymousClass0Fv;
        if (z || (r4 instanceof AnonymousClass0Fv)) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                ((AnonymousClass0Fv) r3).A01(arrayList);
            } else {
                arrayList.add(r3);
            }
            if (r4 instanceof AnonymousClass0Fv) {
                ((AnonymousClass0Fv) r4).A01(arrayList);
            } else {
                arrayList.add(r4);
            }
            r1 = (AnonymousClass0aT[]) arrayList.toArray(new AnonymousClass0aT[arrayList.size()]);
        } else {
            r1 = new AnonymousClass0aT[]{r3, r4};
        }
        return new AnonymousClass0Fv(r1);
    }

    private final void A01(List<AnonymousClass0aT> list) {
        AnonymousClass0aT[] r3 = this.A01;
        int length = r3.length;
        for (int i = this.A00 - 1; i < length; i++) {
            AnonymousClass0aT r1 = r3[i];
            if (r1 instanceof AnonymousClass0Fv) {
                ((AnonymousClass0Fv) r1).A01(list);
            } else {
                list.add(r1);
            }
        }
    }

    @Override // X.AnonymousClass0aT, X.AnonymousClass0LL
    public final EnumC05930lf A0a() throws IOException, C02630aU {
        AnonymousClass0aT r0 = super.A00;
        while (true) {
            EnumC05930lf A0a = r0.A0a();
            if (A0a != null) {
                return A0a;
            }
            int i = this.A00;
            AnonymousClass0aT[] r1 = this.A01;
            if (i >= r1.length) {
                return null;
            }
            this.A00 = i + 1;
            r0 = r1[i];
            super.A00 = r0;
        }
    }

    @Override // X.AnonymousClass0aT, X.AnonymousClass0LL, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        while (true) {
            super.A00.close();
            int i = this.A00;
            AnonymousClass0aT[] r1 = this.A01;
            if (i < r1.length) {
                this.A00 = i + 1;
                super.A00 = r1[i];
            } else {
                return;
            }
        }
    }
}
