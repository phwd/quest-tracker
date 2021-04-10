package X;

import android.app.Application;
import android.content.res.Resources;

public final class X9 {
    public static final void A01(int i, StringBuilder sb, String str, int i2) {
        if ((i & i2) > 0) {
            if (sb.length() > 0) {
                sb.append("|");
            }
            sb.append(str);
        }
    }

    public static final int A00(int i) {
        Application A00 = BX.A00();
        C0514bB.A01(A00, "ApplicationHolder.get()");
        Resources resources = A00.getResources();
        return (int) (resources.getDimension(i) / resources.getDisplayMetrics().density);
    }
}
