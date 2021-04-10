package com.oculus.alpenglow.enterpriseserver;

import X.AnonymousClass006;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import X.AnonymousClass0TS;
import X.C07420rx;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import com.facebook.infer.annotation.SuppressLint;
import com.oculus.alpenglow.boot.UserUnlockedBroadcastReceiver;
import com.oculus.alpenglow.config.ConfigurationStore;
import com.oculus.alpenglow.config.ExportedBroadcastReceiver;
import com.oculus.alpenglow.config.FetchConfigurationAction;
import com.oculus.alpenglow.constants.ForegroundServiceNotificationIds;
import com.oculus.alpenglow.database.Application;
import com.oculus.alpenglow.database.ApplicationDatabase;
import com.oculus.alpenglow.install.InstallerState;
import com.oculus.alpenglow.util.ForegroundNotificationManager;
import com.oculus.auth.device.DeviceAuthTokenRefreshAction;
import com.oculus.managed.ManagedMode;
import com.oculus.os.enterprise.internal.AppInstallInfo;
import com.oculus.os.enterprise.internal.Configuration;
import com.oculus.os.enterprise.internal.IAppInstallStatusChangeCallback;
import com.oculus.os.enterprise.internal.IEnterpriseServer;
import com.oculus.os.enterprise.internal.Version;
import com.oculus.signature.SignatureHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public class EnterpriseServer extends Service {
    public static final String TAG = "EnterpriseServer.EnterpriseServer";
    public AnonymousClass0R7 _UL_mInjectionContext;
    public final IBinder mBinder = new EnterpriseServerBinder();
    public DeviceTokenBroadcastReceiver mDeviceTokenBroadcastReceiver;
    public ExportedBroadcastReceiver mExportedBroadcastReceiver;
    public AnonymousClass0TS mTrustedCallerVerifier;
    public UserUnlockedBroadcastReceiver mUserUnlockedBroadcastReceiver;

    public class EnterpriseServerBinder extends IEnterpriseServer.Stub {
        public EnterpriseServerBinder() {
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public final AppInstallInfo getAppInstallInfo(String str, String str2) {
            return ((InstallerState) AnonymousClass0Lh.A03(2, 70, EnterpriseServer.this._UL_mInjectionContext)).A02(str, str2);
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public final Map getAppInstallInfos() {
            return ((InstallerState) AnonymousClass0Lh.A03(2, 70, EnterpriseServer.this._UL_mInjectionContext)).A03();
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        @SuppressLint({"InconsistentSubclassReturnAnnotation"})
        @Nullable
        public final Configuration getConfiguration() {
            return getConfigurationWithVersion(Version.VERSION_0);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:56:0x013c, code lost:
            if (r15.A4k() != com.oculus.alpenglow.graphql.enums.GraphQLHWMUnknownSourcesSetting.ALLOW) goto L_0x013e;
         */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x00ba  */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00c2  */
        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        @com.facebook.infer.annotation.SuppressLint({"InconsistentSubclassReturnAnnotation"})
        @javax.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.oculus.os.enterprise.internal.Configuration getConfigurationWithVersion(com.oculus.os.enterprise.internal.Version r32) {
            /*
            // Method dump skipped, instructions count: 730
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.alpenglow.enterpriseserver.EnterpriseServer.EnterpriseServerBinder.getConfigurationWithVersion(com.oculus.os.enterprise.internal.Version):com.oculus.os.enterprise.internal.Configuration");
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public final AppInstallInfo registerCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) {
            InstallerState installerState = (InstallerState) AnonymousClass0Lh.A03(2, 70, EnterpriseServer.this._UL_mInjectionContext);
            List<IAppInstallStatusChangeCallback> list = installerState.callbackMap.get(str);
            if (list == null) {
                list = Collections.synchronizedList(new ArrayList());
                installerState.callbackMap.put(str, list);
            }
            list.add(iAppInstallStatusChangeCallback);
            return installerState.A02(str, null);
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public final Map registerCallbackForAllApps(IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) {
            InstallerState installerState = (InstallerState) AnonymousClass0Lh.A03(2, 70, EnterpriseServer.this._UL_mInjectionContext);
            List<IAppInstallStatusChangeCallback> list = installerState.callbackMap.get("-1");
            if (list == null) {
                list = Collections.synchronizedList(new ArrayList());
                installerState.callbackMap.put("-1", list);
            }
            list.add(iAppInstallStatusChangeCallback);
            return installerState.A03();
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public final void unregisterCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) {
            List<IAppInstallStatusChangeCallback> list = ((InstallerState) AnonymousClass0Lh.A03(2, 70, EnterpriseServer.this._UL_mInjectionContext)).callbackMap.get(str);
            if (list != null) {
                list.remove(iAppInstallStatusChangeCallback);
            }
        }
    }

    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        boolean z = false;
        if (((ConfigurationStore) AnonymousClass0Lh.A03(0, 97, this._UL_mInjectionContext)).mData.device != null) {
            z = true;
        }
        printWriter.format("Has config: %b\n", Boolean.valueOf(z));
        printWriter.format("Last config fetch: %s\n", ((ConfigurationStore) AnonymousClass0Lh.A03(0, 97, this._UL_mInjectionContext)).mData.lastFetchTime);
        printWriter.format("Is enterprise mode enabled: %b\n", Boolean.valueOf(((ManagedMode) AnonymousClass0Lh.A03(4, 86, this._UL_mInjectionContext)).isEnterpriseModeEnabled));
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            startForeground(ForegroundServiceNotificationIds.ENTERPRISE_SERVICE_FOREGROUND_ID, ((ForegroundNotificationManager) AnonymousClass0Lh.A03(3, 106, this._UL_mInjectionContext)).A00(getApplicationContext()));
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DeviceAuthTokenRefreshAction.ACTION_NEW_TOKEN_AVAILABLE);
        registerReceiver(this.mDeviceTokenBroadcastReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.USER_UNLOCKED");
        intentFilter2.addAction("android.intent.action.USER_PRESENT");
        registerReceiver(this.mUserUnlockedBroadcastReceiver, intentFilter2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction(FetchConfigurationAction.ACTION_FETCH_CONFIGURATION);
        registerReceiver(this.mExportedBroadcastReceiver, intentFilter3, FetchConfigurationAction.ACTION_FETCH_CONFIGURATION, null);
        return 1;
    }

    public static String A00(ApplicationDatabase applicationDatabase, @Nullable String str) {
        String str2;
        String str3;
        Application A2w;
        String str4 = "";
        if (TextUtils.isEmpty(str)) {
            return str4;
        }
        int indexOf = str.indexOf(47);
        if (indexOf != -1) {
            str3 = str.substring(0, indexOf);
            str2 = str.substring(indexOf);
        } else {
            str2 = str4;
            str3 = str;
        }
        if (!TextUtils.isDigitsOnly(str3) || ((A2w = applicationDatabase.mApplicationDao.A2w(str3)) != null && (str4 = A2w.packageName) == null)) {
            return str;
        }
        return AnonymousClass006.A05(str4, str2);
    }

    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new AnonymousClass0R7(5, AnonymousClass0Lh.get(this));
        C07420rx r2 = new C07420rx();
        r2.A01(SignatureHelper.OCULUS_FIRST_PARTY_PROD_SIG, "com.oculus.vrshell");
        this.mTrustedCallerVerifier = new AnonymousClass0TS(r2.A02(), getPackageManager());
        this.mDeviceTokenBroadcastReceiver = new DeviceTokenBroadcastReceiver();
        this.mUserUnlockedBroadcastReceiver = new UserUnlockedBroadcastReceiver();
        this.mExportedBroadcastReceiver = new ExportedBroadcastReceiver();
    }

    public final void onDestroy() {
        super.onDestroy();
        AnonymousClass0NK.A01(TAG, "onDestroy called");
        try {
            unregisterReceiver(this.mUserUnlockedBroadcastReceiver);
            unregisterReceiver(this.mDeviceTokenBroadcastReceiver);
        } catch (IllegalArgumentException unused) {
        }
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
