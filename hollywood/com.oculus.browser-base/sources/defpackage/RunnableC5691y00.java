package defpackage;

import android.app.Activity;
import android.app.ActivityManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;

/* renamed from: y00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5691y00 implements Runnable {
    public final C5861z00 F;

    public RunnableC5691y00(C5861z00 z00) {
        this.F = z00;
    }

    public void run() {
        Objects.requireNonNull(this.F);
        HashSet hashSet = new HashSet();
        Iterator it = ((ArrayList) ApplicationStatus.d()).iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            int e = ApplicationStatus.e(activity);
            if (!(e == 5 || e == 6)) {
                hashSet.add(Integer.valueOf(activity.getTaskId()));
            }
        }
        for (ActivityManager.AppTask appTask : ((ActivityManager) ContextUtils.getApplicationContext().getSystemService("activity")).getAppTasks()) {
            ActivityManager.RecentTaskInfo b = AbstractC5028u6.b(appTask);
            if (b != null) {
                String a2 = AbstractC5028u6.a(appTask);
                if ((AbstractActivityC2601fu.A1(a2) || TextUtils.equals(a2, Lr.class.getName())) && !hashSet.contains(Integer.valueOf(b.id))) {
                    appTask.finishAndRemoveTask();
                }
            }
        }
    }
}
