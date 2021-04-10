package X;

import androidx.recyclerview.widget.RecyclerView;
import java.util.Comparator;

/* renamed from: X.1BC  reason: invalid class name */
public class AnonymousClass1BC implements Comparator<AnonymousClass1BJ> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final int compare(AnonymousClass1BJ r7, AnonymousClass1BJ r8) {
        AnonymousClass1BJ r72 = r7;
        AnonymousClass1BJ r82 = r8;
        RecyclerView recyclerView = r72.A03;
        boolean z = false;
        if (recyclerView == null) {
            z = true;
        }
        boolean z2 = false;
        if (r82.A03 == null) {
            z2 = true;
        }
        if (z == z2) {
            boolean z3 = r72.A04;
            if (z3 == r82.A04) {
                int i = r82.A02 - r72.A02;
                if (i == 0 && (i = r72.A00 - r82.A00) == 0) {
                    return 0;
                }
                return i;
            } else if (z3) {
                return -1;
            }
        } else if (recyclerView == null) {
            return 1;
        } else {
            return -1;
        }
        return 1;
    }
}
