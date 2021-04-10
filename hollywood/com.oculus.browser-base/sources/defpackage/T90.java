package defpackage;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/* renamed from: T90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class T90 {

    /* renamed from: a  reason: collision with root package name */
    public final IntentFilter f8942a;
    public final BroadcastReceiver b;
    public boolean c;

    public T90(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
        this.f8942a = intentFilter;
        this.b = broadcastReceiver;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("Receiver{");
        sb.append(this.b);
        sb.append(" filter=");
        sb.append(this.f8942a);
        sb.append("}");
        return sb.toString();
    }
}
