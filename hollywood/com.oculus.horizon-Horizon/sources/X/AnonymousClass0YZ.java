package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.facebook.rti.common.util.NonInjectTwoParamsProvider;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import org.json.JSONException;

@VisibleForTesting
@NotThreadSafe
/* renamed from: X.0YZ  reason: invalid class name */
public final class AnonymousClass0YZ {
    public long A00;
    public long A01;
    public BroadcastReceiver A02;
    public BroadcastReceiver A03;
    public Context A04;
    public Handler A05;
    public AnonymousClass0WB A06;
    public RealtimeSinceBootClock A07;
    public C01620We A08;
    public C01630Wg A09;
    public AnonymousClass0Wo A0A;
    public AnonymousClass0Wu A0B;
    public AnonymousClass0X3 A0C;
    public AnonymousClass0XJ A0D;
    public AnonymousClass0XL A0E;
    public AnonymousClass0YF A0F;
    public AnonymousClass0YH A0G;
    public NonInjectTwoParamsProvider<List<SubscribeTopic>, AnonymousClass0ZF> A0H;
    public C01970Yh A0I;
    public C06030mO A0J;
    public AnonymousClass0IR A0K;
    public AnonymousClass0IQ A0L;
    public C05220kZ A0M;
    public C02270a7 A0N;
    public C04910js A0O;
    public C04640ie A0P;
    public C04620ib A0Q;
    public String A0R;
    public Map<String, SubscribeTopic> A0S = new HashMap();
    public Executor A0T;
    public AtomicBoolean A0U = new AtomicBoolean(false);
    public AtomicInteger A0V;
    public boolean A0W;
    public boolean A0X;
    public boolean A0Y = false;
    public long A0Z;
    public Method A0a;
    public long A0b;
    public final C06130mY A0c = new C06130mY(this);
    public final Runnable A0d = new AnonymousClass0YK(this);
    public final Runnable A0e = new AnonymousClass0YM(this);
    public final Runnable A0f = new AnonymousClass0YN(this);
    public final Runnable A0g = new AnonymousClass0YJ(this);
    public final AtomicLong A0h = new AtomicLong();
    public final Object A0i = new Object();
    public volatile long A0j;
    public volatile AnonymousClass0X5 A0k;
    public volatile AnonymousClass0ZF A0l;
    public volatile AnonymousClass0ZF A0m;
    public volatile C05860lz A0n;
    public volatile String A0o;
    public volatile boolean A0p;
    public volatile long A0q;

    /* JADX WARN: Incorrect return type in method signature: (LX/0ZF;LX/0Wk;Lcom/facebook/rti/mqtt/manager/FbnsConnectionManager$DisconnectReason;)Ljava/util/concurrent/Future<*>; */
    public final void A09(AnonymousClass0ZF r5, EnumC01660Wk r6, Integer num) {
        synchronized (this) {
            if (this.A0l == r5) {
                this.A0l = null;
            }
        }
        if (r5 != null) {
            boolean z = false;
            if (r5.A0X == AnonymousClass007.A0D) {
                z = true;
            }
            r5.A0W = null;
            synchronized (r5) {
                AnonymousClass0ZF.A04(r5, r6, AnonymousClass0ZP.DISCONNECT, null);
            }
            System.currentTimeMillis();
            if (z) {
                return;
            }
        }
        A03(this, num, C06530na.A00);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0220, code lost:
        if (r12.equals("") != false) goto L_0x0230;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(X.AnonymousClass0YZ r39) {
        /*
        // Method dump skipped, instructions count: 983
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0YZ.A00(X.0YZ):void");
    }

    public static void A02(AnonymousClass0YZ r4, AnonymousClass0Ws r5, boolean z) {
        String str;
        if (r5 != null) {
            try {
                str = AnonymousClass0Ws.A00(r5, r5.A00).toString();
            } catch (JSONException unused) {
                str = "";
            }
            if (!TextUtils.isEmpty(str)) {
                try {
                    EnumC02170Zn r3 = EnumC02170Zn.ACKNOWLEDGED_DELIVERY;
                    if (z) {
                        r3 = EnumC02170Zn.FIRE_AND_FORGET;
                    }
                    try {
                        r4.A05("/mqtt_health_stats", str.getBytes("UTF-8"), r3, null);
                    } catch (UnsupportedEncodingException unused2) {
                        throw new RuntimeException("UTF-8 not supported");
                    }
                } catch (AnonymousClass0ZK unused3) {
                }
            }
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/facebook/rti/mqtt/manager/FbnsConnectionManager$DisconnectReason;LX/0W8<LX/0Yy;>;)V */
    public static void A03(AnonymousClass0YZ r14, Integer num, AnonymousClass0W8 r16) {
        String str;
        String str2;
        C02270a7 r1;
        long j;
        String obj;
        AnonymousClass0ZF r0 = r14.A0l;
        if (r0 != null) {
            C05890m2 r3 = r0.A0B;
            StringBuilder sb = new StringBuilder();
            InetAddress inetAddress = r3.A04;
            if (inetAddress != null) {
                sb.append("Remote:");
                sb.append(inetAddress.toString());
                sb.append('\n');
            }
            InetAddress inetAddress2 = r3.A03;
            if (inetAddress2 != null) {
                sb.append("Local:");
                sb.append(inetAddress2.toString());
                sb.append('\n');
            }
            AbstractC02050Yt r32 = r3.A0A;
            synchronized (r32) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Cache{");
                Iterator<AnonymousClass0ZS> it = r32.A00.A01().iterator();
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
        C01970Yh r2 = r14.A0I;
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
        AnonymousClass0ZK r11 = new AnonymousClass0ZK(AnonymousClass006.A08("Connection lost ", str2, ", ", str));
        ArrayList arrayList = new ArrayList();
        Map<Integer, C06050mQ> map = r2.A03;
        synchronized (map) {
            arrayList.addAll(map.values());
            map.clear();
        }
        arrayList.size();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            C06050mQ r33 = (C06050mQ) it2.next();
            synchronized (r33) {
                r33.A00 = r11;
            }
            if (r33.A06 != null) {
                r33.A06.cancel(false);
            }
            int i = r33.A01;
            AnonymousClass0ZF r02 = r33.A03;
            if (r02 == null) {
                j = 0;
            } else {
                j = r02.A0U;
            }
            r2.A00.A05("abort", r33.A05, EnumC02170Zn.ACKNOWLEDGED_DELIVERY.getValue(), i, i, r11, j);
        }
        r11.getMessage();
        switch (num.intValue()) {
            case 1:
                r14.A0B.A0D = AnonymousClass007.A0H;
                long j2 = r14.A0b;
                if (j2 <= 0 || (SystemClock.elapsedRealtime() - j2) / 1000 >= ((long) r14.A0C.A00().A0L)) {
                    C02270a7 r12 = r14.A0N;
                    synchronized (r12) {
                        r12.A08 = false;
                    }
                    r1 = r14.A0N;
                    synchronized (r1) {
                        C02270a7.A00(r1);
                    }
                } else {
                    r1 = r14.A0N;
                    synchronized (r1) {
                        r1.A08 = true;
                    }
                }
                break;
            case 0:
                r14.A0N.A02();
                break;
        }
        boolean A022 = r16.A02();
        if (A022) {
            r16.A01();
        }
        AbstractServiceC06020mN r13 = r14.A0J.A00;
        if (A022) {
            r13.A0S((AnonymousClass0Yy) r16.A01());
        }
        AbstractServiceC06020mN.A09(r13);
    }

    public static final void A04(@Nullable AnonymousClass0YZ r13, String str) {
        AnonymousClass0ZF r8 = r13.A0l;
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if ((elapsedRealtime - r13.A0q) / 1000 >= ((long) r13.A0C.A00().A0M)) {
                r13.A0q = elapsedRealtime;
                C02590an.A02.A01 = str;
                if (r13.A0X) {
                    if (r13.A0U.get()) {
                        ((AtomicLong) ((AnonymousClass0nJ) r13.A0B.A06(AnonymousClass0IW.class)).A00(AnonymousClass0nF.ForegroundPing)).incrementAndGet();
                    } else {
                        ((AtomicLong) ((AnonymousClass0nJ) r13.A0B.A06(AnonymousClass0IW.class)).A00(AnonymousClass0nF.BackgroundPing)).incrementAndGet();
                    }
                    if (r8 != null && r8.A05()) {
                        A02(r13, r13.A0B.A05(SystemClock.elapsedRealtime() - r8.A0U), false);
                    }
                } else if (r8 != null && r8.A05()) {
                    r13.A0I.A00(r8, "callPing", EnumC02120Zg.PINGRESP, -1, r13.A0C.A00().A0I);
                    synchronized (r8) {
                        if (r8.A05()) {
                            r8.A0G.execute(new AnonymousClass0ZB(r8));
                        } else {
                            throw new AnonymousClass0ZK(AnonymousClass007.A00);
                        }
                    }
                }
            }
        } catch (AnonymousClass0ZK e) {
            AnonymousClass0NO.A0H("FbnsConnectionManager", e, "exception/send_keepalive");
            r13.A09(r8, EnumC01660Wk.SEND_FAILURE, AnonymousClass007.A01);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0042 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0108  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A05(java.lang.String r25, byte[] r26, X.EnumC02170Zn r27, @javax.annotation.Nullable X.AnonymousClass0ZO r28) throws X.AnonymousClass0ZK {
        /*
        // Method dump skipped, instructions count: 305
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0YZ.A05(java.lang.String, byte[], X.0Zn, X.0ZO):int");
    }

    public final void A06() {
        this.A0G.A00();
        if (!this.A0W) {
            this.A0F.A04();
        } else {
            this.A0G.A01();
        }
    }

    public final void A07() {
        int i;
        boolean z = this.A0U.get();
        AnonymousClass0X5 A002 = this.A0O.A00.A00();
        if (z) {
            i = A002.A0C;
        } else {
            i = A002.A06;
        }
        if (this.A0V.getAndSet(i) != i) {
            A06();
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0Wk;)Ljava/util/concurrent/Future<*>; */
    public final void A08(EnumC01660Wk r3) {
        C02270a7 r1 = this.A0N;
        synchronized (r1) {
            C02270a7.A00(r1);
        }
        A09(this.A0l, r3, AnonymousClass007.A0C);
    }

    @VisibleForTesting
    public final void A0A(Integer num) {
        Integer num2;
        AnonymousClass0ZF r2 = this.A0l;
        if (AnonymousClass007.A08.equals(num)) {
            num2 = AnonymousClass007.A0C;
        } else if (!this.A0J.A00.A0W()) {
            A08(EnumC01660Wk.KICK_SHOULD_NOT_CONNECT);
            return;
        } else {
            if (r2 != null) {
                if (!r2.A06()) {
                    num2 = AnonymousClass007.A0D;
                } else {
                    return;
                }
            }
            A00(this);
        }
        A09(r2, EnumC01660Wk.EXPIRE_CONNECTION, num2);
        A00(this);
    }

    public final void A0B(Integer num) {
        AnonymousClass0Y2 r0;
        if (!this.A0W) {
            AnonymousClass0YF r1 = this.A0F;
            synchronized (r1) {
                if (!r1.A03) {
                    r1.A04();
                } else {
                    SystemClock.elapsedRealtime();
                }
            }
        } else {
            AnonymousClass0YH r12 = this.A0G;
            synchronized (r12) {
                if (!r12.A00) {
                    r12.A01();
                }
            }
        }
        if (!this.A0J.A00.A0W()) {
            A08(EnumC01660Wk.KICK_SHOULD_NOT_CONNECT);
            return;
        }
        AnonymousClass0ZF r2 = this.A0l;
        if (r2 == null || !r2.A05()) {
            AnonymousClass0ZF r02 = this.A0l;
            if (r02 != null && r02.A06()) {
                return;
            }
        } else {
            C04620ib r13 = this.A0Q;
            synchronized (r13) {
                r0 = r13.A00;
            }
            if (!((String) r0.first).equals(r2.A0Y)) {
                A08(EnumC01660Wk.AUTH_CREDENTIALS_CHANGE);
            } else if (this.A01 <= this.A0b) {
                if (num.equals(AnonymousClass007.A08)) {
                    this.A0B.A0D = AnonymousClass007.A0G;
                    C02270a7 r22 = this.A0N;
                    synchronized (r22) {
                        if (r22.A05 == null) {
                            AnonymousClass0NO.A09("ConnectionRetryManager", "No force reconnect runnable set. Completing early from kickNow()");
                        } else {
                            Handler handler = r22.A0A;
                            if (handler == null || handler.getLooper().getThread() != Thread.currentThread()) {
                                r22.A0C.submit(r22.A05);
                            } else {
                                r22.A05.run();
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }
        A07();
        this.A0B.A0D = num;
        C02270a7 r14 = this.A0N;
        synchronized (r14) {
            C02270a7.A00(r14);
            r14.A02();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0123, code lost:
        if (r0.isConnected() == false) goto L_0x0125;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A01(X.AnonymousClass0YZ r7, android.content.Intent r8) {
        /*
        // Method dump skipped, instructions count: 422
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0YZ.A01(X.0YZ, android.content.Intent):void");
    }
}
