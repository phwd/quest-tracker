package X;

import android.net.NetworkInfo;
import android.os.SystemClock;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.facebook.rti.mqtt.protocol.messages.MqttPublishRequestBody;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.0wz  reason: invalid class name and case insensitive filesystem */
public final class C08300wz {
    public static final EnumSet<EnumC08920y5> A0b = EnumSet.of(EnumC08920y5.ACKNOWLEDGED_DELIVERY, EnumC08920y5.PROCESSING_LASTACTIVE_PRESENCEINFO, EnumC08920y5.EXACT_KEEPALIVE, EnumC08920y5.DELTA_SENT_MESSAGE_ENABLED, EnumC08920y5.USE_THRIFT_FOR_INBOX, EnumC08920y5.USE_ENUM_TOPIC);
    public static final HashSet<String> A0c = new HashSet<>(Arrays.asList("/t_rtc", "/t_rtc_multi"));
    public static final AtomicInteger A0d = new AtomicInteger(1);
    public int A00;
    @GuardedBy("this")
    public List<MqttPublishRequestBody> A01;
    public final C07610ve A02;
    public final RealtimeSinceBootClock A03;
    public final AbstractC09610zk<String> A04;
    public final C07590vY A05;
    public final C07700vo A06;
    public final AnonymousClass0x2 A07;
    public final C08380x8 A08;
    public final C08310x0 A09;
    public final C08400xB A0A;
    public final C08610xX A0B;
    public final C08530xO A0C = new C08530xO(this);
    public final AnonymousClass0x9 A0D;
    @Nullable
    public final Long A0E;
    public final Map<String, SubscribeTopic> A0F = new HashMap();
    public final ExecutorService A0G;
    public final AtomicInteger A0H;
    public final boolean A0I;
    public final boolean A0J;
    public final AnonymousClass104 A0K = new AnonymousClass104(this);
    public final AnonymousClass0vN A0L;
    public final C07600vd A0M;
    public final C07750vw A0N;
    public volatile long A0O;
    public volatile long A0P = Long.MAX_VALUE;
    public volatile long A0Q = Long.MAX_VALUE;
    public volatile long A0R = Long.MAX_VALUE;
    public volatile long A0S = Long.MAX_VALUE;
    public volatile long A0T = Long.MAX_VALUE;
    public volatile long A0U;
    public volatile NetworkInfo A0V;
    public volatile C08520xN A0W;
    public volatile Integer A0X = AnonymousClass007.A0D;
    @Nullable
    public volatile String A0Y;
    public volatile String A0Z = "none";
    public volatile boolean A0a;

    /* JADX WARN: Incorrect return type in method signature: (LX/0xi;LX/0y3;Ljava/lang/Throwable;)Ljava/util/concurrent/Future<*>; */
    public static synchronized void A04(C08300wz r2, EnumC08720xi r3, @Nullable AnonymousClass0y3 r4, Throwable th) {
        synchronized (r2) {
            if (r2.A06()) {
                r2.A0G.submit(new RunnableC09240yv(r2, r3, r4, th));
            }
        }
    }

    public static void A02(C08300wz r9) throws InterruptedException {
        long j = (long) (r9.A0D.A03 * 1000);
        synchronized (r9) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (true) {
                Integer num = r9.A0X;
                if (num != AnonymousClass007.A00 && num != AnonymousClass007.A01) {
                    break;
                }
                long elapsedRealtime2 = j - (SystemClock.elapsedRealtime() - elapsedRealtime);
                if (elapsedRealtime2 <= 0) {
                    break;
                }
                r9.wait(elapsedRealtime2);
            }
        }
    }

    public final boolean A05() {
        if (this.A0X == AnonymousClass007.A0C) {
            return true;
        }
        return false;
    }

    public final boolean A06() {
        Integer num = this.A0X;
        if (num == AnonymousClass007.A0C || num == AnonymousClass007.A00 || num == AnonymousClass007.A01) {
            return true;
        }
        return false;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("[MqttClient (");
        AnonymousClass0x9 r1 = this.A0D;
        sb.append(r1.A00);
        sb.append(":");
        sb.append(this.A00);
        if (r1.A0K) {
            sb.append(" +ssl");
        }
        sb.append(") ");
        Integer num = this.A0X;
        if (num != null) {
            str = C09380zK.A00(num);
        } else {
            str = "null";
        }
        sb.append(str);
        sb.append("]");
        return sb.toString();
    }

    private AbstractC09150yk<Long> A00(long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j > elapsedRealtime) {
            return C09340zG.A00;
        }
        return AbstractC09150yk.A00(Long.valueOf(elapsedRealtime - j));
    }

    public static String A01(C08300wz r1, long j) {
        AbstractC09150yk<Long> A002 = r1.A00(j);
        if (A002.A02()) {
            return new Date(System.currentTimeMillis() - A002.A01().longValue()).toString();
        }
        return "N/A";
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0xB;LX/0vY;LX/0x2;LX/0x0;LX/0x9;Lcom/facebook/rti/common/time/MonotonicClock;Ljava/util/concurrent/ExecutorService;LX/0x8;Lcom/facebook/rti/common/fbtrace/FbTraceLogger;Lcom/facebook/rti/mqtt/protocol/MqttClientTopicSubscriptionCallback;Lcom/facebook/rti/mqtt/protocol/lifecycle/MqttLifeCycleListener;Lcom/facebook/rti/mqtt/credentials/MqttCredentials;LX/0vo;Lcom/facebook/rti/mqtt/protocol/MqttClientCore;LX/0zk<Ljava/lang/String;>;Lcom/facebook/rtc/helpers/signalingflowloggerinterface/IRtcSignalingFlowLogger;Ljava/util/concurrent/atomic/AtomicReference<Ljava/lang/Integer;>;LX/0zk<Ljava/lang/Boolean;>;ZZZZLjava/lang/Long;)V */
    public C08300wz(C08400xB r5, C07590vY r6, AnonymousClass0x2 r7, C08310x0 r8, AnonymousClass0x9 r9, RealtimeSinceBootClock realtimeSinceBootClock, ExecutorService executorService, C08380x8 r12, C07610ve r13, AnonymousClass0vN r14, C07600vd r15, C07750vw r16, C07700vo r17, C08610xX r18, AbstractC09610zk r19, @Nullable boolean z, @Nullable Long l) {
        String str;
        boolean z2 = false;
        this.A0H = new AtomicInteger(0);
        this.A0A = r5;
        this.A05 = r6;
        this.A07 = r7;
        this.A09 = r8;
        this.A0D = r9;
        this.A03 = realtimeSinceBootClock;
        this.A0G = executorService;
        this.A08 = r12;
        this.A02 = r13;
        this.A0L = r14;
        this.A0M = r15;
        this.A0N = r16;
        this.A06 = r17;
        this.A0B = r18;
        this.A04 = r19;
        C08530xO r2 = this.A0C;
        AnonymousClass104 r0 = this.A0K;
        r18.A0G = r2;
        r18.A0F = r0;
        if ("".equals("device_auth") && (str = this.A0D.A0G) != null && "".equals(str)) {
            z2 = true;
        }
        this.A0J = z2;
        this.A0I = z;
        this.A0E = l;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0086, code lost:
        if (r28 != null) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0088, code lost:
        r7 = X.C09340zG.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x008a, code lost:
        r0 = r25.A0U;
        r1 = r25.A0A.A06.get();
        r0 = r25.A0V;
        r10 = new java.util.HashMap();
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00ad, code lost:
        if (android.provider.Settings.Global.getInt(r8.A00.getContentResolver(), "airplane_mode_on", 0) != 0) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00af, code lost:
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00b0, code lost:
        r10.put("is_airplane_mode_on", java.lang.String.valueOf(r12));
        r9 = r8.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00bc, code lost:
        r7 = new X.AnonymousClass0yR(r28);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r15 = r9.A00.registerReceiver(null, new android.content.IntentFilter("android.intent.action.BATTERY_CHANGED"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00d0, code lost:
        if (r15 != null) goto L_0x00d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00d2, code lost:
        r12 = X.C09340zG.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00d5, code lost:
        r9 = r15.getIntExtra("status", -1);
        r13 = true;
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00df, code lost:
        if (r9 == 2) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00e1, code lost:
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e3, code lost:
        if (r9 != 5) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00e6, code lost:
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00e7, code lost:
        r9 = r15.getIntExtra("level", -1);
        r0 = r15.getIntExtra("scale", -1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00f3, code lost:
        if (r9 == -1) goto L_0x0111;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00f5, code lost:
        if (r0 == -1) goto L_0x0111;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f7, code lost:
        r12 = new X.AnonymousClass0yR(new X.C09300zC(r12, r13, X.AbstractC09150yk.A00(java.lang.Integer.valueOf((int) ((((float) r9) / ((float) r0)) * 100.0f)))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0111, code lost:
        r12 = new X.AnonymousClass0yR(new X.C09300zC(r12, r13, X.C09340zG.A00));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x011e, code lost:
        r12 = X.C09340zG.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        r6 = r25.A09;
        ((X.AbstractC08430xE) r6.A06(X.C09660zz.class)).A02(X.AnonymousClass0y2.LastDisconnectReason, r26.name());
        r6.A00.A02.set(android.os.SystemClock.elapsedRealtime());
        ((java.util.concurrent.atomic.AtomicLong) ((X.AbstractC08430xE) r6.A06(X.C08560xS.class)).A00(X.EnumC08570xT.MqttTotalDurationMs)).addAndGet(android.os.SystemClock.elapsedRealtime() - r25.A0U);
        r8 = r25.A07;
        r23 = r25.A00(r25.A0P);
        r22 = r25.A00(r25.A0T);
        r17 = r25.A00(r25.A0S);
        r16 = r25.A00(r25.A0R);
        r21 = r26.toString();
        r20 = X.AbstractC09150yk.A00(r21);
        r19 = r27.toString();
        r18 = X.AbstractC09150yk.A00(r19);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A03(X.C08300wz r25, X.EnumC08720xi r26, @javax.annotation.Nullable X.AnonymousClass0y3 r27, java.lang.Throwable r28) {
        /*
        // Method dump skipped, instructions count: 672
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08300wz.A03(X.0wz, X.0xi, X.0y3, java.lang.Throwable):void");
    }
}
