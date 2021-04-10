package X;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.DeadObjectException;
import android.os.Handler;
import com.facebook.annotations.DoNotOptimize;
import javax.annotation.Nullable;

/* renamed from: X.0wa  reason: invalid class name and case insensitive filesystem */
public final class C08110wa {
    public static final C08110wa A00 = new C08110wa();

    @TargetApi(19)
    @DoNotOptimize
    public final void A00(AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        try {
            alarmManager.setExact(2, j, pendingIntent);
        } catch (SecurityException e) {
            AnonymousClass0NK.A09("RtiGracefulSystemMethodHelper", e, "Failed to setExact");
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
            AnonymousClass0NK.A09("RtiGracefulSystemMethodHelper", e, "Failed to setAndAllowWhileIdle");
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
            AnonymousClass0NK.A09("RtiGracefulSystemMethodHelper", e, "Failed to setExactAndAllowWhileIdle");
        } catch (NullPointerException e2) {
        } catch (RuntimeException e3) {
            if (!(e3.getCause() instanceof DeadObjectException)) {
                throw e3;
            }
        }
    }

    public final boolean A06(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable Handler handler) {
        try {
            context.registerReceiver(broadcastReceiver, intentFilter, null, handler);
            return true;
        } catch (IllegalArgumentException | SecurityException e) {
            AnonymousClass0NK.A09("RtiGracefulSystemMethodHelper", e, "Failed to registerReceiver");
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

    public final void A04(Context context, Intent intent) {
        RuntimeException e;
        try {
            context.startService(intent);
        } catch (SecurityException e2) {
            AnonymousClass0NK.A09("RtiGracefulSystemMethodHelper", e2, "Failed to startService");
        } catch (IllegalStateException e3) {
            e = e3;
            if (Build.VERSION.SDK_INT >= 26) {
                return;
            }
            throw e;
        } catch (RuntimeException e4) {
            e = e4;
            if (e.getCause() instanceof DeadObjectException) {
                return;
            }
            throw e;
        }
    }

    public final boolean A05(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            context.unregisterReceiver(broadcastReceiver);
            return true;
        } catch (IllegalArgumentException | SecurityException e) {
            AnonymousClass0NK.A09("RtiGracefulSystemMethodHelper", e, "Failed to unregisterReceiver");
            return false;
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof DeadObjectException) {
                return false;
            }
            throw e2;
        }
    }
}
