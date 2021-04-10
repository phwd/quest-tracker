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
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.facebook.rti.mqtt.common.hardware.MqttNetworkChangeListener;
import com.facebook.rti.push.service.FbnsService;
import com.oculus.alpenglow.logging.LoggerConstants;
import com.squareup.okhttp.internal.framed.Hpack;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import org.json.JSONException;

@NotThreadSafe
/* renamed from: X.0wx  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractServiceC08280wx extends AnonymousClass11R {
    public long A00;
    public AbstractC09080yd A01;
    public C07710vp A02;
    public RealtimeSinceBootClock A03;
    public C08380x8 A04;
    public C08310x0 A05;
    public C08400xB A06;
    public C08270ww A07;
    public AbstractC08160wg A08;
    public C08290wy A09;
    public C07490ue A0A;
    public AtomicBoolean A0B = new AtomicBoolean(false);
    public Integer A0C = AnonymousClass007.A0D;
    public final AnonymousClass105 A0D = new AnonymousClass105(this);
    public volatile AnonymousClass0x2 A0E;

    public abstract C07490ue A0F();

    public void A0I() {
    }

    public void A0J() {
    }

    public void A0L(Intent intent, C09450zT r2) {
    }

    public void A0M(AnonymousClass0yD r1) {
    }

    public void A0O(String str, byte[] bArr, int i, long j, C09440zS r6, @Nullable Long l) {
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static String A07(AbstractServiceC08280wx r13) {
        long j;
        C08300wz r1 = r13.A09.A0l;
        if (r1 == null || !r1.A05()) {
            j = 0;
        } else {
            j = SystemClock.elapsedRealtime() - r1.A0U;
        }
        C08310x0 r12 = r13.A05;
        try {
            return C08550xR.A00(new C08550xR(C08310x0.A00(r12), C08310x0.A01(r12, j), (C09660zz) r12.A06(C09660zz.class), null, r12.A00.A00(false), (C09510za) r12.A06(C09510za.class), (C09490zY) r12.A06(C09490zY.class), (C09500zZ) r12.A06(C09500zZ.class), false, true), false).toString(2);
        } catch (JSONException unused) {
            return "";
        }
    }

    public static final void A08(AbstractServiceC08280wx r5) {
        Integer num;
        Integer num2;
        C08300wz r0 = r5.A09.A0l;
        if (r0 == null) {
            num = AnonymousClass007.A0D;
        } else {
            num = r0.A0X;
        }
        if (num != null && num != (num2 = r5.A0C)) {
            String A002 = C09380zK.A00(num2);
            String A003 = C09380zK.A00(num);
            r5.A01.log(AnonymousClass006.A08("[state_machine] ", A002, " -> ", A003));
            r5.A0C = num;
            r5.A04.A01(A003);
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0xi;)Ljava/util/concurrent/Future<*>; */
    public static final void A09(AbstractServiceC08280wx r6, EnumC08720xi r7) {
        if (!r6.A0B.getAndSet(false)) {
            AnonymousClass0NK.A02("MqttPushService", "service/stop/inactive_connection");
            return;
        }
        r6.A0J();
        C08290wy r5 = r6.A09;
        r5.A0F.A03();
        r5.A0G.A00();
        C08270ww r3 = r5.A0E;
        String str = "ScreenStateListener unregistration should be called on MqttThread. Current Looper:" + Looper.myLooper();
        if (!r3.A02.getLooper().equals(Looper.myLooper())) {
            Log.w(C07650vi.class.getName(), str);
            if (!C07650vi.A00) {
                throw new AssertionError(str);
            }
        }
        try {
            r3.A01.unregisterReceiver(r3.A00);
        } catch (IllegalArgumentException unused) {
        }
        r3.A04.set(null);
        C08400xB r2 = r5.A0D;
        AnonymousClass106 r1 = r5.A0c;
        synchronized (r2) {
            r2.A04.remove(r1);
        }
        BroadcastReceiver broadcastReceiver = r5.A03;
        if (broadcastReceiver != null) {
            try {
                r5.A04.unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException e) {
                AnonymousClass0NK.A0A("FbnsConnectionManager", e, "Failed to unregister broadcast receiver");
            }
            r5.A03 = null;
        }
        BroadcastReceiver broadcastReceiver2 = r5.A02;
        if (broadcastReceiver2 != null) {
            try {
                r5.A04.unregisterReceiver(broadcastReceiver2);
            } catch (IllegalArgumentException e2) {
                AnonymousClass0NK.A0A("FbnsConnectionManager", e2, "Failed to unregister broadcast receiver");
            }
            r5.A02 = null;
        }
        r5.A0L.A00();
        r5.A0K.A00();
        r6.A09.A08(r7);
        A08(r6);
    }

    public static void A0A(AbstractServiceC08280wx r3, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(LoggerConstants.EVENT_KEY, str);
        hashMap.put("pid", String.valueOf(Process.myPid()));
        r3.A01.A5h("life_cycle", hashMap);
    }

    @Override // X.AnonymousClass11R
    public void A0D() {
        String str;
        AnonymousClass0x2 r2 = this.A0E;
        String A052 = AnonymousClass006.A05(AnonymousClass0vG.A00(AnonymousClass007.A01), ".SERVICE_DESTROY");
        if (!(this instanceof FbnsService)) {
            str = "N/A";
        } else {
            str = "FBNS_ALWAYS";
        }
        C09340zG r6 = C09340zG.A00;
        r2.A06(A052, str, null, r6, r6, this.A0B.get(), this.A06.A06.get(), this.A06.A02());
        A0A(this, "doDestroy");
        this.A01.A8B(null);
        A0K();
    }

    @Override // X.AnonymousClass11R
    public void A0E(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str;
        String valueOf;
        Throwable th;
        HashSet hashSet;
        String str2;
        String str3;
        String str4;
        SocketAddress remoteSocketAddress;
        try {
            printWriter.println("[ MqttPushService ]");
            if (!(this instanceof FbnsService)) {
                str = "N/A";
            } else {
                str = "FBNS_ALWAYS";
            }
            printWriter.println(AnonymousClass006.A05("persistence=", str));
            long j = this.A09.A01;
            if (j > 0) {
                valueOf = new Date(j).toString();
            } else {
                valueOf = String.valueOf(j);
            }
            printWriter.println(AnonymousClass006.A05("networkChangedTime=", valueOf));
            StringBuilder sb = new StringBuilder();
            sb.append("subscribedTopics=");
            C08290wy r0 = this.A09;
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
                C08290wy r5 = this.A09;
                printWriter.println("[ FbnsConnectionManager ]");
                printWriter.println("keepAliveIntervalSeconds=" + r5.A0V);
                NetworkInfo A012 = r5.A0D.A01();
                if (A012 != null) {
                    str2 = A012.toString();
                } else {
                    str2 = "null";
                }
                printWriter.println(AnonymousClass006.A05("networkInfo=", str2));
                if (r5.A0o != null) {
                    printWriter.println(AnonymousClass006.A05("lastConnectLostTime=", new Date((System.currentTimeMillis() + r5.A0j) - SystemClock.elapsedRealtime()).toString()));
                    printWriter.println(AnonymousClass006.A05("lastConnectLostReason=", r5.A0o));
                }
                C08300wz r52 = r5.A0l;
                if (r52 != null) {
                    synchronized (r52) {
                        try {
                            printWriter.println("[ MqttClient ]");
                            Integer num = r52.A0X;
                            if (num != null) {
                                str3 = C09380zK.A00(num);
                            } else {
                                str3 = "null";
                            }
                            printWriter.println(AnonymousClass006.A05("state=", str3));
                            printWriter.println(AnonymousClass006.A05("lastMessageSent=", C08300wz.A01(r52, r52.A0S)));
                            printWriter.println(AnonymousClass006.A05("lastMessageReceived=", C08300wz.A01(r52, r52.A0R)));
                            printWriter.println(AnonymousClass006.A05("connectionEstablished=", C08300wz.A01(r52, r52.A0P)));
                            printWriter.println(AnonymousClass006.A05("lastPing=", C08300wz.A01(r52, r52.A0T)));
                            C08610xX r3 = r52.A0B;
                            synchronized (r3) {
                                Socket socket = r3.A05;
                                if (socket == null || (remoteSocketAddress = socket.getRemoteSocketAddress()) == null) {
                                    str4 = "N/A";
                                } else {
                                    String str5 = r3.A02;
                                    if (str5 != null) {
                                        str4 = AnonymousClass006.A07(str5, "|", remoteSocketAddress.toString());
                                    } else {
                                        str4 = remoteSocketAddress.toString();
                                    }
                                }
                            }
                            printWriter.println(AnonymousClass006.A05("peer=", str4));
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

    public void A0G() {
        C08310x0 r3 = this.A05;
        AnonymousClass0z3 r0 = AnonymousClass0z3.ServiceCreatedTimestamp;
        C08310x0.A04(r3, r0).set(SystemClock.elapsedRealtime());
    }

    public void A0H() {
        C07490ue r0 = this.A0A;
        C08290wy r9 = r0.A0O;
        C08400xB r8 = r0.A0I;
        C08140wd r7 = r0.A0K;
        RealtimeSinceBootClock realtimeSinceBootClock = r0.A04;
        AnonymousClass0x2 r5 = r0.A0B;
        C08310x0 r4 = r0.A0D;
        C08270ww r3 = r0.A0J;
        C08380x8 r2 = r0.A0C;
        AbstractC09080yd r1 = r0.A02;
        C07710vp r02 = r0.A03;
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

    public final void A0K() {
        if (this.A0B.get()) {
            A09(this, EnumC08720xi.SERVICE_DESTROY);
        }
        C08290wy r1 = this.A09;
        if (r1 != null) {
            r1.A08(EnumC08720xi.SERVICE_DESTROY);
        }
        C07490ue r4 = this.A0A;
        if (r4 != null && !r4.A0W) {
            r4.A0W = true;
            C08120wb r3 = r4.A0M;
            if (r3 != null) {
                synchronized (r3) {
                    r3.A00();
                    if (r3.A01) {
                        boolean z = false;
                        if (!r3.A08.A05(r3.A06, r3.A05)) {
                            z = true;
                        }
                        r3.A01 = z;
                    }
                }
            }
            C08400xB r32 = r4.A0I;
            if (r32 != null) {
                synchronized (r32) {
                    try {
                        r32.A01.unregisterReceiver(r32.A00);
                    } catch (IllegalArgumentException e) {
                        AnonymousClass0NK.A0A("MqttNetworkManager", e, "Failed to unregister broadcast receiver");
                    }
                }
            }
            ExecutorServiceC08580xU r0 = r4.A0G;
            if (r0 != null) {
                r0.shutdown();
            }
            C08200wo r33 = r4.A0L;
            if (r33 != null) {
                synchronized (r33) {
                    r33.A03();
                    if (r33.A0N != null) {
                        C08110wa r2 = r33.A0F;
                        Context context = r33.A0C;
                        r2.A05(context, r33.A0A);
                        r2.A05(context, r33.A0B);
                        r2.A05(context, r33.A09);
                    }
                }
                return;
            }
            return;
        }
        return;
    }

    public final void A0N(Integer num, C09450zT r8) {
        String str;
        Integer num2;
        if (!this.A0B.getAndSet(true)) {
            if (!(r8 == null || (num2 = r8.A01) == null)) {
                int intValue = num2.intValue();
                if (this instanceof FbnsService) {
                    ((FbnsService) this).A03.A01().A00.set(((long) intValue) * 1000);
                }
            }
            C08310x0 r2 = this.A05;
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
                case Hpack.PREFIX_4_BITS /*{ENCODED_INT: 15}*/:
                    str = "AUTH_CREDENTIALS_CHANGE";
                    break;
                case 16:
                    str = "FORCE_KICK";
                    break;
                default:
                    str = "SERVICE_START";
                    break;
            }
            AnonymousClass0yS r3 = r2.A00;
            if (r3.A07 == null) {
                r3.A07 = str;
                r3.A04.set(SystemClock.elapsedRealtime());
                r3.A02.set(SystemClock.elapsedRealtime());
            }
            A0I();
            C08290wy r4 = this.A09;
            C09100yf r32 = new C09100yf(r4);
            r4.A03 = r32;
            r4.A04.registerReceiver(r32, new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED"), null, r4.A05);
            C09220ys r33 = new C09220ys(r4);
            r4.A02 = r33;
            r4.A04.registerReceiver(r33, new IntentFilter("com.facebook.rti.mqtt.ACTION_MQTT_CONFIG_CHANGED"), null, r4.A05);
            C08400xB r22 = r4.A0D;
            MqttNetworkChangeListener mqttNetworkChangeListener = r4.A0c;
            synchronized (r22) {
                r22.A04.add(mqttNetworkChangeListener);
            }
            C07740vv r1 = r4.A0L;
            if (((AbstractC07830w5) r1).A00 == null) {
                AnonymousClass0LT r34 = new AnonymousClass0LT("com.facebook.rti.mqtt.ACTION_ZR_SWITCH", new C07820w4(r1));
                ((AbstractC07830w5) r1).A00 = r34;
                r1.A01.registerReceiver(r34, new IntentFilter("com.facebook.rti.mqtt.ACTION_ZR_SWITCH"), "com.facebook.permission.prod.FB_APP_COMMUNICATION", null);
            }
        }
        this.A09.A0B(num);
    }

    public final boolean A0P() {
        if (!this.A0B.get()) {
            this.A01.log("MqttPushService/not_started");
            return false;
        }
        HashMap hashMap = new HashMap();
        if (this.A08.A8L(hashMap)) {
            return true;
        }
        this.A01.A5h("MqttPushService/should_not_connect", hashMap);
        return false;
    }

    @Override // X.AnonymousClass11R
    public final void onDestroy() {
        String str;
        if (this.A0E != null) {
            AnonymousClass0x2 r2 = this.A0E;
            String A052 = AnonymousClass006.A05(AnonymousClass0vG.A00(AnonymousClass007.A01), ".SERVICE_ON_DESTROY");
            if (!(this instanceof FbnsService)) {
                str = "N/A";
            } else {
                str = "FBNS_ALWAYS";
            }
            C09340zG r6 = C09340zG.A00;
            r2.A06(A052, str, null, r6, r6, this.A0B.get(), 0, null);
        }
        super.onDestroy();
    }
}
