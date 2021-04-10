package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.PowerManager;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1XQ  reason: invalid class name */
public final class AnonymousClass1XQ {
    public static final IntentFilter A06;
    public final BroadcastReceiver A00;
    public final Context A01;
    public final Handler A02;
    public final AtomicLong A03 = new AtomicLong(-1);
    public final AtomicReference<Boolean> A04 = new AtomicReference<>(null);
    public final AnonymousClass1QQ A05;

    static {
        IntentFilter intentFilter = new IntentFilter();
        A06 = intentFilter;
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        IntentFilter intentFilter2 = A06;
        intentFilter2.addAction("android.intent.action.SCREEN_OFF");
        intentFilter2.setPriority(999);
    }

    public final boolean A00() {
        Boolean bool = this.A04.get();
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            AnonymousClass1QO A002 = this.A05.A00("power", PowerManager.class);
            if (A002.A02()) {
                return ((PowerManager) A002.A01()).isInteractive();
            }
            return false;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public AnonymousClass1XQ(Context context, AnonymousClass1QQ r5, Handler handler, RealtimeSinceBootClock realtimeSinceBootClock) {
        this.A01 = context;
        this.A05 = r5;
        this.A02 = handler;
        this.A00 = new AnonymousClass1XP(this, realtimeSinceBootClock);
    }
}
