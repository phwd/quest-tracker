package X;

import android.app.AlarmManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Messenger;
import android.os.PowerManager;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public final class HM {
    public static HM A02;
    public static final long A03 = TimeUnit.SECONDS.toMillis(90);
    public AlarmManager A00;
    public PowerManager A01;

    public static HM A00() {
        HM hm;
        synchronized (HM.class) {
            hm = A02;
            if (hm == null) {
                hm = new HM();
                A02 = hm;
            }
        }
        return hm;
    }

    public final void A01(Context context, String str, H8 h8, @Nullable Bundle bundle, int i, @Nullable HQ hq) {
        PowerManager powerManager;
        if (hq == null || (hq.A01 >= 0 && hq.A00 >= 0)) {
            ComponentName A022 = HL.A00(context).A02();
            synchronized (HM.class) {
                powerManager = this.A01;
                if (powerManager == null) {
                    powerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
                    this.A01 = powerManager;
                }
            }
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, AnonymousClass06.A06("JobSchedulerHack-", A022.getShortClassName(), "-client-", String.valueOf(i)));
            newWakeLock.setReferenceCounted(false);
            HS hs = new HS(new YV(newWakeLock));
            Messenger messenger = new Messenger(hs);
            HT.A01().add(hs);
            Intent putExtras = new Intent().setComponent(A022).setAction(str).putExtras(new HT(messenger, bundle, str, h8, i, hq, context).A02());
            newWakeLock.acquire(A03);
            context.startService(putExtras);
            return;
        }
        throw new IllegalStateException("fallback delay ms must be >= 0");
    }
}
