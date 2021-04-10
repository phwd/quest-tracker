package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0HV  reason: invalid class name */
public final class AnonymousClass0HV extends AnonymousClass0So {
    public int A00 = 1;
    public final AbstractC02280iQ[] A01;

    public AnonymousClass0HV(AbstractC02280iQ[] r2) {
        super(r2[0]);
        this.A01 = r2;
    }

    public static AnonymousClass0HV A00(AbstractC02280iQ r3, AbstractC02280iQ r4) {
        AbstractC02280iQ[] r1;
        boolean z = r3 instanceof AnonymousClass0HV;
        if (z || (r4 instanceof AnonymousClass0HV)) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                ((AnonymousClass0HV) r3).A01(arrayList);
            } else {
                arrayList.add(r3);
            }
            if (r4 instanceof AnonymousClass0HV) {
                ((AnonymousClass0HV) r4).A01(arrayList);
            } else {
                arrayList.add(r4);
            }
            r1 = (AbstractC02280iQ[]) arrayList.toArray(new AbstractC02280iQ[arrayList.size()]);
        } else {
            r1 = new AbstractC02280iQ[]{r3, r4};
        }
        return new AnonymousClass0HV(r1);
    }

    private final void A01(List<AbstractC02280iQ> list) {
        AbstractC02280iQ[] r3 = this.A01;
        int length = r3.length;
        for (int i = this.A00 - 1; i < length; i++) {
            AbstractC02280iQ r1 = r3[i];
            if (r1 instanceof AnonymousClass0HV) {
                ((AnonymousClass0HV) r1).A01(list);
            } else {
                list.add(r1);
            }
        }
    }

    @Override // X.AnonymousClass0So, X.AbstractC02280iQ
    public final EnumC03640oE A0j() throws IOException, C02290iR {
        AbstractC02280iQ r0 = ((AnonymousClass0So) this).A00;
        while (true) {
            EnumC03640oE A0j = r0.A0j();
            if (A0j != null) {
                return A0j;
            }
            int i = this.A00;
            AbstractC02280iQ[] r1 = this.A01;
            if (i >= r1.length) {
                return null;
            }
            this.A00 = i + 1;
            r0 = r1[i];
            ((AnonymousClass0So) this).A00 = r0;
        }
    }

    @Override // java.io.Closeable, X.AnonymousClass0So, java.lang.AutoCloseable, X.AbstractC02280iQ
    public final void close() throws IOException {
        while (true) {
            ((AnonymousClass0So) this).A00.close();
            int i = this.A00;
            AbstractC02280iQ[] r1 = this.A01;
            if (i < r1.length) {
                this.A00 = i + 1;
                ((AnonymousClass0So) this).A00 = r1[i];
            } else {
                return;
            }
        }
    }
}
