package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.facebook.rti.common.util.NonInjectTwoParamsProvider;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import com.facebook.rti.push.service.FbnsService;
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
/* renamed from: X.0wy  reason: invalid class name and case insensitive filesystem */
public final class C08290wy {
    public long A00;
    public long A01;
    public BroadcastReceiver A02;
    public BroadcastReceiver A03;
    public Context A04;
    public Handler A05;
    public AbstractC09080yd A06;
    public RealtimeSinceBootClock A07;
    public C07590vY A08;
    public C08800xq A09;
    public AnonymousClass0x2 A0A;
    public C08310x0 A0B;
    public AnonymousClass0v4 A0C;
    public C08400xB A0D;
    public C08270ww A0E;
    public C08200wo A0F;
    public C08120wb A0G;
    public NonInjectTwoParamsProvider<List<SubscribeTopic>, C08300wz> A0H;
    public C08370x7 A0I;
    public AnonymousClass105 A0J;
    public C07810w3 A0K;
    public C07740vv A0L;
    public AnonymousClass0vc A0M;
    public C08330x3 A0N;
    public C07540vS A0O;
    public C07880wA A0P;
    public C07750vw A0Q;
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
    public final AnonymousClass106 A0c = new AnonymousClass106(this);
    public final Runnable A0d = new RunnableC09330zF(this);
    public final Runnable A0e = new RunnableC08490xK(this);
    public final Runnable A0f = new RunnableC09160yl(this);
    public final Runnable A0g = new AnonymousClass0z7(this);
    public final AtomicLong A0h = new AtomicLong();
    public final Object A0i = new Object();
    public volatile long A0j;
    public volatile C08460xH A0k;
    public volatile C08300wz A0l;
    public volatile C08300wz A0m;
    public volatile AnonymousClass0vN A0n;
    public volatile String A0o;
    public volatile boolean A0p;
    public volatile long A0q;

    /* JADX WARN: Incorrect return type in method signature: (LX/0wz;LX/0xi;Lcom/facebook/rti/mqtt/manager/FbnsConnectionManager$DisconnectReason;)Ljava/util/concurrent/Future<*>; */
    public final void A09(C08300wz r5, EnumC08720xi r6, Integer num) {
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
                C08300wz.A04(r5, r6, AnonymousClass0y3.DISCONNECT, null);
            }
            System.currentTimeMillis();
            if (z) {
                return;
            }
        }
        A03(this, num, C09340zG.A00);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:58:0x021b, code lost:
        if (r12.equals("") != false) goto L_0x022b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(X.C08290wy r40) {
        /*
        // Method dump skipped, instructions count: 983
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08290wy.A00(X.0wy):void");
    }

    public static void A02(C08290wy r4, C08550xR r5, boolean z) {
        String str;
        if (r5 != null) {
            try {
                str = C08550xR.A00(r5, r5.A00).toString();
            } catch (JSONException unused) {
                str = "";
            }
            if (!TextUtils.isEmpty(str)) {
                try {
                    EnumC08980yG r3 = EnumC08980yG.ACKNOWLEDGED_DELIVERY;
                    if (z) {
                        r3 = EnumC08980yG.FIRE_AND_FORGET;
                    }
                    try {
                        r4.A05("/mqtt_health_stats", str.getBytes("UTF-8"), r3, null);
                    } catch (UnsupportedEncodingException unused2) {
                        throw new RuntimeException("UTF-8 not supported");
                    }
                } catch (C09540zd unused3) {
                }
            }
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/facebook/rti/mqtt/manager/FbnsConnectionManager$DisconnectReason;LX/0yk<LX/0xw;>;)V */
    public static void A03(C08290wy r14, Integer num, AbstractC09150yk r16) {
        String str;
        String str2;
        C08330x3 r1;
        long j;
        String sb;
        C08300wz r0 = r14.A0l;
        if (r0 != null) {
            C08610xX r3 = r0.A0B;
            StringBuilder sb2 = new StringBuilder();
            InetAddress inetAddress = r3.A04;
            if (inetAddress != null) {
                sb2.append("Remote:");
                sb2.append(inetAddress.toString());
                sb2.append('\n');
            }
            InetAddress inetAddress2 = r3.A03;
            if (inetAddress2 != null) {
                sb2.append("Local:");
                sb2.append(inetAddress2.toString());
                sb2.append('\n');
            }
            AbstractC08480xJ r32 = r3.A0A;
            synchronized (r32) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Cache{");
                Iterator<C08440xF> it = r32.A00.A01().iterator();
                while (it.hasNext()) {
                    sb3.append(it.next().toString());
                    sb3.append(',');
                }
                sb3.append("}\n");
                sb = sb3.toString();
            }
            sb2.append(sb);
            str = sb2.toString();
        } else {
            str = "";
        }
        C08370x7 r2 = r14.A0I;
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
        C09540zd r11 = new C09540zd(AnonymousClass006.A08("Connection lost ", str2, ", ", str));
        ArrayList<C08510xM> arrayList = new ArrayList();
        Map<Integer, C08510xM> map = r2.A03;
        synchronized (map) {
            arrayList.addAll(map.values());
            map.clear();
        }
        arrayList.size();
        for (C08510xM r33 : arrayList) {
            synchronized (r33) {
                r33.A00 = r11;
            }
            if (r33.A06 != null) {
                r33.A06.cancel(false);
            }
            int i = r33.A01;
            C08300wz r02 = r33.A03;
            if (r02 == null) {
                j = 0;
            } else {
                j = r02.A0U;
            }
            r2.A00.A05("abort", r33.A05, EnumC08980yG.ACKNOWLEDGED_DELIVERY.getValue(), i, i, r11, j);
        }
        r11.getMessage();
        switch (num.intValue()) {
            case 1:
                r14.A0B.A0D = AnonymousClass007.A0H;
                long j2 = r14.A0b;
                if (j2 <= 0 || (SystemClock.elapsedRealtime() - j2) / 1000 >= ((long) ((AnonymousClass0v5) r14.A0C).A01.A0L)) {
                    C08330x3 r12 = r14.A0N;
                    synchronized (r12) {
                        r12.A08 = false;
                    }
                    r1 = r14.A0N;
                    synchronized (r1) {
                        C08330x3.A00(r1);
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
        AbstractServiceC08280wx r6 = r14.A0J.A00;
        if (A022) {
            EnumC08860xw r13 = (EnumC08860xw) r16.A01();
            if (r6 instanceof FbnsService) {
                FbnsService fbnsService = (FbnsService) r6;
                if (EnumC08860xw.FAILED_CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD.equals(r13)) {
                    if (System.currentTimeMillis() - fbnsService.A06.A00.A00(EnumC07690vn.FBNS_STATE).A3x("auto_reg_retry", 0) > 86400000) {
                        C07720vq A2E = fbnsService.A06.A00.A00(EnumC07690vn.FBNS_STATE).A2E();
                        A2E.A00.putLong("auto_reg_retry", System.currentTimeMillis());
                        A2E.A00();
                        List<AnonymousClass0un> A032 = fbnsService.A06.A03();
                        fbnsService.A06.A04();
                        fbnsService.A01.A02(AnonymousClass007.A06, String.valueOf(A032.size()));
                        for (AnonymousClass0un r34 : A032) {
                            Intent intent = new Intent("com.facebook.rti.fbns.intent.REGISTER");
                            intent.putExtra("pkg_name", r34.A02);
                            intent.putExtra("appid", r34.A01);
                            intent.setClassName(fbnsService.getPackageName(), fbnsService.getClass().getName());
                            FbnsService.A03(fbnsService, intent);
                        }
                    }
                }
            }
        }
        AbstractServiceC08280wx.A08(r6);
    }

    public static final void A04(@Nullable C08290wy r13, String str) {
        C08300wz r8 = r13.A0l;
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if ((elapsedRealtime - r13.A0q) / 1000 >= ((long) ((AnonymousClass0v5) r13.A0C).A01.A0M)) {
                r13.A0q = elapsedRealtime;
                AnonymousClass10A.A02.A01 = str;
                if (r13.A0X) {
                    if (r13.A0U.get()) {
                        ((AtomicLong) ((AbstractC08430xE) r13.A0B.A06(C08560xS.class)).A00(EnumC08570xT.ForegroundPing)).incrementAndGet();
                    } else {
                        ((AtomicLong) ((AbstractC08430xE) r13.A0B.A06(C08560xS.class)).A00(EnumC08570xT.BackgroundPing)).incrementAndGet();
                    }
                    if (r8 != null && r8.A05()) {
                        A02(r13, r13.A0B.A05(SystemClock.elapsedRealtime() - r8.A0U), false);
                    }
                } else if (r8 != null && r8.A05()) {
                    r13.A0I.A00(r8, "callPing", EnumC08830xt.PINGRESP, -1, ((AnonymousClass0v5) r13.A0C).A01.A0I);
                    synchronized (r8) {
                        if (r8.A05()) {
                            r8.A0G.execute(new RunnableC08750xl(r8));
                        } else {
                            throw new C09540zd(AnonymousClass007.A00);
                        }
                    }
                }
            }
        } catch (C09540zd e) {
            AnonymousClass0NK.A09("FbnsConnectionManager", e, "exception/send_keepalive");
            r13.A09(r8, EnumC08720xi.SEND_FAILURE, AnonymousClass007.A01);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0042 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0108  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A05(java.lang.String r25, byte[] r26, X.EnumC08980yG r27, @javax.annotation.Nullable X.AnonymousClass10E r28) throws X.C09540zd {
        /*
        // Method dump skipped, instructions count: 305
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08290wy.A05(java.lang.String, byte[], X.0yG, X.10E):int");
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
        C08460xH r0 = ((AnonymousClass0v5) this.A0O.A00).A01;
        if (z) {
            i = r0.A0C;
        } else {
            i = r0.A06;
        }
        if (this.A0V.getAndSet(i) != i) {
            A06();
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0xi;)Ljava/util/concurrent/Future<*>; */
    public final void A08(EnumC08720xi r3) {
        C08330x3 r1 = this.A0N;
        synchronized (r1) {
            C08330x3.A00(r1);
        }
        A09(this.A0l, r3, AnonymousClass007.A0C);
    }

    @VisibleForTesting
    public final void A0A(Integer num) {
        Integer num2;
        C08300wz r2 = this.A0l;
        if (AnonymousClass007.A08.equals(num)) {
            num2 = AnonymousClass007.A0C;
        } else if (!this.A0J.A00.A0P()) {
            A08(EnumC08720xi.KICK_SHOULD_NOT_CONNECT);
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
        A09(r2, EnumC08720xi.EXPIRE_CONNECTION, num2);
        A00(this);
    }

    public final void A0B(Integer num) {
        C07760vx r0;
        if (!this.A0W) {
            C08200wo r1 = this.A0F;
            synchronized (r1) {
                if (!r1.A03) {
                    r1.A04();
                } else {
                    SystemClock.elapsedRealtime();
                }
            }
        } else {
            C08120wb r12 = this.A0G;
            synchronized (r12) {
                if (!r12.A00) {
                    r12.A01();
                }
            }
        }
        if (!this.A0J.A00.A0P()) {
            A08(EnumC08720xi.KICK_SHOULD_NOT_CONNECT);
            return;
        }
        C08300wz r2 = this.A0l;
        if (r2 == null || !r2.A05()) {
            C08300wz r02 = this.A0l;
            if (r02 != null && r02.A06()) {
                return;
            }
        } else {
            C07750vw r13 = this.A0Q;
            synchronized (r13) {
                r0 = r13.A00;
            }
            if (!((String) r0.first).equals(r2.A0Y)) {
                A08(EnumC08720xi.AUTH_CREDENTIALS_CHANGE);
            } else if (this.A01 <= this.A0b) {
                if (num.equals(AnonymousClass007.A08)) {
                    this.A0B.A0D = AnonymousClass007.A0G;
                    C08330x3 r22 = this.A0N;
                    synchronized (r22) {
                        if (r22.A05 == null) {
                            AnonymousClass0NK.A02("ConnectionRetryManager", "No force reconnect runnable set. Completing early from kickNow()");
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
        C08330x3 r14 = this.A0N;
        synchronized (r14) {
            C08330x3.A00(r14);
            r14.A02();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011e, code lost:
        if (r0.isConnected() == false) goto L_0x0120;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A01(X.C08290wy r7, android.content.Intent r8) {
        /*
        // Method dump skipped, instructions count: 409
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08290wy.A01(X.0wy, android.content.Intent):void");
    }
}
