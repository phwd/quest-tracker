package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.acra.util.JavaProcFileReader;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.facebook.rti.mqtt.common.hardware.MqttNetworkChangeListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import org.json.JSONException;

@NotThreadSafe
/* renamed from: X.0mN  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractServiceC06020mN extends AbstractServiceC01930Yb {
    public long A00;
    public AnonymousClass0WB A01;
    public C06510nV A02;
    public RealtimeSinceBootClock A03;
    public C01680Wq A04;
    public AnonymousClass0Wu A05;
    public AnonymousClass0XJ A06;
    public AnonymousClass0XL A07;
    public AbstractC01900Xy A08;
    public AnonymousClass0YZ A09;
    public C01940Yd A0A;
    public AtomicBoolean A0B = new AtomicBoolean(false);
    public Integer A0C = AnonymousClass007.A0D;
    public final C06030mO A0D = new C06030mO(this);
    public volatile AnonymousClass0Wo A0E;

    @Override // X.AbstractServiceC01930Yb
    @Nullable
    public final Looper A0C() {
        return null;
    }

    public abstract C01940Yd A0H();

    public abstract Integer A0I();

    public String A0J() {
        return "N/A";
    }

    public void A0K() {
    }

    public void A0N() {
    }

    public void A0O() {
    }

    public void A0Q(int i) {
    }

    public void A0R(Intent intent, C01990Ym r2) {
    }

    public void A0S(@Nonnull AnonymousClass0Yy r1) {
    }

    public void A0T(C02150Zl r1) {
    }

    public void A0V(String str, byte[] bArr, int i, long j, C01610Wd r6, @Nullable Long l) {
    }

    public boolean A0X(Intent intent) {
        return true;
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static String A07(AbstractServiceC06020mN r13) {
        long j;
        AnonymousClass0ZF r1 = r13.A09.A0l;
        if (r1 == null || !r1.A05()) {
            j = 0;
        } else {
            j = SystemClock.elapsedRealtime() - r1.A0U;
        }
        AnonymousClass0Wu r12 = r13.A05;
        try {
            return AnonymousClass0Ws.A00(new AnonymousClass0Ws(AnonymousClass0Wu.A00(r12), AnonymousClass0Wu.A01(r12, j), (AnonymousClass0Ie) r12.A06(AnonymousClass0Ie.class), null, r12.A00.A00(false), (AnonymousClass0Ic) r12.A06(AnonymousClass0Ic.class), (AnonymousClass0IU) r12.A06(AnonymousClass0IU.class), (AnonymousClass0IV) r12.A06(AnonymousClass0IV.class), false, true), false).toString(2);
        } catch (JSONException unused) {
            return "";
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0Wk;)Ljava/util/concurrent/Future<*>; */
    private final void A08(EnumC01660Wk r7) {
        if (!this.A0B.getAndSet(false)) {
            AnonymousClass0NO.A09("MqttPushService", "service/stop/inactive_connection");
            return;
        }
        A0O();
        AnonymousClass0YZ r5 = this.A09;
        r5.A0F.A03();
        r5.A0G.A00();
        AnonymousClass0XL r3 = r5.A0E;
        boolean equals = r3.A02.getLooper().equals(Looper.myLooper());
        StringBuilder sb = new StringBuilder("ScreenStateListener unregistration should be called on MqttThread. Current Looper:");
        sb.append(Looper.myLooper());
        String obj = sb.toString();
        if (!equals) {
            Log.w(AnonymousClass0WR.class.getName(), obj);
            if (!AnonymousClass0WR.A00) {
                throw new AssertionError(obj);
            }
        }
        try {
            r3.A01.unregisterReceiver(r3.A00);
        } catch (IllegalArgumentException unused) {
        }
        r3.A04.set(null);
        AnonymousClass0XJ r2 = r5.A0D;
        C06130mY r1 = r5.A0c;
        synchronized (r2) {
            r2.A03.remove(r1);
        }
        BroadcastReceiver broadcastReceiver = r5.A03;
        if (broadcastReceiver != null) {
            try {
                r5.A04.unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException e) {
                AnonymousClass0NO.A0I("FbnsConnectionManager", e, "Failed to unregister broadcast receiver");
            }
            r5.A03 = null;
        }
        BroadcastReceiver broadcastReceiver2 = r5.A02;
        if (broadcastReceiver2 != null) {
            try {
                r5.A04.unregisterReceiver(broadcastReceiver2);
            } catch (IllegalArgumentException e2) {
                AnonymousClass0NO.A0I("FbnsConnectionManager", e2, "Failed to unregister broadcast receiver");
            }
            r5.A02 = null;
        }
        r5.A0L.A04();
        r5.A0K.A04();
        this.A09.A08(r7);
        A09(this);
    }

    public static final void A09(AbstractServiceC06020mN r5) {
        Integer num;
        Integer num2;
        AnonymousClass0ZF r0 = r5.A09.A0l;
        if (r0 == null) {
            num = AnonymousClass007.A0D;
        } else {
            num = r0.A0X;
        }
        if (num != null && num != (num2 = r5.A0C)) {
            String A002 = C02070Yz.A00(num2);
            String A003 = C02070Yz.A00(num);
            r5.A01.log(AnonymousClass006.A08("[state_machine] ", A002, JavaProcFileReader.LS_SYMLINK_ARROW, A003));
            r5.A0C = num;
            r5.A04.A01(A003);
        }
    }

    private void A0A(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", str);
        hashMap.put("pid", String.valueOf(Process.myPid()));
        this.A01.A5K("life_cycle", hashMap);
    }

    @Override // X.AbstractServiceC01930Yb
    public final void A0D() {
        boolean z = false;
        if (this.A0A == null) {
            z = true;
        }
        AnonymousClass0W9.A01(z);
        this.A0A = A0H();
        A0M();
        A0L();
        this.A01.A8o(new C06040mP(this));
        A0A("doCreate");
        AnonymousClass0Wo r2 = this.A0E;
        String A052 = AnonymousClass006.A05(C02030Yr.A00(A0I()), ".SERVICE_CREATE");
        String A0J = A0J();
        C06530na r6 = C06530na.A00;
        r2.A06(A052, A0J, null, r6, r6, this.A0B.get(), this.A06.A05.get(), this.A06.A02());
    }

    @Override // X.AbstractServiceC01930Yb
    public void A0E() {
        AnonymousClass0Wo r2 = this.A0E;
        String A052 = AnonymousClass006.A05(C02030Yr.A00(A0I()), ".SERVICE_DESTROY");
        String A0J = A0J();
        C06530na r6 = C06530na.A00;
        r2.A06(A052, A0J, null, r6, r6, this.A0B.get(), this.A06.A05.get(), this.A06.A02());
        A0A("doDestroy");
        this.A01.A8o(null);
        A0P();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0191, code lost:
        if (r1 != null) goto L_0x000a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x012e  */
    @Override // X.AbstractServiceC01930Yb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0F(android.content.Intent r15, int r16, int r17) {
        /*
        // Method dump skipped, instructions count: 480
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractServiceC06020mN.A0F(android.content.Intent, int, int):void");
    }

    @Override // X.AbstractServiceC01930Yb
    public void A0G(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String valueOf;
        Throwable th;
        HashSet hashSet;
        String str;
        String str2;
        String str3;
        SocketAddress remoteSocketAddress;
        try {
            printWriter.println("[ MqttPushService ]");
            printWriter.println(AnonymousClass006.A05("persistence=", A0J()));
            long j = this.A09.A01;
            if (j > 0) {
                valueOf = new Date(j).toString();
            } else {
                valueOf = String.valueOf(j);
            }
            printWriter.println(AnonymousClass006.A05("networkChangedTime=", valueOf));
            StringBuilder sb = new StringBuilder();
            sb.append("subscribedTopics=");
            AnonymousClass0YZ r0 = this.A09;
            synchronized (r0.A0S) {
                try {
                    hashSet = new HashSet(r0.A0S.keySet());
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
            sb.append(hashSet);
            printWriter.println(sb.toString());
            if (!(!this.A0A.A06.A02)) {
                AnonymousClass0YZ r5 = this.A09;
                printWriter.println("[ FbnsConnectionManager ]");
                StringBuilder sb2 = new StringBuilder("keepAliveIntervalSeconds=");
                sb2.append(r5.A0V);
                printWriter.println(sb2.toString());
                NetworkInfo A012 = r5.A0D.A01();
                if (A012 != null) {
                    str = A012.toString();
                } else {
                    str = "null";
                }
                printWriter.println(AnonymousClass006.A05("networkInfo=", str));
                if (r5.A0o != null) {
                    printWriter.println(AnonymousClass006.A05("lastConnectLostTime=", new Date((System.currentTimeMillis() + r5.A0j) - SystemClock.elapsedRealtime()).toString()));
                    printWriter.println(AnonymousClass006.A05("lastConnectLostReason=", r5.A0o));
                }
                AnonymousClass0ZF r52 = r5.A0l;
                if (r52 != null) {
                    synchronized (r52) {
                        try {
                            printWriter.println("[ MqttClient ]");
                            Integer num = r52.A0X;
                            if (num != null) {
                                str2 = C02070Yz.A00(num);
                            } else {
                                str2 = "null";
                            }
                            printWriter.println(AnonymousClass006.A05("state=", str2));
                            printWriter.println(AnonymousClass006.A05("lastMessageSent=", AnonymousClass0ZF.A01(r52, r52.A0S)));
                            printWriter.println(AnonymousClass006.A05("lastMessageReceived=", AnonymousClass0ZF.A01(r52, r52.A0R)));
                            printWriter.println(AnonymousClass006.A05("connectionEstablished=", AnonymousClass0ZF.A01(r52, r52.A0P)));
                            printWriter.println(AnonymousClass006.A05("lastPing=", AnonymousClass0ZF.A01(r52, r52.A0T)));
                            C05890m2 r3 = r52.A0B;
                            synchronized (r3) {
                                Socket socket = r3.A05;
                                if (socket == null || (remoteSocketAddress = socket.getRemoteSocketAddress()) == null) {
                                    str3 = "N/A";
                                } else {
                                    String str4 = r3.A02;
                                    if (str4 != null) {
                                        str3 = AnonymousClass006.A07(str4, "|", remoteSocketAddress.toString());
                                    } else {
                                        str3 = remoteSocketAddress.toString();
                                    }
                                }
                            }
                            printWriter.println(AnonymousClass006.A05("peer=", str3));
                        } catch (Throwable th3) {
                            th = th3;
                            throw th;
                        }
                    }
                } else {
                    printWriter.println("mMqttClient=null");
                }
                printWriter.println("[ MqttHealthStats ]");
                printWriter.println(A07(this));
            }
        } catch (Exception unused) {
        }
    }

    public void A0L() {
        AnonymousClass0Wu r3 = this.A05;
        EnumC01700Wt r0 = EnumC01700Wt.ServiceCreatedTimestamp;
        AnonymousClass0Wu.A04(r3, r0).set(SystemClock.elapsedRealtime());
    }

    public void A0M() {
        C01940Yd r0 = this.A0A;
        AnonymousClass0YZ r9 = r0.A0O;
        AnonymousClass0XJ r8 = r0.A0I;
        C06190mf r7 = r0.A0K;
        RealtimeSinceBootClock realtimeSinceBootClock = r0.A04;
        AnonymousClass0Wo r5 = r0.A0B;
        AnonymousClass0Wu r4 = r0.A0D;
        AnonymousClass0XL r3 = r0.A0J;
        C01680Wq r2 = r0.A0C;
        AnonymousClass0WB r1 = r0.A02;
        C06510nV r02 = r0.A03;
        this.A09 = r9;
        this.A06 = r8;
        this.A08 = r7;
        this.A03 = realtimeSinceBootClock;
        this.A0E = r5;
        this.A05 = r4;
        this.A07 = r3;
        this.A04 = r2;
        this.A01 = r1;
        this.A02 = r02;
    }

    public final void A0P() {
        if (this.A0B.get()) {
            A08(EnumC01660Wk.SERVICE_DESTROY);
        }
        AnonymousClass0YZ r1 = this.A09;
        if (r1 != null) {
            r1.A08(EnumC01660Wk.SERVICE_DESTROY);
        }
        C01940Yd r4 = this.A0A;
        if (r4 != null && !r4.A0W) {
            r4.A0W = true;
            AnonymousClass0YH r3 = r4.A0M;
            if (r3 != null) {
                synchronized (r3) {
                    r3.A00();
                    if (r3.A01) {
                        boolean z = false;
                        if (!r3.A08.A06(r3.A06, r3.A05)) {
                            z = true;
                        }
                        r3.A01 = z;
                    }
                }
            }
            AnonymousClass0XJ r32 = r4.A0I;
            if (r32 != null) {
                synchronized (r32) {
                    try {
                        r32.A01.unregisterReceiver(r32.A00);
                    } catch (IllegalArgumentException e) {
                        AnonymousClass0NO.A0I("MqttNetworkManager", e, "Failed to unregister broadcast receiver");
                    }
                }
            }
            AnonymousClass08r r0 = r4.A0G;
            if (r0 != null) {
                r0.shutdown();
            }
            AnonymousClass0YF r33 = r4.A0L;
            if (r33 != null) {
                synchronized (r33) {
                    r33.A03();
                    if (r33.A0N != null) {
                        AnonymousClass0Wc r2 = r33.A0F;
                        Context context = r33.A0C;
                        r2.A06(context, r33.A0A);
                        r2.A06(context, r33.A0B);
                        r2.A06(context, r33.A09);
                    }
                }
                return;
            }
            return;
        }
        return;
    }

    public final void A0U(Integer num, C01990Ym r8) {
        String str;
        Integer num2;
        if (!this.A0B.getAndSet(true)) {
            if (!(r8 == null || (num2 = r8.A01) == null)) {
                A0Q(num2.intValue());
            }
            AnonymousClass0Wu r2 = this.A05;
            switch (num.intValue()) {
                case 1:
                    str = "SERVICE_RESTART";
                    break;
                case 2:
                    str = "PERSISTENT_KICK";
                    break;
                case 3:
                    str = "CONNECTIVITY_CHANGED";
                    break;
                case 4:
                    str = "CONFIG_CHANGED";
                    break;
                case 5:
                    str = "EXPIRE_CONNECTION";
                    break;
                case 6:
                    str = "CONNECT_NOW";
                    break;
                case 7:
                    str = "CONNECTION_LOST";
                    break;
                case 8:
                    str = "KEEPALIVE";
                    break;
                case 9:
                    str = "APP_FOREGROUND";
                    break;
                case 10:
                    str = "FBNS_REGISTER";
                    break;
                case 11:
                    str = "FBNS_REGISTER_RETRY";
                    break;
                case 12:
                    str = "FBNS_UNREGISTER";
                    break;
                case 13:
                    str = "CREDENTIALS_UPDATED";
                    break;
                case 14:
                    str = "CLIENT_KICK";
                    break;
                case 15:
                    str = "AUTH_CREDENTIALS_CHANGE";
                    break;
                case 16:
                    str = "FORCE_KICK";
                    break;
                default:
                    str = "SERVICE_START";
                    break;
            }
            C01710Ww r3 = r2.A00;
            if (r3.A07 == null) {
                r3.A07 = str;
                r3.A04.set(SystemClock.elapsedRealtime());
                r3.A02.set(SystemClock.elapsedRealtime());
            }
            A0N();
            AnonymousClass0YZ r5 = this.A09;
            AnonymousClass0YP r32 = new AnonymousClass0YP(r5);
            r5.A03 = r32;
            r5.A04.registerReceiver(r32, new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED"), null, r5.A05);
            AnonymousClass0YQ r33 = new AnonymousClass0YQ(r5);
            r5.A02 = r33;
            r5.A04.registerReceiver(r33, new IntentFilter("com.facebook.rti.mqtt.ACTION_MQTT_CONFIG_CHANGED"), null, r5.A05);
            AnonymousClass0XJ r22 = r5.A0D;
            MqttNetworkChangeListener mqttNetworkChangeListener = r5.A0c;
            synchronized (r22) {
                r22.A03.add(mqttNetworkChangeListener);
            }
            r5.A0L.A03();
        }
        this.A09.A0B(num);
    }

    public final boolean A0W() {
        if (!this.A0B.get()) {
            this.A01.log("MqttPushService/not_started");
            return false;
        }
        HashMap hashMap = new HashMap();
        if (this.A08.A8x(hashMap)) {
            return true;
        }
        this.A01.A5K("MqttPushService/should_not_connect", hashMap);
        return false;
    }

    @Override // X.AbstractServiceC01930Yb
    public final void onDestroy() {
        if (this.A0E != null) {
            AnonymousClass0Wo r2 = this.A0E;
            String A052 = AnonymousClass006.A05(C02030Yr.A00(A0I()), ".SERVICE_ON_DESTROY");
            String A0J = A0J();
            C06530na r6 = C06530na.A00;
            r2.A06(A052, A0J, null, r6, r6, this.A0B.get(), 0, null);
        }
        super.onDestroy();
    }
}
