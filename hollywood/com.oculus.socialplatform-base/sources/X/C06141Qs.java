package X;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.DeadObjectException;
import android.os.Handler;
import com.facebook.annotations.DoNotOptimize;
import javax.annotation.Nullable;

/* renamed from: X.1Qs  reason: invalid class name and case insensitive filesystem */
public final class C06141Qs {
    public static final C06141Qs A00 = new C06141Qs();

    @TargetApi(19)
    @DoNotOptimize
    public final void A00(AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        try {
            alarmManager.setExact(2, j, pendingIntent);
        } catch (SecurityException e) {
            AnonymousClass0MD.A0C("RtiGracefulSystemMethodHelper", e, "Failed to setExact");
        } catch (RuntimeException e2) {
            if (!(e2.getCause() instanceof DeadObjectException)) {
                throw e2;
            }
        }
    }

    @TargetApi(23)
    @DoNotOptimize
    public final void A01(AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        try {
            alarmManager.setAndAllowWhileIdle(2, j, pendingIntent);
        } catch (SecurityException e) {
            AnonymousClass0MD.A0C("RtiGracefulSystemMethodHelper", e, "Failed to setAndAllowWhileIdle");
        } catch (RuntimeException e2) {
            if (!(e2.getCause() instanceof DeadObjectException)) {
                throw e2;
            }
        }
    }

    @TargetApi(23)
    @DoNotOptimize
    public final void A02(AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        try {
            alarmManager.setExactAndAllowWhileIdle(2, j, pendingIntent);
        } catch (SecurityException e) {
            AnonymousClass0MD.A0C("RtiGracefulSystemMethodHelper", e, "Failed to setExactAndAllowWhileIdle");
        } catch (NullPointerException e2) {
        } catch (RuntimeException e3) {
            if (!(e3.getCause() instanceof DeadObjectException)) {
                throw e3;
            }
        }
    }

    public final boolean A05(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable Handler handler) {
        try {
            context.registerReceiver(broadcastReceiver, intentFilter, null, handler);
            return true;
        } catch (IllegalArgumentException | SecurityException e) {
            AnonymousClass0MD.A0C("RtiGracefulSystemMethodHelper", e, "Failed to registerReceiver");
            return false;
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof DeadObjectException) {
                return false;
            }
            throw e2;
        }
    }

    public final void A03(AlarmManager alarmManager, PendingIntent pendingIntent) {
        if (pendingIntent != null) {
            try {
                alarmManager.cancel(pendingIntent);
            } catch (SecurityException unused) {
            } catch (RuntimeException e) {
                if (!(e.getCause() instanceof DeadObjectException)) {
                    throw e;
                }
            }
        }
    }

    public final void A04(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException | SecurityException e) {
            AnonymousClass0MD.A0C("RtiGracefulSystemMethodHelper", e, "Failed to unregisterReceiver");
        } catch (RuntimeException e2) {
            if (!(e2.getCause() instanceof DeadObjectException)) {
                throw e2;
            }
        }
    }
}
