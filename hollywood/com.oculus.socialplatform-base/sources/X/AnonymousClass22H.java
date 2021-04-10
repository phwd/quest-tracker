package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.facebook.rti.common.util.NonInjectTwoParamsProvider;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import com.oculus.messengervr.fb.VrMsysMqttClientCallbacks;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import okhttp3.internal.http2.Settings;
import org.json.JSONException;

@VisibleForTesting
@NotThreadSafe
/* renamed from: X.22H  reason: invalid class name */
public final class AnonymousClass22H {
    public long A00;
    public long A01;
    public long A02;
    public BroadcastReceiver A03;
    public BroadcastReceiver A04;
    public Context A05;
    public Handler A06;
    public AnonymousClass1R6 A07;
    public C144423g A08;
    public C146023y A09;
    public AnonymousClass22G A0A;
    public RealtimeSinceBootClock A0B;
    public C144923n A0C;
    public AnonymousClass1QQ A0D;
    public AnonymousClass1QK A0E;
    public AnonymousClass1QF A0F;
    public AnonymousClass1YF A0G;
    public AnonymousClass1QS A0H;
    public AnonymousClass1XQ A0I;
    public AnonymousClass1WV A0J;
    public AnonymousClass1R4 A0K;
    public NonInjectTwoParamsProvider<List<SubscribeTopic>, AnonymousClass22J> A0L;
    public AnonymousClass22P A0M;
    public AnonymousClass1PH A0N;
    public AnonymousClass1PI A0O;
    public AnonymousClass1Jd A0P;
    public AnonymousClass22S A0Q;
    public String A0R = "FbnsConnectionManager";
    public String A0S;
    public Map<String, SubscribeTopic> A0T = new HashMap();
    public Executor A0U;
    public AtomicBoolean A0V = new AtomicBoolean(false);
    public AtomicInteger A0W;
    public boolean A0X;
    public boolean A0Y;
    public boolean A0Z = false;
    public long A0a;
    public long A0b;
    public Method A0c;
    public final AnonymousClass1QX A0d = new AnonymousClass1QX(this);
    public final Runnable A0e = new RunnableC144723l(this);
    public final Runnable A0f = new AnonymousClass22O(this);
    public final Runnable A0g = new AnonymousClass23F(this);
    public final Runnable A0h = new RunnableC144223e(this);
    public final AtomicLong A0i = new AtomicLong();
    public final Object A0j = new Object();
    public volatile long A0k;
    public volatile long A0l;
    public volatile AnonymousClass22Y A0m;
    public volatile AnonymousClass22J A0n;
    public volatile AnonymousClass22J A0o;
    public volatile AnonymousClass1Jf A0p;
    public volatile String A0q;
    public volatile boolean A0r;

    /* JADX WARN: Incorrect return type in method signature: (LX/22J;LX/22g;Lcom/facebook/rti/mqtt/manager/FbnsConnectionManager$DisconnectReason;)Ljava/util/concurrent/Future<*>; */
    public final void A08(AnonymousClass22J r5, EnumC141822g r6, Integer num) {
        synchronized (this) {
            if (this.A0n == r5) {
                this.A0n = null;
            }
        }
        if (r5 != null) {
            boolean z = false;
            if (r5.A0X == AnonymousClass007.A04) {
                z = true;
            }
            r5.A0W = null;
            synchronized (r5) {
                AnonymousClass22J.A03(r5, r6, EnumC142922r.DISCONNECT, null);
            }
            this.A01 = System.currentTimeMillis();
            if (z) {
                return;
            }
        }
        A04(this, num, AnonymousClass1QP.A00);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x01dd, code lost:
        if (r12.equals("") != false) goto L_0x01ed;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A01(X.AnonymousClass22H r42) {
        /*
        // Method dump skipped, instructions count: 968
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass22H.A01(X.22H):void");
    }

    public static void A03(AnonymousClass22H r6, AnonymousClass1Q6 r7, boolean z) {
        String str;
        if (r7 != null) {
            try {
                str = AnonymousClass1Q6.A00(r7, r7.A00).toString();
            } catch (JSONException unused) {
                str = "";
            }
            if (!TextUtils.isEmpty(str)) {
                try {
                    EnumC143322v r4 = EnumC143322v.ACKNOWLEDGED_DELIVERY;
                    if (z) {
                        r4 = EnumC143322v.FIRE_AND_FORGET;
                    }
                    try {
                        AnonymousClass1QO A002 = A00(r6, "/mqtt_health_stats", str.getBytes("UTF-8"), r4, null, ((AnonymousClass1YE) r6.A0G).A00.A0I, null);
                        if (A002.A02()) {
                            A002.A01();
                        }
                    } catch (UnsupportedEncodingException unused2) {
                        throw new RuntimeException("UTF-8 not supported");
                    }
                } catch (AnonymousClass23U unused3) {
                }
            }
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/facebook/rti/mqtt/manager/FbnsConnectionManager$DisconnectReason;LX/1QO<LX/22n;>;)V */
    public static void A04(AnonymousClass22H r14, Integer num, AnonymousClass1QO r16) {
        String str;
        String str2;
        AnonymousClass22S r1;
        EnumC142522n r0;
        long j;
        String obj;
        AnonymousClass22J r02 = r14.A0n;
        if (r02 != null) {
            AnonymousClass22M r3 = r02.A0D;
            StringBuilder sb = new StringBuilder();
            InetAddress inetAddress = r3.A03;
            if (inetAddress != null) {
                sb.append("Remote:");
                sb.append(inetAddress.toString());
                sb.append('\n');
            }
            InetAddress inetAddress2 = r3.A02;
            if (inetAddress2 != null) {
                sb.append("Local:");
                sb.append(inetAddress2.toString());
                sb.append('\n');
            }
            AnonymousClass1P7 r32 = r3.A09;
            synchronized (r32) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Cache{");
                Iterator<AnonymousClass1P5> it = r32.A00.A01().iterator();
                while (it.hasNext()) {
                    sb2.append(it.next().toString());
                    sb2.append(',');
                }
                sb2.append("}\n");
                obj = sb2.toString();
            }
            sb.append(obj);
            str = sb.toString();
        } else {
            str = "";
        }
        AnonymousClass22P r2 = r14.A0M;
        if (num != null) {
            switch (num.intValue()) {
                case 1:
                    str2 = "CONNECTION_LOST";
                    break;
                case 2:
                    str2 = "BY_REQUEST";
                    break;
                case 3:
                    str2 = "DISCONNECTED";
                    break;
                case 4:
                    str2 = "STALED_CONNECTION";
                    break;
                case 5:
                    str2 = "PREEMPTIVE_RECONNECT_SUCCESS";
                    break;
                default:
                    str2 = "CONNECT_FAILED";
                    break;
            }
        } else {
            str2 = "null";
        }
        AnonymousClass23U r11 = new AnonymousClass23U(AnonymousClass006.A0B("Connection lost ", str2, ", ", str));
        ArrayList arrayList = new ArrayList();
        Map<Integer, AnonymousClass22b> map = r2.A03;
        synchronized (map) {
            arrayList.addAll(map.values());
            map.clear();
        }
        arrayList.size();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            AnonymousClass22b r33 = (AnonymousClass22b) it2.next();
            synchronized (r33) {
                r33.A00 = r11;
            }
            if (r33.A07 != null) {
                r33.A07.onFailure(r33.A01);
            }
            if (r33.A06 != null) {
                r33.A06.cancel(false);
            }
            int i = r33.A01;
            AnonymousClass22J r03 = r33.A03;
            if (r03 == null) {
                j = 0;
            } else {
                j = r03.A0U;
            }
            r2.A00.A05("abort", r33.A05, EnumC143322v.ACKNOWLEDGED_DELIVERY.getValue(), i, i, r11, j);
        }
        r11.getMessage();
        switch (num.intValue()) {
            case 1:
                r14.A0F.A0E = AnonymousClass007.A08;
                long j2 = r14.A02;
                if (j2 <= 0 || (SystemClock.elapsedRealtime() - j2) / 1000 >= ((long) ((AnonymousClass1YE) r14.A0G).A00.A0L)) {
                    AnonymousClass22S r12 = r14.A0Q;
                    synchronized (r12) {
                        r12.A07 = false;
                    }
                    r1 = r14.A0Q;
                    synchronized (r1) {
                        AnonymousClass22S.A00(r1);
                    }
                } else {
                    r1 = r14.A0Q;
                    synchronized (r1) {
                        r1.A07 = true;
                    }
                }
                break;
            case 0:
                r14.A0Q.A02();
                break;
        }
        boolean A022 = r16.A02();
        if (A022) {
            r16.A01();
        }
        AnonymousClass22G r13 = r14.A0A;
        if (A022) {
            r0 = (EnumC142522n) r16.A01();
        } else {
            r0 = null;
        }
        AnonymousClass22G.A02(r13, r0);
    }

    public final void A05() {
        this.A0K.A00();
        if (!this.A0X) {
            this.A0J.A04();
        } else {
            this.A0K.A01();
        }
    }

    public final void A06() {
        int i;
        boolean z = this.A0V.get();
        AnonymousClass1R6 r0 = this.A07;
        if (z) {
            i = r0.A01;
        } else {
            i = r0.A00;
        }
        if (this.A0W.getAndSet(i) != i) {
            A05();
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/22g;)Ljava/util/concurrent/Future<*>; */
    public final void A07(EnumC141822g r3) {
        AnonymousClass22S r1 = this.A0Q;
        synchronized (r1) {
            AnonymousClass22S.A00(r1);
        }
        A08(this.A0n, r3, AnonymousClass007.A03);
    }

    @VisibleForTesting
    public final void A09(Integer num) {
        Integer num2;
        AnonymousClass22J r2 = this.A0n;
        if (AnonymousClass007.A02.equals(num)) {
            num2 = AnonymousClass007.A03;
        } else if (!this.A0A.A04()) {
            A07(EnumC141822g.KICK_SHOULD_NOT_CONNECT);
            return;
        } else {
            if (r2 != null) {
                if (!r2.A05()) {
                    num2 = AnonymousClass007.A04;
                } else {
                    return;
                }
            }
            A01(this);
        }
        A08(r2, EnumC141822g.EXPIRE_CONNECTION, num2);
        A01(this);
    }

    public final void A0A(Integer num) {
        if (!this.A0X) {
            AnonymousClass1WV r1 = this.A0J;
            synchronized (r1) {
                if (!r1.A03) {
                    r1.A04();
                } else {
                    SystemClock.elapsedRealtime();
                }
            }
        } else {
            AnonymousClass1R4 r12 = this.A0K;
            synchronized (r12) {
                if (!r12.A00) {
                    r12.A01();
                }
            }
        }
        if (!this.A0A.A04()) {
            A07(EnumC141822g.KICK_SHOULD_NOT_CONNECT);
            return;
        }
        AnonymousClass22J r2 = this.A0n;
        if (r2 == null || !r2.A04()) {
            AnonymousClass22J r0 = this.A0n;
            if (r0 != null && r0.A05()) {
                return;
            }
        } else if (!((String) this.A08.A01.first).equals(r2.A0Y)) {
            A07(EnumC141822g.AUTH_CREDENTIALS_CHANGE);
        } else if (this.A0b <= this.A02) {
            if (num.equals(AnonymousClass007.A02)) {
                this.A0F.A0E = AnonymousClass007.A07;
                AnonymousClass22S r22 = this.A0Q;
                synchronized (r22) {
                    if (r22.A04 == null) {
                        AnonymousClass0MD.A05("ConnectionRetryManager", "No force reconnect runnable set. Completing early from kickNow()");
                    } else {
                        Handler handler = r22.A09;
                        if (handler == null || handler.getLooper().getThread() != Thread.currentThread()) {
                            r22.A0B.submit(r22.A04);
                        } else {
                            r22.A04.run();
                        }
                    }
                }
            } else {
                return;
            }
        }
        A06();
        this.A0F.A0E = num;
        AnonymousClass22S r13 = this.A0Q;
        synchronized (r13) {
            AnonymousClass22S.A00(r13);
            r13.A02();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010e, code lost:
        if (r0.isConnected() == false) goto L_0x0110;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A02(X.AnonymousClass22H r7, android.content.Intent r8) {
        /*
        // Method dump skipped, instructions count: 399
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass22H.A02(X.22H, android.content.Intent):void");
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;[BLX/22v;Lcom/facebook/rti/mqtt/protocol/MqttPublishListener;IJLjava/lang/String;Lcom/facebook/rti/mqtt/protocol/MqttPubAckCallback;)LX/1QO<Lcom/facebook/rti/mqtt/manager/PublishOperation;>; */
    public static final AnonymousClass1QO A00(AnonymousClass22H r22, String str, byte[] bArr, @Nullable EnumC143322v r25, AnonymousClass238 r26, int i, @Nullable VrMsysMqttClientCallbacks.AnonymousClass1.AnonymousClass1 r28) throws AnonymousClass23U {
        long j;
        int i2;
        AnonymousClass22b r4;
        AnonymousClass22b put;
        long j2;
        boolean z = false;
        if (r25.getValue() < EnumC143322v.ASSURED_DELIVERY.getValue()) {
            z = true;
        }
        AnonymousClass1ZK.A00(z);
        AnonymousClass22J r3 = r22.A0n;
        if (r3 == null) {
            j = 0;
        } else if (!r3.A05()) {
            j = r3.A0U;
        } else {
            C143823a r9 = C143823a.A01;
            try {
                int andIncrement = AnonymousClass22J.A0d.getAndIncrement() & Settings.DEFAULT_INITIAL_WINDOW_SIZE;
                Integer num = r3.A0X;
                Integer num2 = AnonymousClass007.A00;
                if (num == num2 || num == AnonymousClass007.A01) {
                    if (r3.A0U > 0) {
                        j2 = SystemClock.elapsedRealtime() - r3.A0U;
                    } else {
                        j2 = 0;
                    }
                    long j3 = ((long) ((AnonymousClass1YE) r22.A0G).A00.A0H) * 1000;
                    long j4 = j3 - j2;
                    if (j4 < 0) {
                        j4 = 0;
                    } else if (j4 > j3) {
                        j4 = j3;
                    }
                    i2 = (int) (j4 / 1000);
                } else {
                    i2 = 0;
                }
                int i3 = i + i2;
                if (r25 != EnumC143322v.ACKNOWLEDGED_DELIVERY) {
                    r4 = new AnonymousClass22b(r3, str, EnumC142622o.PUBACK, andIncrement, SystemClock.elapsedRealtime());
                    if (r4.A07 != null) {
                        r4.A07.onSuccess(r4.A01);
                    }
                    if (r4.A06 != null) {
                        r4.A06.cancel(false);
                    }
                    r22.A0E.A04(str, r25.getValue(), andIncrement, 0, r4.A01, r3.A0U);
                } else if (r28 == null) {
                    r4 = r22.A0M.A01(r3, str, EnumC142622o.PUBACK, andIncrement, i3);
                } else {
                    AnonymousClass22P r0 = r22.A0M;
                    r4 = new AnonymousClass22b(r3, str, EnumC142622o.PUBACK, andIncrement, SystemClock.elapsedRealtime());
                    Map<Integer, AnonymousClass22b> map = r0.A03;
                    synchronized (map) {
                        put = map.put(Integer.valueOf(r4.A01), r4);
                    }
                    if (put != null) {
                        put.A00();
                        AnonymousClass0MD.A09("MqttOperationManager", "operation/add/duplicate; id=%d, name=%s", Integer.valueOf(put.A01), put.A04.name());
                    }
                    AbstractScheduledFutureC02560jD<?> A032 = r0.A02.schedule(new AnonymousClass23T(r0, r4), (long) i3, TimeUnit.SECONDS);
                    boolean z2 = false;
                    if (r4.A06 == null) {
                        z2 = true;
                    }
                    AnonymousClass1ZK.A01(z2);
                    r4.A06 = A032;
                    boolean z3 = false;
                    if (r4.A07 == null) {
                        z3 = true;
                    }
                    AnonymousClass1ZK.A01(z3);
                    r4.A07 = r28;
                }
                synchronized (r3) {
                    if (r3.A05()) {
                        r3.A0H.execute(new AnonymousClass22U(r3, str, bArr, r25, andIncrement, r26, r9));
                    } else {
                        throw new AnonymousClass23U(num2);
                    }
                }
                if (!"/mqtt_health_stats".equals(str) && r25 == EnumC143322v.ACKNOWLEDGED_DELIVERY) {
                    r22.A05();
                }
                return AnonymousClass1QO.A00(r4);
            } catch (AnonymousClass23U e) {
                AnonymousClass0MD.A0D(r22.A0R, e, "exception/publish");
                r22.A08(r3, EnumC141822g.SEND_FAILURE, AnonymousClass007.A01);
                PowerManager.WakeLock wakeLock = r9.A00;
                if (wakeLock != null) {
                    wakeLock.release();
                }
            } catch (Throwable unused) {
            }
        }
        r22.A0E.A05("not_connected", str, r25.getValue(), 0, 0, null, j);
        return AnonymousClass1QP.A00;
        throw e;
    }
}
