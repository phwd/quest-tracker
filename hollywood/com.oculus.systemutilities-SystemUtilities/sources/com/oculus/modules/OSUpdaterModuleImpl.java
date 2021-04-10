package com.oculus.modules;

import android.content.Context;
import android.util.Log;
import com.oculus.modules.codegen.OSUpdaterModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.ToasterModule;
import com.oculus.osupdaterapi.OsUpdater;
import com.oculus.osupdaterapi.UpdaterOtaAvailability;
import com.oculus.systemutilities.R;
import com.oculus.toast.ToastAPI;

public class OSUpdaterModuleImpl extends OSUpdaterModule {
    private static final String TAG = OSUpdaterModuleImpl.class.getSimpleName();
    private static Context mContext;
    private static Resolver<OSUpdaterModule.OSUpdaterResponse> mResolver;
    private final OsUpdater mOsUpdater;

    public OSUpdaterModuleImpl(Context context) {
        mContext = context;
        this.mOsUpdater = new OsUpdater(context);
    }

    private static class OtaAvailabilityCallback implements OsUpdater.UpdaterOtaAvailabilityCallback {
        private OtaAvailabilityCallback() {
        }

        @Override // com.oculus.osupdaterapi.OsUpdater.UpdaterOtaAvailabilityCallback
        public void onReceive(UpdaterOtaAvailability response) {
            boolean z = false;
            Log.d(OSUpdaterModuleImpl.TAG, String.format("Got OSUpdaterResponse. areUpdatesAvailable = %b, updaterState = %s", response.mAreUpdatesAvailable, response.mUpdaterState));
            OSUpdaterModule.OSUpdaterResponse result = new OSUpdaterModule.OSUpdaterResponse();
            if (response.mAreUpdatesAvailable != null) {
                z = response.mAreUpdatesAvailable.booleanValue();
            }
            result.areUpdatesAvailable = z;
            if (response.mCurrentOSVersion != null) {
                result.currentOSVersion = response.mCurrentOSVersion.toString();
            }
            if (response.mTargetVersion != null) {
                result.targetVersion = response.mTargetVersion.toString();
            }
            if (response.mUpdaterState != null) {
                result.updaterState = OSUpdaterModule.OSUpdateState.valueOf(response.mUpdaterState.name());
            }
            OSUpdaterModuleImpl.mResolver.resolve(result);
        }
    }

    private static class OtaUpdateProgressCallback implements OsUpdater.OtaUpdateProgressCallback {
        private OtaUpdateProgressCallback() {
        }

        @Override // com.oculus.osupdaterapi.OsUpdater.OtaUpdateProgressCallback
        public void onProgress(float progress) {
        }

        @Override // com.oculus.osupdaterapi.OsUpdater.OtaUpdateProgressCallback
        public void onError(String errorMessage) {
            ToastAPI.createToast(OSUpdaterModuleImpl.mContext, "oculus_mobile_os_updater_ota_progress_error", ToasterModule.ToastDuration.DEFAULT, ToasterModule.ToastIcon.gear, OSUpdaterModuleImpl.mContext.getString(R.string.notification_os_update_error), null, false);
        }

        @Override // com.oculus.osupdaterapi.OsUpdater.OtaUpdateProgressCallback
        public void onComplete() {
            ToastAPI.createToast(OSUpdaterModuleImpl.mContext, "oculus_mobile_os_updater_ota_progress_complete", ToasterModule.ToastDuration.DEFAULT, ToasterModule.ToastIcon.gear, OSUpdaterModuleImpl.mContext.getString(R.string.notification_os_update_complete), null, false);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.OSUpdaterModule
    public void checkIfUpdatesAreAvailableImpl(Resolver<OSUpdaterModule.OSUpdaterResponse> resolver) {
        mResolver = resolver;
        this.mOsUpdater.checkIfUpdatesAreAvailable(false, null, new OtaAvailabilityCallback());
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.OSUpdaterModule
    public void downloadUpdateIfAvailableImpl(Resolver<OSUpdaterModule.OSUpdaterResponse> resolver) {
        mResolver = resolver;
        this.mOsUpdater.downloadUpdateIfAvailable(false, null, new OtaAvailabilityCallback(), new OtaUpdateProgressCallback());
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.OSUpdaterModule
    public void rebootAndApplyUpdateImpl() {
        this.mOsUpdater.rebootAndApplyUpdate();
    }
}
