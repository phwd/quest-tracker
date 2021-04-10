package X;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.facebook.FacebookSdk;

/* renamed from: X.0Az  reason: invalid class name */
public final class AnonymousClass0Az {
    public boolean A00;
    public boolean A01;
    public final IntentFilter A02;
    public final BroadcastReceiver A03;

    public final String toString() {
        StringBuilder sb = new StringBuilder((int) FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE);
        sb.append("Receiver{");
        sb.append(this.A03);
        sb.append(" filter=");
        sb.append(this.A02);
        if (this.A01) {
            sb.append(" DEAD");
        }
        sb.append("}");
        return sb.toString();
    }

    public AnonymousClass0Az(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
        this.A02 = intentFilter;
        this.A03 = broadcastReceiver;
    }
}
