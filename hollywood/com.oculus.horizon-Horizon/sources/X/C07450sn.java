package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator;

/* renamed from: X.0sn  reason: invalid class name and case insensitive filesystem */
public final class C07450sn extends AnonymousClass03V {
    public boolean A00;
    public boolean A01;
    public final Context A02;
    public final PowerManager.WakeLock A03;
    public final PowerManager.WakeLock A04;

    @Override // X.AnonymousClass03V
    public final void A00() {
        synchronized (this) {
            if (this.A01) {
                if (this.A00) {
                    this.A03.acquire(MobileConfigAppAwareAccessorDecorator.MS_IN_ONE_MINUTE);
                }
                this.A01 = false;
                this.A04.release();
            }
        }
    }

    @Override // X.AnonymousClass03V
    public final void A01() {
        synchronized (this) {
            if (!this.A01) {
                this.A01 = true;
                this.A04.acquire(600000);
                this.A03.release();
            }
        }
    }

    @Override // X.AnonymousClass03V
    public final void A02() {
        synchronized (this) {
            this.A00 = false;
        }
    }

    @Override // X.AnonymousClass03V
    public final void A04(Intent intent) {
        Intent intent2 = new Intent(intent);
        intent2.setComponent(super.A02);
        if (this.A02.startService(intent2) != null) {
            synchronized (this) {
                if (!this.A00) {
                    this.A00 = true;
                    if (!this.A01) {
                        this.A03.acquire(MobileConfigAppAwareAccessorDecorator.MS_IN_ONE_MINUTE);
                    }
                }
            }
        }
    }

    public C07450sn(Context context, ComponentName componentName) {
        super(componentName);
        this.A02 = context.getApplicationContext();
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, AnonymousClass006.A05(componentName.getClassName(), ":launch"));
        this.A03 = newWakeLock;
        newWakeLock.setReferenceCounted(false);
        PowerManager.WakeLock newWakeLock2 = powerManager.newWakeLock(1, AnonymousClass006.A05(componentName.getClassName(), ":run"));
        this.A04 = newWakeLock2;
        newWakeLock2.setReferenceCounted(false);
    }
}
