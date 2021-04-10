package X;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.facebook.analytics2.logger.HandlerThreadFactory;
import com.oculus.auth.service.contract.ServiceContract;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class YX implements H5<File> {
    @GuardedBy("this")
    public long A00;
    @GuardedBy("this")
    @Nullable
    public File A01;
    @GuardedBy("this")
    @Nullable
    public File A02;
    @GuardedBy("this")
    @Nullable
    public File A03;
    @GuardedBy("this")
    @Nullable
    public String A04;
    public boolean A05;
    public boolean A06;
    @GuardedBy("this")
    public boolean A07 = false;
    public final int A08;
    public final Context A09;
    public final Fg A0A;
    public final H8 A0B;
    public final HN A0C;
    public final HN A0D;
    public final Class<? extends HandlerThreadFactory> A0E;
    public final long A0F;

    private synchronized void A01(long j, long j2) {
        if (!this.A06) {
            Context context = this.A09;
            HandlerThreadFactory A032 = G7.A00(context).A03(this.A0E.getName());
            int i = 19;
            if (this.A0B.A02 == AnonymousClass07.A01) {
                i = 18;
            }
            HandlerThread A1f = A032.A1f("JobRanReceiver", i);
            context.registerReceiver(new HH(this), new IntentFilter("com.facebook.analytics2.action.UPLOAD_JOB_RAN"), null, new Handler(A1f.getLooper()));
            this.A06 = true;
        }
        if (this.A04 == null) {
            if (!this.A05) {
                this.A05 = true;
                this.A00 = HL.A00(this.A09).A01(this.A08);
            }
            if (this.A00 > j) {
                if (j == 0 && j2 == 0) {
                    A00();
                } else {
                    HL.A00(this.A09).A04(this.A08, null, this.A0B, j, j2);
                    this.A00 = j;
                }
            }
        }
    }

    public static synchronized void A02(YX yx) {
        synchronized (yx) {
            yx.A00 = Long.MAX_VALUE;
        }
    }

    @Override // X.H5
    public final void A3W() {
        HN hn;
        synchronized (this) {
            this.A02 = this.A01;
        }
        if (this.A0A.A2G()) {
            hn = this.A0C;
        } else {
            hn = this.A0D;
        }
        A01(0, hn.A00);
    }

    @Override // X.H5
    public final void A3X() {
        HN hn;
        synchronized (this) {
            this.A03 = this.A01;
        }
        if (this.A0A.A2G()) {
            hn = this.A0C;
        } else {
            hn = this.A0D;
        }
        A01(hn.A02, hn.A01);
    }

    @Override // X.H5
    public final synchronized void A5V() {
        A00();
    }

    private final synchronized void A00() {
        AlarmManager alarmManager;
        Context context = this.A09;
        HL A002 = HL.A00(context);
        int i = this.A08;
        A002.A03(i);
        A02(this);
        this.A04 = "com.facebook.analytics2.logger.UPLOAD_NOW";
        if (!HW.A00(context).A01()) {
            HM.A00().A01(context, "com.facebook.analytics2.logger.UPLOAD_NOW", this.A0B, null, i, new HQ(0, 0, "com.facebook.analytics2.logger.UPLOAD_NOW"));
        } else {
            try {
                HM A003 = HM.A00();
                H8 h8 = this.A0B;
                long j = this.A0F;
                TimeUnit timeUnit = TimeUnit.MINUTES;
                PendingIntent service = PendingIntent.getService(context, 1, new Intent().setComponent(HL.A00(context).A02()).setAction("com.facebook.analytics2.logger.UPLOAD_NOW").putExtras(new HT(null, null, "com.facebook.analytics2.logger.UPLOAD_NOW", h8, i, new HQ(timeUnit.toMillis(15), timeUnit.toMillis(30), "com.facebook.analytics2.logger.UPLOAD_NOW"), context).A02()), 134217728);
                try {
                    synchronized (A003) {
                        alarmManager = A003.A00;
                        if (alarmManager == null) {
                            alarmManager = (AlarmManager) context.getSystemService("alarm");
                            A003.A00 = alarmManager;
                        }
                    }
                    alarmManager.setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + j, service);
                } catch (NullPointerException e) {
                    Mu.A03("JobSchedulerHack", "Unexpected NPE when scheduling alarm. This is most likely an OS bug.", e);
                }
                this.A07 = true;
                HV.A01(HV.A00(context), "com.facebook.analytics2.logger.UPLOAD_NOW", new HT(null, null, "com.facebook.analytics2.logger.UPLOAD_NOW", h8, i, new HQ(0, 0, "com.facebook.analytics2.logger.UPLOAD_NOW"), context), null);
            } catch (IllegalArgumentException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    @Override // X.H5
    public final synchronized void A3Z(@Nullable String str) {
        this.A02 = null;
        this.A03 = null;
        Context context = this.A09;
        HL A002 = HL.A00(context);
        int i = this.A08;
        A002.A03(i);
        A02(this);
        this.A04 = "com.facebook.analytics2.logger.USER_LOGOUT";
        HR hr = new HR(str);
        if (!HW.A00(context).A01()) {
            HM A003 = HM.A00();
            H8 h8 = this.A0B;
            Bundle bundle = new Bundle();
            bundle.putString(ServiceContract.EXTRA_USER_ID, hr.A00);
            A003.A01(context, "com.facebook.analytics2.logger.USER_LOGOUT", h8, bundle, i, null);
        } else {
            try {
                HV A004 = HV.A00(context);
                Bundle bundle2 = new Bundle();
                bundle2.putString(ServiceContract.EXTRA_USER_ID, hr.A00);
                HV.A01(A004, "com.facebook.analytics2.logger.USER_LOGOUT", new HT(null, bundle2, "com.facebook.analytics2.logger.USER_LOGOUT", this.A0B, i, null, context), null);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.H5
    public final void A51(@Nullable File file) {
        File file2 = file;
        synchronized (this) {
            this.A01 = file2;
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;ILX/H8;LX/Fg;Ljava/lang/Class<+Lcom/facebook/analytics2/logger/HandlerThreadFactory;>;LX/HN;LX/HN;ZJ)V */
    public YX(Context context, int i, H8 h8, Fg fg, Class cls, HN hn, HN hn2, long j) {
        this.A09 = context;
        this.A08 = i;
        this.A0B = h8;
        this.A0A = fg;
        this.A0E = cls;
        this.A0D = hn;
        this.A0C = hn2;
        A02(this);
        this.A0F = j;
    }
}
