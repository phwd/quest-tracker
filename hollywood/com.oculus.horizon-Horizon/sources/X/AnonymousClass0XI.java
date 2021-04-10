package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: X.0XI  reason: invalid class name */
public class AnonymousClass0XI extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass0XJ A00;

    public AnonymousClass0XI(AnonymousClass0XJ r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        int i;
        ArrayList arrayList;
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            AnonymousClass0XJ r2 = this.A00;
            AnonymousClass0XJ.A00(r2, (NetworkInfo) intent.getParcelableExtra("networkInfo"));
            if (!isInitialStickyBroadcast()) {
                NetworkInfo A01 = r2.A01();
                if (A01 == null || !A01.isConnected()) {
                    i = -1;
                } else {
                    i = A01.getType();
                }
                r2.A03();
                Intent intent2 = new Intent("com.facebook.orca.ACTION_NETWORK_CONNECTIVITY_CHANGED");
                intent2.putExtra("com.facebook.mqtt.EXTRA_NETWORK_TYPE", i);
                synchronized (r2) {
                    arrayList = new ArrayList(r2.A03);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    C06130mY r1 = (C06130mY) it.next();
                    if (AnonymousClass0W7.A00(intent2.getAction(), "com.facebook.orca.ACTION_NETWORK_CONNECTIVITY_CHANGED")) {
                        AnonymousClass0YZ.A01(r1.A00, intent2);
                    }
                }
            }
        }
    }
}
