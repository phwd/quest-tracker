package X;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;

@VisibleForTesting
@RestrictTo({AnonymousClass02D.LIBRARY})
/* renamed from: X.02q  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC000302q {
    public BroadcastReceiver A00;
    public final /* synthetic */ LayoutInflater$Factory2C04430et A01;

    public abstract int A03();

    @Nullable
    public abstract IntentFilter A04();

    public abstract void A05();

    public AbstractC000302q(LayoutInflater$Factory2C04430et r1) {
        this.A01 = r1;
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
        A02();
        IntentFilter A04 = A04();
        if (A04 != null && A04.countActions() != 0) {
            BroadcastReceiver broadcastReceiver = this.A00;
            if (broadcastReceiver == null) {
                broadcastReceiver = new AnonymousClass02p(this);
                this.A00 = broadcastReceiver;
            }
            this.A01.A0j.registerReceiver(broadcastReceiver, A04);
        }
    }
}
