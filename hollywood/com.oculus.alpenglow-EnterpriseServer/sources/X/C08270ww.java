package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.PowerManager;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.0ww  reason: invalid class name and case insensitive filesystem */
public final class C08270ww {
    public static final IntentFilter A06;
    public final BroadcastReceiver A00;
    public final Context A01;
    public final Handler A02;
    public final AtomicLong A03 = new AtomicLong(-1);
    public final AtomicReference<Boolean> A04 = new AtomicReference<>(null);
    public final C08800xq A05;

    static {
        IntentFilter intentFilter = new IntentFilter();
        A06 = intentFilter;
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        IntentFilter intentFilter2 = A06;
        intentFilter2.addAction("android.intent.action.SCREEN_OFF");
        intentFilter2.setPriority(AnonymousClass0Fr.MAX_BIND_PARAMETER_CNT);
    }

    public final boolean A00() {
        Boolean bool = this.A04.get();
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            AbstractC09150yk A002 = this.A05.A00("power", PowerManager.class);
            if (A002.A02()) {
                return ((PowerManager) A002.A01()).isInteractive();
            }
            return false;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public C08270ww(Context context, C08800xq r5, Handler handler, RealtimeSinceBootClock realtimeSinceBootClock) {
        this.A01 = context;
        this.A05 = r5;
        this.A02 = handler;
        this.A00 = new C08260wv(this, realtimeSinceBootClock);
    }
}
