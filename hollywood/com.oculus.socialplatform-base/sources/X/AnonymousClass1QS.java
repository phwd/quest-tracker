package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.SystemClock;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.facebook.rti.mqtt.common.hardware.MqttNetworkChangeListener;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

/* renamed from: X.1QS  reason: invalid class name */
public final class AnonymousClass1QS {
    public final BroadcastReceiver A00;
    public final Context A01;
    public final Handler A02;
    public final AnonymousClass1QQ A03;
    public final Set<MqttNetworkChangeListener> A04 = new HashSet();
    public final AtomicLong A05 = new AtomicLong(0);
    public final AtomicLong A06 = new AtomicLong(0);
    public final AtomicLong A07 = new AtomicLong(-1);
    public final AnonymousClass1QO<ConnectivityManager> A08;
    public final RealtimeSinceBootClock A09;
    public final AtomicLong A0A = new AtomicLong(-1);

    public static synchronized void A00(AnonymousClass1QS r8, NetworkInfo networkInfo) {
        AtomicLong atomicLong;
        synchronized (r8) {
            long j = -1;
            if (networkInfo != null) {
                if (networkInfo.isConnected()) {
                    AtomicLong atomicLong2 = r8.A06;
                    if (atomicLong2.compareAndSet(0, SystemClock.elapsedRealtime())) {
                        AtomicLong atomicLong3 = r8.A0A;
                        if (atomicLong3.get() != -1) {
                            atomicLong = r8.A07;
                            j = atomicLong2.get() - atomicLong3.get();
                            atomicLong.set(j);
                        }
                    }
                }
            }
            AtomicLong atomicLong4 = r8.A0A;
            atomicLong4.set(SystemClock.elapsedRealtime());
            long andSet = r8.A06.getAndSet(0);
            if (andSet != 0) {
                r8.A05.addAndGet(atomicLong4.get() - andSet);
            }
            atomicLong = r8.A07;
            atomicLong.set(j);
        }
    }

    @Nullable
    public final NetworkInfo A01() {
        try {
            AnonymousClass1QO<ConnectivityManager> r1 = this.A08;
            if (r1.A02()) {
                return r1.A01().getActiveNetworkInfo();
            }
            return null;
        } catch (RuntimeException e) {
            AnonymousClass0MD.A07("MqttNetworkManager", "getActiveNetworkInfoSafe caught Exception", e);
            return null;
        }
    }

    public AnonymousClass1QS(AnonymousClass1QQ r6, Context context, RealtimeSinceBootClock realtimeSinceBootClock, Handler handler) {
        this.A03 = r6;
        this.A08 = r6.A00("connectivity", ConnectivityManager.class);
        this.A01 = context;
        this.A09 = realtimeSinceBootClock;
        this.A02 = handler;
        this.A00 = new AnonymousClass1QV(this);
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

    public final AnonymousClass1QT A03() {
        NetworkInfo A012 = A01();
        if (A012 == null || !A012.isConnected()) {
            return AnonymousClass1QT.NoNetwork;
        }
        int type = A012.getType();
        int subtype = A012.getSubtype();
        if (type != 0) {
            if (type == 1) {
                return AnonymousClass1QT.WIFI;
            }
            if (!(type == 2 || type == 3 || type == 4 || type == 5)) {
                return AnonymousClass1QT.Other;
            }
        }
        switch (subtype) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return AnonymousClass1QT.MOBILE_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return AnonymousClass1QT.MOBILE_3G;
            case 13:
                return AnonymousClass1QT.MOBILE_4G;
            default:
                return AnonymousClass1QT.MOBILE_OTHER;
        }
    }
}
