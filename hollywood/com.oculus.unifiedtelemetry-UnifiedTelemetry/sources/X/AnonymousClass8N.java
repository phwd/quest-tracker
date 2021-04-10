package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.8N  reason: invalid class name */
public final class AnonymousClass8N extends DK {
    public int A00 = 1;
    public final AbstractC0232Ww[] A01;

    public AnonymousClass8N(AbstractC0232Ww[] wwArr) {
        super(wwArr[0]);
        this.A01 = wwArr;
    }

    public static AnonymousClass8N A00(AbstractC0232Ww ww, AbstractC0232Ww ww2) {
        AbstractC0232Ww[] wwArr;
        boolean z = ww instanceof AnonymousClass8N;
        if (z || (ww2 instanceof AnonymousClass8N)) {
            ArrayList arrayList = new ArrayList();
            if (z) {
                ((AnonymousClass8N) ww).A01(arrayList);
            } else {
                arrayList.add(ww);
            }
            if (ww2 instanceof AnonymousClass8N) {
                ((AnonymousClass8N) ww2).A01(arrayList);
            } else {
                arrayList.add(ww2);
            }
            wwArr = (AbstractC0232Ww[]) arrayList.toArray(new AbstractC0232Ww[arrayList.size()]);
        } else {
            wwArr = new AbstractC0232Ww[]{ww, ww2};
        }
        return new AnonymousClass8N(wwArr);
    }

    private final void A01(List<AbstractC0232Ww> list) {
        AbstractC0232Ww[] wwArr = this.A01;
        int length = wwArr.length;
        for (int i = this.A00 - 1; i < length; i++) {
            AbstractC0232Ww ww = wwArr[i];
            if (ww instanceof AnonymousClass8N) {
                ((AnonymousClass8N) ww).A01(list);
            } else {
                list.add(ww);
            }
        }
    }

    @Override // X.DK, X.AbstractC0232Ww
    public final EnumC0470q2 A0a() throws IOException, Wx {
        AbstractC0232Ww ww = ((DK) this).A00;
        while (true) {
            EnumC0470q2 A0a = ww.A0a();
            if (A0a != null) {
                return A0a;
            }
            int i = this.A00;
            AbstractC0232Ww[] wwArr = this.A01;
            if (i >= wwArr.length) {
                return null;
            }
            this.A00 = i + 1;
            ww = wwArr[i];
            ((DK) this).A00 = ww;
        }
    }

    @Override // X.DK, java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0232Ww
    public final void close() throws IOException {
        while (true) {
            ((DK) this).A00.close();
            int i = this.A00;
            AbstractC0232Ww[] wwArr = this.A01;
            if (i < wwArr.length) {
                this.A00 = i + 1;
                ((DK) this).A00 = wwArr[i];
            } else {
                return;
            }
        }
    }
}
