package com.oculus.panelapp.bugreporter.util;

import android.content.Context;
import com.oculus.osupdaterapi.OsUpdater;
import com.oculus.osupdaterapi.UpdaterOtaAvailability;
import com.oculus.panelapp.bugreporter.util.OSUpdaterUtil;
import com.oculus.vrshell.util.UiThreadExecutor;

public class OSUpdaterUtil {

    public interface OSUpdateAvailabilityCallback {
        void run(boolean z);
    }

    public static void checkOSVersion(Context context, OSUpdateAvailabilityCallback oSUpdateAvailabilityCallback) {
        new OsUpdater(context).checkIfUpdatesAreAvailable(false, null, new OsUpdater.UpdaterOtaAvailabilityCallback() {
            /* class com.oculus.panelapp.bugreporter.util.$$Lambda$OSUpdaterUtil$u27zEunxgS3Att4zm5RAbZHT9GQ */

            @Override // com.oculus.osupdaterapi.OsUpdater.UpdaterOtaAvailabilityCallback
            public final void onReceive(UpdaterOtaAvailability updaterOtaAvailability) {
                UiThreadExecutor.getInstance().execute(new Runnable(updaterOtaAvailability) {
                    /* class com.oculus.panelapp.bugreporter.util.$$Lambda$OSUpdaterUtil$Kat5zPAXw_JGxT6blPWbek00NOo */
                    private final /* synthetic */ UpdaterOtaAvailability f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        OSUpdaterUtil.OSUpdateAvailabilityCallback.this.run(Boolean.TRUE.equals(this.f$1.mAreUpdatesAvailable));
                    }
                });
            }
        });
    }
}
