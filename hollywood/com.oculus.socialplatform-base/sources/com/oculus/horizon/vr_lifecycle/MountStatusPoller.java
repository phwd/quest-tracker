package com.oculus.horizon.vr_lifecycle;

import X.AnonymousClass0MD;
import android.content.Context;
import com.oculus.horizon.vr_lifecycle.MountStatusBase;
import com.oculus.util.system.SystemProps;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.Nullable;

public class MountStatusPoller extends MountStatusBase {
    public static final int POLL_INTERVAL_MS = 250;
    public static final String TAG = "MountStatusPoller";
    public boolean mLastMountStatus = false;
    @Nullable
    public Timer mMountStatusPoller = null;

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    @Override // com.oculus.horizon.vr_lifecycle.MountStatusBase
    public boolean isRunning() {
        if (this.mMountStatusPoller != null) {
            return true;
        }
        return false;
    }

    @Override // com.oculus.horizon.vr_lifecycle.MountStatusBase
    public void start(Context context, final MountStatusBase.Callback callback) {
        String str;
        String str2;
        if (this.mMountStatusPoller != null) {
            str = TAG;
            str2 = "Attempting to call startMountStatusPoller when it's already running.";
        } else if (callback == null) {
            str = TAG;
            str2 = "Null callback.  Aborting start, this would have no effect";
        } else {
            Timer timer = new Timer();
            this.mMountStatusPoller = timer;
            timer.scheduleAtFixedRate(new TimerTask() {
                /* class com.oculus.horizon.vr_lifecycle.MountStatusPoller.AnonymousClass1 */

                public void run() {
                    boolean bool = SystemProps.getBool("sys.hmt.mounted", true);
                    MountStatusPoller mountStatusPoller = MountStatusPoller.this;
                    if (bool != mountStatusPoller.mLastMountStatus) {
                        mountStatusPoller.mLastMountStatus = bool;
                        callback.onMountStatusChanged(bool);
                    }
                }
            }, 0, 250);
            return;
        }
        AnonymousClass0MD.A04(str, str2);
    }

    @Override // com.oculus.horizon.vr_lifecycle.MountStatusBase
    public void teardown() {
        Timer timer = this.mMountStatusPoller;
        if (timer != null) {
            timer.cancel();
            this.mMountStatusPoller = null;
        }
    }
}
