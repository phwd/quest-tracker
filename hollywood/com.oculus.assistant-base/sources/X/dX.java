package X;

import android.content.ComponentName;
import android.content.Context;
import android.os.PowerManager;

public final class dX extends AnonymousClass17 {
    public boolean A00;
    public boolean A01;
    public final Context A02;
    public final PowerManager.WakeLock A03;
    public final PowerManager.WakeLock A04;

    public dX(Context context, ComponentName componentName) {
        super(componentName);
        this.A02 = context.getApplicationContext();
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, AnonymousClass08.A04(componentName.getClassName(), ":launch"));
        this.A03 = newWakeLock;
        newWakeLock.setReferenceCounted(false);
        PowerManager.WakeLock newWakeLock2 = powerManager.newWakeLock(1, AnonymousClass08.A04(componentName.getClassName(), ":run"));
        this.A04 = newWakeLock2;
        newWakeLock2.setReferenceCounted(false);
    }
}
