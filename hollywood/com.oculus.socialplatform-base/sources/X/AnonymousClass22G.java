package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import com.oculus.messengervr.fb.$$Lambda$VrMsysMqttClientCallbacks$O_Zp9F_wgej5LDIVLNVImUs2JG02;
import com.oculus.messengervr.fb.$$Lambda$VrMsysMqttClientCallbacks$YQAzKNR0OPr5z4Ya6bxvedcq9Ws2;
import com.oculus.messengervr.fb.$$Lambda$VrMsysMqttClientCallbacks$fDCk4RJ2sVQX3AFy96f42X6u_uY2;
import com.oculus.messengervr.fb.VrMsysMqttClientCallbacks;
import java.util.HashMap;
import javax.annotation.Nullable;
import org.json.JSONException;

/* renamed from: X.22G  reason: invalid class name */
public interface AnonymousClass22G extends AbstractC141321x {
    public Context A00;
    public Handler A01;
    @VisibleForTesting
    public HandlerThread A02;
    @VisibleForTesting
    public C142422m A03;
    @VisibleForTesting
    public AnonymousClass1YE A04;
    @VisibleForTesting
    public C144423g A05;
    public AnonymousClass1Vm A06;
    public AnonymousClass1QF A07;
    @VisibleForTesting
    public AnonymousClass22Y A08;
    @VisibleForTesting
    public AnonymousClass1EW A09;
    @VisibleForTesting
    public AnonymousClass22H A0A;
    @VisibleForTesting
    @Nullable
    public $$Lambda$VrMsysMqttClientCallbacks$O_Zp9F_wgej5LDIVLNVImUs2JG02 A0B;
    @Nullable
    public $$Lambda$VrMsysMqttClientCallbacks$YQAzKNR0OPr5z4Ya6bxvedcq9Ws2 A0C;
    @VisibleForTesting
    @Nullable
    public $$Lambda$VrMsysMqttClientCallbacks$fDCk4RJ2sVQX3AFy96f42X6u_uY2 A0D;
    public VrMsysMqttClientCallbacks.AnonymousClass2 A0E;
    public boolean A0F;
    @Nullable
    public volatile EnumC142522n A0G = null;
    public volatile Integer A0H = AnonymousClass007.A04;
    public volatile boolean A0I;

    private default void A00() {
        if (!this.A0I) {
            throw new RuntimeException("You must call init() before calling this method");
        }
    }

    static default void A01(AnonymousClass22G r5, EnumC141822g r6) {
        if (r5.A0F) {
            r5.A0F = false;
            AnonymousClass22H r4 = r5.A0A;
            r4.A0J.A03();
            r4.A0K.A00();
            AnonymousClass1XQ r3 = r4.A0I;
            boolean equals = r3.A02.getLooper().equals(Looper.myLooper());
            StringBuilder sb = new StringBuilder("ScreenStateListener unregistration should be called on MqttThread. Current Looper:");
            sb.append(Looper.myLooper());
            String obj = sb.toString();
            if (!equals) {
                Log.w(AnonymousClass245.class.getName(), obj);
                if (!AnonymousClass245.A00) {
                    throw new AssertionError(obj);
                }
            }
            try {
                r3.A01.unregisterReceiver(r3.A00);
            } catch (IllegalArgumentException unused) {
            }
            r3.A04.set(null);
            AnonymousClass1QS r2 = r4.A0H;
            AnonymousClass1QX r1 = r4.A0d;
            synchronized (r2) {
                r2.A04.remove(r1);
            }
            BroadcastReceiver broadcastReceiver = r4.A04;
            if (broadcastReceiver != null) {
                try {
                    r4.A05.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException e) {
                    AnonymousClass0MD.A0D(r4.A0R, e, "Failed to unregister broadcast receiver");
                }
                r4.A04 = null;
            }
            BroadcastReceiver broadcastReceiver2 = r4.A03;
            if (broadcastReceiver2 != null) {
                try {
                    r4.A05.unregisterReceiver(broadcastReceiver2);
                } catch (IllegalArgumentException e2) {
                    AnonymousClass0MD.A0D(r4.A0R, e2, "Failed to unregister broadcast receiver");
                }
                r4.A03 = null;
            }
            r4.A0O.A00();
            r4.A0N.A00();
            r5.A0A.A07(r6);
            A02(r5, null);
        }
    }

    static default void A02(@Nullable AnonymousClass22G r7, EnumC142522n r8) {
        Integer num;
        AnonymousClass22J r0 = r7.A0A.A0n;
        if (r0 == null) {
            num = AnonymousClass007.A04;
        } else {
            num = r0.A0X;
        }
        if (num != null && num != r7.A0H) {
            r7.A0H = num;
            if (num == AnonymousClass007.A04) {
                r7.A0G = r8;
            }
            AnonymousClass1Vm r1 = r7.A06;
            String A002 = AnonymousClass1Wh.A00(num);
            r1.A01(A002);
            $$Lambda$VrMsysMqttClientCallbacks$YQAzKNR0OPr5z4Ya6bxvedcq9Ws2 r12 = r7.A0C;
            if (r12 != null) {
                AnonymousClass22D r3 = AnonymousClass22D.DISCONNECTED;
                try {
                    r3 = AnonymousClass22D.valueOf(A002);
                } catch (IllegalArgumentException | NullPointerException unused) {
                }
                AnonymousClass22H r02 = r7.A0A;
                r12.onChannelStateChanged(new C141421y(r3, r8, r02.A00, r02.A01));
            }
        }
    }

    final default boolean A04() {
        if (this.A0F) {
            if (this.A09.AAM(new HashMap())) {
                return true;
            }
        }
        return false;
    }

    @Override // X.AbstractC141321x
    final default AnonymousClass228 A4V() {
        long j;
        A00();
        String A002 = AnonymousClass1Wh.A00(this.A0H);
        EnumC142522n r15 = this.A0G;
        AnonymousClass22D r14 = AnonymousClass22D.DISCONNECTED;
        try {
            r14 = AnonymousClass22D.valueOf(A002);
        } catch (IllegalArgumentException | NullPointerException unused) {
        }
        AnonymousClass22H r4 = this.A0A;
        C141421y r13 = new C141421y(r14, r15, r4.A00, r4.A01);
        AnonymousClass1QF r2 = this.A07;
        AnonymousClass22J r1 = r4.A0n;
        if (r1 == null || !r1.A04()) {
            j = 0;
        } else {
            j = SystemClock.elapsedRealtime() - r1.A0U;
        }
        try {
            AnonymousClass1Q6.A00(new AnonymousClass1Q6(AnonymousClass1QF.A00(r2), AnonymousClass1QF.A01(r2, j), (AnonymousClass1QB) r2.A04(AnonymousClass1QB.class), null, r2.A00.A00(), (C06101Pi) r2.A04(C06101Pi.class), (C06081Pg) r2.A04(C06081Pg.class), (C06091Ph) r2.A04(C06091Ph.class), false, true), false).toString();
        } catch (JSONException unused2) {
        }
        return new AnonymousClass228(r13);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0161, code lost:
        if ((!r4.A06.A02) != false) goto L_0x0163;
     */
    @Override // X.AbstractC141321x
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final synchronized default void A5c(X.C142422m r39) {
        /*
        // Method dump skipped, instructions count: 1336
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass22G.A5c(X.22m):void");
    }

    default AnonymousClass22G(VrMsysMqttClientCallbacks.AnonymousClass2 r2) {
        this.A0E = r2;
    }

    static default void A03(AnonymousClass22G r2, Runnable runnable) {
        if (Looper.myLooper() != r2.A01.getLooper()) {
            r2.A01.post(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // X.AbstractC141321x
    final default int A8i(String str, byte[] bArr, Integer num, @Nullable AnonymousClass22C r14, @Nullable VrMsysMqttClientCallbacks.AnonymousClass1.AnonymousClass1 r15) {
        int i;
        int i2;
        AnonymousClass238 r7;
        A00();
        if (str == null) {
            throw null;
        } else if (bArr == null) {
            throw null;
        } else if (num != null) {
            try {
                AnonymousClass22H r3 = this.A0A;
                if (1 - num.intValue() != 0) {
                    i2 = 0;
                } else {
                    i2 = 1;
                }
                EnumC143322v fromInt = EnumC143322v.fromInt(i2);
                if (r14 == null) {
                    r7 = null;
                } else {
                    r7 = new AnonymousClass238(this, r14);
                }
                AnonymousClass1QO A002 = AnonymousClass22H.A00(r3, str, bArr, fromInt, r7, ((AnonymousClass1YE) r3.A0G).A00.A0I, r15);
                if (!A002.A02()) {
                    i = -1;
                } else {
                    i = ((AnonymousClass22b) A002.A01()).A01;
                    if (i == -1) {
                    }
                    return i;
                }
            } catch (AnonymousClass23U unused) {
                i = -1;
            }
            if (r14 != null) {
                A03(this, new AnonymousClass23R(this, r14));
            }
            return i;
        } else {
            throw null;
        }
    }

    @Override // X.AbstractC141321x
    final default void destroy() {
        A00();
        this.A01.post(new AnonymousClass1YJ(this));
    }

    @Override // X.AbstractC141321x
    final default void start() {
        A00();
        this.A01.post(new AnonymousClass1YI(this));
    }

    @Override // X.AbstractC141321x
    final default void stop() {
        A00();
        this.A01.post(new AnonymousClass1YK(this));
    }
}
