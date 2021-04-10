package X;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/* renamed from: X.0B0  reason: invalid class name */
public final class AnonymousClass0B0 {
    public boolean A00;
    public final BroadcastReceiver A01;
    public final IntentFilter A02;

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Receiver{");
        sb.append(this.A01);
        sb.append(" filter=");
        sb.append(this.A02);
        if (this.A00) {
            sb.append(" DEAD");
        }
        sb.append("}");
        return sb.toString();
    }

    public AnonymousClass0B0(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
        this.A02 = intentFilter;
        this.A01 = broadcastReceiver;
    }
}
