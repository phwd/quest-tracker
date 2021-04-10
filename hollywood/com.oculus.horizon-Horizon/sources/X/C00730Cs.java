package X;

import android.view.View;
import java.util.Comparator;

/* renamed from: X.0Cs  reason: invalid class name and case insensitive filesystem */
public class C00730Cs implements Comparator<View> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final int compare(View view, View view2) {
        boolean z = ((C00710Cm) view.getLayoutParams()).A02;
        if (z == ((C00710Cm) view2.getLayoutParams()).A02) {
            return 0;
        }
        if (z) {
            return 1;
        }
        return -1;
    }
}
