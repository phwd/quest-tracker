package X;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public final class Bw {
    public static Bw A02;
    public static final Integer A03 = 4;
    public final PendingIntent A00;
    public final Context A01;

    public Bw(Context context) {
        this.A01 = context;
        this.A00 = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
    }
}
