package X;

import android.content.Context;
import android.content.ServiceConnection;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public final class S3 {
    public static final Object A01 = new Object();
    public static volatile S3 A02;
    public ConcurrentHashMap A00 = new ConcurrentHashMap();

    public final void A00(Context context, ServiceConnection serviceConnection) {
        if ((serviceConnection instanceof AbstractC0340Rx) || !this.A00.containsKey(serviceConnection)) {
            try {
                context.unbindService(serviceConnection);
            } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException unused) {
            }
        } else {
            try {
                try {
                    context.unbindService((ServiceConnection) this.A00.get(serviceConnection));
                } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException unused2) {
                }
            } finally {
                this.A00.remove(serviceConnection);
            }
        }
    }
}
