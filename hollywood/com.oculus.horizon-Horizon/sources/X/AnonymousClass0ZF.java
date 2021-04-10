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
/* renamed from: X.0ZF  reason: invalid class name */
public final class AnonymousClass0ZF {
    public static final EnumSet<AnonymousClass0ZE> A0b = EnumSet.of(AnonymousClass0ZE.ACKNOWLEDGED_DELIVERY, AnonymousClass0ZE.PROCESSING_LASTACTIVE_PRESENCEINFO, AnonymousClass0ZE.EXACT_KEEPALIVE, AnonymousClass0ZE.DELTA_SENT_MESSAGE_ENABLED, AnonymousClass0ZE.USE_THRIFT_FOR_INBOX, AnonymousClass0ZE.USE_ENUM_TOPIC);
    public static final HashSet<String> A0c = new HashSet<>(Arrays.asList("/t_rtc", "/t_rtc_multi"));
    public static final AtomicInteger A0d = new AtomicInteger(1);
    public int A00;
    @GuardedBy("this")
    public List<MqttPublishRequestBody> A01;
    public final C06540nb A02;
    public final RealtimeSinceBootClock A03;
    public final AnonymousClass0WY<String> A04;
    public final C01620We A05;
    public final C01670Wl A06;
    public final AnonymousClass0Wo A07;
    public final C01680Wq A08;
    public final AnonymousClass0Wu A09;
    public final AnonymousClass0XJ A0A;
    public final C05890m2 A0B;
    public final C05870m0 A0C = new C05870m0(this);
    public final AnonymousClass0ZM A0D;
    @Nullable
    public final Long A0E;
    public final Map<String, SubscribeTopic> A0F = new HashMap();
    public final ExecutorService A0G;
    public final AtomicInteger A0H;
    public final boolean A0I;
    public final boolean A0J;
    public final C05880m1 A0K = new C05880m1(this);
    public final C05860lz A0L;
    public final C05840lw A0M;
    public final C04620ib A0N;
    public volatile long A0O;
    public volatile long A0P = Long.MAX_VALUE;
    public volatile long A0Q = Long.MAX_VALUE;
    public volatile long A0R = Long.MAX_VALUE;
    public volatile long A0S = Long.MAX_VALUE;
    public volatile long A0T = Long.MAX_VALUE;
    public volatile long A0U;
    public volatile NetworkInfo A0V;
    public volatile C06120mX A0W;
    public volatile Integer A0X = AnonymousClass007.A0D;
    @Nullable
    public volatile String A0Y;
    public volatile String A0Z = "none";
    public volatile boolean A0a;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0089, code lost:
        if (r26 != null) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x008b, code lost:
        r8 = X.C06530na.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x008d, code lost:
        r0 = r23.A0U;
        r0 = r23.A0A.A05.get();
        r6 = r23.A0V;
        r10 = new java.util.HashMap();
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00b0, code lost:
        if (android.provider.Settings.Global.getInt(r2.A00.getContentResolver(), "airplane_mode_on", 0) != 0) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00b2, code lost:
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00b3, code lost:
        r10.put("is_airplane_mode_on", java.lang.String.valueOf(r12));
        r9 = r2.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00bf, code lost:
        r8 = new X.AnonymousClass0nZ(r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r15 = r9.A00.registerReceiver(null, new android.content.IntentFilter("android.intent.action.BATTERY_CHANGED"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00d3, code lost:
        if (r15 != null) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00d5, code lost:
        r12 = X.C06530na.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00d8, code lost:
        r9 = r15.getIntExtra("status", -1);
        r13 = true;
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00e2, code lost:
        if (r9 == 2) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00e4, code lost:
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e6, code lost:
        if (r9 != 5) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00e9, code lost:
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ea, code lost:
        r9 = r15.getIntExtra("level", -1);
        r6 = r15.getIntExtra("scale", -1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00f6, code lost:
        if (r9 == -1) goto L_0x0114;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00f8, code lost:
        if (r6 == -1) goto L_0x0114;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00fa, code lost:
        r12 = new X.AnonymousClass0nZ(new X.AnonymousClass0XG(r12, r13, X.AnonymousClass0W8.A00(java.lang.Integer.valueOf((int) ((((float) r9) / ((float) r6)) * 100.0f)))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0114, code lost:
        r12 = new X.AnonymousClass0nZ(new X.AnonymousClass0XG(r12, r13, X.C06530na.A00));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0121, code lost:
        r12 = X.C06530na.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0025, code lost:
        r6 = r23.A09;
        ((X.AnonymousClass0nJ) r6.A06(X.AnonymousClass0Ie.class)).A02(X.AnonymousClass0nK.LastDisconnectReason, r24.name());
        r6.A00.A02.set(android.os.SystemClock.elapsedRealtime());
        ((java.util.concurrent.atomic.AtomicLong) ((X.AnonymousClass0nJ) r6.A06(X.AnonymousClass0IW.class)).A00(X.AnonymousClass0nF.MqttTotalDurationMs)).addAndGet(android.os.SystemClock.elapsedRealtime() - r23.A0U);
        r2 = r23.A07;
        r23 = r23.A00(r23.A0P);
        r22 = r23.A00(r23.A0T);
        r17 = r23.A00(r23.A0S);
        r16 = r23.A00(r23.A0R);
        r21 = r24.toString();
        r20 = X.AnonymousClass0W8.A00(r21);
        r19 = r25.toString();
        r18 = X.AnonymousClass0W8.A00(r19);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A03(X.AnonymousClass0ZF r23, X.EnumC01660Wk r24, @javax.annotation.Nullable X.AnonymousClass0ZP r25, java.lang.Throwable r26) {
        /*
        // Method dump skipped, instructions count: 658
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0ZF.A03(X.0ZF, X.0Wk, X.0ZP, java.lang.Throwable):void");
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0Wk;LX/0ZP;Ljava/lang/Throwable;)Ljava/util/concurrent/Future<*>; */
    public static synchronized void A04(AnonymousClass0ZF r2, EnumC01660Wk r3, @Nullable AnonymousClass0ZP r4, Throwable th) {
        synchronized (r2) {
            if (r2.A06()) {
                r2.A0G.submit(new AnonymousClass0ZC(r2, r3, r4, th));
            }
        }
    }

    public static void A02(AnonymousClass0ZF r9) throws InterruptedException {
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
        AnonymousClass0ZM r1 = this.A0D;
        sb.append(r1.A00);
        sb.append(":");
        sb.append(this.A00);
        if (r1.A0K) {
            sb.append(" +ssl");
        }
        sb.append(") ");
        Integer num = this.A0X;
        if (num != null) {
            str = C02070Yz.A00(num);
        } else {
            str = "null";
        }
        sb.append(str);
        sb.append("]");
        return sb.toString();
    }

    private AnonymousClass0W8<Long> A00(long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j > elapsedRealtime) {
            return C06530na.A00;
        }
        return AnonymousClass0W8.A00(Long.valueOf(elapsedRealtime - j));
    }

    public static String A01(AnonymousClass0ZF r1, long j) {
        AnonymousClass0W8<Long> A002 = r1.A00(j);
        if (A002.A02()) {
            return new Date(System.currentTimeMillis() - A002.A01().longValue()).toString();
        }
        return "N/A";
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0XJ;LX/0We;LX/0Wo;LX/0Wu;LX/0ZM;Lcom/facebook/rti/common/time/MonotonicClock;Ljava/util/concurrent/ExecutorService;LX/0Wq;Lcom/facebook/rti/common/fbtrace/FbTraceLogger;Lcom/facebook/rti/mqtt/protocol/MqttClientTopicSubscriptionCallback;Lcom/facebook/rti/mqtt/protocol/lifecycle/MqttLifeCycleListener;Lcom/facebook/rti/mqtt/credentials/MqttCredentials;LX/0Wl;Lcom/facebook/rti/mqtt/protocol/MqttClientCore;LX/0WY<Ljava/lang/String;>;Lcom/facebook/rtc/helpers/signalingflowloggerinterface/IRtcSignalingFlowLogger;Ljava/util/concurrent/atomic/AtomicReference<Ljava/lang/Integer;>;LX/0WY<Ljava/lang/Boolean;>;ZZZZLjava/lang/Long;)V */
    public AnonymousClass0ZF(AnonymousClass0XJ r5, C01620We r6, AnonymousClass0Wo r7, AnonymousClass0Wu r8, AnonymousClass0ZM r9, RealtimeSinceBootClock realtimeSinceBootClock, ExecutorService executorService, C01680Wq r12, C06540nb r13, C05860lz r14, C05840lw r15, C04620ib r16, C01670Wl r17, C05890m2 r18, AnonymousClass0WY r19, @Nullable boolean z, @Nullable Long l) {
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
        C05870m0 r2 = this.A0C;
        C05880m1 r0 = this.A0K;
        r18.A0G = r2;
        r18.A0F = r0;
        if ("".equals("device_auth") && (str = this.A0D.A0G) != null && "".equals(str)) {
            z2 = true;
        }
        this.A0J = z2;
        this.A0I = z;
        this.A0E = l;
    }
}
