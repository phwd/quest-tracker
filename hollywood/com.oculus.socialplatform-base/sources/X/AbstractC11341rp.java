package X;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting
@RestrictTo({AnonymousClass02C.LIBRARY})
/* renamed from: X.1rp  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC11341rp {
    public BroadcastReceiver A00;
    public final /* synthetic */ AnonymousClass1rJ A01;

    public AbstractC11341rp(AnonymousClass1rJ r1) {
        this.A01 = r1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A00() {
        /*
        // Method dump skipped, instructions count: 243
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC11341rp.A00():int");
    }

    public final void A02() {
        BroadcastReceiver broadcastReceiver = this.A00;
        if (broadcastReceiver != null) {
            try {
                this.A01.A0j.unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException unused) {
            }
            this.A00 = null;
        }
    }

    public final void A01() {
        IntentFilter intentFilter;
        String str;
        A02();
        if (!(this instanceof C11311rm)) {
            intentFilter = new IntentFilter();
            str = "android.os.action.POWER_SAVE_MODE_CHANGED";
        } else {
            intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            str = "android.intent.action.TIME_TICK";
        }
        intentFilter.addAction(str);
        if (intentFilter.countActions() != 0) {
            BroadcastReceiver broadcastReceiver = this.A00;
            if (broadcastReceiver == null) {
                broadcastReceiver = new C11451s0(this);
                this.A00 = broadcastReceiver;
            }
            this.A01.A0j.registerReceiver(broadcastReceiver, intentFilter);
        }
    }
}
