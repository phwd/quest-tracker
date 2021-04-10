package X;

import java.util.ArrayList;

/* renamed from: X.2b2  reason: invalid class name */
public class AnonymousClass2b2 extends AnonymousClass2ac {
    public ArrayList<AnonymousClass2ac> A00 = new ArrayList<>();

    @Override // X.AnonymousClass2ac
    public void A0A() {
        this.A00.clear();
        super.A0A();
    }

    public void A0V() {
        ArrayList<AnonymousClass2ac> arrayList = this.A00;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                AnonymousClass2ac r1 = this.A00.get(i);
                if (r1 instanceof AnonymousClass2b2) {
                    ((AnonymousClass2b2) r1).A0V();
                }
            }
        }
    }

    @Override // X.AnonymousClass2ac
    public final void A0H(AnonymousClass2bA r4) {
        super.A0H(r4);
        int size = this.A00.size();
        for (int i = 0; i < size; i++) {
            this.A00.get(i).A0H(r4);
        }
    }
}
