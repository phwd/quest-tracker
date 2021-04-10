package X;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.facebook.proxygen.HTTPTransportCallback;

public final class Bc {
    public final long A00;
    public final IntentFilter A01;
    public final BroadcastReceiver A02;

    public final String toString() {
        StringBuilder sb = new StringBuilder((int) HTTPTransportCallback.BODY_BYTES_RECEIVED);
        sb.append("Receiver{");
        sb.append(this.A02);
        sb.append(" filter=");
        sb.append(this.A01);
        sb.append(" looperId=");
        sb.append(this.A00);
        sb.append("}");
        return sb.toString();
    }
}
