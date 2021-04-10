package X;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailability;

/* renamed from: X.s2  reason: case insensitive filesystem */
public final class HandlerC1082s2 extends SR {
    public final Context A00;
    public final /* synthetic */ GoogleApiAvailability A01;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HandlerC1082s2(com.google.android.gms.common.GoogleApiAvailability r2, android.content.Context r3) {
        /*
            r1 = this;
            r1.A01 = r2
            android.os.Looper r0 = android.os.Looper.myLooper()
            if (r0 != 0) goto L_0x0016
            android.os.Looper r0 = android.os.Looper.getMainLooper()
        L_0x000c:
            r1.<init>(r0)
            android.content.Context r0 = r3.getApplicationContext()
            r1.A00 = r0
            return
        L_0x0016:
            android.os.Looper r0 = android.os.Looper.myLooper()
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: X.HandlerC1082s2.<init>(com.google.android.gms.common.GoogleApiAvailability, android.content.Context):void");
    }

    public final void handleMessage(Message message) {
        PendingIntent activity;
        int i = message.what;
        if (i != 1) {
            StringBuilder sb = new StringBuilder(50);
            sb.append("Don't know how to handle this message: ");
            sb.append(i);
            Log.w("GoogleApiAvailability", sb.toString());
            return;
        }
        GoogleApiAvailability googleApiAvailability = this.A01;
        Context context = this.A00;
        int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable == 1 || isGooglePlayServicesAvailable == 2 || isGooglePlayServicesAvailable == 3 || isGooglePlayServicesAvailable == 9) {
            Intent A02 = googleApiAvailability.A02(context, isGooglePlayServicesAvailable, "n");
            if (A02 == null) {
                activity = null;
            } else {
                activity = PendingIntent.getActivity(context, 0, A02, 134217728);
            }
            GoogleApiAvailability.A00(googleApiAvailability, context, isGooglePlayServicesAvailable, activity);
        }
    }
}
