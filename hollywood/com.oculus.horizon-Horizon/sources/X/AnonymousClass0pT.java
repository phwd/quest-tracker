package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0pT  reason: invalid class name */
public final class AnonymousClass0pT extends AbstractC01300Mt {
    public final ArrayList<Object> A00;

    @Override // X.AbstractC01300Mt
    public final void A06() {
        this.A00.clear();
    }

    @Override // X.AbstractC01300Mt
    public final void A07() {
        this.A01.A02.A01(this);
    }

    @Override // X.AbstractC01300Mt
    public final void A08() {
        ArrayList<Object> arrayList = this.A00;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            if (obj instanceof AbstractC01300Mt) {
                ((AbstractC01300Mt) obj).A04();
            }
        }
    }

    @Override // X.AbstractC01300Mt
    public final void A09(int i) {
        ArrayList<Object> arrayList = this.A00;
        int size = arrayList.size() - i;
        while (true) {
            int i2 = size - 1;
            if (size > 0) {
                arrayList.remove(arrayList.size() - 1);
                size = i2;
            } else {
                arrayList.trimToSize();
                return;
            }
        }
    }

    public final AnonymousClass0pS A0A() {
        AnonymousClass0pS A002 = this.A01.A00();
        if (this.A04) {
            A002.A03();
            if (this.A04) {
                this.A00.add(A002);
                A002.A03();
                ((AbstractC01300Mt) A002).A00 = this;
                return A002;
            }
        }
        throw new IllegalStateException("Expected object to be mutable");
    }

    public AnonymousClass0pT(int i) {
        this.A00 = new ArrayList<>(i);
    }
}
