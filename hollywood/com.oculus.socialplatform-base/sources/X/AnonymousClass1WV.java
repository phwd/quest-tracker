package X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@TargetApi(15)
/* renamed from: X.1WV  reason: invalid class name */
public final class AnonymousClass1WV {
    @VisibleForTesting
    public static final String A0O = AnonymousClass006.A07("KeepaliveManager", ".ACTION_INEXACT_ALARM.");
    public static final List<Long> A0P = Collections.unmodifiableList(new AnonymousClass1Jt());
    public static final String A0Q = AnonymousClass006.A07("KeepaliveManager", ".ACTION_BACKUP_ALARM.");
    public static final String A0R = AnonymousClass006.A07("KeepaliveManager", ".ACTION_EXACT_ALARM.");
    @GuardedBy("this")
    public long A00;
    @GuardedBy("this")
    public long A01;
    @GuardedBy("this")
    public long A02 = -1;
    @GuardedBy("this")
    public boolean A03;
    public final int A04;
    public final AlarmManager A05;
    public final PendingIntent A06;
    public final PendingIntent A07;
    public final PendingIntent A08;
    public final BroadcastReceiver A09;
    public final BroadcastReceiver A0A;
    public final BroadcastReceiver A0B;
    public final Context A0C;
    public final Handler A0D;
    public final RealtimeSinceBootClock A0E;
    public final C06141Qs A0F;
    @VisibleForTesting
    public final String A0G;
    @VisibleForTesting
    public final String A0H;
    @VisibleForTesting
    public final String A0I;
    public final boolean A0J;
    public final AbstractC02660jW A0K = new AnonymousClass1R3(this);
    public final String A0L;
    public final AtomicInteger A0M;
    public volatile Runnable A0N;

    public final synchronized void A03() {
        if (this.A03) {
            this.A03 = false;
            C06141Qs r2 = this.A0F;
            AlarmManager alarmManager = this.A05;
            r2.A03(alarmManager, this.A08);
            if (!this.A0J) {
                r2.A03(alarmManager, this.A06);
            }
            r2.A03(alarmManager, this.A07);
        }
        this.A00 = 900000;
        this.A02 = -1;
    }

    public final synchronized void A04() {
        long j = (long) (this.A0M.get() * 1000);
        if (j > 900000) {
            boolean z = false;
            if (j >= 900000) {
                z = true;
            }
            AnonymousClass1ZK.A00(z);
            Iterator<Long> it = A0P.iterator();
            while (true) {
                if (!it.hasNext()) {
                    j = 900000;
                    break;
                }
                long longValue = it.next().longValue();
                if (j >= longValue) {
                    j = longValue;
                    break;
                }
            }
        }
        this.A00 = j;
        this.A01 = SystemClock.elapsedRealtime() + j;
        if (this.A03) {
            C06141Qs r2 = this.A0F;
            AlarmManager alarmManager = this.A05;
            r2.A03(alarmManager, this.A07);
            if (!this.A0J) {
                r2.A03(alarmManager, this.A06);
            }
        } else {
            this.A03 = true;
        }
        try {
            long j2 = this.A00;
            if (j2 < 900000) {
                long j3 = this.A01;
                int i = this.A04;
                if (i >= 23 && this.A0J) {
                    this.A0F.A02(this.A05, j3, this.A07);
                } else if (i >= 19) {
                    this.A0F.A00(this.A05, j3, this.A07);
                } else {
                    this.A05.setRepeating(2, j3, j2, this.A07);
                }
            } else {
                if (this.A02 != j2) {
                    this.A02 = j2;
                    this.A0F.A03(this.A05, this.A08);
                    A02(this, this.A01, this.A00);
                }
                if (!this.A0J) {
                    A01(this, this.A01 + 20000);
                }
            }
        } catch (Throwable unused) {
        }
    }

    private String A00(String str, Context context) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(this.A0L);
        String packageName = context.getPackageName();
        if (!TextUtils.isEmpty(packageName)) {
            sb.append('.');
            sb.append(packageName);
        }
        return sb.toString();
    }

    @GuardedBy("this")
    public static void A01(AnonymousClass1WV r3, long j) {
        int i = r3.A04;
        if (i >= 23 && r3.A0J) {
            r3.A0F.A02(r3.A05, j, r3.A06);
        } else if (i >= 19) {
            r3.A0F.A00(r3.A05, j, r3.A06);
        } else {
            r3.A05.set(2, j, r3.A06);
        }
    }

    @GuardedBy("this")
    @SuppressLint({"BadMethodUse-android.app.AlarmManager.setInexactRepeating", "SetInexactRepeatingArgs"})
    public static void A02(AnonymousClass1WV r5, long j, long j2) {
        if (r5.A04 < 23 || !r5.A0J) {
            r5.A05.setInexactRepeating(2, j, j2, r5.A08);
        } else {
            r5.A0F.A01(r5.A05, j, r5.A08);
        }
    }

    public AnonymousClass1WV(Context context, AnonymousClass1QQ r9, String str, AtomicInteger atomicInteger, RealtimeSinceBootClock realtimeSinceBootClock, Handler handler, C06141Qs r14) {
        this.A0C = context;
        this.A0L = str;
        this.A0J = C06111Qo.A00(context.getPackageName());
        this.A0M = atomicInteger;
        AnonymousClass1QO A002 = r9.A00("alarm", AlarmManager.class);
        if (A002.A02()) {
            this.A05 = (AlarmManager) A002.A01();
            this.A0E = realtimeSinceBootClock;
            this.A04 = Build.VERSION.SDK_INT;
            this.A0D = handler;
            this.A0F = r14;
            this.A0A = new AnonymousClass1WX(this);
            String A003 = A00(A0R, context);
            this.A0H = A003;
            Intent intent = new Intent(A003);
            C02670jZ r3 = new C02670jZ();
            r3.A04(intent, context.getClassLoader());
            r3.A01 |= 1;
            r3.A08 = this.A0K;
            this.A07 = r3.A03(context, 0);
            this.A0B = new AnonymousClass1WW(this);
            String A004 = A00(A0O, context);
            this.A0I = A004;
            Intent intent2 = new Intent(A004);
            C02670jZ r32 = new C02670jZ();
            r32.A04(intent2, context.getClassLoader());
            r32.A01 |= 1;
            r32.A08 = this.A0K;
            this.A08 = r32.A03(context, 0);
            this.A09 = new AnonymousClass1WY(this);
            String A005 = A00(A0Q, context);
            this.A0G = A005;
            Intent intent3 = new Intent(A005);
            C02670jZ r33 = new C02670jZ();
            r33.A04(intent3, context.getClassLoader());
            r33.A01 |= 1;
            r33.A08 = this.A0K;
            this.A06 = r33.A03(context, 0);
            return;
        }
        throw new IllegalArgumentException("Cannot acquire Alarm service");
    }
}
