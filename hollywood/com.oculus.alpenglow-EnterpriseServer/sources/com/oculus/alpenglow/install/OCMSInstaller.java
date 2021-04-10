package com.oculus.alpenglow.install;

import X.AbstractC02990bJ;
import X.AnonymousClass006;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import X.AnonymousClass0iX;
import X.AnonymousClass0u6;
import X.C04910hv;
import X.C05120ig;
import X.C05280ix;
import X.C05290iy;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.config.BroadcastIntentHelper;
import com.oculus.alpenglow.config.ConfigChangeListener;
import com.oculus.alpenglow.config.ConfigurationStore;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.config.RemoteResourceAsset;
import com.oculus.alpenglow.database.ApplicationDatabase;
import com.oculus.alpenglow.database.MigrationCompleteListener;
import com.oculus.alpenglow.logging.InstallLogger;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.appmanager.info.model.InstallerResult;
import com.oculus.appmanager.info.model.InstallerResultError;
import com.oculus.appmanager.info.model.RequestOrigin;
import com.oculus.appmanager.model.UpdateConfig;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.library.model.AppStatus;
import com.oculus.library.model.InstallerCallback;
import com.oculus.library.model.InstallerCallbackReceiver;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.managed.ManagedMode;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_database_ApplicationDatabase_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_install_InstallerState_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_logging_InstallLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_secure_context_SecureContextHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_install_InvokeAppInstallListeners_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_config_ConfigurationStore_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_config_BroadcastIntentHelper_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OCMSInstaller implements ConfigChangeListener, MigrationCompleteListener {
    public static final String DEFAULT_SHELL_MODE = "default";
    public static final String TAG = "EnterpriseServer.OCMSInstaller";
    public static volatile OCMSInstaller _UL__ULSEP_com_oculus_alpenglow_install_OCMSInstaller_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;
    public final AtomicBoolean mOVRLibraryListenerRegistered = new AtomicBoolean(false);

    public final void A01(String str, String str2) {
        ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, this._UL_mInjectionContext)).A06(str, AppStatus.UNINSTALLING);
        ((InstallLogger) AnonymousClass0Lh.A03(4, 133, this._UL_mInjectionContext)).A04(str, str2, false);
        OVRLibrary oVRLibrary = (OVRLibrary) AnonymousClass0Lh.A03(0, 119, this._UL_mInjectionContext);
        RequestOrigin requestOrigin = RequestOrigin.ENTERPRISE;
        $$Lambda$OCMSInstaller$y9khH8UQ7x28Yk34_fEe4mXIEVU2 r4 = new InstallerCallback(str, str2) {
            /* class com.oculus.alpenglow.install.$$Lambda$OCMSInstaller$y9khH8UQ7x28Yk34_fEe4mXIEVU2 */
            public final /* synthetic */ String f$1;
            public final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.library.model.InstallerCallback
            public final void A69(InstallerResult installerResult) {
                String str;
                OCMSInstaller oCMSInstaller = OCMSInstaller.this;
                String str2 = this.f$1;
                String str3 = this.f$2;
                InstallerResultError installerResultError = installerResult.error;
                if (installerResult.isSuccess() || installerResultError == InstallerResultError.NOT_INSTALLED) {
                    ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext)).A05(str2);
                    for (String str4 : ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext)).A04(str3)) {
                        ((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, oCMSInstaller._UL_mInjectionContext)).A05(str4);
                        ((InstallerState) AnonymousClass0Lh.A03(3, 70, oCMSInstaller._UL_mInjectionContext)).A05(str2, installerResult.packageName);
                    }
                    AnonymousClass0R7 r1 = oCMSInstaller._UL_mInjectionContext;
                    BroadcastIntentHelper.A01((BroadcastIntentHelper) AnonymousClass0Lh.A03(9, 6, r1), (Context) AnonymousClass0Lh.A03(1, 4, r1), BroadcastIntentHelper.APP_UNINSTALLED, "com.oculus.systemux");
                    if (installerResult.error == InstallerResultError.NOT_INSTALLED) {
                        ((InstallLogger) AnonymousClass0Lh.A03(4, 133, oCMSInstaller._UL_mInjectionContext)).A05(str2, str3, true, true, null);
                    } else {
                        ((InstallLogger) AnonymousClass0Lh.A03(4, 133, oCMSInstaller._UL_mInjectionContext)).A05(str2, str3, true, false, null);
                    }
                } else {
                    if (installerResultError != null) {
                        str = installerResultError.name();
                    } else {
                        str = null;
                    }
                    ((InstallLogger) AnonymousClass0Lh.A03(4, 133, oCMSInstaller._UL_mInjectionContext)).A05(str2, str3, false, false, str);
                }
            }
        };
        try {
            if (oVRLibrary.mContext.getPackageManager().getPackageInfo(str2, 0) != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(OCMSLibraryContract.EXTRA_RESULT_RECEIVER, OVRLibrary.A00(new InstallerCallbackReceiver(r4).mReceiver));
                bundle.putString(OCMSLibraryContract.EXTRA_REQUEST_ORIGIN, requestOrigin.name());
                oVRLibrary.mContext.getContentResolver().call(OCMSLibraryContract.A00(), OCMSLibraryContract.METHOD_UNINSTALL, str2, bundle);
                return;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        r4.A69(InstallerResult.createForError(str2, System.currentTimeMillis(), InstallerResultError.NOT_INSTALLED));
    }

    @Override // com.oculus.alpenglow.config.ConfigChangeListener
    public final void A5z(@Nullable Device device, @Nullable Device device2) {
        String str;
        String str2;
        Device.ManagementInfo A3y;
        Device.ManagementInfo.DeviceConfig A3Q;
        if (!((ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, this._UL_mInjectionContext)).mMigrationToDeviceProtectedComplete) {
            str = TAG;
            str2 = "Migration incomplete, stop processing config";
        } else {
            if (this.mOVRLibraryListenerRegistered.compareAndSet(false, true)) {
                IntentFilter intentFilter = new IntentFilter(OVRLibrary.ACTION_UPDATE_BROADCAST);
                AnonymousClass0R7 r1 = this._UL_mInjectionContext;
                C05280ix A01 = C05290iy.A01(C05120ig.A11, AnonymousClass0iX.A05);
                OVRLibraryBroadcastListener oVRLibraryBroadcastListener = new OVRLibraryBroadcastListener();
                oVRLibraryBroadcastListener.mScope = C04910hv.A01((C04910hv) AnonymousClass0Lh.A03(5, 15, r1), A01);
                ((Context) AnonymousClass0Lh.A03(1, 4, r1)).registerReceiver(oVRLibraryBroadcastListener, intentFilter, null, null);
            }
            if (device2 == null || (A3y = device2.A3y()) == null || (A3Q = A3y.A3Q()) == null) {
                str = TAG;
                str2 = "device config not available, skipping software installs";
            } else {
                OculusThreadExecutor.A00().execute(new Runnable(A3Q) {
                    /* class com.oculus.alpenglow.install.$$Lambda$OCMSInstaller$497rou024jjq0TVjm4hpjuTs8uo2 */
                    public final /* synthetic */ Device.ManagementInfo.DeviceConfig f$1;

                    {
                        this.f$1 = r2;
                    }

                    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d5, code lost:
                        if (r8.status != com.oculus.library.model.AppStatus.INSTALLED) goto L_0x00d7;
                     */
                    /* JADX WARNING: Removed duplicated region for block: B:46:0x0117 A[LOOP:3: B:44:0x0111->B:46:0x0117, LOOP_END] */
                    /* JADX WARNING: Removed duplicated region for block: B:51:0x0132  */
                    /* JADX WARNING: Removed duplicated region for block: B:56:0x0158  */
                    /* JADX WARNING: Removed duplicated region for block: B:73:0x01ce  */
                    /* JADX WARNING: Removed duplicated region for block: B:76:0x01d6 A[ADDED_TO_REGION] */
                    /* JADX WARNING: Removed duplicated region for block: B:84:0x0215  */
                    /* JADX WARNING: Removed duplicated region for block: B:88:0x024b A[LOOP:5: B:86:0x0245->B:88:0x024b, LOOP_END] */
                    /* JADX WARNING: Removed duplicated region for block: B:92:0x028b A[LOOP:6: B:90:0x0285->B:92:0x028b, LOOP_END] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final void run() {
                        /*
                        // Method dump skipped, instructions count: 821
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.oculus.alpenglow.install.$$Lambda$OCMSInstaller$497rou024jjq0TVjm4hpjuTs8uo2.run():void");
                    }
                });
                return;
            }
        }
        AnonymousClass0NK.A01(str, str2);
    }

    @Override // com.oculus.alpenglow.database.MigrationCompleteListener
    public final void A6G() {
        AnonymousClass0R7 r3 = this._UL_mInjectionContext;
        if (((ManagedMode) AnonymousClass0Lh.A03(8, 86, r3)).isEnterpriseModeEnabled) {
            A5z(null, ((ConfigurationStore) AnonymousClass0Lh.A03(7, 97, r3)).mData.device);
        }
    }

    @Inject
    public OCMSInstaller(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(10, r3);
    }

    @Nullable
    public static UpdateConfig A00(String str, RemoteResourceAsset remoteResourceAsset, ApkUpdateInfoContract.UpdateType updateType, @Nullable Map<String, String> map) {
        if (remoteResourceAsset.getId() == null) {
            AnonymousClass0NK.A01(TAG, "missing application asset id, skip");
            return null;
        }
        HashMap hashMap = new HashMap();
        AnonymousClass0u6<? extends RemoteResourceAsset.Headers> it = remoteResourceAsset.A3d().iterator();
        String str2 = "";
        while (it.hasNext()) {
            RemoteResourceAsset.Headers headers = (RemoteResourceAsset.Headers) it.next();
            String key = headers.getKey();
            if (key != null) {
                boolean equals = "Accept".equals(key);
                String value = headers.getValue();
                if (equals) {
                    if (value == null) {
                        value = str2;
                    }
                    String[] split = value.split(",");
                    for (String str3 : split) {
                        if (!"application/vnd.android.package-archive".equals(str3) && !"application/octet-stream".equals(str3)) {
                            str2 = str2.concat(AnonymousClass006.A05(str3, ", "));
                        }
                    }
                } else {
                    if (value == null) {
                        value = str2;
                    }
                    hashMap.put(key, value);
                }
            }
        }
        hashMap.put("Accept", str2.concat("application/vnd.android.package-archive, ").concat("application/octet-stream"));
        return new UpdateConfig(str, updateType, remoteResourceAsset.A3T(), remoteResourceAsset.A3E(), map, hashMap);
    }
}
