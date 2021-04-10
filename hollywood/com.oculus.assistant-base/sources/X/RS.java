package X;

import android.content.ServiceConnection;
import android.os.Handler;
import java.util.HashMap;
import java.util.Map;

public abstract class RS {
    public static RS A00;
    public static final Object A01 = new Object();

    public final void A00(RR rr, ServiceConnection serviceConnection, String str) {
        C1103sc scVar = (C1103sc) this;
        RZ.A02(serviceConnection, "ServiceConnection must not be null");
        HashMap hashMap = scVar.A03;
        synchronized (hashMap) {
            ServiceConnectionC1104sd sdVar = (ServiceConnectionC1104sd) hashMap.get(rr);
            if (sdVar != null) {
                Map map = sdVar.A05;
                if (map.containsKey(serviceConnection)) {
                    map.remove(serviceConnection);
                    if (map.isEmpty()) {
                        Handler handler = scVar.A01;
                        handler.sendMessageDelayed(handler.obtainMessage(0, rr), 5000);
                    }
                } else {
                    String valueOf = String.valueOf(rr);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 76);
                    sb.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
                }
            } else {
                String valueOf2 = String.valueOf(rr);
                StringBuilder sb2 = new StringBuilder(valueOf2.length() + 50);
                sb2.append("Nonexistent connection status for service config: ");
                sb2.append(valueOf2);
                throw new IllegalStateException(sb2.toString());
            }
        }
    }
}
