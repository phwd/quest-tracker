package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.08Y  reason: invalid class name */
public final class AnonymousClass08Y extends AnonymousClass0HN {
    public int A00 = 1;
    public final AbstractC04100gp[] A01;

    public AnonymousClass08Y(AbstractC04100gp[] r2) {
        super(r2[0]);
        this.A01 = r2;
    }

    public static AnonymousClass08Y A00(AbstractC04100gp r3, AbstractC04100gp r4) {
        AbstractC04100gp[] r1;
        boolean z = r3 instanceof AnonymousClass08Y;
        if (z || (r4 instanceof AnonymousClass08Y)) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                ((AnonymousClass08Y) r3).A01(arrayList);
            } else {
                arrayList.add(r3);
            }
            if (r4 instanceof AnonymousClass08Y) {
                ((AnonymousClass08Y) r4).A01(arrayList);
            } else {
                arrayList.add(r4);
            }
            r1 = (AbstractC04100gp[]) arrayList.toArray(new AbstractC04100gp[arrayList.size()]);
        } else {
            r1 = new AbstractC04100gp[]{r3, r4};
        }
        return new AnonymousClass08Y(r1);
    }

    private final void A01(List<AbstractC04100gp> list) {
        AbstractC04100gp[] r3 = this.A01;
        int length = r3.length;
        for (int i = this.A00 - 1; i < length; i++) {
            AbstractC04100gp r1 = r3[i];
            if (r1 instanceof AnonymousClass08Y) {
                ((AnonymousClass08Y) r1).A01(list);
            } else {
                list.add(r1);
            }
        }
    }

    @Override // X.AnonymousClass0HN, X.AbstractC04100gp
    public final EnumC04820ji A0b() throws IOException, C04110gq {
        AbstractC04100gp r0 = ((AnonymousClass0HN) this).A00;
        while (true) {
            EnumC04820ji A0b = r0.A0b();
            if (A0b != null) {
                return A0b;
            }
            int i = this.A00;
            AbstractC04100gp[] r1 = this.A01;
            if (i >= r1.length) {
                return null;
            }
            this.A00 = i + 1;
            r0 = r1[i];
            ((AnonymousClass0HN) this).A00 = r0;
        }
    }

    @Override // java.io.Closeable, X.AnonymousClass0HN, java.lang.AutoCloseable, X.AbstractC04100gp
    public final void close() throws IOException {
        while (true) {
            ((AnonymousClass0HN) this).A00.close();
            int i = this.A00;
            AbstractC04100gp[] r1 = this.A01;
            if (i < r1.length) {
                this.A00 = i + 1;
                ((AnonymousClass0HN) this).A00 = r1[i];
            } else {
                return;
            }
        }
    }
}
