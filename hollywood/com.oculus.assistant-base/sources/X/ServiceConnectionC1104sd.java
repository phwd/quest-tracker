package X;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.sd  reason: case insensitive filesystem */
public final class ServiceConnectionC1104sd implements ServiceConnection, AbstractC0340Rx {
    public int A00 = 2;
    public ComponentName A01;
    public IBinder A02;
    public boolean A03;
    public final RR A04;
    public final Map A05 = new HashMap();
    public final /* synthetic */ C1103sc A06;

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0062, code lost:
        if (r7 != null) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00f7, code lost:
        if ((X.S8.A00(r4).A00.getPackageManager().getApplicationInfo(r1, 0).flags & 2097152) == 0) goto L_0x00f9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0102  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A00(java.lang.String r12) {
        /*
        // Method dump skipped, instructions count: 272
        */
        throw new UnsupportedOperationException("Method not decompiled: X.ServiceConnectionC1104sd.A00(java.lang.String):void");
    }

    public ServiceConnectionC1104sd(C1103sc scVar, RR rr) {
        this.A06 = scVar;
        this.A04 = rr;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C1103sc scVar = this.A06;
        synchronized (scVar.A03) {
            scVar.A01.removeMessages(1, this.A04);
            this.A02 = iBinder;
            this.A01 = componentName;
            for (ServiceConnection serviceConnection : this.A05.values()) {
                serviceConnection.onServiceConnected(componentName, iBinder);
            }
            this.A00 = 1;
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        C1103sc scVar = this.A06;
        synchronized (scVar.A03) {
            scVar.A01.removeMessages(1, this.A04);
            this.A02 = null;
            this.A01 = componentName;
            for (ServiceConnection serviceConnection : this.A05.values()) {
                serviceConnection.onServiceDisconnected(componentName);
            }
            this.A00 = 2;
        }
    }
}
