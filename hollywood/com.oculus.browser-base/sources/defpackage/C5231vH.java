package defpackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;

/* renamed from: vH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5231vH extends AbstractC1677aZ0 {
    public final IBinder b = new BinderC5061uH(this);
    public NotificationManager c;

    @Override // defpackage.AbstractC1677aZ0
    public IBinder a(Intent intent) {
        return this.b;
    }

    @Override // defpackage.AbstractC1677aZ0
    public void b() {
        this.c = (NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification");
    }

    @Override // defpackage.AbstractC1677aZ0
    public void c() {
        AbstractC5404wI.c(1);
        for (String str : AH.a()) {
            if (AH.b(str) != null) {
                C5234vI vIVar = AbstractC5064uI.f11404a;
                vIVar.b();
                for (BI bi : vIVar.d.f7952a) {
                    if (!bi.c) {
                        vIVar.g(bi.g, bi.e, true, true, false, bi.h, null, null, false, false, false, 1);
                    }
                }
                if (NU0.f8549a.f("ResumptionAttemptLeft", 5) > 0) {
                    C5914zI.b().c();
                }
            }
        }
    }

    @Override // defpackage.AbstractC1677aZ0
    public void d() {
        AbstractC5404wI.c(3);
    }

    @Override // defpackage.AbstractC1677aZ0
    public int e(Intent intent, int i, int i2) {
        if (intent != null) {
            return 1;
        }
        AbstractC5404wI.c(4);
        this.f9437a.stopSelf();
        return 1;
    }

    @Override // defpackage.AbstractC1677aZ0
    public void f(Intent intent) {
        AbstractC5404wI.c(2);
        for (String str : AH.a()) {
            if (AH.b(str) != null) {
                C5234vI vIVar = AbstractC5064uI.f11404a;
                Objects.requireNonNull(vIVar);
                if (ApplicationStatus.f()) {
                    vIVar.b();
                }
            }
        }
    }

    public void h(int i, Notification notification) {
        AbstractC1220Ua0.f("DownloadFg", AbstractC2531fV.w("startForegroundInternal id: ", i), new Object[0]);
        AbstractServiceC1857bZ0 bz0 = this.f9437a;
        if (notification != null) {
            if (Build.VERSION.SDK_INT >= 29) {
                bz0.startForeground(i, notification, 0);
            } else {
                bz0.startForeground(i, notification);
            }
        }
    }

    public void i(int i) {
        AbstractC1220Ua0.f("DownloadFg", AbstractC2531fV.w("stopForegroundInternal flags: ", i), new Object[0]);
        try {
            this.f9437a.stopForeground(i);
        } catch (NullPointerException e) {
            AbstractC1220Ua0.a("ForegroundService", "Failed to stop foreground service, ", e);
        }
    }
}
