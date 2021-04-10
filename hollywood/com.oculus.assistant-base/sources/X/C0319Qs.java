package X;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import com.facebook.acra.config.StartupBlockingConfig;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zaaa;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.Qs  reason: case insensitive filesystem */
public final class C0319Qs implements Handler.Callback {
    public static C0319Qs A0E;
    public static final Status A0F = new Status(4, "Sign-out occurred while this API call was in progress.");
    public static final Status A0G = new Status(4, "The user must be signed in to make this API call.");
    public static final Object A0H = new Object();
    public long A00 = StartupBlockingConfig.BLOCKING_UPLOAD_MAX_WAIT_MILLIS;
    public AbstractC0328Ri A01;
    public boolean A02 = false;
    public zaaa A03;
    public final Context A04;
    public final Handler A05;
    public final GoogleApiAvailability A06;
    public final C0332Rm A07;
    public final Map A08 = new ConcurrentHashMap(5, 0.75f, 1);
    public final Set A09 = new C00010d();
    public final AtomicInteger A0A = new AtomicInteger(1);
    public final AtomicInteger A0B = new AtomicInteger(0);
    public final Set A0C = new C00010d();
    public volatile boolean A0D = true;

    public static Status A00(C0315Qm qm, ConnectionResult connectionResult) {
        String str = qm.A00.A01;
        String valueOf = String.valueOf(connectionResult);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 63 + valueOf.length());
        sb.append("API: ");
        sb.append(str);
        sb.append(" is not available on this device. Connection failed with: ");
        sb.append(valueOf);
        return new Status(1, 17, sb.toString(), connectionResult.A01, connectionResult);
    }

    private final C0675ex A01(C0310Qg qg) {
        C0315Qm qm = qg.A04;
        Map map = this.A08;
        C0675ex exVar = (C0675ex) map.get(qm);
        if (exVar == null) {
            exVar = new C0675ex(this, qg);
            map.put(qm, exVar);
        }
        if (exVar.A03.A4r()) {
            this.A0C.add(qm);
        }
        exVar.A0A();
        return exVar;
    }

    public static C0319Qs A02(Context context) {
        C0319Qs qs;
        synchronized (A0H) {
            qs = A0E;
            if (qs == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                qs = new C0319Qs(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.A00);
                A0E = qs;
            }
        }
        return qs;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        if (r0 != 0) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A03() {
        /*
            r4 = this;
            com.google.android.gms.common.internal.zaaa r3 = r4.A03
            if (r3 == 0) goto L_0x0042
            int r0 = r3.A01
            if (r0 > 0) goto L_0x002f
            boolean r0 = r4.A02
            if (r0 != 0) goto L_0x003f
            java.lang.Class<X.Rb> r1 = X.C0324Rb.class
            monitor-enter(r1)
            X.Rb r0 = X.C0324Rb.A00     // Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x001e
            X.Rb r0 = new X.Rb     // Catch:{ all -> 0x001b }
            r0.<init>()     // Catch:{ all -> 0x001b }
            X.C0324Rb.A00 = r0     // Catch:{ all -> 0x001b }
            goto L_0x001e
        L_0x001b:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x001e:
            monitor-exit(r1)
            X.Rm r0 = r4.A07
            r2 = 203390000(0xc1f7c30, float:1.2286286E-31)
            android.util.SparseIntArray r0 = r0.A01
            r1 = -1
            int r0 = r0.get(r2, r1)
            if (r0 == r1) goto L_0x002f
            if (r0 != 0) goto L_0x003f
        L_0x002f:
            X.Ri r1 = r4.A01
            if (r1 != 0) goto L_0x003c
            android.content.Context r0 = r4.A04
            X.sV r1 = new X.sV
            r1.<init>(r0)
            r4.A01 = r1
        L_0x003c:
            r1.A62(r3)
        L_0x003f:
            r0 = 0
            r4.A03 = r0
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0319Qs.A03():void");
    }

    public final boolean A04(ConnectionResult connectionResult, int i) {
        Intent A022;
        PendingIntent activity;
        GoogleApiAvailability googleApiAvailability = this.A06;
        Context context = this.A04;
        int i2 = connectionResult.A00;
        if ((i2 == 0 || (activity = connectionResult.A01) == null) && ((A022 = googleApiAvailability.A02(context, i2, null)) == null || (activity = PendingIntent.getActivity(context, 0, A022, 134217728)) == null)) {
            return false;
        }
        Intent intent = new Intent(context, GoogleApiActivity.class);
        intent.putExtra("pending_intent", activity);
        intent.putExtra("failing_client_id", i);
        intent.putExtra("notify_manager", true);
        GoogleApiAvailability.A00(googleApiAvailability, context, i2, PendingIntent.getActivity(context, 0, intent, 134217728));
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0184, code lost:
        if (r1.A01 != false) goto L_0x0186;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r12) {
        /*
        // Method dump skipped, instructions count: 1022
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0319Qs.handleMessage(android.os.Message):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x005a, code lost:
        if (r1.hasSystemFeature("android.hardware.type.automotive") == false) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0319Qs(android.content.Context r6, android.os.Looper r7, com.google.android.gms.common.GoogleApiAvailability r8) {
        /*
        // Method dump skipped, instructions count: 118
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0319Qs.<init>(android.content.Context, android.os.Looper, com.google.android.gms.common.GoogleApiAvailability):void");
    }
}
