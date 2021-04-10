package X;

import android.content.Context;

public final class S8 {
    public static S8 A01 = new S8();
    public S7 A00 = null;

    public static S7 A00(Context context) {
        S7 s7;
        S8 s8 = A01;
        synchronized (s8) {
            s7 = s8.A00;
            if (s7 == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                s7 = new S7(context);
                s8.A00 = s7;
            }
        }
        return s7;
    }
}
