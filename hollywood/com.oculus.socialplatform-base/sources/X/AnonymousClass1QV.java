package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: X.1QV  reason: invalid class name */
public class AnonymousClass1QV extends BroadcastReceiver {
    public final /* synthetic */ AnonymousClass1QS A00;

    public AnonymousClass1QV(AnonymousClass1QS r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        int i;
        ArrayList arrayList;
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            AnonymousClass1QS r2 = this.A00;
            AnonymousClass1QS.A00(r2, (NetworkInfo) intent.getParcelableExtra("networkInfo"));
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
                    arrayList = new ArrayList(r2.A04);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    AnonymousClass1QX r1 = (AnonymousClass1QX) it.next();
                    if (AnonymousClass1QW.A00(intent2.getAction(), "com.facebook.orca.ACTION_NETWORK_CONNECTIVITY_CHANGED")) {
                        AnonymousClass22H.A02(r1.A00, intent2);
                    }
                }
            }
        }
    }
}
