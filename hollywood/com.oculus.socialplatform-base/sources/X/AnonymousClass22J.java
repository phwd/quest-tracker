package X;

import android.net.NetworkInfo;
import android.os.SystemClock;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.facebook.rti.mqtt.protocol.messages.MqttPublishRequestBody;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.util.Arrays;
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
/* renamed from: X.22J  reason: invalid class name */
public final class AnonymousClass22J {
    public static final EnumSet<EnumC143422w> A0b = EnumSet.of(EnumC143422w.ACKNOWLEDGED_DELIVERY, EnumC143422w.PROCESSING_LASTACTIVE_PRESENCEINFO, EnumC143422w.EXACT_KEEPALIVE, EnumC143422w.DELTA_SENT_MESSAGE_ENABLED, EnumC143422w.USE_THRIFT_FOR_INBOX, EnumC143422w.USE_ENUM_TOPIC);
    public static final HashSet<String> A0c = new HashSet<>(Arrays.asList("/t_rtc", "/t_rtc_multi"));
    public static final AtomicInteger A0d = new AtomicInteger(1);
    public int A00;
    @GuardedBy("this")
    public List<MqttPublishRequestBody> A01;
    public final C144423g A02;
    public final C145723v A03;
    public final RealtimeSinceBootClock A04;
    @Nullable
    public final AnonymousClass1QM<Boolean> A05;
    public final AnonymousClass1QM<String> A06;
    public final C144923n A07;
    public final AnonymousClass1PK A08;
    public final AnonymousClass1QK A09;
    public final AnonymousClass1Vm A0A;
    public final AnonymousClass1QF A0B;
    public final AnonymousClass1QS A0C;
    public final AnonymousClass22M A0D;
    public final C141922h A0E = new C141922h(this);
    public final AnonymousClass22T A0F;
    public final Map<String, SubscribeTopic> A0G = new HashMap();
    public final ExecutorService A0H;
    public final AtomicInteger A0I;
    public final boolean A0J;
    public final boolean A0K;
    public final AnonymousClass247 A0L = new AnonymousClass247(this);
    public final AnonymousClass1Jf A0M;
    public final AnonymousClass244 A0N;
    public volatile long A0O;
    public volatile long A0P = RecyclerView.FOREVER_NS;
    public volatile long A0Q;
    public volatile long A0R = RecyclerView.FOREVER_NS;
    public volatile long A0S = RecyclerView.FOREVER_NS;
    public volatile long A0T = RecyclerView.FOREVER_NS;
    public volatile long A0U;
    public volatile NetworkInfo A0V;
    public volatile C142322l A0W;
    public volatile Integer A0X = AnonymousClass007.A04;
    @Nullable
    public volatile String A0Y;
    public volatile String A0Z = "none";
    public volatile boolean A0a;

    /* JADX WARN: Incorrect return type in method signature: (LX/22g;LX/22r;Ljava/lang/Throwable;)Ljava/util/concurrent/Future<*>; */
    public static synchronized void A03(AnonymousClass22J r2, EnumC141822g r3, @Nullable EnumC142922r r4, Throwable th) {
        synchronized (r2) {
            if (r2.A05()) {
                r2.A0H.submit(new AnonymousClass23X(r2, r3, r4, th));
            }
        }
    }

    public static void A01(AnonymousClass22J r9) throws InterruptedException {
        long j = (long) (r9.A0F.A03 * 1000);
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

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0086, code lost:
        if (r24 != null) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0088, code lost:
        r4 = X.AnonymousClass1QP.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x008a, code lost:
        r0 = r21.A0U;
        r1 = r21.A0C.A06.get();
        r11 = r21.A0V;
        r0 = r21.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x009a, code lost:
        if (r0 != null) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x009c, code lost:
        r16 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x009e, code lost:
        r10 = new java.util.HashMap();
        r13 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00b1, code lost:
        if (android.provider.Settings.Global.getInt(r5.A00.getContentResolver(), "airplane_mode_on", 0) != 0) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00b3, code lost:
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00b4, code lost:
        r10.put("is_airplane_mode_on", java.lang.String.valueOf(r13));
        r3 = r5.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00c0, code lost:
        r16 = r0.get().booleanValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00cb, code lost:
        r4 = new X.AnonymousClass1QL(r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r15 = r3.A00.registerReceiver(null, new android.content.IntentFilter("android.intent.action.BATTERY_CHANGED"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00df, code lost:
        if (r15 != null) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e1, code lost:
        r12 = X.AnonymousClass1QP.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00e4, code lost:
        r3 = r15.getIntExtra("status", -1);
        r13 = true;
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ee, code lost:
        if (r3 == 2) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00f0, code lost:
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00f2, code lost:
        if (r3 != 5) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f5, code lost:
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00f6, code lost:
        r3 = r15.getIntExtra("level", -1);
        r0 = r15.getIntExtra("scale", -1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0102, code lost:
        if (r3 == -1) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0104, code lost:
        if (r0 == -1) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0106, code lost:
        r12 = new X.AnonymousClass1QL(new X.AnonymousClass23Q(r12, r13, X.AnonymousClass1QO.A00(java.lang.Integer.valueOf((int) ((((float) r3) / ((float) r0)) * 100.0f)))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0120, code lost:
        r12 = new X.AnonymousClass1QL(new X.AnonymousClass23Q(r12, r13, X.AnonymousClass1QP.A00));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x012d, code lost:
        r12 = X.AnonymousClass1QP.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        r4 = r21.A0B;
        ((X.AnonymousClass1Q3) r4.A04(X.AnonymousClass1QB.class)).A02(X.AnonymousClass1QC.LastDisconnectReason, r22.name());
        r4.A00.A02.set(android.os.SystemClock.elapsedRealtime());
        ((java.util.concurrent.atomic.AtomicLong) ((X.AnonymousClass1Q3) r4.A04(X.AnonymousClass1Q5.class)).A00(X.AnonymousClass1QD.MqttTotalDurationMs)).addAndGet(android.os.SystemClock.elapsedRealtime() - r21.A0U);
        r5 = r21.A09;
        r22 = r21.A00(r21.A0P);
        r21 = r21.A00(r21.A0T);
        r20 = r21.A00(r21.A0S);
        r19 = r21.A00(r21.A0R);
        r18 = X.AnonymousClass1QO.A00(r22.toString());
        r17 = X.AnonymousClass1QO.A00(r23.toString());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A02(X.AnonymousClass22J r21, X.EnumC141822g r22, @javax.annotation.Nullable X.EnumC142922r r23, java.lang.Throwable r24) {
        /*
        // Method dump skipped, instructions count: 595
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass22J.A02(X.22J, X.22g, X.22r, java.lang.Throwable):void");
    }

    public final boolean A04() {
        if (this.A0X == AnonymousClass007.A03) {
            return true;
        }
        return false;
    }

    public final boolean A05() {
        Integer num = this.A0X;
        if (num == AnonymousClass007.A03 || num == AnonymousClass007.A00 || num == AnonymousClass007.A01) {
            return true;
        }
        return false;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("[MqttClient (");
        AnonymousClass22T r1 = this.A0F;
        sb.append(r1.A00);
        sb.append(":");
        sb.append(this.A00);
        if (r1.A0K) {
            sb.append(" +ssl");
        }
        sb.append(") ");
        Integer num = this.A0X;
        if (num != null) {
            str = AnonymousClass1Wh.A00(num);
        } else {
            str = "null";
        }
        sb.append(str);
        sb.append("]");
        return sb.toString();
    }

    private AnonymousClass1QO<Long> A00(long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j > elapsedRealtime) {
            return AnonymousClass1QP.A00;
        }
        return AnonymousClass1QO.A00(Long.valueOf(elapsedRealtime - j));
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1QS;LX/23n;LX/1QK;LX/1QF;LX/22T;Lcom/facebook/rti/common/time/MonotonicClock;Ljava/util/concurrent/ExecutorService;LX/1Vm;Lcom/facebook/rti/common/fbtrace/FbTraceLogger;Lcom/facebook/rti/mqtt/protocol/MqttClientTopicSubscriptionCallback;Lcom/facebook/rti/mqtt/protocol/lifecycle/MqttLifeCycleListener;Lcom/facebook/rti/mqtt/credentials/MqttCredentials;LX/1PK;Lcom/facebook/rti/mqtt/protocol/MqttClientCore;LX/1QM<Ljava/lang/String;>;Lcom/facebook/rtc/helpers/signalingflowloggerinterface/IRtcSignalingFlowLogger;Ljava/util/concurrent/atomic/AtomicReference<Ljava/lang/Integer;>;LX/1QM<Ljava/lang/Boolean;>;ZZZZLjava/lang/Long;)V */
    public AnonymousClass22J(AnonymousClass1QS r5, C144923n r6, AnonymousClass1QK r7, AnonymousClass1QF r8, AnonymousClass22T r9, RealtimeSinceBootClock realtimeSinceBootClock, ExecutorService executorService, AnonymousClass1Vm r12, C145723v r13, AnonymousClass1Jf r14, AnonymousClass244 r15, C144423g r16, AnonymousClass1PK r17, AnonymousClass22M r18, AnonymousClass1QM r19, @Nullable AnonymousClass1QM r20, @Nullable boolean z) {
        String str;
        boolean z2 = false;
        this.A0I = new AtomicInteger(0);
        this.A0C = r5;
        this.A07 = r6;
        this.A09 = r7;
        this.A0B = r8;
        this.A0F = r9;
        this.A04 = realtimeSinceBootClock;
        this.A0H = executorService;
        this.A0A = r12;
        this.A03 = r13;
        this.A0M = r14;
        this.A0N = r15;
        this.A02 = r16;
        this.A08 = r17;
        this.A0D = r18;
        this.A06 = r19;
        C141922h r2 = this.A0E;
        AnonymousClass247 r0 = this.A0L;
        r18.A0F = r2;
        r18.A0E = r0;
        if ("".equals(this.A02.A00) && (str = this.A0F.A0F) != null && "".equals(str)) {
            z2 = true;
        }
        this.A0K = z2;
        this.A05 = r20;
        this.A0J = z;
    }
}
