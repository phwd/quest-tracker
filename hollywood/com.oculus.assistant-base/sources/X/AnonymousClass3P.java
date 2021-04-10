package X;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.facebook.proxygen.HTTPTransportCallback;

/* renamed from: X.3P  reason: invalid class name */
public final class AnonymousClass3P {
    public boolean A00;
    public final BroadcastReceiver A01;
    public final IntentFilter A02;

    public final String toString() {
        StringBuilder sb = new StringBuilder((int) HTTPTransportCallback.BODY_BYTES_RECEIVED);
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

    public AnonymousClass3P(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
        this.A02 = intentFilter;
        this.A01 = broadcastReceiver;
    }
}
