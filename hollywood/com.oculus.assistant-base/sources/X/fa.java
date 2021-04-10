package X;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.PowerManager;
import com.facebook.analytics2.logger.GooglePlayUploadService;
import com.facebook.common.build.BuildConstants;
import com.facebook.common.gcmcompat.OneoffTask;
import com.facebook.common.stringformat.StringFormatUtil;

public final class fa implements AnonymousClass7l {
    public final AnonymousClass7u A00;
    public final /* synthetic */ AnonymousClass81 A01;

    public fa(AnonymousClass81 r1, AnonymousClass7u r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass7l
    public final void A48() {
        PowerManager.WakeLock wakeLock = this.A01.A00;
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

    @Override // X.AnonymousClass7l
    public final void A4R(boolean z) {
        AnonymousClass81 r1;
        AnonymousClass7y r0;
        ComponentName componentName;
        if (z && (r0 = (r1 = this.A01).A07) != null) {
            AnonymousClass7u r8 = this.A00;
            int i = r1.A02;
            String str = r0.A02;
            AnonymousClass7j r11 = r1.A06;
            long j = r0.A01;
            long j2 = r0.A00;
            if (!(r8 instanceof fD)) {
                Context context = ((f3) r8).A00;
                synchronized (GooglePlayUploadService.class) {
                    GooglePlayUploadService.A01(context);
                    long j3 = j / 1000;
                    long j4 = j2 / 1000;
                    if (j2 < j) {
                        C0139Dd.A0Q("GooglePlay-MaxDelay", "MaxDelayms(%d) < MinDelayms(%d)", Long.valueOf(j2), Long.valueOf(j));
                    }
                    if (j3 >= j4) {
                        j4 = 1 + j3;
                    }
                    C0677f5 f5Var = new C0677f5(new Bundle());
                    f5Var.A4a("action", str);
                    f5Var.A4Z("__VERSION_CODE", BuildConstants.getBuildID());
                    C0827j3 j3Var = new C0827j3();
                    ((C4) j3Var).A01 = GooglePlayUploadService.class.getName();
                    j3Var.A02 = AnonymousClass08.A00("analytics2-gcm-", i);
                    j3Var.A01 = j3;
                    j3Var.A00 = j4;
                    j3Var.A03 = true;
                    ((C4) j3Var).A00 = (Bundle) r11.A00(f5Var);
                    j3Var.A04 = GooglePlayUploadService.A01;
                    j3Var.A00();
                    GooglePlayUploadService.A03(context, i, new OneoffTask(j3Var));
                    GooglePlayUploadService.A01 = true;
                }
                return;
            }
            fD fDVar = (fD) r8;
            JobScheduler jobScheduler = fDVar.A00;
            if (jobScheduler != null && (componentName = fDVar.A01) != null) {
                fC fCVar = new fC(new PersistableBundle());
                fCVar.A4a("action", str);
                fCVar.A4Z("__VERSION_CODE", BuildConstants.getBuildID());
                try {
                    jobScheduler.schedule(new JobInfo.Builder(i, componentName).setMinimumLatency(j).setOverrideDeadline(j2).setExtras((PersistableBundle) r11.A00(fCVar)).setRequiredNetworkType(1).setPersisted(false).build());
                } catch (IllegalArgumentException e) {
                    PackageManager packageManager = fDVar.A02.getPackageManager();
                    int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
                    if (componentEnabledSetting != 1) {
                        if (!(componentEnabledSetting == 2 || componentEnabledSetting == 3 || componentEnabledSetting == 4)) {
                            if (packageManager.getServiceInfo(componentName, 512).isEnabled()) {
                                throw e;
                            }
                        }
                        Object[] objArr = {componentName};
                        if (C0139Dd.A01.A3Y(4)) {
                            String formatStrLocaleSafe = StringFormatUtil.formatStrLocaleSafe("The Service is disabled, cannot schedule job for %s", objArr);
                            if (C0139Dd.A01.A3Y(4)) {
                                C0139Dd.A01.A3D("LollipopUploadScheduler", formatStrLocaleSafe, e);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    throw e;
                } catch (NullPointerException e2) {
                    C0139Dd.A0T("LollipopUploadScheduler", e2, "Nullpointer exception encountered while scheduling job");
                } catch (Throwable th) {
                    C0139Dd.A0L("LollipopUploadScheduler", "Error getting serviceInfo from PackageManager", th);
                    throw e;
                }
            }
        }
    }
}
