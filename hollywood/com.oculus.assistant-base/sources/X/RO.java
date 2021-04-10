package X;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.IGmsServiceBroker;
import com.google.android.gms.common.internal.zzc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class RO {
    public static final Feature[] A0O = new Feature[0];
    public static final String[] A0P = {"service_esmobile", "service_googleme"};
    public int A00 = 1;
    public ConnectionResult A01 = null;
    public RK A02;
    public IGmsServiceBroker A03;
    public AtomicInteger A04 = new AtomicInteger(0);
    public boolean A05 = false;
    public IInterface A06;
    public RN A07;
    public C0341Ry A08;
    public final Context A09;
    public final Handler A0A;
    public final RI A0B;
    public final RJ A0C;
    public final Object A0D = new Object();
    public final Object A0E = new Object();
    public final String A0F;
    public final ArrayList A0G = new ArrayList();
    public final int A0H;
    public final Looper A0I;
    public final QT A0J;
    public final RS A0K;
    public volatile zzc A0L = null;
    public volatile String A0M;
    public volatile String A0N = null;

    public static final void A00(RO ro, int i, IInterface iInterface) {
        String str;
        boolean z;
        boolean z2;
        String str2;
        C0341Ry ry;
        boolean z3 = false;
        if (i == 4) {
            z3 = true;
        }
        boolean z4 = false;
        if (iInterface != null) {
            z4 = true;
        }
        if (z3 == z4) {
            synchronized (ro.A0D) {
                ro.A00 = i;
                ro.A06 = iInterface;
                if (i == 1) {
                    RN rn = ro.A07;
                    if (rn != null) {
                        RS rs = ro.A0K;
                        C0341Ry ry2 = ro.A08;
                        String str3 = ry2.A01;
                        RZ.A01(str3);
                        String str4 = ry2.A02;
                        int i2 = ry2.A00;
                        String str5 = ro.A0F;
                        if (str5 == null) {
                            str5 = ro.A09.getClass().getName();
                        }
                        rs.A00(new RR(str3, str4, i2, ry2.A03), rn, str5);
                        ro.A07 = null;
                    }
                } else if (i == 2 || i == 3) {
                    RN rn2 = ro.A07;
                    if (!(rn2 == null || (ry = ro.A08) == null)) {
                        String str6 = ry.A01;
                        String str7 = ry.A02;
                        StringBuilder sb = new StringBuilder(String.valueOf(str6).length() + 70 + String.valueOf(str7).length());
                        sb.append("Calling connect() while still connected, missing disconnect() for ");
                        sb.append(str6);
                        sb.append(" on ");
                        sb.append(str7);
                        Log.e("GmsClient", sb.toString());
                        RS rs2 = ro.A0K;
                        C0341Ry ry3 = ro.A08;
                        String str8 = ry3.A01;
                        RZ.A01(str8);
                        String str9 = ry3.A02;
                        int i3 = ry3.A00;
                        String str10 = ro.A0F;
                        if (str10 == null) {
                            str10 = ro.A09.getClass().getName();
                        }
                        rs2.A00(new RR(str8, str9, i3, ry3.A03), rn2, str10);
                        ro.A04.incrementAndGet();
                    }
                    RN rn3 = new RN(ro, ro.A04.get());
                    ro.A07 = rn3;
                    if (!(ro instanceof C0643dj)) {
                        str = "com.google.android.gms.common.telemetry.service.START";
                    } else {
                        str = "com.google.android.gms.signin.service.START";
                    }
                    if (!(ro instanceof C0654dz)) {
                        z = false;
                    } else {
                        z = true;
                    }
                    C0341Ry ry4 = new C0341Ry(str, z);
                    ro.A08 = ry4;
                    boolean z5 = ry4.A03;
                    if (!z5 || ro.A2g() >= 17895000) {
                        RS rs3 = ro.A0K;
                        String str11 = ry4.A01;
                        RZ.A01(str11);
                        String str12 = ry4.A02;
                        int i4 = ry4.A00;
                        String str13 = ro.A0F;
                        if (str13 == null) {
                            str13 = ro.A09.getClass().getName();
                        }
                        RR rr = new RR(str11, str12, i4, z5);
                        C1103sc scVar = (C1103sc) rs3;
                        RZ.A02(rn3, "ServiceConnection must not be null");
                        HashMap hashMap = scVar.A03;
                        synchronized (hashMap) {
                            ServiceConnectionC1104sd sdVar = (ServiceConnectionC1104sd) hashMap.get(rr);
                            if (sdVar == null) {
                                sdVar = new ServiceConnectionC1104sd(scVar, rr);
                                sdVar.A05.put(rn3, rn3);
                                sdVar.A00(str13);
                                hashMap.put(rr, sdVar);
                            } else {
                                scVar.A01.removeMessages(0, rr);
                                Map map = sdVar.A05;
                                if (!map.containsKey(rn3)) {
                                    map.put(rn3, rn3);
                                    int i5 = sdVar.A00;
                                    if (i5 == 1) {
                                        rn3.onServiceConnected(sdVar.A01, sdVar.A02);
                                    } else if (i5 == 2) {
                                        sdVar.A00(str13);
                                    }
                                } else {
                                    String valueOf = String.valueOf(rr);
                                    StringBuilder sb2 = new StringBuilder(valueOf.length() + 81);
                                    sb2.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                                    sb2.append(valueOf);
                                    throw new IllegalStateException(sb2.toString());
                                }
                            }
                            z2 = sdVar.A03;
                        }
                        if (!z2) {
                            C0341Ry ry5 = ro.A08;
                            String str14 = ry5.A01;
                            String str15 = ry5.A02;
                            StringBuilder sb3 = new StringBuilder(String.valueOf(str14).length() + 34 + String.valueOf(str15).length());
                            sb3.append("unable to connect to service: ");
                            sb3.append(str14);
                            sb3.append(" on ");
                            sb3.append(str15);
                            Log.e("GmsClient", sb3.toString());
                            int i6 = ro.A04.get();
                            Handler handler = ro.A0A;
                            handler.sendMessage(handler.obtainMessage(7, i6, -1, new GG(ro, 16)));
                        }
                    } else {
                        String valueOf2 = String.valueOf(ry4.A01);
                        if (valueOf2.length() != 0) {
                            str2 = "Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: ".concat(valueOf2);
                        } else {
                            str2 = new String("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: ");
                        }
                        throw new IllegalStateException(str2);
                    }
                } else if (i == 4) {
                    RZ.A01(iInterface);
                    System.currentTimeMillis();
                }
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0093, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0094, code lost:
        android.util.Log.w("GmsClient", "IGmsServiceBroker.getService failed", r2);
        r4 = r5.A04.get();
        r3 = r5.A0A;
        r3.sendMessage(r3.obtainMessage(1, r4, -1, new X.Gy(r5, 8, null, null)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b4, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A2o(com.google.android.gms.common.internal.IAccountAccessor r6, java.util.Set r7) {
        /*
        // Method dump skipped, instructions count: 209
        */
        throw new UnsupportedOperationException("Method not decompiled: X.RO.A2o(com.google.android.gms.common.internal.IAccountAccessor, java.util.Set):void");
    }

    public final boolean A4q() {
        return true;
    }

    public boolean A4r() {
        return false;
    }

    public static final boolean A01(RO ro, int i, int i2, IInterface iInterface) {
        boolean z;
        synchronized (ro.A0D) {
            if (ro.A00 != i) {
                z = false;
            } else {
                A00(ro, i2, iInterface);
                z = true;
            }
        }
        return z;
    }

    public final IInterface A02() {
        IInterface iInterface;
        synchronized (this.A0D) {
            if (this.A00 == 5) {
                throw new DeadObjectException();
            } else if (isConnected()) {
                iInterface = this.A06;
                RZ.A02(iInterface, "Client is connected but service is null");
            } else {
                throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
            }
        }
        return iInterface;
    }

    public final void A1T(RK rk) {
        RZ.A02(rk, "Connection progress callbacks cannot be null.");
        this.A02 = rk;
        A00(this, 2, null);
    }

    public final void A1h() {
        this.A04.incrementAndGet();
        ArrayList arrayList = this.A0G;
        synchronized (arrayList) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                RM rm = (RM) arrayList.get(i);
                synchronized (rm) {
                    rm.A00 = null;
                }
            }
            arrayList.clear();
        }
        synchronized (this.A0E) {
            this.A03 = null;
        }
        A00(this, 1, null);
    }

    public final void A1i(String str) {
        this.A0N = str;
        A1h();
    }

    public final Feature[] A2H() {
        zzc zzc = this.A0L;
        if (zzc == null) {
            return null;
        }
        return zzc.A00;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000a, code lost:
        if (r2 == 3) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A3Q() {
        /*
            r4 = this;
            java.lang.Object r3 = r4.A0D
            monitor-enter(r3)
            int r2 = r4.A00     // Catch:{ all -> 0x000f }
            r0 = 2
            if (r2 == r0) goto L_0x000c
            r1 = 3
            r0 = 0
            if (r2 != r1) goto L_0x000d
        L_0x000c:
            r0 = 1
        L_0x000d:
            monitor-exit(r3)     // Catch:{ all -> 0x000f }
            return r0
        L_0x000f:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x000f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.RO.A3Q():boolean");
    }

    public final boolean isConnected() {
        boolean z;
        synchronized (this.A0D) {
            z = false;
            if (this.A00 == 4) {
                z = true;
            }
        }
        return z;
    }

    public final String A2P() {
        C0341Ry ry;
        if (isConnected() && (ry = this.A08) != null) {
            return ry.A02;
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    public final String A2Y() {
        return this.A0N;
    }

    public int A2g() {
        return 12451000;
    }

    public final void A4P(RL rl) {
        rl.A4L();
    }

    public RO(Context context, Looper looper, RS rs, QT qt, int i, RI ri, RJ rj, String str) {
        RZ.A02(context, "Context must not be null");
        this.A09 = context;
        RZ.A02(looper, "Looper must not be null");
        this.A0I = looper;
        RZ.A02(rs, "Supervisor must not be null");
        this.A0K = rs;
        RZ.A02(qt, "API availability must not be null");
        this.A0J = qt;
        this.A0A = new HandlerC1098sO(this, looper);
        this.A0H = i;
        this.A0B = ri;
        this.A0C = rj;
        this.A0F = str;
    }
}
