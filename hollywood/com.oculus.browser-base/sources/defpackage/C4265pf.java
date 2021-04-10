package defpackage;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;
import org.chromium.components.background_task_scheduler.internal.BackgroundTaskJobService;

/* renamed from: pf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4265pf implements AbstractC2385ef {

    /* renamed from: a  reason: collision with root package name */
    public static AbstractC3923nf f11079a = new C3752mf();

    public static C2046cf1 c(JobParameters jobParameters) {
        C1875bf1 bf1 = new C1875bf1(jobParameters.getJobId());
        PersistableBundle persistableBundle = jobParameters.getExtras().getPersistableBundle("_background_task_extras");
        Bundle bundle = new Bundle();
        bundle.putAll(persistableBundle);
        bf1.b = bundle;
        return bf1.a();
    }

    @Override // defpackage.AbstractC2385ef
    public void a(Context context, int i) {
        Object obj = ThreadUtils.f10596a;
        try {
            ((JobScheduler) context.getSystemService("jobscheduler")).cancel(i);
        } catch (NullPointerException unused) {
            AbstractC1220Ua0.a("BkgrdTaskSchedulerJS", AbstractC2531fV.w("Failed to cancel task: ", i), new Object[0]);
        }
    }

    @Override // defpackage.AbstractC2385ef
    public boolean b(Context context, TaskInfo taskInfo) {
        boolean z;
        Object obj = ThreadUtils.f10596a;
        PersistableBundle persistableBundle = new PersistableBundle();
        Bundle bundle = taskInfo.b;
        PersistableBundle persistableBundle2 = new PersistableBundle();
        HashSet hashSet = new HashSet();
        for (String str : bundle.keySet()) {
            Object obj2 = bundle.get(str);
            if (obj2 == null) {
                persistableBundle2.putString(str, null);
            } else if (obj2 instanceof Boolean) {
                persistableBundle2.putBoolean(str, ((Boolean) obj2).booleanValue());
            } else if (obj2 instanceof boolean[]) {
                persistableBundle2.putBooleanArray(str, (boolean[]) obj2);
            } else if (obj2 instanceof Double) {
                persistableBundle2.putDouble(str, ((Double) obj2).doubleValue());
            } else if (obj2 instanceof double[]) {
                persistableBundle2.putDoubleArray(str, (double[]) obj2);
            } else if (obj2 instanceof Integer) {
                persistableBundle2.putInt(str, ((Integer) obj2).intValue());
            } else if (obj2 instanceof int[]) {
                persistableBundle2.putIntArray(str, (int[]) obj2);
            } else if (obj2 instanceof Long) {
                persistableBundle2.putLong(str, ((Long) obj2).longValue());
            } else if (obj2 instanceof long[]) {
                persistableBundle2.putLongArray(str, (long[]) obj2);
            } else if (obj2 instanceof String) {
                persistableBundle2.putString(str, (String) obj2);
            } else if (obj2 instanceof String[]) {
                persistableBundle2.putStringArray(str, (String[]) obj2);
            } else {
                hashSet.add(str);
            }
        }
        if (hashSet.size() > 0) {
            StringBuilder i = AbstractC2531fV.i("Failed converting extras to PersistableBundle: ");
            StringBuilder i2 = AbstractC2531fV.i("{");
            Iterator it = hashSet.iterator();
            boolean z2 = true;
            while (it.hasNext()) {
                String str2 = (String) it.next();
                if (!z2) {
                    i2.append(", ");
                }
                i2.append(str2);
                z2 = false;
            }
            i2.append("}");
            i.append(i2.toString());
            AbstractC1220Ua0.f("BkgrdTaskSchedulerJS", i.toString(), new Object[0]);
        }
        persistableBundle.putPersistableBundle("_background_task_extras", persistableBundle2);
        JobInfo.Builder requiredNetworkType = new JobInfo.Builder(taskInfo.f10811a, new ComponentName(context, BackgroundTaskJobService.class)).setPersisted(taskInfo.e).setRequiresCharging(taskInfo.d).setRequiredNetworkType(taskInfo.c);
        taskInfo.g.a(new C4094of(requiredNetworkType, persistableBundle));
        JobInfo build = requiredNetworkType.build();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (!taskInfo.f) {
            int i3 = taskInfo.f10811a;
            Iterator<JobInfo> it2 = jobScheduler.getAllPendingJobs().iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (it2.next().getId() == i3) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return true;
            }
        }
        try {
            return jobScheduler.schedule(build) == 1;
        } catch (Exception e) {
            AbstractC1220Ua0.a("BkgrdTaskSchedulerJS", "Unable to schedule with Android.", e);
            return false;
        }
    }
}
