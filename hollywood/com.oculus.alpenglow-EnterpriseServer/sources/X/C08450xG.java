package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import java.util.ArrayList;

/* renamed from: X.0xG  reason: invalid class name and case insensitive filesystem */
public class C08450xG extends BroadcastReceiver {
    public final /* synthetic */ C08400xB A00;

    public C08450xG(C08400xB r1) {
        this.A00 = r1;
    }

    public final void onReceive(Context context, Intent intent) {
        int i;
        ArrayList<AnonymousClass106> arrayList;
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            C08400xB r2 = this.A00;
            C08400xB.A00(r2, (NetworkInfo) intent.getParcelableExtra("networkInfo"));
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
                for (AnonymousClass106 r1 : arrayList) {
                    if (C07950wI.A00(intent2.getAction(), "com.facebook.orca.ACTION_NETWORK_CONNECTIVITY_CHANGED")) {
                        C08290wy.A01(r1.A00, intent2);
                    }
                }
            }
        }
    }
}
