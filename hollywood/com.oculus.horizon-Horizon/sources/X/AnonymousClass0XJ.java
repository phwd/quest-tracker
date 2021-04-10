package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.facebook.rti.mqtt.common.hardware.MqttNetworkChangeListener;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

/* renamed from: X.0XJ  reason: invalid class name */
public final class AnonymousClass0XJ {
    public final BroadcastReceiver A00;
    public final Context A01;
    public final Handler A02;
    public final Set<MqttNetworkChangeListener> A03 = new HashSet();
    public final AtomicLong A04 = new AtomicLong(0);
    public final AtomicLong A05 = new AtomicLong(0);
    public final AtomicLong A06 = new AtomicLong(-1);
    public final AnonymousClass0W8<ConnectivityManager> A07;
    public final RealtimeSinceBootClock A08;
    public final C01630Wg A09;
    public final AtomicLong A0A = new AtomicLong(-1);

    public static synchronized void A00(AnonymousClass0XJ r9, NetworkInfo networkInfo) {
        AtomicLong atomicLong;
        synchronized (r9) {
            long j = -1;
            if (networkInfo == null || !networkInfo.isConnected()) {
                AtomicLong atomicLong2 = r9.A0A;
                atomicLong2.set(SystemClock.elapsedRealtime());
                long andSet = r9.A05.getAndSet(0);
                if (andSet != 0) {
                    r9.A04.addAndGet(atomicLong2.get() - andSet);
                }
                atomicLong = r9.A06;
            } else {
                AtomicLong atomicLong3 = r9.A05;
                if (atomicLong3.compareAndSet(0, SystemClock.elapsedRealtime())) {
                    AtomicLong atomicLong4 = r9.A0A;
                    if (atomicLong4.get() != -1) {
                        atomicLong = r9.A06;
                        j = atomicLong3.get() - atomicLong4.get();
                    }
                }
            }
            atomicLong.set(j);
        }
    }

    @Nullable
    public final NetworkInfo A01() {
        try {
            AnonymousClass0W8<ConnectivityManager> r1 = this.A07;
            if (r1.A02()) {
                return r1.A01().getActiveNetworkInfo();
            }
            return null;
        } catch (RuntimeException e) {
            AnonymousClass0NO.A0B("MqttNetworkManager", "getActiveNetworkInfoSafe caught Exception", e);
            return null;
        }
    }

    public final boolean A04() {
        try {
            AnonymousClass0W8 A002 = this.A09.A00("power", PowerManager.class);
            return Build.VERSION.SDK_INT >= 23 && A002.A02() && ((PowerManager) A002.A01()).isDeviceIdleMode();
        } catch (Exception unused) {
            AnonymousClass0NO.A08("MqttNetworkManager", "Exception in getting DeviceIdleMode");
            return false;
        }
    }

    public AnonymousClass0XJ(C01630Wg r6, Context context, RealtimeSinceBootClock realtimeSinceBootClock, Handler handler) {
        this.A09 = r6;
        this.A07 = r6.A00("connectivity", ConnectivityManager.class);
        this.A01 = context;
        this.A08 = realtimeSinceBootClock;
        this.A02 = handler;
        this.A00 = new AnonymousClass0XI(this);
        A00(this, A01());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.A01.registerReceiver(this.A00, intentFilter, null, this.A02);
    }

    @Nullable
    public final NetworkInfo A02() {
        NetworkInfo A012 = A01();
        if (A012 == null || !A012.isConnected()) {
            return null;
        }
        return A012;
    }

    public final AnonymousClass0WW A03() {
        NetworkInfo A012 = A01();
        if (A012 == null || !A012.isConnected()) {
            return AnonymousClass0WW.NoNetwork;
        }
        int type = A012.getType();
        int subtype = A012.getSubtype();
        if (type != 0) {
            if (type == 1) {
                return AnonymousClass0WW.WIFI;
            }
            if (!(type == 2 || type == 3 || type == 4 || type == 5)) {
                return AnonymousClass0WW.Other;
            }
        }
        switch (subtype) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return AnonymousClass0WW.MOBILE_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return AnonymousClass0WW.MOBILE_3G;
            case 13:
                return AnonymousClass0WW.MOBILE_4G;
            default:
                return AnonymousClass0WW.MOBILE_OTHER;
        }
    }
}
