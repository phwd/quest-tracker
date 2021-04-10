package com.oculus.headlesshorizon;

import X.AnonymousClass0NO;
import X.AnonymousClass0Q4;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import com.facebook.acra.AppComponentStats;
import com.oculus.base.app.ApplicationLike;
import com.oculus.horizon.app.MainProcessApplicationLike;
import com.oculus.horizon.app.SecondaryProcessApplicationLike;
import com.oculus.processhelper.ProcessHelper;
import java.util.Iterator;

public class HeadlessHorizonApplication extends Application implements AnonymousClass0Q4 {
    public static final String TAG = "HeadlessHorizonApplication";
    public ApplicationLike mDelegate;

    @Override // X.AnonymousClass0Q4
    public final Object A3a() {
        synchronized (this) {
            if (this.mDelegate == null) {
                this.mDelegate = A00();
            }
        }
        return this.mDelegate;
    }

    private ApplicationLike A00() {
        String str;
        String packageName = getPackageName();
        int myPid = Process.myPid();
        Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) getSystemService(AppComponentStats.TAG_ACTIVITY)).getRunningAppProcesses().iterator();
        while (true) {
            if (!it.hasNext()) {
                str = null;
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.pid == myPid) {
                AnonymousClass0NO.A0F(ProcessHelper.TAG, "Current process name is: %s", next.processName);
                str = next.processName;
                break;
            }
        }
        if (!packageName.equals(str)) {
            return new SecondaryProcessApplicationLike();
        }
        return new MainProcessApplicationLike();
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ea A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate() {
        /*
        // Method dump skipped, instructions count: 287
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.headlesshorizon.HeadlessHorizonApplication.onCreate():void");
    }

    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
