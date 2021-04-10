package X;

import androidx.constraintlayout.solver.widgets.Flow;
import java.util.ArrayList;

/* renamed from: X.2b4  reason: invalid class name */
public final class AnonymousClass2b4 extends AnonymousClass2b6 {
    public ArrayList<Flow.WidgetsList> A00 = new ArrayList<>();

    @Override // X.AnonymousClass2ac
    public final void A0J(C15022am r4, boolean z) {
        super.A0J(r4, z);
        ArrayList<Flow.WidgetsList> arrayList = this.A00;
        if (arrayList.size() > 0) {
            arrayList.get(0);
            throw new NullPointerException("createConstraints");
        }
    }
}
