package X;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.HashMap;

public final class Rv implements Handler.Callback {
    public final /* synthetic */ C1103sc A00;

    public Rv(C1103sc scVar) {
        this.A00 = scVar;
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            HashMap hashMap = this.A00.A03;
            synchronized (hashMap) {
                RR rr = (RR) message.obj;
                ServiceConnectionC1104sd sdVar = (ServiceConnectionC1104sd) hashMap.get(rr);
                if (sdVar != null && sdVar.A05.isEmpty()) {
                    if (sdVar.A03) {
                        C1103sc scVar = sdVar.A06;
                        scVar.A01.removeMessages(1, sdVar.A04);
                        scVar.A02.A00(scVar.A00, sdVar);
                        sdVar.A03 = false;
                        sdVar.A00 = 2;
                    }
                    hashMap.remove(rr);
                }
            }
            return true;
        } else if (i != 1) {
            return false;
        } else {
            HashMap hashMap2 = this.A00.A03;
            synchronized (hashMap2) {
                RR rr2 = (RR) message.obj;
                ServiceConnectionC1104sd sdVar2 = (ServiceConnectionC1104sd) hashMap2.get(rr2);
                if (sdVar2 != null && sdVar2.A00 == 3) {
                    String valueOf = String.valueOf(rr2);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 47);
                    sb.append("Timeout waiting for ServiceConnection callback ");
                    sb.append(valueOf);
                    Log.e("GmsClientSupervisor", sb.toString(), new Exception());
                    ComponentName componentName = sdVar2.A01;
                    if (componentName == null) {
                        String str = rr2.A02;
                        RZ.A01(str);
                        componentName = new ComponentName(str, "unknown");
                    }
                    sdVar2.onServiceDisconnected(componentName);
                }
            }
            return true;
        }
    }
}
