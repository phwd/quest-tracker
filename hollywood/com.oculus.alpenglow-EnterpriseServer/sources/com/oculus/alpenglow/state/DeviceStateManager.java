package com.oculus.alpenglow.state;

import X.AbstractC02990bJ;
import X.AnonymousClass08h;
import X.AnonymousClass0Cg;
import X.AnonymousClass0Hv;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0P6;
import X.AnonymousClass0P8;
import X.AnonymousClass0R7;
import X.AnonymousClass0u6;
import X.C03170bk;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.graphql.calls.GraphQlCallInput;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.config.ConfigurationStore;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.config.LocalApp;
import com.oculus.alpenglow.database.Application;
import com.oculus.alpenglow.database.ApplicationDatabase;
import com.oculus.alpenglow.graphql.calls.HWMEntDeviceAppInstallStatus;
import com.oculus.alpenglow.graphql.calls.OculusDeviceStateInput;
import com.oculus.alpenglow.graphql.calls.OculusInstalledApplicationInput;
import com.oculus.alpenglow.http.HttpMethods;
import com.oculus.alpenglow.logging.DeviceStateLogger;
import com.oculus.alpenglow.state.OculusDeviceStateUpdateMutationImpl;
import com.oculus.alpenglow.util.StringUtils;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.executors.OculusThreadExecutor;
import com.oculus.os.Controllers;
import com.oculus.os.SettingsManager;
import com.oculus.util.network.NetworkUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_http_HttpMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_database_ApplicationDatabase_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_os_Controllers_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_graphql_query_interfaces_IGraphQLQueryExecutor_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_logging_DeviceStateLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_alpenglow_config_ConfigurationStore_ULSEP_BINDING_ID"})
@ApplicationScoped
public class DeviceStateManager implements LoginHandler {
    public static final String TAG = "EnterpriseServer.DeviceStateManager";
    public static volatile DeviceStateManager _UL__ULSEP_com_oculus_alpenglow_state_DeviceStateManager_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;

    public static class StateReporter extends AsyncTask<JobService, Void, Void> {
        public final ApplicationDatabase mApplicationDatabase;
        public final WeakReference<ConfigurationStore> mConfigurationStore;
        public final WeakReference<Context> mContext;
        public final Controllers mControllers;
        public final WeakReference<DeviceStateLogger> mDeviceStateLogger;
        public final WeakReference<AnonymousClass0P6> mGraphQLQueryExecutor;
        public final WeakReference<HttpMethods> mHttpMethods;
        @Nullable
        public final WeakReference<JobParameters> mJobParameters;
        public final WeakReference<String> mLoggingUUID;
        public final SettingsManager mSettingsManager;

        /* Return type fixed from 'java.lang.Object' to match base method */
        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object[]] */
        @Override // android.os.AsyncTask
        public final /* bridge */ /* synthetic */ Void doInBackground(JobService[] jobServiceArr) {
            Device.ManagementInfo.DeviceConfig.DeviceApps A3P;
            String str;
            String str2;
            String str3;
            Device device;
            Device.ManagementInfo A3y;
            final JobService[] jobServiceArr2 = jobServiceArr;
            WeakReference<JobParameters> weakReference = this.mJobParameters;
            final JobParameters jobParameters = weakReference != null ? weakReference.get() : null;
            Context context = this.mContext.get();
            HttpMethods httpMethods = this.mHttpMethods.get();
            AnonymousClass0P6 r0 = this.mGraphQLQueryExecutor.get();
            String str4 = Build.VERSION.INCREMENTAL;
            final DeviceStateLogger deviceStateLogger = this.mDeviceStateLogger.get();
            ConfigurationStore configurationStore = this.mConfigurationStore.get();
            Device.ManagementInfo.DeviceConfig A3Q = (configurationStore == null || (device = configurationStore.mData.device) == null || (A3y = device.A3y()) == null) ? null : A3y.A3Q();
            int i = 0;
            JobService jobService = jobServiceArr2.length > 0 ? jobServiceArr2[0] : null;
            if (context == null) {
                AnonymousClass0NK.A01(DeviceStateManager.TAG, "context is null.");
                if (!(jobService == null || jobParameters == null)) {
                    jobService.jobFinished(jobParameters, false);
                }
                if (deviceStateLogger != null) {
                    str2 = this.mLoggingUUID.get();
                    str3 = "context is null";
                }
                return null;
            } else if (httpMethods == null) {
                AnonymousClass0NK.A01(DeviceStateManager.TAG, "httpMethods is null.");
                if (!(jobService == null || jobParameters == null)) {
                    jobService.jobFinished(jobParameters, false);
                }
                if (deviceStateLogger != null) {
                    str2 = this.mLoggingUUID.get();
                    str3 = "httpMethods is null";
                }
                return null;
            } else {
                if (A3Q != null) {
                    i = A3Q.A3C();
                }
                float f = -1.0f;
                Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                if (registerReceiver != null) {
                    f = 100.0f * (((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1)));
                }
                List<WifiConfiguration> configuredNetworks = ((WifiManager) context.getSystemService(NetworkUtils.WIFI)).getConfiguredNetworks();
                ArrayList arrayList = new ArrayList();
                for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                    if (!TextUtils.isEmpty(wifiConfiguration.SSID)) {
                        arrayList.add(StringUtils.A00(wifiConfiguration.SSID));
                    }
                }
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                WifiManager wifiManager = (WifiManager) context.getSystemService(NetworkUtils.WIFI);
                String str5 = null;
                for (Network network : connectivityManager.getAllNetworks()) {
                    if (connectivityManager.getNetworkCapabilities(network).hasTransport(1)) {
                        str5 = StringUtils.A00(wifiManager.getConnectionInfo().getSSID());
                    }
                }
                List A00 = PeripheralStateCollector.A00(this.mControllers);
                ApplicationDatabase applicationDatabase = this.mApplicationDatabase;
                HashMap hashMap = new HashMap();
                for (Application application : applicationDatabase.A03()) {
                    OculusInstalledApplicationInput oculusInstalledApplicationInput = new OculusInstalledApplicationInput();
                    oculusInstalledApplicationInput.A05("remote_app_id", application.appId);
                    switch (application.status.ordinal()) {
                        case 0:
                        case 1:
                        case 3:
                        case 4:
                        case 5:
                            str = HWMEntDeviceAppInstallStatus.INSTALL_IN_PROGRESS;
                            break;
                        case 2:
                        default:
                            str = HWMEntDeviceAppInstallStatus.INSTALL_ERROR;
                            break;
                        case 6:
                        case 7:
                            str = HWMEntDeviceAppInstallStatus.INSTALL_SUCCESS;
                            break;
                    }
                    oculusInstalledApplicationInput.A05("install_status", str);
                    if (!TextUtils.isEmpty(application.packageName)) {
                        oculusInstalledApplicationInput.A05("package_name", application.packageName);
                    }
                    hashMap.put(application.packageName, oculusInstalledApplicationInput);
                }
                if (!(A3Q == null || (A3P = A3Q.A3P()) == null)) {
                    AnonymousClass0u6<? extends Device.ManagementInfo.DeviceConfig.DeviceApps.Nodes> it = A3P.A4C().iterator();
                    while (it.hasNext()) {
                        LocalApp A17 = ((Device.ManagementInfo.DeviceConfig.DeviceApps.Nodes) it.next()).A17();
                        if (A17 != null) {
                            String A3r = A17.A3r();
                            String id = A17.getId();
                            if (!TextUtils.isEmpty(A3r) && !TextUtils.isEmpty(id)) {
                                String str6 = A3r.split("/")[0];
                                OculusInstalledApplicationInput oculusInstalledApplicationInput2 = new OculusInstalledApplicationInput();
                                oculusInstalledApplicationInput2.A05("package_name", str6);
                                oculusInstalledApplicationInput2.A05("remote_app_id", id);
                                oculusInstalledApplicationInput2.A05("install_status", HWMEntDeviceAppInstallStatus.INSTALL_SUCCESS);
                                hashMap.put(str6, oculusInstalledApplicationInput2);
                            }
                        }
                    }
                }
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(0)) {
                        GraphQlCallInput graphQlCallInput = (GraphQlCallInput) hashMap.get(applicationInfo.packageName);
                        if (graphQlCallInput == null) {
                            String str7 = applicationInfo.packageName;
                            if (str7 != null && (applicationInfo.flags & 1) == 0) {
                                graphQlCallInput = new OculusInstalledApplicationInput();
                                graphQlCallInput.A05("package_name", str7);
                                graphQlCallInput.A05("remote_app_id", "");
                                graphQlCallInput.A05("install_status", HWMEntDeviceAppInstallStatus.INSTALL_SUCCESS);
                            }
                        }
                        try {
                            PackageInfo packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, 0);
                            graphQlCallInput.A05("version", packageInfo.versionName);
                            C03170bk.A00(graphQlCallInput.A03(), "version_code", Integer.valueOf(packageInfo.versionCode));
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                        hashMap.put(applicationInfo.packageName, graphQlCallInput);
                    }
                }
                ArrayList arrayList2 = new ArrayList(hashMap.values());
                String locale = Resources.getSystem().getConfiguration().locale.toString();
                String id2 = TimeZone.getDefault().getID();
                String A002 = ProvisioningStateCollector.A00(this.mSettingsManager);
                OculusDeviceStateInput oculusDeviceStateInput = new OculusDeviceStateInput();
                C03170bk.A00(oculusDeviceStateInput.A03(), "config_version", Integer.valueOf(i));
                C03170bk.A00(oculusDeviceStateInput.A03(), "battery_level", Double.valueOf((double) f));
                oculusDeviceStateInput.A05("os_version", str4);
                GraphQlCallInput.A01(oculusDeviceStateInput, oculusDeviceStateInput.A03().A04("configured_wifi_networks"), arrayList);
                GraphQlCallInput.A01(oculusDeviceStateInput, oculusDeviceStateInput.A03().A04("peripherals"), A00);
                GraphQlCallInput.A01(oculusDeviceStateInput, oculusDeviceStateInput.A03().A04("installed_apps"), arrayList2);
                oculusDeviceStateInput.A05("locale", locale);
                oculusDeviceStateInput.A05("timezone", id2);
                oculusDeviceStateInput.A05("provisioning_state", A002);
                if (str5 != null) {
                    oculusDeviceStateInput.A05("current_wifi_network", str5);
                }
                r0.A2X(new OculusDeviceStateUpdateMutationImpl.Builder().A7y(oculusDeviceStateInput).A1a(), new AnonymousClass0Cg<AnonymousClass0P8<OculusDeviceStateUpdateResponse>>() {
                    /* class com.oculus.alpenglow.state.DeviceStateManager.StateReporter.AnonymousClass1 */

                    @Override // X.AnonymousClass0Cg
                    public final void onFailure(Throwable th) {
                        JobService jobService;
                        JobParameters jobParameters;
                        AnonymousClass0NK.A04(DeviceStateManager.TAG, "device state update failed with error", th);
                        DeviceStateLogger deviceStateLogger = deviceStateLogger;
                        if (deviceStateLogger != null) {
                            deviceStateLogger.A03(StateReporter.this.mLoggingUUID.get(), th.getMessage());
                        }
                        JobService[] jobServiceArr = jobServiceArr2;
                        if (jobServiceArr.length > 0 && (jobService = jobServiceArr[0]) != null && (jobParameters = jobParameters) != null) {
                            jobService.jobFinished(jobParameters, true);
                        }
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    @Override // X.AnonymousClass0Cg
                    public final void onSuccess(AnonymousClass0P8<OculusDeviceStateUpdateResponse> r4) {
                        JobService jobService;
                        JobParameters jobParameters;
                        DeviceStateLogger deviceStateLogger = deviceStateLogger;
                        if (deviceStateLogger != null) {
                            deviceStateLogger.A02(StateReporter.this.mLoggingUUID.get());
                        }
                        JobService[] jobServiceArr = jobServiceArr2;
                        if (jobServiceArr.length > 0 && (jobService = jobServiceArr[0]) != null && (jobParameters = jobParameters) != null) {
                            jobService.jobFinished(jobParameters, false);
                        }
                    }
                }, AnonymousClass08h.INSTANCE);
                return null;
            }
            deviceStateLogger.A03(str2, str3);
            return null;
        }

        public StateReporter(Context context, HttpMethods httpMethods, ApplicationDatabase applicationDatabase, Controllers controllers, AnonymousClass0P6 r6, SettingsManager settingsManager, DeviceStateLogger deviceStateLogger, ConfigurationStore configurationStore, String str, @Nullable JobParameters jobParameters) {
            this.mContext = new WeakReference<>(context);
            this.mHttpMethods = new WeakReference<>(httpMethods);
            this.mApplicationDatabase = applicationDatabase;
            this.mControllers = controllers;
            this.mGraphQLQueryExecutor = new WeakReference<>(r6);
            this.mSettingsManager = settingsManager;
            this.mDeviceStateLogger = new WeakReference<>(deviceStateLogger);
            this.mConfigurationStore = new WeakReference<>(configurationStore);
            this.mLoggingUUID = new WeakReference<>(str);
            this.mJobParameters = jobParameters != null ? new WeakReference<>(jobParameters) : null;
        }
    }

    public final AsyncTask<JobService, Void, Void> A00(@Nullable JobParameters jobParameters, String str) {
        AnonymousClass0R7 r2 = this._UL_mInjectionContext;
        return new StateReporter((Context) AnonymousClass0Lh.A03(0, 4, r2), (HttpMethods) AnonymousClass0Lh.A03(1, 109, r2), (ApplicationDatabase) AnonymousClass0Lh.A03(2, 3, r2), (Controllers) AnonymousClass0Lh.A03(3, 60, r2), (AnonymousClass0P6) AnonymousClass0Lh.A03(4, 51, r2), (SettingsManager) AnonymousClass0Lh.A03(5, 105, r2), (DeviceStateLogger) AnonymousClass0Lh.A03(6, 130, r2), (ConfigurationStore) AnonymousClass0Lh.A03(7, 97, r2), str, jobParameters);
    }

    /* JADX DEBUG: Type inference failed for r0v5. Raw type applied. Possible types: X.0Hv<?>, X.0Hv<java.lang.Void> */
    @Override // com.oculus.auth.handler.LoginHandler
    public final AnonymousClass0Hv<Void> afterLoginAsync() {
        A00(null, ((DeviceStateLogger) AnonymousClass0Lh.A03(6, 130, this._UL_mInjectionContext)).A01("device auth login")).executeOnExecutor(OculusThreadExecutor.A00(), new JobService[0]);
        return AnonymousClass0Hv.A07;
    }

    @Inject
    public DeviceStateManager(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(8, r3);
    }
}
