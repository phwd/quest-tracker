package X;

import android.content.SharedPreferences;

public final class W0 {
    public static W0 A01;
    public SharedPreferences A00 = BX.A00().getSharedPreferences("assistant_config", 0);

    public static W0 A00() {
        W0 w0 = A01;
        if (w0 != null) {
            return w0;
        }
        W0 w02 = new W0();
        A01 = w02;
        return w02;
    }
}
